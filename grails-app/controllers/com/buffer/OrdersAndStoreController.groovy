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
import com.torntrading.portal.SqlService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.transaction.Transactional

    class OfferHeaderResult {
        int status
        String msg
        OfferHeader ofh
    }
 
@Secured(['ROLE_ADMIN','ROLE_SALES', 'ROLE_SUPPLIER'])
class OrdersAndStoreController {
    def springSecurityService
    def prodBufferService
    def sqlService
    
    def changedSelection() {
        def mill = params.sawMill?params.sawMill:''
        session.setAttribute('sawMill', mill)
        render 'OK'
    }

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
        if (!userOk()) {
            flash.error = "User have no sawmill defined!" 

            redirect(uri:'/')
        }
        println("OrdersAndStoreController - list - params"+params)
        params.max = Math.min(max ?: 24, 100)
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
        def List<ProdBuffer> prodBuffer = getBufferList()
        // Paging def prodBuffer = getPaginatedList(prodBuffer, max, params.offset?.toInteger())
        def filters = [sawMill: session.getAttribute('sawMill'), sort: params.sort, order: params.order]
        println("Filters: "+filters)
        println("ProdBuffer: "+prodBuffer)
        def model = [prodBuffer: prodBuffer, offerDetails: offerDetails, millList: millList, selectedMill:true, prodBufferCount: ProdBuffer.count(), filters:filters]
        if (request.xhr) {
            println("AJAX-Request!!!")
            render(template:"Grid_Products", model:model)
//            render(template:"Grid_Products", model:[prodBuffer: prodBuffer, offerDetails:offerDetails, filters:filters])
        } else {

 //           respond prodBuffer, model: [prodBuffer: prodBuffer, offerDetails: offerDetails, millList: millList, selectedMill:false, prodBufferCount: ProdBuffer.count()]
            [prodBuffer: prodBuffer, offerDetails: offerDetails, millList: millList, selectedMill:false, prodBufferCount: ProdBuffer.count(), filters:filters]
        }
    }
    
    def getPaginatedList(list, max, offset) {
        max = Math.min(max ?: 24, 100)
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
        if (request.xhr) {
            println("-----AJAX-Request!!!")
        }
        System.out.println("OrdersAndStoreController - availableProducts - params"+params)
        def offerDetails = null 
        def List<ProdBuffer> prodBuffer = getBufferList()
        //        render(model:[prodBuffer: prodBuffer, offerDetails:offerDetails])
        if (params.sort != null) {
            redirect controller: "ordersAndStore", action: "list" , model:[prodBuffer: prodBuffer, selectedMill:true]
        } else {
            render(template:"AvailableProductData", model:[prodBuffer: prodBuffer, offerDetails:offerDetails])
        }
    }
    def listOffers(){
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
        ProdBuffer.executeQuery("SELECT DISTINCT sawMill FROM ProdBuffer WHERE sawMill is not NULL")
    }
    def List<String> getActiveAvailableMills() {
        ProdBuffer.executeQuery("SELECT DISTINCT sawMill FROM ProdBuffer PB WHERE PB.status='Active' AND PB.volumeAvailable > 0.1 ORDER BY PB.sawMill" )
    }
    
    def List<OfferDetail> getOfferList() {
        def int id = params.id.toInteger()
        return OfferDetail.createCriteria().list( params ) {eq ( "millOfferID", id ) && eq ( "offerType", 'o' )}
        //        def ol = OfferDetail.list()
        //        return ol.findAll({it.millOfferID==id})
    }
    def List<ProdBuffer> getBufferList() {
        def User user
        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        def roles = springSecurityService.getPrincipal().getAuthorities()
        
//        def mill = params.sawMill?params.sawMill:''
//        println("MILL From params: "+mill)
        def mill
        for(def role in roles){ if(role.getAuthority() == "ROLE_ADMIN") {
            }else if(role.getAuthority() == "ROLE_SALES") {
            }else if(role.getAuthority() == "ROLE_SUPPLIER") {
                mill = (us != null) ? us.supplierName :''
            }
        }
        println("MILL: "+mill)
        def c = ProdBuffer.createCriteria()
        def tempList = c.list {
            if (mill) eq("sawMill", mill) and {eq ( "status", 'Active' )}
//            maxResults(10)
            if (params.sort){
                order(params.sort, params.order)
            }
        }
        return tempList
    }
    
    def List<ProdBuffer> getProductsFromMill(String aMill) {
        return prodBuffer.findAll({it.sawMill==aMill})  
    }
    
    def createStocknotes() {
        def List<String> sawMills = getActiveAvailableMills()
        [sawMills:sawMills]
    }
    

    boolean isCollectionOrArray(object) {    
        [Collection, Object[]].any { it.isAssignableFrom(object.getClass()) }
    }
    def int createTheStockNotes(String wood, String mill) {
        int count = 0
        def List<ProdBuffer> products = ProdBuffer.createCriteria().list( params ) {eq ( "sawMill", "${mill}" ) && eq ( "species", "${wood}" ) &&  eq ("status", "Active")}//findAll(status=='Active',species==params('species'))
        println("OrdersAndStoreController - createTheStockNotes - mill: "+mill+" wood: "+wood+" products: "+products)
        if (!products.empty) {
            def OfferHeader ofh = new OfferHeader(termsOfDelivery: 'CIP', volumeUnit: 'AM3', currency: 'SEK', sawMill:mill).save(failOnError: true)
            ofh.offerType='s'
            ofh.species=wood
            for (pb in products) {
                if (pb.sawMill==mill && pb.volumeAvailable > 0.1) {
                    createOfferDetail(ofh, pb.id, ofh.offerType)
                    count = count +1
                }
            }
        }
        return count
    }
    @Transactional
    def createStocknote() {
        println("YYYY Params: "+params)
        if (params.sawMill != null) {
            int count
            int total=0
            int noOfSelectedSawMills = 0
            ArrayList<String> species = params.list("species")
            if (isCollectionOrArray(params.sawMill)) {
                //            println("OrdersAndStoreController - createStockNote - IsCollectionOrArray - params.sawMill: "+params.sawMill)
                noOfSelectedSawMills = params.sawMill.length 
                for (sm in params.sawMill){
                    int mc=0
                    for (mill in params.Mills) {
                        if (sm==mill) {
                            String wood = species[mc]
                            total = total + createTheStockNotes(wood, mill)                            
                        }
                        mc++
                    }
                }
            } else {
                //            println("OrdersAndStoreController - createStockNote - IsNOT!!!!CollectionOrArray - params.sawMill: "+params.sawMill+" params.Mills: "+params.Mills)
                int mc=0
                noOfSelectedSawMills = 1
                for (mill in params.Mills) {
                    if (params.sawMill == mill) {
                        String wood = species[mc]
                        println(">>>>>>>>>>> MC: "+mc+" Species[MC]: "+ species[mc])
                        total = total + createTheStockNotes(wood, params.sawMill)
                    }
                    mc++
                }
            }
            if (total <= 0) {
                transactionStatus.setRollbackOnly()
                flash.message = "No product found within criteria so no Stocknote created" 
                println("OrderAndStore, Create Stocknote, No product found!")
            } else if (total < noOfSelectedSawMills) {
                flash.message = "Just ${total} of ${noOfSelectedSawMills} selected stocknotes was created!"
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
        println(">>> CreateOffer - Params:"+params)
        int count=0
        def prodList = params.list('id[]' )
        def OfferHeaderResult ofhr = createOfferHeader()
        if (ofhr.status != 0) {
            return render(status: 400, text:ofhr.msg)
        }
        def ofh = ofhr.ofh
        if (ofh != null) {
            for( n in prodList) {
                if (n.isInteger()) {
                    int value = n as Integer
                    createOfferDetail(ofh, value, offerType)
                    count = count +1
                }
            }
            def user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null
            flash.message = "${count} " +  "${message(code:'offerRequested.label')}" + "User Id: ${user.id}" 
            render ofh.id
            //            redirect(controller:"offerHeader", action: 'edit', id: ofh.id)
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
    
    def OfferHeaderResult createOfferHeader() {
        // Check all is same sawmill and grab sawmill name and id
        def OfferHeaderResult result = new OfferHeaderResult(status: -99, ofh: null)
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
        int count=0
        def prodList = params.list('id[]')  
        for( n in prodList) {
            if (n.isInteger()) {
                int id = n as Integer
                pb = ProdBuffer.get(id)
                
                nextMill = pb.sawMill
                nextSpecies = pb.species
                nextCurrency = pb.currency

                
                if (( mill != null) && (nextMill != mill)) {
                    result.msg = 'Could not create offer due to Mixed sawmills!'
                    result.status = -2
                    return result 
                }
                
                if (( mill != null) && (nextCurrency != currency)) {
                    result.msg = 'Could not create offer due to Mixed currency!'
                    error = id
                    result.status = -3
                    return result 
                }
                
                
                species = nextSpecies
                mill = nextMill
                currency = nextCurrency
                if (checkCertPrices(pb) == 0) {
                    result.msg = "No prices are set! Can not create offer!" 
                    result.status = -4
                    return result 
                }
                     
                //createOfferFromBuffer(value)
            }
        }
        def Supplier supplier = Supplier.findBySearchName(mill)
        def int clientNo = supplier.clientNo
        def OfferHeader ofh = new OfferHeader(sawMill: mill, species: pb.species, termsOfDelivery: 'CIP', volumeUnit: pb.volumeUnit, currency: pb.currency).save(failOnError: true)
        result.ofh = ofh
        result.status = 0
        result.msg = 'OfferHeader created ${ofh.id}'
        return result
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
                if (od.offerType == 's') {
                    flash.message= "Deletion abanded due to connected stocknote: ${od.offerHeader.id}"
                } else {
                    flash.message= "Deletion abanded due to connected offer: ${od.offerHeader.id}"
                }
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
    def test () {
        render sqlService.sp()
    }
}
