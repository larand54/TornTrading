package com.torntrading.legacy

class Client  {
    String  clientCode
    Integer     sequenceNo
    String  clientName
    Integer     salesRegionNo
    Double  percentCommision
    Integer     termsCommision
    String  url     
    String  vatNo
    String  specialText
    String  searchName
    Integer     marketRegionNo
    String  pktNrLevKod
    String  orgNr
    String  bankgiro
    String  postgiro
    Date    dateCreated
    Date    dateModified
    Integer     createdUser
    Integer     modifiedUser
    String  clientID
    String  pyramidMapp
    Integer     avdelningsNr
    String  partyIdentifier
    String  paymentInstruction_1
    String  paymentInstruction_2
    String  sIBAN
    
    
    def beforeInsert() {
        sIBAN = '111-222-IBAN-001'
        dateModified = new Date()
    }
    
    def beforUpdate() {
        dateModified = new Date()
    }
    
    
    
   static mapping = {
        id name: 'id', sqltype:'int'//, generator:'assigned'//, composite:['clientNo', 'clientID', 'clientName']
        id column: 'ClientNo', sqltype: 'int'
        version false
        sIBAN                   column: "IBAN_Text",                sqltype:   "char", length: 255
//        clientNo                column: "ClientNo"
        clientCode              column: "ClientCode",               sqltype:   "char", length: 3
        sequenceNo              column: "SequenceNo"
        clientName              column: "ClientName",               sqltype:	"char", length: 80
        clientID                column: "ClientID",                 sqltype:	"char", length: 10
        salesRegionNo           column: "SalesRegionNo"
        percentCommision        column: "Percent_Commision"
        termsCommision          column: "Terms_Commision"
        url                     column: "URL",                      sqltype:	"char", length: 100
        vatNo                   column: "VATNo",                    sqltype:	"char", length: 20
        specialText             column: "SpecialText",    type: 'text', sqlType: 'text'
        searchName              column: "SearchName",               sqltype:	"char", length: 80
        marketRegionNo          column: "MarketRegionNo"
        pktNrLevKod             column: "PktNrLevKod",              sqltype:	"char", length: 5
        orgNr                   column: "OrgNr",                    sqltype:	"char", length: 14
        bankgiro                column: "BankGiro",                 sqltype:	"char", length: 12
        postgiro                column: "PostGiro",                 sqltype:	"char", length: 12
        dateCreated             column: 'DateCreated',              sqltype:    'datetime'
        dateModified            column: 'DateModified',             sqltype:    'datetime'
        createdUser             column: "CreatedUser"
        modifiedUser            column: "ModifiedUser"
        pyramidMapp             column: "PyramidMapp",              sqltype:	"char", length: 255
        avdelningsNr            column: "AvdelningsNr"
        partyIdentifier         column: "PartyIdentifier",          sqltype:	"char", length: 50
        paymentInstruction_1    column: "PaymentInstruction_1",  type: 'text', sqlType: 'text'    
        paymentInstruction_2    column: "PaymentInstruction_2",  type: 'text', sqlType: 'text'
    }
   
    static constraints = {
        clientCode              nullable:true
        sequenceNo              nullable:true
        salesRegionNo           nullable:true
        percentCommision        nullable:true
        termsCommision          nullable:true
        url                     nullable:true
        vatNo                   nullable:true
        specialText             nullable:true
        searchName              nullable:true
        marketRegionNo          nullable:true
        pktNrLevKod             nullable:true
        orgNr                   nullable:true
        bankgiro                nullable:true
        postgiro                nullable:true
        dateCreated             nullable:true
        dateModified            nullable:true
        createdUser             nullable:true
        modifiedUser            nullable:true
        pyramidMapp             nullable:true
        avdelningsNr            nullable:true
        partyIdentifier         nullable:true
        paymentInstruction_1    nullable:true
        paymentInstruction_2    nullable:true
//        sIBAN                   nullable:true
    }  
}
