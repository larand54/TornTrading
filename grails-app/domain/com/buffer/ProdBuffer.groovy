package com.buffer

class ProdBuffer {
    int id
    String sawMill 
    String status
    int    loBuffertNo     //-- sätt default tll verknummer
    int    pkgArticleNo
    String product
    String length
    int    productNo
    int    packageSize 
    double actualLengthMM  
    String packageSizename 
    String kd
    String grade
    double volumeAvailable // Leverantörens erbjudande
    double volumeOffered   // summa till kund offererade volymer 
    double onOrder          // kontrakterad volym = bokat 
    double volumeRest      // Resterande volym av leverantörens erbjudande Urspr. - kontrakterat
    double volumeRestInclOffers // Ursprung. - kontrakterat - offererat
    double delivered       // levererat av urspr. volym
    double makeInquiry 
    int changed 
    int appid 
    
    String volumeUnit
    String currency
    BigDecimal priceFSC     // Pris för FSC
    BigDecimal pricePEFC    // pris för PEFC
    BigDecimal priceCW      // Pris för ControllWood
    BigDecimal priceUC      // pris för UnCertified
    String weekStart
    String weekEnd
    double availW01
    double availW02
    double availW03
    double availW04
    double availW05
    double availW06
    double availW07
    double availW08
    double availW09
    double availW10
    double[] volList

    static transients = ['volList']
    static mapping	= {
        table 'LOBuffertv2'
        version false
        id              column: "id",              type:        'integer'
	sawMill 	column: "sawMill",         sqltype:	"char", length: 80
	product 	column: "Produkt",         sqltype:	"char", length: 150
	length  	column: "Length",          sqltype:	"char", length: 25
	currency 	column: "currency",        sqltype:	"char", length: 3
        priceFSC        column: "price_fsc"
        pricePEFC       column: "price_pefc"
        priceUC         column: "price_uc"
        priceCW         column: "price_cw"
	volumeUnit	column: "volumeUnit",        sqltype:     "char", length: 6
        loBuffertNo     column: "LOBuffertNo"
        kd              column: "kd",                sqltype:     "char", length: 4
        grade           column: "grade",             sqltype:     "char", length: 8
        pkgArticleNo    column: "PkgArticleNo"
        productNo       column: "ProductNo"
        packageSize     column: "PackageSize"
        packageSizeName column: "PackageSizeName"
        actuallengthMM  column: "ActualLengthMM"
        onOrder         column: "OnOrder"
        delivered       column: "Delivered"
        makeInquiry     column: "MakeInquiry"
        changed         column: "Changed"
        appid           column: "Appid"
        availW01        column: "Period1"
        availW02        column: "Period2"
        availW03        column: "Period3"
        availW04        column: "Period4"
        availW05        column: "Period5"
        availW06        column: "Period6"
        availW07        column: "Period7"
        availW08        column: "Period8"
        availW09        column: "Period9"
        availW10        column: "Period10"
        
    }
    static constraints = {
        status(inList:["Preliminary","Active","Finished","Cancelled"])
        
        sawMill                 nullable: true
        status                  nullable: true
        pkgArticleNo            nullable: true
        product                 nullable: true
        length                  nullable: true
        kd                      nullable: true
        grade                   nullable: true
        productNo               nullable: true
        packageSize             nullable: true
        packageSizename         nullable: true
        actualLengthMM          nullable: true
        volumeAvailable         nullable: true
        volumeOffered           nullable: true
        onOrder                 nullable: true
        volumeRest              nullable: true
        volumeRestInclOffers    nullable: true
        delivered               nullable: true
        makeInquiry             nullable: true
        changed                 nullable: true
        appid                   nullable: true
        currency                nullable: true
        priceFSC                nullable: true          
        pricePEFC               nullable: true           
        priceCW                 nullable: true          
        priceUC                 nullable: true/*,
           validator: { val, obj -> 
              if ((val != null) && ((obj.pricePEFC!=null) || (obj.priceCW != null) || (obj.priceFSC != null))) return 'buffer.validation.only_one_price'}*/
          
        volumeUnit              nullable: true
        
        weekEnd                 nullable: true,
           validator: { val, obj -> 
              (val as int) >= (obj.weekStart as int) }
          
        weekStart               nullable: true,
           validator: { val, obj -> 
              if (obj.id) {
                // don't check existing instances
                return
              }
              (val as int) >= obj.getCurrentWeek()}
          
        availW01                nullable: true
        availW02                nullable: true
        availW03                nullable: true
        availW04                nullable: true
        availW05                nullable: true
        availW06                nullable: true
        availW07                nullable: true
        availW08                nullable: true
        availW09                nullable: true
        availW10                nullable: true
       
    }
    
    def beforeValidate() {
        updateVolumes()
        updateVolumesOnWeek()
    }
    
    def beforeInsert() {
        initiateVolumes()
        fillWeekList()
    }
    
    def beforeupdate() {
        updateVolumes()
        updateVolumesOnWeek()
    }
        
    def int getCurrentWeek() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.WEEK_OF_YEAR)        
    }
    
    def int getCurrentYear() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.YEAR)-2000        
    }
    
    def int getWeekFromYearWeek(int aYearWeek) {
        // aYearWeek = 1718, aYear = 17 -> week = 1718 - 1700 = 18        
        return ((aYearWeek as int) - getYearFromYearWeek(aYearWeek)*100) as int     
    }
    
    def int getYearFromYearWeek(aYearWeek) {
        return (aYearWeek as int) / 100
    }
    
    def int getWeeksInYear() {
        def Date d = new Date()
	def cal = d.toCalendar()
        return cal.getWeeksInWeekYear()
    }
    
    def initiateVolumes() {
        
        // Beräkna index för start och slut veckorna
        def cw = getCurrentWeek()
        def sw = getWeekFromYearWeek(weekStart as int)
        if (sw < cw) sw = cw
        def ew = getWeekFromYearWeek(weekEnd as int)
        def weeksInYear = getWeeksInYear()
        def ixStart = 1
        def ixEnd = ixStart
        def cy = getCurrentYear()
        def sy = getYearFromYearWeek(weekStart as int)
        def ey = getYearFromYearWeek(weekEnd as int)
        if (ey == sy) {
            if (cy == sy) {
                // normal case -- everything in current year
                ixStart = 1 - cw + sw
                ixEnd   = 1 - cw + ew
            }
            else {
                ixStart = 1 + weeksInYear - cw + sw
                ixEnd   = 1 + weeksInYear - cw + ew
            }
        } else {
                ixStart = 1 - cw + sw
                ixEnd   = 1 + weeksInYear - cw + ew
        }
        if (ixStart > 10) ixStart = 10
        if (ixEnd > 10) ixEnd = 10
        //Kopiera in volymer i veckolistan
        volList = new double[10]
        for (int i = 1; i < ixStart; i++) {
            volList[i-1] = 0.0            
        }
        for (int i = ixStart; i <= ixEnd; i++) {
            volList[i-1] = volumeAvailable - getOnOrder(cw)
        }
        volumeRest = volumeAvailable - onOrder
        volumeRestInclOffers = volumeRest - volumeOffered
    }
    def fillWeekList() {
        availW01 = volList[0]
        availW02 = volList[1]
        availW03 = volList[2]
        availW04 = volList[3]
        availW05 = volList[4]
        availW06 = volList[5]
        availW07 = volList[6]
        availW08 = volList[7]
        availW09 = volList[8]
        availW10 = volList[9]
    }
            
    def int getOnOrder(int cw) {
        return onOrder
    }
        
        
    
    def updateVolumes() {
        initiateVolumes()
    }

    def updateVolumesOnWeek() {
        fillWeekList()
    }
}