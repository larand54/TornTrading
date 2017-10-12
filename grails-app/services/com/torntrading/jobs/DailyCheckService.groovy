package com.torntrading.jobs

import grails.transaction.Transactional

@Transactional
class DailyCheckService {

    def checkOffers() {
        println('>>>>>>>> DailyCheckService - Check offers - Time:' + new Date().toLocaleString())
    }
}
