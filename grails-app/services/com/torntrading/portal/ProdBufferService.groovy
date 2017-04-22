package com.torntrading.portal

import com.buffer.ProdBuffer
import com.buffer.Orders
import grails.transaction.Transactional

@Transactional
class ProdBufferService {

    def List<String> getActiveAvailableMills() {
        System.out.println("getActiveAvailableMills <<<<")
        ProdBuffer.executeQuery("SELECT DISTINCT sawMill FROM ProdBuffer PB WHERE PB.status='Active' AND PB.volumeAvailable > 0.1 ORDER BY PB.sawMill" )
    }
    
    def List<ProdBuffer> getActiveAvailableProducts() {
        ProdBuffer.executeQuery("SELECT * FROM ProdBuffer PB WHERE PB.status='Active' AND PB.volumeAvailable > 0.1 ORDER BY PB.sawMill" )
    }
    
    def addOfferVolume(ProdBuffer aPB, Double aVol, Integer aWeek) {
        aPB.volumeOffered = aPB.volumeOffered + aVol
        aPB.volumeAvailable = aPB.volumeAvailable - aVol
        if (aPB.volumeInStock > aVol) {
            aPB.volumeInStock = aPB.volumeInStock - aVol 
        } else {
            aPB.volumeInStock = 0.0 
        }
        aPB.save(flush:true, failOnError:true)
    }
    def addPlannedVolume(ProdBuffer aPB, Double aVol, Integer aWeek) {
        aPB.volumeAvailable = aPB.volumeAvailable + aVol
        if (aWeek == (getCurrentWeek()+getCurrentYear()*100)) {
            def diff = aVol - getTotalOfferVolume(aPB)
            if (diff > 0) {
                aPB.volumeInStock = aPB.volumeInStock + diff  // kolla omatchade offervolymer först - fixa detta
            }
        }
        aPB.save(flush:true, failOnError:true)
    }
    def addOrderVolume(ProdBuffer aPB, Double aVol) {
        aPB.volumeOnOrder = aPB.volumeOnOrder + aVol
        aPB.volumeAvailable = aPB.volumeAvailable - aVol
        if (aPB.volumeInStock > aVol) {
            aPB.volumeInStock = aPB.volumeInStock - aVol 
        } else {
            def diff = aVol - aPB.volumeInStock
            aPB.volumeInStock = 0.0 
            aPB.volumeOffered = aPB.volumeOffered - diff
        }
        aPB.save(flush:true, failOnError:true)
    }
    
    def Double getTotalOfferVolume(ProdBuffer aPB) {
        def Double offerTotal = 0.0
        if (aPB.status =='Active') {
            def offerDetails = OfferDetail.list()
            def offerList = OfferDetail.findAllByMillOfferID(aPB.id)  
            for (od in offerList) {
                offerTotal = offerTotal + od.volumeOffered
            }
        }
        return offerTotal
    }
    
    def Double getTotalOrderVolume(ProdBuffer aPB) {
        def Double orderTotal = 0.0
        if (aPB.status =='Active') {
            def orderList = Orders.findAllByMillOfferID(aPB.id)
            for (or in orderList) {
                orderTotal = orderTotal + or.quantity
            }
        }
        return orderTotal
    }

    def Double getTotalPlannedVolume(ProdBuffer aPB) {
        def Double plannedTotal = 0.0
        if (aPB.status =='Active') {
            def plannedList = aPB.plannedVolumes
            for (pv in plannedList) {
                plannedTotal = plannedTotal + pv.quantity
            }
        }
        return plannedTotal
    }

    def updateWeekList(ProdBuffer aPB) {
        def Double[] volList = new Double[10]
        calcWeekList(aPB, volList)
        fillWeekList(aPB,volList)
    }
/*    def updateVolumes(ProdBuffer aPB) {
        if (aPB.status =='Active') {
            System.out.println(">>>>> Service: updateVolumes ProdBuffer dimension: "+aPB.dimension)
            def Double offeredVolume
            def offerDetails = OfferDetail.list()
            System.out.println("List: "+offerDetails)
            def offerList = offerDetails.findAll({(it.millOfferID==aPB.id)})  
            System.out.println("List: "+offerList)
            def orderList = Orders.findAllByMillOfferID(aPB.id) 
            System.out.println("OrdersList: "+orderList)
            def plannedList = aPB.plannedVolumes
            System.out.println("PlannedList: "+plannedList)
            def Double offerTotal = 0.0
            def Double orderTotal = 0.0
            def Double plannedTotal = 0.0
            for (pt in plannedList) {
                plannedTotal = plannedTotal + pt.volume
            }
            for (od in offerList) {
                offerTotal = offerTotal + od.volumeOffered
            }
            for (or in orderList) {
                orderTotal = orderTotal + or.quantity
            }
            
            System.out.println("Planned total: "+plannedTotal)
            System.out.println("Offered total: "+offerTotal)
            System.out.println("Ordered total: "+orderTotal)
            def Double available = aPB.volumeAvailable?:0.0
            def Double inStock = aPB.volumeInStock?:0.0
            def booked = offerTotal + orderTotal
            available = inStock + plannedTotal - booked
            if (booked < inStock) {
                inStock = inStock - booked 
            } else {
                def diff = booked - inStock
                inStock = 0.0
                offerTotal = offerTotal - diff
            }
            aPB.volumeAvailable = available
            aPB.volumeInStock = inStock
            aPB.volumeOffered = offerTotal
            aPB.volumeOnOrder = orderTotal
            aPB.save()
        }
    }
 */
    def int getCurrentWeek() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.WEEK_OF_YEAR)        
    }
    
    def int getCurrentYear() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.YEAR)-2000        
    }
    
    def int getWeekFromYearWeek(int aYearWeek) {
        // aYearWeek = 1718, aYear = 17 -> week = 1718 - 1700 = 18        
        return ((aYearWeek as int) - getYearFromYearWeek(aYearWeek)*100) as int     
    }
    
    def int getYearFromYearWeek(aYearWeek) {
        return (aYearWeek as int) / 100
    }
    
    def int getWeeksInYear() {
        def Date d = new Date()
	def cal = d.toCalendar()
        return cal.getWeeksInWeekYear()
    }
   
    def int nextYearWeek(Date d) {
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

    
    def fillWeekList(ProdBuffer aPB, Double[] volList) {
        aPB.availW01 = Math.max(volList[0],0.0)
        aPB.availW02 = Math.max(volList[1],0.0)
        aPB.availW03 = Math.max(volList[2],0.0)
        aPB.availW04 = Math.max(volList[3],0.0)
        aPB.availW05 = Math.max(volList[4],0.0)
        aPB.availW06 = Math.max(volList[5],0.0)
        aPB.availW07 = Math.max(volList[6],0.0)
        aPB.availW08 = Math.max(volList[7],0.0)
        aPB.availW09 = Math.max(volList[8],0.0)
        aPB.availW10 = Math.max(volList[9],0.0)
    }
}