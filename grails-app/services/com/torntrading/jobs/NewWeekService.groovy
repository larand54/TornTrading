package com.torntrading.jobs

import grails.transaction.Transactional

@Transactional
class NewWeekService {

    def checkOffers() {
        println('>>>>>>>> NewWeekService  - Time:' + new Date().toLocaleString())
    }
}
