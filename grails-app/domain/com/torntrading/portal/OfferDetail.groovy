package com.torntrading.portal

class OfferDetail {

    def springSecurityService
    
    static belongsTo = [offerHeader: OfferHeader]
    String              grade
    String              kd
    String		product
    String		lengthDescr
    String              choosedCert
    
    // Priser -- Notera! Endast en av de 4 olika certifieringarna får ha ett prispåslag Vilket kontrolleras i controllern.
    BigDecimal          markup          // Prispåslag
    BigDecimal          freight         // Fraktkostnad   
    BigDecimal          fscPrice        // tillägg för FSC
    BigDecimal          ucPrice         // tillägg för UnControlled wood 
    BigDecimal          cwPrice         // tillägg för ControllWood
    BigDecimal          pefcPrice       // tillägg för PEFC
    BigDecimal          endPrice        // kundens slutpris
    double		volumeOffered
    String		weekStart
    String		weekEnd
    Date		dateCreated
    int                 createdBy 
    int                 millOfferID // id för sågverkserbjudande som offerten utgått från
    int                 requestID   // Om ej null, så anger den den förfrågan som offerten skapats från
	
    def beforeInsert() {
        createdBy = getUserID()
        endPrice = 0 //price + freight + markup
    }
    def beforeUpdate() {
        endPrice = freight + markup
        if (choosedCert == 'FSC') endPrice = endPrice + priceFSC
        else if (choosedCert == 'PEFC') endPrice = endPrice + pricePEFC
        else if (choosedCert == 'UC') endPrice = endPrice + priceUC
        else if (choosedCert == 'CW') endPrice = endPrice + priceCW
    }
    
    static mapping	= {
	product 	column: "product",         sqltype:	"char", length: 100
	lengthDescr	column: "lengthDescr",     sqltype:	"char", length: 50
	volumeOffered	column: "volumeOffered",   sqltype:	"float"
	weekStart	column: "weekStart",       sqltype:	"char", length: 4
	weekEnd		column: "weekEnd",         sqltype:	"char", length: 4
	dateCreated	column: "dateCreated",     defaultValue: newDate()
        grade            column: 'grade',          sqltype: 'char', length: 8
        kd               column: 'kd',             sqltype: 'char', length: 4

    }
    static constraints = {
		product		size: 0..100
		lengthDescr	size: 0..50
		weekStart	size: 0..4
		weekEnd		size: 0..4
                grade size: 0..8
                kd size: 0..4
		product()
		lengthDescr()
                kd()
                grade()
		volumeOffered()
		weekStart()
		weekEnd()
		dateCreated()
//                choosedCert(inList: getAvailableCert())
                millOfferID     nullable:true
                requestID       nullable:true
                weekStart       nullable:true
                weekEnd         nullable:true
                volumeOffered   nullable:true
                kd              nullable:true
                grade           nullable:true
                endPrice        nullable:true
                fscPrice        nullable:true
                pefcPrice       nullable:true
                ucPrice         nullable:true
                cwPrice         nullable:true
                markup          nullable:true
                freight         nullable:true
                lengthDescr     nullable:true
                choosedCert     nullable:true
    }
    def int getUserID() {
        def user = springSecurityService.isLoggedIn() ?
            springSecurityService.loadCurrentUser() :
            null
        return user ? user.id: -1 //securityService.currentUser.id//
    }
    
    def List<String> getAvailableCert() {
        def List<String> sl
        sl.add(priceFSC?'FSC':null)
        sl.add(pricePEFC?'PEFC':null)
        sl.add(priceUC?'UC':null)
        sl.add(priceCW?'CW':null)
        return sl
    }
}
