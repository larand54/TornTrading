package com.buffer

class ProdBuffer {
	String sawMill
	String product
	Integer length
	String packageSize
	Integer store
	Integer onOrder
	Integer delivered
	Integer rest
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
	
	static mapping = {
		orderNo		sqlType: varchar(20)
		sawMill		sqlType: varchar(50)
		product		sqlType: varchar(50)
		packageSize	sqlType: varchar(15)		
	}
    static constraints = {
		sawMill()
		product()
		length()
		packageSize()
		store()
		onOrder()
		delivered()
		rest()
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
