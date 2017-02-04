package com.buffer

class Request1 {
    double length
    double width
    double thickness
    double volumeRequested
    String quality
    String kd
    String weekStart
    String weekEnd
    String company
    String country
    String city
    String contactPerson
    String contactPhone
    String contactEmail
	String	status
	Date	dateCreated
	String freeText

//	static hasOne = [description:FreeText]
    static mapping = {
        length                                     sqltype: 'float'
        thickness                                  sqltype: 'float'
        width                                      sqltype: 'float'
        volumeRequested                            sqltype: 'float'
        quality          column: 'quality',        sqltype: 'char', length: 8
        kd               column: 'kd',             sqltype: 'char', length: 4
        company          column: 'company',        sqltype: 'char', length: 50
        country          column: 'country',        sqltype: 'char', length: 20
        city                                       sqltype: 'char', length: 50
        contactPerson                              sqltype: 'char', length: 50
        contactPhone     column: "contactPhone",   sqltype: 'char', length: 25
        contactEmail     column: "contactEmail",   sqltype: "char", length: 100
        weekStart                                  sqltype: 'char', length: 4
        weekEnd                                    sqltype: 'char', length: 4
        freeText                                   sqltype: 'char', length: 1000
		dateCreated		column: "dateCreated", defaultValue: newDate()
    }
    static constraints = {
//        description unique: true
        quality size: 0..8
        kd size: 0..4
        company size: 0..50
        country size: 0..50
        city size: 0..50
        contactPerson size: 0..50
        contactPhone size: 0..25
        contactEmail size: 0..100
        weekStart size: 0..4
        weekEnd size: 0..4

        contactEmail(email: true)
        contactPhone(phoneNumber: [strict: true])


        thickness()
        width()
        quality()
        kd()
        volumeRequested()
        length()
        weekStart()
        weekEnd()
        company()
        contactEmail()
        country()
        contactPerson()
        contactPhone()
        city()
//		fText(new FreeText())
		status(inList: ["Ny", "Kontrakterad", "Ej Avslut"])
		dateCreated()

    }
}
