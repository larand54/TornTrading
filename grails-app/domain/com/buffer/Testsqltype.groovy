package com.buffer

class Testsqltype {
	String sawMill
	String orderNo
	String packageSize
	String t1
	String	t2
	
	static mapping = {
		sawMill 		column: "sawMill", 	sqltype: "char", length: 80
		orderNo 		column: "orderNo", 	sqltype: "char", length: 80
		packageSize 		column: "packageSize", 	sqltype: "char", length: 80
		t1		column:	"trubbel", 	sqltype:	"char",	length:	80
		t2 		column: "trubbel2",	sqltype: "char", length: 80
	}
    static constraints = {
    }
}
