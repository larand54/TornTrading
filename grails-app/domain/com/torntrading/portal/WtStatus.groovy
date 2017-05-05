package com.torntrading.portal

class WtStatus {
    def prodBufferService
    int     id
    int     weekUpdated
    Date    dateUpdated
    
    def beforeInsert() {
        dateUpdated = new Date()
        weekUpdated = prodBufferService.getCurrentYearWeek()
    }
    def beforeUpdate() {
        dateUpdated = new Date()
    }
    static constraints = {
        id              nullable:true
        dateUpdated     nullable:true
        weekUpdated     nullable:true
    }
    
    static mapping = {
        table   'wt_status'
        version true
        id              column: "id",              type:        'int'
    }
}
