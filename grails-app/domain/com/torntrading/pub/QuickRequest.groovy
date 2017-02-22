package com.torntrading.pub

class QuickRequest {
    String title
    String contactPerson
    String contactPhone
    String contactEmail
    String specReq
    
    static mapping = {
        title            column: "title",          sqltype: "char", length: 100
        contactPerson                              sqltype: 'char', length: 50
        contactPhone     column: "contactPhone",   sqltype: 'char', length: 25
        contactEmail     column: "contactEmail",   sqltype: "char", length: 100
        specReq          column: "specReq",        sqltype: "char", length: 1000

    }
    static constraints = {
        title size: 0..100
        specReq size: 0..1000
        contactPerson size: 0..50
        contactPhone size: 0..25
        contactEmail size: 0..100
        

        title()
        contactPerson()
        contactPhone(nullable: true, blank: true)
        contactEmail()
        specReq()
        
        contactEmail(email: true)
        contactPhone(phoneNumber: [strict: true])

    }
}