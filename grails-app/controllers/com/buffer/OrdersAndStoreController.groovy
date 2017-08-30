package com.buffer

import com.torntrading.security.UserSettings
import com.torntrading.portal.WtStatus
import com.torntrading.security.Role
import com.torntrading.security.User
import com.torntrading.security.UserRole
import com.torntrading.legacy.Supplier
import com.torntrading.portal.OfferDetail
import com.torntrading.portal.OfferHeader
import com.torntrading.portal.OfferWeeklyAvailableVolume
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN','ROLE_SALES', 'ROLE_SUPPLIER'])
class OrdersAndStoreController {
    def springSecurityService
    def prodBufferService

    def boolean userOk() {
        def User user
        if(SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN,ROLE_SALES')) { 
            return true
        }    
/*        def roles = springSecurityService.getPrincipal().getAuthorities()
        for(def role in roles){ if((role.getAuthority() == "ROLE_ADMIN") || (role.getAuthority() == "ROLE_SALES")){
                SpringSecurityUtils.ifAnyGranted( ROLE_ADMIN, ROLE_SALES) {     
            }        
*/        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        return us.supplierName != null && us.supplierName != ''
    }
    
    def list(Integer max) { 
            println "Timeout: ${session.getMaxInactiveInterval()} seconds"
        if (!userOk()) {
            flash.error = "User have no sawmill defined!" 

            redirect(uri:'/')
        }
        params.max = Math.min(max ?: 10, 100)
        System.out.println("Controller List <<<<<<<< params: "+params)
        def offerDetails = null//OfferDetail.list()

        //        System.out.println("Offerdetails count: "+offerDetails.size)
        //        def prodList = ProdBuffer.list()
        //        respond ProdBuffer.list(params), model:[prodListCount: ProdBuffer.count()]
        //         respond Orders.list(params), model:[ordersCount: Orders.count()]
        // Update woodtrading status with new week and correct volumelist on weekchange
        int id = 1
        WtStatus wts = WtStatus.get(id)?:new WtStatus(id:1).save(failOnError:true)
        ////        def WtStatus wts = WtStatus.findOrSaveById( id )
        prodBufferService.checkWeekStatus()
//        prodBufferService.updateAvailableVolumes(getBufferList()) // För att testa funktionen
        def List<String> millList = getMills()
        def List<ProdBuffer> pbl = getBufferList()
        def List<ProdBuffer> prodBuffer = pbl
//        def prodBuffer = getPaginatedList(pbl, max, params.offset?.toInteger())
        respond prodBuffer, model: [prodBuffer: prodBuffer, offerDetails: offerDetails, millList: millList, selectedMill:false, prodBufferCount: ProdBuffer.count()]
    }
    
    def getPaginatedList(list, max, offset) {
        max = Math.min(max ?: 10, 100)
        offset = offset?:0
        int total = list.size()
        int upperLimit = offset+max
        if (upperLimit >= total) {
            upperLimit = total-1
        } else {
            upperLimit = offset+max-1
        }
        if (offset < total) {
            return list.getAt(offset..upperLimit)
        } else {
            return []
        }  
    }
    
    def availableProducts() {
        System.out.println(params)
        def offerDetails = null 
        def List<ProdBuffer> prodBuffer = getBufferList()
        render(template:"AvailableProductData", model:[prodBuffer: prodBuffer, offerDetails:offerDetails])
        render(template:"ListOffers", model:[offerDetails: offerDetails])
    }
    def listOffers(){
        System.out.println("#--#"+params)
        /*        if (SpringSecurityUtils.ifAnyGranted( "ROLE_ADMIN, ROLE_SALES, ROLE_SUPPLIER")) {
        System.out.println("Authorized: "+roles)             
        }     
         */
        if (params.sort) redirect controller: "ordersAndStore", action: "list"
        def User user
        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        //        def mill = (us != null) ? us.supplierName :''
        def roles = springSecurityService.getPrincipal().getAuthorities()
        for(def role in roles){ if((role.getAuthority() == "ROLE_ADMIN") || (role.getAuthority() == "ROLE_SALES")){
                //SpringSecurityUtils.ifAnyGranted( ROLE_ADMIN, ROLE_SALES) {     
                def List<OfferDetail> offerDetails = getOfferList()
                System.out.println("OfferDetail filtered count: "+offerDetails.count) 
                render(template:"ListOffers", model:[offerDetails: offerDetails])
            }        
        }
    }

	
    def show_prodbuffer() {
        redirect(controller: "prodBuffer",action: "show", id: params.id)
    }

    def edit_prodbuffer() {
        redirect(controller: "prodBuffer",action: "edit", id: params.id)
    }

    def show_order() {
        redirect(controller: "orders",action: "show", id: params.id)
    }
	
    def add_prodBuffer() {
        redirect(controller: "prodBuffer", action: "create", id: params.id)
    }
    
    def List<String> getMills() {
        System.out.println("getMills <<<<")
        ProdBuffer.executeQuery("SELECT DISTINCT sawMill FROM ProdBuffer WHERE sawMill is not NULL")
    }
    def List<String> getActiveAvailableMills() {
        System.out.println("getActiveAvailableMills <<<<")
        ProdBuffer.executeQuery("SELECT DISTINCT sawMill FROM ProdBuffer PB WHERE PB.status='Active' AND PB.volumeAvailable > 0.1 ORDER BY PB.sawMill" )
    }
    
    def List<OfferDetail> getOfferList() {
        System.out.println(" getOfferList>>>" + params)
        def int id = params.id.toInteger()
        System.out.println(" getOfferList>>>ID: " + id)
        return OfferDetail.createCriteria().list( params ) {eq ( "millOfferID", id ) && eq ( "offerType", 'o' )}
//        def ol = OfferDetail.list()
//        return ol.findAll({it.millOfferID==id})
    }
    def List<ProdBuffer> getBufferList() {
        System.out.println(" GetBufferList>>>" + params)
        def User user
        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        def mill = (us != null) ? us.supplierName :''
        println('OrdersAndStoreController, getBufferList, mill: '+mill)
        println('OrdersAndStoreController, getBufferList, user: '+user.username)
        def roles = springSecurityService.getPrincipal().getAuthorities()
        
        def prodBuffer = ProdBuffer.findAllByStatus('Active', [sort:params.sort, order:params.order])
        println('OrdersAndStoreController, getBufferList, All active products: '+prodBuffer.size+prodBuffer.sawMill)
        def List<ProdBuffer> myList
        def List<ProdBuffer> tempList
        System.out.println(" GetBufferList active count: " + prodBuffer.count)
        if ((params.sawMill != null) && (params.sawMill != '')) {
            tempList = prodBuffer.findAll({it.sawMill==params.sawMill}) 
            System.out.println("Filtered")
        } else {
            tempList = prodBuffer  
            System.out.println("UN-Filtered")
        }
        for(def role in roles){ if(role.getAuthority() == "ROLE_ADMIN") {
                myList = tempList
                break
            }else if(role.getAuthority() == "ROLE_SALES") {
                myList = tempList
                break
            }else if(role.getAuthority() == "ROLE_SUPPLIER") {
                tempList.findAll{println('OrdersAndStoreController, getBufferList(SUPPLIER), it.sawMill: '+it.sawMill)}
                tempList.findAll{println('OrdersAndStoreController, getBufferList(SUPPLIER), found: '+(mill==it.sawMill))}
                myList = tempList.findAll{mill == it.sawMill}
//        println('OrdersAndStoreController, getBufferList(SUPPLIER), it.sawMill: '+it.sawMill)
        println('OrdersAndStoreController, getBufferList(SUPPLIER), mill: '+mill+ '  Found: '+myList.size)
                break
            }
        }
        return myList
    }
    
    def List<ProdBuffer> getProductsFromMill(String aMill) {
        return prodBuffer.findAll({it.sawMill==aMill})  
    }
    
    def createStocknotes() {
        def List<String> sawMills = getActiveAvailableMills()
        println(">>>> CreateStocknotes <<<<"+ sawMills)
        [sawMills:sawMills]
    }
    
    @Transactional
    def createStocknote() {
        println("XXXX CreateStocknotes-- XXXX "+" params.species:"+params.species)
        println("YYYY Params: "+params)
        if (params.sawMill != null) {
            int count
            int total=0
            int mc=0
            for (mill in params.list("sawMill")) {
                def String species = params.list("species")[mc]
                println("Mill: "+mill+" Species: "+species)
        
                def List<ProdBuffer> products = ProdBuffer.createCriteria().list( params ) {eq ( "sawMill", "${mill}" ) && eq ( "species", "${species}" ) &&  eq ("status", "Active")}//findAll(status=='Active',species==params('species'))
                println("Products: "+products)
                println("mill: "+mill)
                def OfferHeader ofh = new OfferHeader(termsOfDelivery: 'CIP', volumeUnit: 'AM3', currency: 'SEK', sawMill:mill).save(failOnError: true)
                ofh.offerType='s'
                ofh.species=species
                count = 0
                for (pb in products) {
                    println("Sawmill: "+pb.sawMill+" mill: "+mill)
                    if (pb.sawMill==mill && pb.volumeAvailable > 0.1) {
                        createOfferDetail(ofh, pb.id, ofh.offerType)
                        count = count +1
                    }
                }
                mc = mc + 1
                total = total + count
            }
            if (total <= 0) {
                transactionStatus.setRollbackOnly()
                flash.message = "No product found within criteria so no Stocknote created" 
                println("OrderAndStore, Create Stocknote, No product found!")
            } else {
                def user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
                flash.message = "${total} " +  "${message(code:'offerRequested.label')}" + "User Id: ${user.id}" 
                println("OrderAndStore, Create Stocknote, OK")
            }
            redirect controller:"stocknote", action:"index"
        } else {
            //flash.message = "Could not create offer due to systemerror" 
            println("OrderAndStore, Create Stocknote, System error!")
            redirect action:"list", method:"GET"
            
        }
    }    
    
    def createOffer(String offerType){
        if(params.toOffer==null) {
            flash.message = "No product selected!" 
            redirect action:"list", method:"GET" 
            return
        }
        int count=0
        def ofh = createOfferHeader()
        if (ofh != null) {
            for( n in params.list('toOffer')) {
                if (n.isInteger()) {
                    int value = n as Integer
                    createOfferDetail(ofh, value, offerType)
                    count = count +1
                }
            }
            def user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
            flash.message = "${count} " +  "${message(code:'offerRequested.label')}" + "User Id: ${user.id}" 
            redirect(controller:"offerHeader", action: 'edit', id: ofh.id)
        } else {
            //flash.message = "Could not create offer due to systemerror" 
            redirect action:"list", method:"GET"
            
        }
    }
    
    def int checkCertPrices(ProdBuffer pb) {
        int count=0
        if (pb.priceFSC?pb.priceFSC:0 > 0) {
            count = count + 1
        }
        if (pb.pricePEFC?pb.pricePEFC:0 > 0) {
            count = count + 1
        }
        if (pb.priceUC?pb.priceUC:0 > 0) {
            count = count + 1
        }
        if (pb.priceCW?pb.priceCW:0 > 0) {
            count = count + 1
        }
        
        return count
    }
    
    def createOfferDetail(OfferHeader ofh, int id, String offerType) {
        def ProdBuffer pb
        pb = ProdBuffer.get(id)
        
        def OfferDetail ofd
        ofd = new OfferDetail(offerHeader: ofh)
        ofd.dateCreated = new Date()             // Varför behövs detta helt plötsligt? Har fungerat sedan start utan
        offerType?ofd.offerType=offerType:null
        
        if (offerType=='s') {
            ofd.volumeOffered = pb.volumeAvailable  
        }
        ofd.species = pb.species
        ofd.grade = pb.grade
        ofd.kd = pb.kd
        ofd.sawMill = pb.sawMill
        ofd.lengthDescr = pb.length
        ofd.priceFSC = pb.priceFSC
        ofd.pricePEFC = pb.pricePEFC
        ofd.priceUC = pb.priceUC
        ofd.priceCW = pb.priceCW
        ofd.endPrice = 0.0
        int count=0
        String cert
        BigDecimal price
        if (pb.priceFSC?pb.priceFSC:0 > 0) {
            count = count + 1
            price = pb.priceFSC
            cert = 'FSC'
        }
        if (pb.pricePEFC?pb.pricePEFC:0 > 0) {
            count = count + 1
            price = pb.pricePEFC
            cert = 'PEFC'
        }
        if (pb.priceUC?pb.priceUC:0 > 0) {
            count = count + 1
            price = pb.priceUC
            cert = 'UC'
        }
        if (pb.priceCW?pb.priceCW:0 > 0) {
            count = count + 1
            price = pb.priceCW
            cert = 'CW'
        }
        
        price = price?price:0.0
        ofd.endPrice = price
        ofd.choosedCert = cert
        ofd.markup = ofd.endPrice * 0.01 * ofh.agentFee
        ofd.dimension = pb.dimension?:''
        ofd.weekStart = pb.weekStart
        ofd.millOfferID = id
        ofd.offerType = offerType
        ofd.save(failOnError: true)
        return ofd
    }
    
    def OfferHeader createOfferHeader() {
        // Check all is same sawmill and grab sawmill name and id
        def ProdBuffer pb
        def String currency = null
        def String nextCurrency = null
        def String mill = null
        def String nextMill=null
        def String species=null
        def String nextSpecies=null
        def Integer millId=null
        def Integer nextMillId=null
        def int error = 0
        for( n in params.list('toOffer')) {
            if (n.isInteger()) {
                int id = n as Integer
                int clientNo
                pb = ProdBuffer.get(id)
                
                nextMill = pb.sawMill
                nextSpecies = pb.species
                nextCurrency = pb.currency
                
                if (( species != null) && (nextSpecies != species)) {
                    flash.message='Could not create offer due to Mixed wood!'
                    error = id
                    return null
                }

                
                System.out.println("Verk: " + nextMill)
                if (( mill != null) && (nextMill != mill)) {
                    flash.message='Could not create offer due to Mixed sawmills!'
                    error = id
                    return null
                }
                
                System.out.println("Currency: " + nextCurrency)
                if (( mill != null) && (nextCurrency != currency)) {
                    flash.message='Could not create offer due to Mixed currency!'
                    error = id
                    return null
                }
                
                
                species = nextSpecies
                mill = nextMill
                currency = nextCurrency
                if (checkCertPrices(pb) == 0) {
                    flash.message = "No prices are set! Can not create offer!" 
                    return null            
                }
                     
                //createOfferFromBuffer(value)
            }
        }
        System.out.println("mill: " + mill)
        def Supplier supplier = Supplier.findBySearchName(mill)
        def int clientNo = supplier.clientNo
        System.out.println(">>> SawMill: "+ supplier.searchName+" ClientNo: "+ supplier.clientNo)
        def OfferHeader ofh = new OfferHeader(sawMill: mill, species: pb.species, termsOfDelivery: 'CIP', volumeUnit: pb.volumeUnit, currency: pb.currency).save(failOnError: true)
        return ofh
    }
    
    def deleteProducts() {
        int count=0
        for( n in params.list('toOffer')) {
            if (n.isInteger()) {
                int value = n as Integer
                deleteProductWithOffers(ProdBuffer.get(params.prodID))
                count = count +1
            }
        }
        redirect action:"list", method:"GET"
    }        
    
    def deleteProduct() {
        deleteProductWithOffers(ProdBuffer.get(params.prodID)) 
        redirect action:"list", method:"GET"
    }    
    
    def deleteProductWithOffers(ProdBuffer aPB) {
        println("Delete product: "+params.prodID) 
        // Check connected offers
        def offers = OfferDetail.findAllByMillOfferID(params.prodID)
        // if any connected offer having status not "new" - abandon this delete
        def statusOk = true
        for (od in offers) {
            def status = od.offerHeader.status
            if (status != 'New') {
                statusOk = false
                flash.message= "Deletion abanded due to connected offers"
                break
            }
        }
        if (statusOk) {
            // delete all connected offers
            // First create a list of offer headers
            def  ohs = []
            for (od in offers) {
                ohs.add(od.offerHeader)
            }
            // remove duplicates
            ohs = ohs.unique()
            // Now, just delete the offerdetails
            for (od in offers) {
                od.delete flush:true
            }
            // delete product
            aPB.delete flush:true
           
            // Now, delete all offerheaders that are empty
            for (oh in ohs) {
                if (oh.offerDetails.isEmpty()) {
                    oh.delete flush: true
                }
            }
        } 
    }
    
    // only for testing
    def testNewWeek() {
        def List<ProdBuffer> pList = ProdBuffer.executeQuery("FROM ProdBuffer PB WHERE PB.status='Active' ORDER BY PB.id" )
        for (p in pList) {
            println("PB: "+p.id+" InStock:"+p.volumeInStock+" -> ")
            for (int i = 0; i<12; i++) print(", "+p.plannedVolumes[i].volume)
            println("")
            prodBufferService.weekAdjustVolumes(p)
            println("PB: "+p.id+" InStock:"+p.volumeInStock+" -> ")
            for (int i = 0; i<12; i++) {
                print(", "+p.plannedVolumes[i].volume)
            }
            println("\n--------------------------------------------------------------")
            prodBufferService.updateAvailableVolumes(p)
        }
        redirect action:"list", method:"GET"
    }
}
