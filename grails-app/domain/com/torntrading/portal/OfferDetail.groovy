package com.torntrading.portal
class OfferDetail {

    def springSecurityService
    def offerDetailService
    
    String              offerType            // ('o') - Offert, ('s') - Stocknota
    String              grade
    String              kd
    String		dimension
    String		lengthDescr
    String              choosedCert
    String              species
    String              sawMill
    Boolean             useWeeklyVolumes = false   // Användning av volymer fördelade på olika veckor
    
    // Priser -- Notera! Endast en av de 4 olika certifieringarna får ha ett prispåslag Vilket kontrolleras i controllern.
    BigDecimal          markup          // Prispåslag
    BigDecimal          priceAdjust = 0.0     // manuell justering av priset från valt cert
    BigDecimal          priceFSC        // tillägg för FSC
    BigDecimal          priceUC         // tillägg för UnControlled wood 
    BigDecimal          priceCW         // tillägg för ControllWood
    BigDecimal          pricePEFC       // tillägg för PEFC
    BigDecimal          endPrice        // kundens slutpris
    double		volumeOffered
    double              fromStock       // Volym allokerad från befintligt lager
    double              inStock         // Befintligt lager
    String		weekStart
    String		weekEnd
    Date		dateCreated
    int                 createdBy 
    int                 millOfferID // id för sågverkserbjudande som offerten utgått från
    int                 requestID   // Om ej null, så anger den den förfrågan som offerten skapats från
    double              oldVolume   // Volym angiven före uppdatering
    
    SortedSet offerPlannedVolumes
    SortedSet availableVolumes
    static belongsTo = [offerHeader: OfferHeader]
    static hasMany = [offerPlannedVolumes: OfferPlannedVolume, availableVolumes: OfferWeeklyAvailableVolume]
//    static transients = ['endPriceM3']
    def beforeInsert() {
        createdBy = getUserID()
        dateCreated = new Date()

        
        //println(offerDetailService.getWeekAsTitle(dateCreated,0))
        def certList = getAvailableCert()
        println("DateCreated: "+ dateCreated)
        println("Antal cert: "+ certList.size())
        if (certList.size() == 1) {
            choosedCert = certList[0]
        }
        calculateEndPrice()
        if (offerType == null) {offerType = 'o'}
        
        fromStock = 0.0
        inStock = 0.0
        for (int i=0; i<12; i++) {
            def ov = new OfferPlannedVolume(week:i+1 as Integer, volume:0 as Double)
            addToOfferPlannedVolumes(ov)
            println("Add offerplanned volume record: "+i)
        }
        println("Add available volume record: ")
        for (int i=0; i<12; i++) {
            def oav = new OfferWeeklyAvailableVolume(week:i+1 as Integer, volume:0 as Double)
            addToAvailableVolumes(oav)
            println("Add available volume record: "+i+" - "+oav.week)
        }

    }
    
    def beforeUpdate() {
        println('OfferDetail-Domain1- OldVolume: '+oldVolume)
        oldVolume =  getPersistentValue('volumeOffered')
        println('OfferDetail-Domain2- OldVolume: '+oldVolume)
        def oldCert = getPersistentValue('choosedCert')
        def certList = getAvailableCert()
        if (certList.size() == 1)       choosedCert = certList[0]
        if (choosedCert == 'FSC')       endPrice = priceFSC
        else if (choosedCert == 'PEFC') endPrice = pricePEFC
        else if (choosedCert == 'UC')   endPrice = priceUC
        else if (choosedCert == 'CW')   endPrice = priceCW
        println("Choose cert UPDATE: "+choosedCert)
        calculateEndPrice()
//        oldVolume = volumeOffered
        println("EndPrice at domain: "+endPrice)
    }
    
    def afterUpdate() {
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
        fromStock       column: 'from_stock',      sqlType:     'float' 

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
                grade(inList:["SF(AB)", "O/S-V(AB)", "V(B)", "VI(C)", "VII(D)", "C24"])
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
                fromStock       nullable:true
                kd              nullable:true
                grade           nullable:true
                priceAdjust     nullable:true
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
    
    def calculateEndPrice() {
            endPrice = (getCertPrice(choosedCert)?:0.0) + priceAdjust
            endPrice = endPrice * volumeOffered
            markup = offerHeader.agentFee * 0.01 * endPrice
            endPrice = endPrice + markup
    }
}
