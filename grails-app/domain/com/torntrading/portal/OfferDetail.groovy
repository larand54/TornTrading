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

	
    def beforeInsert() {
        createdBy = getUserID()
        def certList = getAvailableCert()
        println("Antal cert: "+ certList.size())
        if (certList.size() == 1) {
            choosedCert = certList[0]
        }
            endPrice = getCertPrice(choosedCert) * volumeOffered
            println("Cert: "+certList[0]+" certPris: "+endPrice)
            markup = offerHeader.agentFee * 0.01 * endPrice
            endPrice = endPrice + markup

        if (offerType == null) {offerType = 'o'}
    }
    
    def beforeUpdate() {
        oldVolume =  getPersistentValue('volumeOffered')
        def oldCert = getPersistentValue('choosedCert')
        def certList = getAvailableCert()
        if (certList.size() == 1)       choosedCert = certList[0]
        if (choosedCert == 'FSC')       endPrice = priceFSC
        else if (choosedCert == 'PEFC') endPrice = pricePEFC
        else if (choosedCert == 'UC')   endPrice = priceUC
        else if (choosedCert == 'CW')   endPrice = priceCW
        println("Choose cert UPDATE: "+choosedCert)
        endPrice =  endPrice * volumeOffered 
        markup = endPrice * offerHeader.agentFee * 0.01
        endPrice = endPrice + markup
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

        return sl
    }
    
    def getCertPrice(String cert) {
       if (cert=='FSC') return priceFSC 
       if (cert=='PEFC') return pricePEFC 
       if (cert=='CW') return priceCW 
       if (cert=='UC') return priceUC 
    }
}
