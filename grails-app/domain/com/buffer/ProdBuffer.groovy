package com.buffer

class ProdBuffer {
    int id
    String sawMill 
    String status
    int    loBuffertNo     //-- sätt default tll verknummer
    int     pkgArticleNo
    String product
    String length
    int    productNo
    int     packageSize 
    double actualLengthMM  
    String  packageSizename 
    double volumeAvailable // Leverantörens erbjudande
    double volumeOffered   // summa till kund offererade volymer 
    double onOrder          // kontrakterad volym = bokat 
    double volumeRest      // Resterande volym av leverantörens erbjudande Urspr. - kontrakterat
    double  volumeRestInclOffers // Ursprung. - kontrakterat - offererat
    double  delivered       // levererat av urspr. volym
    double makeInquiry 
    int changed 
    int appid 
    
    String volumeUnit
    String currency
    BigDecimal price
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

    static mapping	= {
        table 'LOBuffertv2'
        id              column: "id",              type:        'integer'
	sawMill 	column: "sawMill",         sqltype:	"char", length: 80
	product 	column: "Produkt",         sqltype:	"char", length: 150
	length  	column: "Length",          sqltype:	"char", length: 25
	currency 	column: "currency",        sqltype:	"char", length: 3
	volumeUnit	column: "volumeUnit",      sqltype:	"char", length: 6
        loBuffertNo     column: "LOBuffertNo"
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
        status(inList:["Preliminary","Activ","Finished","Cancelled"])
        
        sawMill                 nullable: true
        status                  nullable: true
        pkgArticleNo            nullable: true
        product                 nullable: true
        length                  nullable: true
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
        price                   nullable: true
        volumeUnit              nullable: true
        weekEnd                 nullable: true
        weekStart               nullable: true
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

}