package com.torntrading.portal

import com.buffer.ProdBuffer
import grails.transaction.Transactional

@Transactional
class OfferHeaderService {
    def prodBufferService
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
    
    def boolean okToAddVolume(ProdBuffer aPB, Double aVolChange) {
        println(">>> Available: "+aPB.volumeAvailable+"  Offered: "+aVolChange)
        return aPB.volumeAvailable >= aVolChange        
    }
    
    def addOfferVolume(OfferHeader aOH) {
        for (OfferDetail od in aOH.offerDetails) {
            def ProdBuffer pb = ProdBuffer.get(od.millOfferID)
            prodBufferService.addOfferVolume(pb, od.volumeOffered)  
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
                    flw.addWeek(0,currentWeek,currentYear)  
                }
                for(OfferPlannedVolume opv in od.offerPlannedVolumes) {
                    int tempYw = yw + opv.week
                    currentWeek = prodBufferService.getWeekFromYearWeek(tempYw)
                    currentYear = prodBufferService.getYearFromYearWeek(tempYw)
                    if (opv.volume > 0.001) {
                        flw.addWeek(opv.week,currentWeek,currentYear)
                    }
                } 
            } else {
               currentWeek = prodBufferService.getWeekFromYearWeek(yw)
               currentYear = prodBufferService.getYearFromYearWeek(yw)
               flw.addWeek(0,currentWeek,currentYear) 
            }    
        }
        return flw.toString()
    }
}
