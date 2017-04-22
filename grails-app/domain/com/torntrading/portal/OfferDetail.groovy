package com.torntrading.portal

class OfferDetail {

    def springSecurityService
    
    static belongsTo = [offerHeader: OfferHeader]
    String              offerType            // ('o') - Offert, ('s') - Stocknota
    String              grade
    String              kd
    String		dimension
    String		lengthDescr
    String              choosedCert
    String              species
    String              sawMill
    
    // Priser -- Notera! Endast en av de 4 olika certifieringarna får ha ett prispåslag Vilket kontrolleras i controllern.
    BigDecimal          markup          // Prispåslag
    BigDecimal          freight         // Fraktkostnad   
    BigDecimal          priceFSC        // tillägg för FSC
    BigDecimal          priceUC         // tillägg för UnControlled wood 
    BigDecimal          priceCW         // tillägg för ControllWood
    BigDecimal          pricePEFC       // tillägg för PEFC
    BigDecimal          endPrice        // kundens slutpris
    double		volumeOffered
    String		weekStart
    String		weekEnd
    Date		dateCreated
    int                 createdBy 
    int                 millOfferID // id för sågverkserbjudande som offerten utgått från
    int                 requestID   // Om ej null, så anger den den förfrågan som offerten skapats från
    double              oldVolume   // Volym angiven före uppdatering
//    double              diff        // Ändrad volym vid uppdatering
//    static transients = ['oldVolume', 'diff']

	
    def beforeInsert() {
        createdBy = getUserID()
        endPrice = 0 //price + freight + markup
        if (offerType == null) {offerType = 'o'}
    }
    
    def beforeUpdate() {
        oldVolume =  getPersistentValue('volumeOffered')
        endPrice = (freight?freight:0) + (markup?markup:0)
        if (choosedCert == 'FSC') endPrice = endPrice + priceFSC
        else if (choosedCert == 'PEFC') endPrice = endPrice + pricePEFC
        else if (choosedCert == 'UC') endPrice = endPrice + priceUC
        else if (choosedCert == 'CW') endPrice = endPrice + priceCW
    }
    
    static mapping	= {
        sort "sawMill"
	sawMill 	column: "sawMill",         sqltype:	"char", length: 80
        offerType       column: "offerType",       sqltype:     "char", length: 1   
	dimension 	column: "dimension",         sqltype:	"char", length: 100
	lengthDescr	column: "lengthDescr",     sqltype:	"char", length: 50
        priceFSC        column: "price_fsc"
        pricePEFC       column: "price_pefc"
        priceUC         column: "price_uc"
        priceCW         column: "price_cw"
	volumeOffered	column: "volumeOffered",   sqltype:	"float"
	weekStart	column: "weekStart",       sqltype:	"char", length: 4
	weekEnd		column: "weekEnd",         sqltype:	"char", length: 4
	dateCreated	column: "dateCreated",     defaultValue: newDate()
        grade            column: 'grade',          sqltype: 'char'
        kd               column: 'kd',             sqltype: 'char', length: 4
        oldVolume       column: 'oldVolume',       sqlType:     'float' 

    }
    static constraints = {
		sawMill		size: 0..80, nullable:true
		dimension		size: 0..100
		lengthDescr	size: 0..50
		weekStart	size: 0..4
		weekEnd		size: 0..4
                kd size: 0..4
		dimension()
		lengthDescr()
                kd()
                grade(inList:["SF(AB)", "O/S-V(AB)", "V(B)", "VI(C)", "VII(D)"])
                species(inList:["Redwood", "Whitewood"])
		volumeOffered()
		weekStart()
		weekEnd()
		dateCreated()
                millOfferID     nullable:true
                requestID       nullable:true
                weekStart       nullable:true
                weekEnd         nullable:true
                volumeOffered   nullable:true
                oldVolume       nullable:true
                kd              nullable:true
                grade           nullable:true
                endPrice        nullable:true
                priceFSC        nullable:true
                pricePEFC       nullable:true
                priceUC         nullable:true
                priceCW         nullable:true
                markup          nullable:true
                freight         nullable:true
                lengthDescr     nullable:true
                choosedCert     nullable:true
                offerType       nullable:true
    }
    def int getUserID() {
        def user = springSecurityService.isLoggedIn() ?
            springSecurityService.loadCurrentUser() :
            null
        return user ? user.id: -1 //securityService.currentUser.id//
    }
    
    def List<String> getAvailableCert() {
        def List<String> sl = new ArrayList<String>()
        if (priceFSC != null) sl.add("FSC")
        if (pricePEFC != null) sl.add('PEFC')
        if (priceCW != null) sl.add('CW')
        if (priceUC != null) sl.add('UC')

/*            sl.add(priceFSC?'FSC':null)
        sl.add(pricePEFC?'PEFC':null)
        sl.add(priceUC?'UC':null)
        sl.add(priceCW?'CW':null)
*/        return sl
    }
}
