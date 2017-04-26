package com.torntrading.portal

class OfferHeader {
    def springSecurityService
    String              termsOfDelivery
    String              company
    String              country
    String              city
    String              contactPerson
    String              contactPhone
    String              contactEmail
    String		sawMill
    String		currency
    String		volumeUnit
    String 		status
    String              offerType
    BigDecimal          freight         // Fraktkostnad   
    Date		dateCreated
    int                 createdBy 

    static hasMany =[offerDetails: OfferDetail]
    def beforeInsert() {
        createdBy = getUserID()
        status = 'New'
        if (offerType == null) {offerType = 'o'}
    }
    static mapping	= {
        offerDetails sort: "sawMill"
	sawMill 	column: "sawMill",         sqltype:	"char", length: 80
	currency 	column: "currency",        sqltype:	"char", length: 3
	volumeUnit	column: "volumeUnit",      sqltype:	"char", length: 6
	status		column: "status",          sqltype:	"char", length: 11
	offerType	column: "offerType",       sqltype:	"char", length: 1
	dateCreated	column: "dateCreated",     defaultValue: newDate()
        company          column: 'company',        sqltype: 'char', length: 50
        country          column: 'country',        sqltype: 'char', length: 20
        city                                       sqltype: 'char', length: 50
        contactPerson                              sqltype: 'char', length: 50
        contactPhone     column: "contactPhone",   sqltype: 'char', length: 25
        contactEmail     column: "contactEmail",   sqltype: "char", length: 100

    }
    static constraints = {
		sawMill		size: 0..80
		currency	size: 0..3
		volumeUnit	size: 0..6
                company size: 0..50
                country size: 0..50
                city size: 0..50
                contactPerson size: 0..50
                contactPhone size: 0..25
                contactEmail size: 0..100
		sawMill()
		currency()
		volumeUnit()
		company()
                contactEmail()
                country()
                contactPerson()
                contactPhone()
                city()
                status(inList: ["New", "Active", "Sold", "Rejected"])
		dateCreated()
                termsOfDelivery(inList: ['Fritt leverantören', 'Fritt kunden'])

                sawMill         nullable:true
                termsOfDelivery nullable:true
                contactPhone    nullable:true
                contactEmail    nullable:true
                contactPerson   nullable:true
                city            nullable:true
                country         nullable:true
                company         nullable:true
                currency        nullable:true
                volumeUnit      nullable:true
                status          nullable:true
                offerDetails    nullable:true
                offerType       nullable:true
                freight         nullable:true
    }
    def int getUserID() {
        def user = springSecurityService.isLoggedIn() ?
            springSecurityService.loadCurrentUser() :
            null
        return user ? user.id: -1 //securityService.currentUser.id//
    }
}
