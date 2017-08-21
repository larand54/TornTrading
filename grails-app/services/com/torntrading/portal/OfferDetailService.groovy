package com.torntrading.portal

import grails.transaction.Transactional
import com.buffer.*

@Transactional
class OfferDetailService {
    def offerHeaderService
    def prodBufferService
    def logService

    def Double getVolumeChangeFromForm(OfferDetail aOD, Double aNewVolume) {
        if ((aNewVolume > 0.0001) || (aOD.volumeOffered > 0.0001)){
            double diff = aNewVolume - aOD.volumeOffered
            if (Math.abs(diff) > 0.0001) {
                return diff
            } else {
                return 0.0
            }
        } else return 0.0

    }
    
    def boolean okToAddVolume(ProdBuffer aPB, Double aVolChange) {
        println(">>> Available: "+aPB.volumeAvailable+"  Offered: "+aVolChange)
        return aPB.volumeAvailable >= aVolChange        
    }
    
    def String getWeekAsTitle(Date aDate, int aOffset) {
        def calendar = aDate.toCalendar()
        calendar.add(Calendar.DATE,7*(aOffset as int))
        def int week = calendar.get(Calendar.WEEK_OF_YEAR)
        String weekStr = String.format("W%02d", week)
         
        return weekStr
        
    }
    
    def setAvailableVolumes(OfferDetail aOD) {
        //        println("setAvailableVolumes - volume: ")
        def pb = ProdBuffer.get(aOD.millOfferID)
        aOD.inStock = pb.volumeInStock
        def oav = aOD.availableVolumes
        def pvl = pb.plannedVolumes
        for (pv in pvl) {
            def ov = oav.find {it.week==pv.week}
            ov.volume = pv.volume
            //            println("setAvailableVolumes - volume: "+pv.volume+" - "+ov.week+" - "+pv.week+" OF.Vol: "+ov.volume)
        }

    }
    
    def Double addWeekVolumes( OfferDetail aOD, params) {
        //        println("AddWeeklyVolumes - params: "+params)
        Double[] totAvailableVolumeAtWeek = new Double[12]// Totalt tillgänglig volym för vecka
        Double[] usedVolumeAtWeek = new Double[12]     // Offeread volym för vecka

        //        println("addWeekVolumes - volumeInStock:: "+aOD.inStock)
        def Double volumeInStock = new Double(aOD.inStock)
        def availableVolume = volumeInStock
        def avl = aOD.availableVolumes
        int i = 0
        for (av in avl) {
            //            println("addWeekVolumes - volume: "+av.volume)
            availableVolume = availableVolume + av.volume
            totAvailableVolumeAtWeek[i] = availableVolume
            i++
        }
        Double[] uVol = new Double[12]
        if (params.vol1 == '') uVol[0] = 0
        else
        uVol[0] = params.vol1.toDouble()
            
        if (params.vol2 == '') uVol[1] = 0
        else
        uVol[1] = params.vol2.toDouble()
        
        if (params.vol3 == '') uVol[2] = 0
        else
        uVol[2] = params.vol3.toDouble()

        if (params.vol4 == '') uVol[3] = 0
        else
        uVol[3] = params.vol4.toDouble()
        
        if (params.vol5 == '') uVol[4] = 0
        else
        uVol[4] = params.vol5.toDouble()
        
        if (params.vol6 == '') uVol[5] = 0
        else
        uVol[5] = params.vol6.toDouble()
        
        if (params.vol7 == '') uVol[6] = 0
        else
        uVol[6] = params.vol7.toDouble()
        
        if (params.vol8 == '') uVol[7] = 0
        else
        uVol[7] = params.vol8.toDouble()
        
        if (params.vol9 == '') uVol[8] = 0
        else
        uVol[8] = params.vol9.toDouble()
        
        if (params.vol10 == '') uVol[9] = 0
        else
        uVol[9] = params.vol10.toDouble()
        
        if (params.vol11 == '') uVol[10] = 0
        else
        uVol[10] = params.vol11.toDouble()
        
        if (params.vol12 == '') uVol[11] = 0
        else
        uVol[11] = params.vol12.toDouble()

        Double usedVolume = aOD.fromStock
        //        println("addWeekVolumes - FromStock: "+usedVolume)
        //        println("addWeekVolumes - InStock: "+volumeInStock)
        
        if (usedVolume > volumeInStock) {
            return -1.0
        }
        
        availableVolume = volumeInStock - usedVolume
        volumeInStock = volumeInStock - usedVolume
        def Double restVol
        for (i=0; i < 12; i++) {
            if (uVol[i] > 0.0){
                usedVolume = usedVolume + uVol[i]
                if (usedVolume > totAvailableVolumeAtWeek[i]) {
                    return -2.0
                } 

            }
            /*            println("addWeekVolumes - uVol: "+uVol[i]+" Index: "+i)
            println("addWeekVolumes - avai: "+totAvailableVolumeAtWeek[i])
            println("addWeekVolumes - Stock: "+volumeInStock)
             */
        }
        println("addWeekVolumes - uVol: "+usedVolume)
        
        def ProdBuffer pb = ProdBuffer.get(aOD.millOfferID)
        i = 0
        for (odpv in aOD.offerPlannedVolumes) {

            if (aOD.offerHeader.status == 'Active'){
                print('>>>>>  Vol['+i+']: '+ pb.plannedVolumes[i].volume)
                pb.plannedVolumes[i].volume = pb.plannedVolumes[i].volume + odpv.volume
                print('>>>>>  efter retur: '+ pb.plannedVolumes[i].volume)
                pb.plannedVolumes[i].volume = pb.plannedVolumes[i].volume - uVol[i]
                println('>>>>>  efter avdrag '+ pb.plannedVolumes[i].volume)
            }

            odpv.volume = uVol[i]
            i++
        }
        aOD.inStock = volumeInStock
        if (aOD.offerHeader.status == 'Active'){
            println('addWeekVolumes. We are going to call addOfferVolume: '+aOD.offerHeader.status)
            prodBufferService.addOfferVolume(pb, aOD, usedVolume - aOD.volumeOffered)
        }
        
        return usedVolume
    }
    
    def weekAdjust() {
        def List<OfferHeader> ohList = OfferHeader.executeQuery("FROM OfferHeader OH WHERE OH.status IN ('Active', 'New')" )
        for (oh in ohList) {
            for (od in oh.offerDetails) {
                weekAdjustVolumes(od)
            }
        }
    }
    
    def weekAdjustVolumes(OfferDetail aOD) {
        def weekListSize = 12
        def  opv = aOD.offerPlannedVolumes
        for (int i=0; i< weekListSize-1; i++) {
            //            println("AdjustVolumes, I: "+i)
            if (i==0) {
                aOD.fromStock = aOD.fromStock + opv[0].volume
            }
            opv[i].volume = opv[i+1].volume
            opv[i].save(flush:true, failOnError:true)
        }
        opv[weekListSize-1].volume = 0
        opv[weekListSize-1].save(flush:true, failOnError:true)
    }
    
    def VolumeChange addOfferVolume(OfferDetail aOD, ProdBuffer aPB, Double newVolume) {
        def Double volumeChange = getVolumeChangeFromForm(aOD, newVolume)
        println(" addOfferVolume - Before check " + volumeChange)
        if (Math.abs(volumeChange) > 0.0) {
            if (okToAddVolume(aPB, volumeChange)) {
                println(" addOfferVolume - Volume OK! " + volumeChange)
                if (aOD.offerHeader.status == 'Active') {
                    prodBufferService.addOfferVolume(aPB, aOD, volumeChange)
                    logService.logOfferDetailVolumes('CTRL','update','After addOfferVolume - volumeChange: '+volumeChange ,aOD)
                }
                return new VolumeChange(volumeChange, true)
            } else {
                println(" addOfferVolume - Volume -- NOT -- OK! " + volumeChange)

                return new VolumeChange(volumeChange, false)                           
            }
            
        } else {
            return new VolumeChange(0,true)
        }

    }
    
    def addWeeklyOfferedVolumesAtActivation(OfferDetail aOD) {
        
        assert aOD.useWeeklyVolumes : "Called addWeeklyOfferedVolumesAtActivation not using weekly volumes!"
        assert aOD.offerHeader.status == 'Active' : "Called addWeeklyOfferedVolumesAtActivation and status not active"
        def int i
        def double usedVolume = aOD.fromStock
        def ProdBuffer pb = ProdBuffer.get(aOD.millOfferID)
        i = 0
        for (odpv in aOD.offerPlannedVolumes) {
            pb.plannedVolumes[i].volume = pb.plannedVolumes[i].volume - odpv.volume
            usedVolume = usedVolume + odpv.volume
            i++
        }
        prodBufferService.addOfferVolume(pb, aOD, usedVolume)
        
    }
    
    def checkOldVolumesAndCorrect(OfferDetail aOD) {
        if (aOD.oldVolume > 0.001) {
            println("OfferDetailService.CheckOldVolumesAndCorrect - oldVolume: "+aOD.oldVolume)
            def weekListSize = 12
            def  opv = aOD.offerPlannedVolumes
            def  oav = aOD.availableVolumes
        
            aOD.inStock = aOD.inStock + aOD.fromStock
            for (int i=0; i< weekListSize; i++) {
                oav[i].volume = oav[i].volume + opv[i].volume
                opv[i].volume = 0.0
                opv[i].save(failOnError:true)
                oav[i].save(failOnError:true)
            }
        }
    }
 
}
