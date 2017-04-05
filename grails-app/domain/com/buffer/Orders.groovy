package com.buffer

class Orders {
	
	String 	orderNo
	String 	sawMill
	String 	customer
	String 	destination
	String 	period
	String 	product
	String 	lengthDescr
	String 	packetSize
	Integer quantity
	String	currency
	BigDecimal 	price
	String	status
	Date	dateCreated


	static mapping = {
                table('wt_orders')
		orderNo 		column: "orderNo", 		sqltype: "char", length: 20
		sawMill 		column: "sawMill", 		sqltype: "char", length: 80
		period 		column: "period", 		sqltype: "char", length: 4
		customer 		column: "customer", 		sqltype: "char", length: 50
		destination 		column: "destination", 		sqltype: "char", length: 80
		product 		column: "product", 		sqltype: "char", length: 100
		lengthDescr		column: "lengthDescr", 	sqltype: "char", length: 50
		price	 		column: "price"
		currency 		column: "currency", 	sqltype: "char", length: 3
		packetSize 		column: "packetSize", 		sqltype: "char", length: 15
		dateCreated		column: "dateCreated", defaultValue: newDate()	
	}

    static constraints = {
		orderNo		size: 0..20 
		sawMill		size: 0..50
		customer	size: 0..50
		destination	size: 0..50
		product		size: 0..50
		packetSize	size: 0..15
		
		sawMill()
		customer()
		orderNo()
		destination()
		period()
		product()
		lengthDescr()
		packetSize()
		quantity()
		currency()
		price()
		status(inList: ["Preliminär", "Aktiv", "Avslutad", "Cancellerad"])
		dateCreated()
	
    }
}
