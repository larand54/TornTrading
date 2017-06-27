package com.torntrading.portal

import grails.transaction.Transactional
import com.buffer.*

@Transactional
class OfferDetailService {

    def Double getVolumeChange(OfferDetail aOD) {
        if ((aOD.volumeOffered > 0.0001) || (aOD.oldVolume > 0.0001)){
            double diff = aOD.volumeOffered - aOD.oldVolume
            if (Math.abs(diff) > 0.0001) {
                return diff
            } else {
                return 0.0
            }
        } else return 0.0

    }

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
    
    def String getWeekAsTitle(Date aDate, int aOffset) {
        println("getWeekAsTitle: "+aDate)
        def calendar = aDate.toCalendar()
        println("getWeekAsTitle: calendar ")
        calendar.add(Calendar.DATE,7*(aOffset as int))
        println("getWeekAsTitle: calendar.add ")
        def int week = calendar.get(Calendar.WEEK_OF_YEAR)
        println("getWeekAsTitle: week: "+week)
        String weekStr = String.format("W%02d", week)
        println("Week as title: "+weekStr)
         
        return weekStr
        
    }
    
    def setAvailableVolumes(OfferDetail aOD) {
        println("setAvailableVolumes - volume: ")
        def pb = ProdBuffer.get(aOD.millOfferID)
        aOD.inStock = pb.volumeInStock
        def oav = aOD.availableVolumes
        def pvl = pb.plannedVolumes
        for (pv in pvl) {
            def ov = oav.find {it.week==pv.week}
            ov.volume = pv.volume
            println("setAvailableVolumes - volume: "+pv.volume+" - "+ov.week+" - "+pv.week+" OF.Vol: "+ov.volume)
        }

    }
    
    def Double addWeekVolumes( OfferDetail aOD, params) {
        println("AddWeeklyVolumes - params: "+params)
        Double[] totAvailableVolumeAtWeek = new Double[12]// Totalt tillgänglig volym för vecka
        Double[] usedVolumeAtWeek = new Double[12]     // Offeread volym för vecka

        println("addWeekVolumes - volumeInStock:: "+aOD.inStock)
        def Double volumeInStock = new Double(aOD.inStock)
        def availableVolume = volumeInStock
        def avl = aOD.availableVolumes
        int i = 0
        for (av in avl) {
            println("addWeekVolumes - volume: "+av.volume)
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
        println("addWeekVolumes - FromStock: "+usedVolume)
        println("addWeekVolumes - InStock: "+volumeInStock)
        
        if (usedVolume > volumeInStock) {
            return -1.0
        }
        availableVolume = volumeInStock - usedVolume
        def Double restVol
        for (i=0; i < 12; i++) {
            if (uVol[i] > 0.0){
                usedVolume = usedVolume + uVol[i]
                if (usedVolume > totAvailableVolumeAtWeek[i]) {
                    return -2.0
                } 

            }
            println("addWeekVolumes - uVol: "+uVol[i]+" Index: "+i)
            println("addWeekVolumes - avai: "+totAvailableVolumeAtWeek[i])
            println("addWeekVolumes - Stock: "+volumeInStock)
        }
        println("addWeekVolumes - uVol: "+usedVolume)
        
        i = 0
        for (odpv in aOD.offerPlannedVolumes) {
            odpv.volume = uVol[i]
            i++
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

}
