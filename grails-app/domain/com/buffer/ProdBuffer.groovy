package com.buffer

import com.torntrading.portal.*
import grails.validation.ValidationException

class ProdBuffer {
    int id
    String sawMill 
    String status
    String species
    int    loBuffertNo     //-- sätt default till verknummer
    int    pkgArticleNo
    String dimension
    String length
    int    productNo
    int    packageSize 
    Double actualLengthMM  
    String packageSizename 
    String kd
    String grade
    Double volumeInStock = 0.0          // Vad leverantören har i lager just nu
    Double volumeAvailable = 0.0        // Leverantörens erbjudande
    Double volumeOffered = 0.0          // Leverantörens erbjudande
    Double volumeOnOrder = 0.0          // Såld volym
    Double volumeInitial = 0.0          // Lager då posten skapades av urspr. volym
    Double makeInquiry = 0.0
    int changed 
    int appid 
    
    String volumeUnit
    String currency
    BigDecimal priceFSC     // Pris för FSC
    BigDecimal pricePEFC    // pris för PEFC
    BigDecimal priceCW      // Pris för ControllWood
    BigDecimal priceUC      // pris för UnCertified
    String weekStart
    Double availW01
    Double availW02
    Double availW03
    Double availW04
    Double availW05
    Double availW06
    Double availW07
    Double availW08
    Double availW09
    Double availW10
    Double[] volList

    static transients = ['volList']
    SortedSet plannedVolumes
    static hasMany = [plannedVolumes: PlannedVolume]
    static mapping	= {
        table 'LOBuffertv2'
        version true
        id              column: "id",              type:        'integer'
	sawMill 	column: "sawMill",         sqltype:	"char", length: 80
	dimension 	column: "dimension",        sqltype:	"char", length: 150
	length  	column: "Length",          sqltype:	"char", length: 255
	currency 	column: "currency",        sqltype:	"char", length: 3
        priceFSC        column: "price_fsc"
        pricePEFC       column: "price_pefc"
        priceUC         column: "price_uc"
        priceCW         column: "price_cw"
	volumeUnit	column: "volumeUnit",        sqltype:     "char", length: 6
        loBuffertNo     column: "LOBuffertNo"
        kd              column: "kd",                sqltype:     "char", length: 4
        grade           column: "grade",             sqltype:     "char"
        pkgArticleNo    column: "PkgArticleNo"
        productNo       column: "ProductNo"
        packageSize     column: "PackageSize"
        packageSizeName column: "PackageSizeName"
        actuallengthMM  column: "ActualLengthMM"
        volumeInStock         column: "volume_instock",     defaultValue: "0.0"
        volumeAvailable       column: "volume_available",   defaultValue: "0.0"
        volumeOffered         column: "volume_offered",     defaultValue: "0.0"
        volumeOnOrder         column: "volume_onorder",     defaultValue: "0.0"
        volumeInitial         column: "volume_initial",     defaultValue: "0.0"
        makeInquiry           column: "MakeInquiry",        defaultValue: "0.0"
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
        status(inList:["Active","Finished","Cancelled"])
        species(inList:["Redwood", "Whitewood"])
        grade(inList:["SF(AB)", "O/S-V(AB)", "V(B)", "VI(C)", "VII(D)"])
        
        sawMill                 nullable: true
        status                  nullable: true
        pkgArticleNo            nullable: true
        dimension               nullable: true
        length                  nullable: true
        kd                      nullable: true
        grade                   nullable: true
        productNo               nullable: true
        packageSize             nullable: true
        packageSizename         nullable: true
        actualLengthMM          nullable: true
        volumeAvailable         nullable: true
        volumeOffered           nullable: true
        volumeOnOrder           nullable: true
        volumeInStock           nullable: true
        volumeInitial           nullable: true
        makeInquiry             nullable: true
        changed                 nullable: true
        appid                   nullable: true
        currency                nullable: true
        priceFSC                nullable: true          
        pricePEFC               nullable: true           
        priceCW                 nullable: true          
        priceUC                 nullable: true
        volumeUnit              nullable: true 
        weekStart               nullable: true
/*        weekStart               nullable: true,
           validator: { val, obj -> 
              if (obj.id) {
                // don't check existing instances
                return
              }
              (val as int) >= obj.getCurrentWeek()}
*/          
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
        volList                 nullable: true
       
    }
    
    def beforeValidate() {
    }
    
    def beforeInsert() {
        status = "Active"
        // Set volume available only if InStock is set manually
        if (  volumeInStock > 0) {
              volumeAvailable = volumeAvailable + volumeInStock
              volumeInitial = volumeInStock
          }
        for (int i=0; i<12; i++) {
            def pv = new PlannedVolume(week:i+1 as Integer, volume:0 as Double)
            addToPlannedVolumes(pv)
            println("Add planned volume record: "+i)
        }
    }
    
    def beforeUpdate() {
        if (volumeInitial == 0.0) {
            volumeInitial = volumeInStock
        }
    }
    
    def afterUpdate() {
        
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
    
}