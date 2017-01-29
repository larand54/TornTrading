package com.buffer

class ProdBuffer {
	String sawMill
	String product
	double length
	double volumeOffered
	double volumeBooked
	double volumeAvailable
	String volumeUnit
	String currency
	BigDecimal price
	String weekStart
	String weekEnd
	String status
	Integer availW01
	Integer availW02
	Integer availW03
	Integer availW04
	Integer availW05
	Integer availW06
	Integer availW07
	Integer availW08
	Integer availW09
	Integer availW10

/*
	static mapping = {
		orderNo		sqlType: 'char', length: '20'
		sawMill		sqlType: 'char', length: '50'
		product		sqlType: 'char', length: '50'
	}
*/
    static constraints = {
		sawMill()
		product()
		length()
		volumeOffered()
		volumeBooked()
		volumeAvailable()
		volumeUnit()
		currency()
		price()
		weekStart()
		weekEnd()
		status(inList:["Preliminär","Aktiv","Avslutad","Cancelerad"])
		availW01()
		availW02()
		availW03()
		availW04()
		availW05()
		availW06()
		availW07()
		availW08()
		availW09()
		availW10()
	}

}
