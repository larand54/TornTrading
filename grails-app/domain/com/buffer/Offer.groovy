package com.buffer

class Offer {
    def springSecurityService
    boolean             fsc
    String              grade
    String              kd
    String              termsOfDelivery
    String              company
    String              country
    String              city
    String              contactPerson
    String              contactPhone
    String              contactEmail

    String		sawMill
    String		product
    String		lengthDescr
    BigDecimal          price
    String		currency
    String		volumeUnit
    double		volumeOffered
    String		weekStart
    String		weekEnd
    String 		status
    Date		dateCreated
    int                 createdBy 
    int                 millOfferID // id för sågverkserbjudande som offerten utgått från
    int                 requestID   // Om ej null, så anger den den förfrågan som offerten skapats från
	
    def beforeInsert() {
        createdBy = getUserID()
    }
    static mapping	= {
	sawMill 	column: "sawMill",         sqltype:	"char", length: 80
	product 	column: "product",         sqltype:	"char", length: 100
	lengthDescr	column: "lengthDescr",     sqltype:	"char", length: 50
//	price	 	column: "price",           sqltype:	"char", length: 10
	currency 	column: "currency",        sqltype:	"char", length: 3
	volumeUnit	column: "volumeUnit",      sqltype:	"char", length: 6
	volumeOffered	column: "volumeOffered",   sqltype:	"float"
	weekStart	column: "weekStart",       sqltype:	"char", length: 4
	weekEnd		column: "weekEnd",         sqltype:	"char", length: 4
	status		column: "status",          sqltype:	"char", length: 11
	dateCreated	column: "dateCreated",     defaultValue: newDate()
//        createdBy       column: "createdBy",       defaultValue: 199//getUserID()
        grade            column: 'grade',          sqltype: 'char', length: 8
        kd               column: 'kd',             sqltype: 'char', length: 4
        company          column: 'company',        sqltype: 'char', length: 50
        country          column: 'country',        sqltype: 'char', length: 20
        city                                       sqltype: 'char', length: 50
        contactPerson                              sqltype: 'char', length: 50
        contactPhone     column: "contactPhone",   sqltype: 'char', length: 25
        contactEmail     column: "contactEmail",   sqltype: "char", length: 100

    }
    static constraints = {
		sawMill		size: 0..80
		product		size: 0..100
		lengthDescr	size: 0..50
		currency	size: 0..3
		volumeUnit	size: 0..6
		weekStart	size: 0..4
		weekEnd		size: 0..4
                grade size: 0..8
                kd size: 0..4
                company size: 0..50
                country size: 0..50
                city size: 0..50
                contactPerson size: 0..50
                contactPhone size: 0..25
                contactEmail size: 0..100
		sawMill()
		product()
		lengthDescr()
                kd()
                fsc()
                grade()
		price()
		currency()
		volumeUnit()
		volumeOffered()
		weekStart()
		weekEnd()
		company()
                contactEmail()
                country()
                contactPerson()
                contactPhone()
                city()
                status(inList: ["Preliminär", "Aktiv", "Avslutad", "Anullerad"])
		dateCreated()
                termsOfDelivery(inList: ['Fritt leverantören', 'Fritt kunden'])

                millOfferID     nullable:true
                requestID       nullable:true
                sawMill         nullable:true
                weekStart       nullable:true
                weekEnd         nullable:true
                contactPhone    nullable:true
                contactEmail    nullable:true
                contactPerson   nullable:true
                city            nullable:true
                country         nullable:true
                company         nullable:true
                currency        nullable:true
    }
    def int getUserID() {
        def user = springSecurityService.isLoggedIn() ?
            springSecurityService.loadCurrentUser() :
            null
        return user ? user.id: -1 //securityService.currentUser.id//
    }
}
