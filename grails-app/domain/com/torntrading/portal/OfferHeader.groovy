package com.torntrading.portal
import com.torntrading.legacy.Customer
import com.torntrading.security.User

class OfferHeader {
    def springSecurityService
    String              termsOfDelivery
    String              termsOfPayment = '30 Days netto after delivery'
    String              weekOfDelivery
    String              info
    String              company
    String              country
    String              city
    String              contactPerson
    String              contactPhone
    String              contactEmail
    String		sawMill
    String		currency
    String		volumeUnit
    String 		status
    String              offerType
    String              species         // Används av stocknota
    BigDecimal          freight  = 0.0  // Fraktkostnad
    Integer             agentFee  = 3      //%
    Date		dateCreated
    int                 createdBy 
    Date                validUntil
    String              creatorsName
//    String              getCreatorsName() {User.get(createdBy).username}
    static transients = ['prod1','prod2','prod3','prod4','mixedWood']
    
    public String getProd1() { 
        (offerDetails[0]!=null)? offerDetails[0].dimension : ''
    }
    public String getProd2() { 
        (offerDetails[1]!=null)? offerDetails[1].dimension : ''
    }
    public String getProd3() { 
        (offerDetails[2]!=null)? offerDetails[2].dimension : ''
    }
    public String getProd4() { 
        (offerDetails[3]!=null)? offerDetails[3].dimension : ''
    }
    
    public String getMixedWood() {
        def List<String> woods = new ArrayList<String>() 
        offerDetails.each {
            woods.add(it.species) 
        }
        def String result
        woods = woods.unique()
        if (woods.size > 1) {
            result = woods[0] + '/' + woods[1]
        } else {
            result = woods[0]
        }
        return result
    }


    static hasMany =[offerDetails: OfferDetail]
    def beforeInsert() {
        createdBy = getUserID()
        creatorsName = User.get(createdBy).username
        status = 'New'
        if (offerType == null) {offerType = 'o'}
        termsOfDelivery = 'CIP'
    }
    static mapping	= {
        offerDetails cascade: "all-delete-orphan"
        offerDetails sort: "sawMill"
	sawMill 	column: "sawMill",         sqltype:	"char", length: 80
	currency 	column: "currency",        sqltype:	"char", length: 3
	volumeUnit	column: "volumeUnit",      sqltype:	"char", length: 6
	status		column: "status",          sqltype:	"char", length: 11
	offerType	column: "offerType",       sqltype:	"char", length: 1
	dateCreated	column: "dateCreated",     defaultValue: newDate()
        company          column: 'company',        sqltype: 'char', length: 100
        country          column: 'country',        sqltype: 'char', length: 20
        city                                       sqltype: 'char', length: 50
        contactPerson                              sqltype: 'char', length: 50
        contactPhone     column: "contactPhone",   sqltype: 'char', length: 25
        contactEmail     column: "contactEmail",   sqltype: "char", length: 100
        info             column: "info",           sqltype: "char", length: 500
        agentFee         column: "agentFee",       sqltype: "int"
    }
    static constraints = {
		sawMill		size: 0..80
		currency	size: 0..3
		volumeUnit	size: 0..6
                company size: 0..100
                country size: 0..50
                city size: 0..50
                contactPerson size: 0..50
                contactPhone size: 0..25
                contactEmail size: 0..100
		sawMill()
		currency()
		volumeUnit()
		company()
                contactEmail()
                country()
                contactPerson()
                contactPhone()
                city()
		termsOfPayment		size: 0..40
                weekOfDelivery          size: 0..12
                status(inList: ["New", "Active", "Sold", "Rejected"])
		dateCreated()
                termsOfDelivery(inList: ['CIP', 'DAP', 'CPT', 'EXW'])

                sawMill         nullable:true
                info            nullable:true
                termsOfDelivery nullable:true
                termsOfPayment  nullable:true
                weekOfDelivery  nullable:true
                contactPhone    nullable:true
                contactEmail    nullable:true
                contactPerson   nullable:true
                city            nullable:true
                country         nullable:true
                company         nullable:true
                currency        nullable:true
                volumeUnit      nullable:true
                status          nullable:true
                offerDetails    nullable:true
                offerType       nullable:true
                freight         nullable:true
                species         nullable:true
                validUntil      nullable:true
                creatorsName    nullable:true
    }
    def beforeUpdate() {
        if(company != null && company != '') {
            def customer = Customer.findByName(this.company)
            if (customer != null) {
                this.country = customer.countryName
                this.city = customer.cityName
            }
        }
    }
    
    def int getUserID() {
        def user = springSecurityService.isLoggedIn() ?
            springSecurityService.loadCurrentUser() :
            null
        return user ? user.id: -1 //securityService.currentUser.id//
    }
}