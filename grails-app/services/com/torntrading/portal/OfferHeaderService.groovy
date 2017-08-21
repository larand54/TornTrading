package com.torntrading.portal

import com.buffer.ProdBuffer
import grails.transaction.Transactional

@Transactional
class OfferHeaderService {
    def prodBufferService
    def offerDetailService
    def Boolean allOfferDetailsVolumeOK(OfferHeader aOH) {
        def result = true
        for (od in aOH.offerDetails) {
            result = result && (od.volumeOffered > 0.01)
            def ProdBuffer pb = ProdBuffer.get(od.millOfferID)
            println(">>> Available: "+pb.volumeAvailable+"  Offered: "+od.volumeOffered)
            result = result && pb.volumeAvailable >= od.volumeOffered
        }
        return result
    }
    
    def Boolean volumeOkToSell(OfferHeader aOH) {
        def result = true
        for (od in aOH.offerDetails) {
            result = result && (od.volumeOffered > 0.01)
        }
        return result
    }
    
    def addOfferVolume(OfferHeader aOH) {
        for (OfferDetail od in aOH.offerDetails) {
            def ProdBuffer pb = ProdBuffer.get(od.millOfferID)
            
            if (od.useWeeklyVolumes) {
                offerDetailService.addWeeklyOfferedVolumesAtActivation(od)
            } else {
                offerDetailService.allocateVolumeFromBuffer(od.volumeOffered,od,pb)
//                VolumeChange vc = offerDetailService.addOfferVolume(od, pb, od.volumeOffered*2) // This is the first reg of volume and as volumechange is calculated  as: Chagedvol = NewVol - Actual volume, we need to double the volume
            }
/*            if (vc.allowed) {
                prodBufferService.addOfferVolume(pb, od, vc.volume)
            } else 5/0
*/
        }
    }
    
    def soldOfferVolume(OfferHeader aOH) {
        for (OfferDetail od in aOH.offerDetails) {
            def ProdBuffer pb = ProdBuffer.get(od.millOfferID)
            prodBufferService.soldOfferVolume(pb, od.volumeOffered)  
        }
    }
    
    def rejectOfferVolume(OfferHeader aOH) {
        for (OfferDetail od in aOH.offerDetails) {
            def ProdBuffer pb = ProdBuffer.get(od.millOfferID)
            prodBufferService.rejectOffer(pb, od)  
        }
    }
    
    def setEndPrices(OfferHeader aOH) {
        for (od in aOH.offerDetails) {
            if (od.endPrice > 0.01) {
                println("OfferHeaderService - endPrice before: "+od.endPrice)
                od.markup = od.endPrice * 0.01 * aOH.agentFee
                od.endPrice = od.endPrice + od.markup
                println("OfferHeaderService - endPrice after: "+od.endPrice)
                od.save(failOnError:true)
            }
        }
    }
    
    def Boolean useWeeklyVolumes(OfferHeader aOH) {
        for (OfferDetail od in aOH.offerDetails) {
            if (od.useWeeklyVolumes) {
                return true
            }
        }
        return false
    }
    
    def String weeksOfDelivery(OfferHeader aOH) {
        int yw = prodBufferService.getCurrentYearWeek()
        int currentWeek = prodBufferService.getWeekFromYearWeek(yw)
        int currentYear = prodBufferService.getYearFromYearWeek(yw)
        FirstLastWeek flw = new FirstLastWeek()
        for (OfferDetail od in aOH.offerDetails) {
            if (od.useWeeklyVolumes) {
                if (od.fromStock > 0.01) {
println('##### OfferHeaderService - weeksOfDelivery-fromStock '+od.fromStock+'-'+currentWeek+':'+currentYear)
                    flw.addWeek(0,currentWeek,currentYear)  
                }
                for(OfferPlannedVolume opv in od.offerPlannedVolumes) {
                    if (opv.volume > 0.001) {
                        int tempYw = yw + opv.week
                        int iWeek = prodBufferService.getWeekFromYearWeek(tempYw)
                        int iYear = prodBufferService.getYearFromYearWeek(tempYw)
println('##### OfferHeaderService - weeksOfDelivery-tempYw '+tempYw)
println('##### OfferHeaderService - weeksOfDelivery-iWeek '+iWeek)
println('##### OfferHeaderService - weeksOfDelivery-iYear '+iYear)
                        flw.addWeek(opv.week,iWeek,iYear)
                    }
                } 
            } else {
println('##### OfferHeaderService - weeksOfDelivery-ELSE '+'-'+currentWeek+':'+currentYear)
               flw.addWeek(0,currentWeek,currentYear) 
            }    
        }
        return flw.toString()
    }
}
