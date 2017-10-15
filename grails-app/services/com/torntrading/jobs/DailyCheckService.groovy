package com.torntrading.jobs

import grails.transaction.Transactional
import com.torntrading.portal.*

@Transactional
class DailyCheckService {
    def offerHeaderService
    def checkOffers() {
        println('>>>>>>>> DailyCheckService - Check offers - Time:' + new Date().toLocaleString())
        def cr = OfferHeader.createCriteria()
        def tempList = cr.list {
             lt("validUntil", new Date()) and {'in' ( "status", ['Active','New'] )}
        }
        tempList.each {
            println(it.validUntil.toLocaleString())
            it.status = 'Rejected'
            offerHeaderService.rejectOfferVolume(it)
            it.save(flush:true, failOnError:true)
        }

    }
}
