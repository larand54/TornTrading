package com.buffer

class Orders {
	
String orderNo
String sawMill
String customer
String destination
String period
String product
Integer length
String packetSize
Integer quantity
double price


static mapping = {
	sawMill		sqlType: varchar(50)
	customer	sqlType: varchar(50)
	orderNo		sqlType: varchar(20)
	destination	sqlType: varchar(50)
	product		sqlType: varchar(50)
	packetSize	sqlType: varchar(15)
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
		length()
		packetSize()
		quantity()
		price()
	
    }
}
