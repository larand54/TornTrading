package com.torntrading.portal

import com.buffer.ProdBuffer
import com.buffer.Orders
import com.torntrading.portal.PlannedVolume
import grails.transaction.Transactional

    
@Transactional
class ProdBufferService {
    def offerDetailService
    def logService
    // Hjälp klass för att hantera veckobyten med volymer
    class CellVol {
        Double vol1
        Double vol2
        Double vol3
    }

    def List<String> getActiveAvailableMills() {
        System.out.println("getActiveAvailableMills <<<<")
        ProdBuffer.executeQuery("SELECT DISTINCT sawMill FROM ProdBuffer PB WHERE PB.status='Active' AND PB.volumeAvailable > 0.1 ORDER BY PB.sawMill" )
    }
    
    def List<ProdBuffer> getActiveAvailableProducts() {
        ProdBuffer.executeQuery("FROM ProdBuffer PB WHERE PB.status='Active' AND PB.volumeAvailable > 0.1 ORDER BY PB.sawMill" )
    }
    
    def checkWeekStatus() {
        // get current week
            println( "checkWeekStatus called at: " + new Date())
        def int cWeek = getCurrentYearWeek()
        int id = 1
        WtStatus wts = WtStatus.get(id)?:new WtStatus(id:1).save(failOnError:true)
        def int actWeek = wts.weekUpdated
        if (cWeek > wts.weekUpdated) {
            def List<ProdBuffer> pList = ProdBuffer.executeQuery("FROM ProdBuffer PB WHERE PB.status='Active' ORDER BY PB.sawMill" )
            for (p in pList) {
                weekAdjustVolumes(p)
                updateAvailableVolumes(p)
            }
            offerDetailService.weekAdjust()
            println( "Week adjusted at: " + new Date())
            wts.weekUpdated = cWeek
            wts.save(failOnError:true)
        }
    }
    
    def weekAdjustVolumes(ProdBuffer aP) {
        def weekListSize = 12
        def  pv = aP.plannedVolumes
        for (int i=0; i< weekListSize-1; i++) {
//            println("AdjustVolumes, I: "+i)
            if (i==0) {
                aP.volumeInStock = aP.volumeInStock + pv[0].volume
                aP.volumeInitial = aP.volumeInitial + pv[0].initialVolume
            }
            pv[i].volume = pv[i+1].volume
            pv[i].initialVolume = pv[i+1].initialVolume
            pv[i].save(flush:true, failOnError:true)
        }
        pv[weekListSize-1].volume = 0
        pv[weekListSize-1].initialVolume = 0
        pv[weekListSize-1].save(flush:true, failOnError:true)
    }
    
    // denna funktion skall ej användas då det inte fungerar säkert efter veckoskift
    // vi lagrar bara 12 värden och vid veckobyte skiftas den 1:a bort och vi vet ej vad som var lagrat där tidigare
    // Vi löser detta genom att lagra upp initialvärdena i InStocks-initialvärde vid veckobyte
    def updateAvailableVolumes(ProdBuffer aPB) {
        def Double av = aPB.volumeInStock      // Skall var det initiala värdet
        av = av + getTotalPlannedVolume(aPB)   // skall vara de initiala värdena men efter veckoskift blir de osäkra då det kan tillkomma nya värden
        def Double toffv = getTotalOfferVolume(aPB)
        def Double tordv = getTotalOrderVolume(aPB)
        aPB.volumeAvailable = av
        aPB.volumeOffered = toffv
        aPB.volumeOnOrder = tordv
        aPB.save(flush:true, failOnError:true)
    }
    
    def Double fillCellVolume(CellVol aCellVol, Double aFull) {
        def Double fill = aFull - aCellVol.vol1
        if (fill <= 0.0) {
        } else if (aCellVol.vol2 <= fill) {
           aCellVol.vol1 = aCellVol.vol1 + aCellVol.vol2
           aCellVol.vol2 = 0.0
        } else {
           aCellVol.vol2 = aCellVol.vol2 - fill
           aCellVol.vol1 = aFull
        }
        return aCellVol.vol1 
    }
    
    def Double tapCellVolume(CellVol aCellVol) {
       def cell = aCellVol.vol1
       def tapVol = aCellVol.vol2
       if (tapVol > cell) {
           tapVol = tapVol - cell
           cell = 0.0
       } else {
           cell = cell - tapVol
           tapVol = 0.0
       }  
       aCellVol.vol1 = cell
       aCellVol.vol2 = tapVol
       return cell
    } 
    
    def retrieveVolumeFromBuffer(ProdBuffer aPB, Double aVol){
        def int i = 0
        def CellVol cellVol = new CellVol(vol1:0, vol2:0) 
        def pv = aPB.plannedVolumes
        cellVol.vol2 = aVol
        if (aPB.volumeInStock > 0.0) {
            cellVol.vol1 = aPB.volumeInStock
            aPB.volumeInStock = tapCellVolume(cellVol)
        }
        
        while (cellVol.vol2 > 0.0 && i < 12) {
            cellVol.vol1 = pv[i].volume
            pv[i].volume = tapCellVolume(cellVol)
            i = i + 1
        }
        if (cellVol.vol2 > 0.0) aPB.volumeInStock = aPB.volumeInStock - cellVol.vol2
        aPB.save(failOnError:true)
    }
    
    def restoreVolumeToBuffer(ProdBuffer aPB, Double aVol){
        def int i = 11
        def CellVol cellVol = new CellVol(vol1:0, vol2:0) 
println("RestoreVolumeToBuffer Start - cellVol.vol2: "+cellVol.vol2)
        def pv = aPB.plannedVolumes
        
        cellVol.vol2 = aVol 
        while (cellVol.vol2 > 0.0 && i >= 0) {
            cellVol.vol1 = pv[i].volume
            pv[i].volume = fillCellVolume(cellVol,pv[i].initialVolume)
            i = i - 1
        }
println("RestoreVolumeToBuffer cellVol.vol2: "+cellVol.vol2)
println("RestoreVolumeToBuffer InStock: "+aPB.volumeInStock)
        if (cellVol.vol2 > 0.0) {
            aPB.volumeInStock = aPB.volumeInStock + cellVol.vol2
        }
println("RestoreVolumeToBuffer InStock2: "+aPB.volumeInStock)
        aPB.save(failOnError:true)
    }
    def restorePlannedOfferVolumesToBuffer(ProdBuffer aPB, OfferDetail aOD) {
        
        aPB.volumeInStock = aPB.volumeInStock + aOD.fromStock
        int i = 0
        for (odpv in aOD.offerPlannedVolumes) {
            aPB.plannedVolumes[i].volume = aPB.plannedVolumes[i].volume + odpv.volume
            i = i + 1
        }
        aPB.save(flush:true, failOnError:true)
    }
    
    def rejectOffer(ProdBuffer aPB, OfferDetail aOD) {
        println("ProdBufferService - rejectOffer - BEFORE - Rejected: Volume offered: "+aPB.volumeOffered+" volumeAvailabel: "+aPB.volumeAvailable)
        aPB.volumeOffered = aPB.volumeOffered - aOD.volumeOffered
        aPB.volumeAvailable = aPB.volumeAvailable + aOD.volumeOffered
        println("ProdBufferService - rejectOffer - After - Rejected: Volume offered: "+aPB.volumeOffered+" volumeAvailabel: "+aPB.volumeAvailable)
        aPB.save(flush:true, failOnError:true)
        if (aOD.useWeeklyVolumes) {
            restorePlannedOfferVolumesToBuffer(aPB, aOD)
        } else {
            restoreVolumeToBuffer(aPB, aOD.volumeOffered)
        }
    }
    
    def retrieveOfferedVolumeFromBuffer(ProdBuffer aPB, OfferDetail aOD) {
        aPB.volumeInStock = aPB.volumeInStock - aOD.fromStock
        int i = 0
        for (odpv in aOD.offerPlannedVolumes) {
            aPB.plannedVolumes[i].volume = aPB.plannedVolumes[i].volume - odpv.volume
            i = i + 1
        }
        aPB.save(flush:true, failOnError:true)
    }
    
    def addOfferVolume(ProdBuffer aPB, OfferDetail aOD, Double aVol) {
        logService.logProdBufferVolumes('SRVC','addOfferVolume','At start - volOff: '+aPB.volumeOffered+' VolAvail: '+ aPB.volumeAvailable ,aPB)
        aPB.volumeOffered = aPB.volumeOffered + aVol
        aPB.volumeAvailable = aPB.volumeAvailable - aVol
        logService.logProdBufferVolumes('SRVC','addOfferVolume','After start - volOff: '+aPB.volumeOffered+' VolAvail: '+ aPB.volumeAvailable ,aPB)
        aPB.save(flush:true, failOnError:true)
        if (aOD.useWeeklyVolumes) {
            // We do nothing here
        } else {
            if (aVol > 0) {
                retrieveVolumeFromBuffer(aPB, aVol)
            } else if (aVol < -0.01) {
                restoreVolumeToBuffer(aPB, -aVol)
        logService.logProdBufferVolumes('SRVC','addOfferVolume','After restore - volOff: '+aPB.volumeOffered+' VolAvail: '+ aPB.volumeAvailable ,aPB)
            }
        }
    }
    
    def addPlannedVolume(ProdBuffer aPB, Double aVol) {
        
        aPB.volumeAvailable = aPB.volumeAvailable + aVol 
        
        aPB.save(flush:true, failOnError:true)
    }

    def soldOfferVolume(ProdBuffer aPB, Double aVol) {
        println("!!!!!!!!! ProdBufferService - soldOffer: Entered")
    
        aPB.volumeOnOrder = aPB.volumeOnOrder + aVol
        aPB.volumeOffered = aPB.volumeOffered - aVol
/*//        aPB.volumeAvailable = aPB.volumeAvailable - aVol
        println("!!!!!!!!! ProdBufferService - soldOffer: Commented area")
        
        aPB.volumeInStock = aPB.volumeInStock - aVol
        if (aPB.volumeInStock > aVol) {
            aPB.volumeInStock = aPB.volumeInStock - aVol 
        } else {
            def diff = aVol - aPB.volumeInStock
            aPB.volumeInStock = 0.0 
            aPB.volumeOffered = aPB.volumeOffered - diff
        }
*/
        aPB.save(flush:true, failOnError:true)
    }
    
    def Double getTotalOfferVolume(ProdBuffer aPB) {
        def Double offerTotal = 0.0
        if (aPB.status =='Active') {
            def offerList = OfferDetail.findAllByMillOfferID(aPB.id)  
            for (od in offerList) {
                if (od.offerHeader.status == 'Active') {
                    offerTotal = offerTotal + od.volumeOffered
                }
            }
        }
        return offerTotal
    }
    
    def Double getTotalOrderVolume(ProdBuffer aPB) {
        def Double orderTotal = 0.0
        if (aPB.status =='Active') {
            def offerList = OfferDetail.findAllByMillOfferID(aPB.id)  
            for (od in offerList) {
                if (od.offerHeader.status == 'Sold') {
                    orderTotal = orderTotal + od.volumeOffered
                }
            }
        }
        return orderTotal
    }

    def Double getTotalPlannedVolume(ProdBuffer aPB) {
        def Double plannedTotal = 0.0
        if (aPB.status =='Active') {
            def plannedList = aPB.plannedVolumes
            for (pv in plannedList) {
                plannedTotal = plannedTotal + pv.volume
            }
        }
        return plannedTotal
    }

    def updateWeekList(ProdBuffer aPB) {
        def Double[] volList = new Double[10]
        calcWeekList(aPB, volList)
        fillWeekList(aPB,volList)
    }

    def Double shiftLeft(List<Double> aList) {
        def Double shiftOut = aList[0]
        for (int i=0; i< aList.size()-1; i++) {
            aList[i] = aList[i+1]
        }
        aList[aList.size()-1] = 0.0
        return shiftOut
    }
    
    
    def int getCurrentWeek() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.WEEK_OF_YEAR)        
    }
    
    def int getCurrentYearWeek() {
    	Date date = new Date()
	def calendar = date.toCalendar()
        def cw = getCurrentWeek()
        def result = (calendar.get(calendar.YEAR) - 2000)*100 + cw
        return result 
    }
    
    def int getCurrentYear() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.YEAR)-2000        
    }
    
    def int getWeekFromYearWeek(int aYearWeek) {
        // aYearWeek = 1718, aYear = 17 -> week = 1718 - 1700 = 18 
        int yw = checkYearWeek(aYearWeek)
        return yw % 100     
    }
    
    def int getYearFromYearWeek(int aYearWeek) {
        int yw = checkYearWeek(aYearWeek)
        return yw.intdiv(100)
    }
    
    def int checkYearWeek(int aYearWeek) {
       int lastWeekThisYear = getWeeksInYear()
       int weekNo = aYearWeek % 100
       if (lastWeekThisYear < weekNo) {
           int newYear = aYearWeek.intdiv(100) + 1 
           return newYear*100+aYearWeek % 100 - lastWeekThisYear
       } else {
           return aYearWeek
       }
    }                                                                                                
                                                                                                    
    def int getYearWeekFromWeek(int aWeek, int aYear) {
        return (aYear-2000)*100 + aWeek
    }
    
    def int getWeeksInYear() {
        def Date d = new Date()
	def cal = d.toCalendar()
        return cal.getWeeksInWeekYear()
    }
   
    def int nextYearWeek() {
        //create Calendar instance
        def Calendar now = Calendar.getInstance();
   
        System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1)
            + "-"
            + now.get(Calendar.DATE)
            + "-"
            + now.get(Calendar.YEAR));
                       
        System.out.println("Current week of month is : " +
            now.get(Calendar.WEEK_OF_MONTH));
               
        System.out.println("Current week of year is : " +
            now.get(Calendar.WEEK_OF_YEAR));
 
        //add week to current date using Calendar.add method
        now.add(Calendar.WEEK_OF_YEAR,1);
   
        System.out.println("Week after one week : " + (now.get(Calendar.WEEK_OF_YEAR)))
        System.out.println("date after one week : " + (now.get(Calendar.MONTH) + 1))
        return now.get(Calendar.WEEK_OF_YEAR)    
    }

    def calcWeekList(ProdBuffer aPB, Double[] volList) {
//        println("Volume added: "+aPB.plannedVolumes.volume+" in week: "+aPB.plannedVolumes.week)
        if (aPB.status =='Active') {
            // get Current week number:
            Integer cw = getCurrentWeek()
            Integer cy = getCurrentYear()
            for (int i = 1; i < 11; i++) {
                volList[i-1] = aPB.volumeInitial           
            }
//            print(aPB.id+" Initial Volumelist: "+volList)
            // Add planned volumes
            def Integer ix
            def Integer i
            for (PlannedVolume pv in aPB.plannedVolumes) {
                def pvWeek = pv.week
                def pvYear = getYearFromYearWeek(pvWeek)
                pvWeek = pvWeek - 100 * pvYear
                // calc week index of this volume 
                if (cy == pvYear) {
                    ix = pvWeek-cw+1
//                    println(aPB.id+" Week(1-10) in this year: "+ix)
                } else {
                    // volume in next year - special calc
                    // How many weeks left this year not counting cw
                    def Integer weeksInThisYear = getWeeksInYear()
                    ix = weeksInThisYear - cw + 1 + pvWeek
//                    println("Week(1-10) in next year: "+ix)
                }
                for (i=ix-1;i<10;i++) {
                    volList[i] = volList[i] + pv.volume
                }
//            print(aPB.id+" After Planned Volumelist: "+volList)
            }
        
            // Offerter
            def offerTotal = getTotalOfferVolume(aPB)
            for (i=0;i<10;i++) {
                volList[i] = volList[i] - offerTotal
            }
//            print(aPB.id+" After offer Volumelist: "+volList)
            
            // Sålda volymer
            def orderTotal = getTotalOrderVolume(aPB)
            for (i=0;i<10;i++) {
                volList[i] = volList[i] - orderTotal
            }
//            print(aPB.id+" After sold Volumelist: "+volList)
        }
            
    }

}