package com.buffer

class Offer {
	String		sawMill
	String		product
	String		lengthDescr
	BigDecimal 	price
	String		currency
	String		volumeUnit
	double		volumeOffered
	String		weekStart
	String		weekEnd
	String 		status
	Date		dateCreated
	
	
	static mapping	= {
		sawMill 		column: "sawMill", 	sqltype:	"char", length: 80
		product 		column: "product", 	sqltype:	"char", length: 100
		lengthDescr		column: "lengthDescr", 	sqltype:	"char", length: 50
		price	 		column: "price", sqltype:	"char", length: 10
		currency 		column: "currency", 	sqltype:	"char", length: 3
		volumeUnit		column: "volumeUnit", 	sqltype:	"char", length: 6
		volumeOffered	column: "volumeOffered", sqltype:	float
		weekStart		column: "weekStart", sqltype:	"char", length: 4
		weekEnd			column: "weekEnd", sqltype:	"char", length: 4
		status			column: "status", sqltype:	"char", length: 11
		dateCreated		column: "dateCreated", defaultValue: newDate()
	}
    static constraints = {
		sawMill		size: 0..80
		product		size: 0..100
		lengthDescr	size: 0..50
		currency	size: 0..3
		volumeUnit	size: 0..6
		weekStart	size: 0..4
		weekEnd		size: 0..4
		sawMill()
		product()
		lengthDescr()
		price()
		currency()
		volumeUnit()
		volumeOffered()
		weekStart()
		weekEnd()
		status(inList: ["Preliminär", "Aktiv", "Avslutad", "Anullerad"])
		dateCreated()
    }
}
