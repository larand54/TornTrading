package com.buffer

class FreeText {
	Request1 req
	String descr
	String theMessage
	
	static belongsTo = [req:Request1]
	static mapping = {
		descr		column: "descr", sqlType: 'char', length: 20
		theMessage	column: "message", sqlType: 'char', length: 1000
	}
    static constraints = {
    }
}
