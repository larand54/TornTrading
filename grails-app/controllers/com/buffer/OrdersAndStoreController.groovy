package com.buffer

import com.torntrading.portal.UserSettings
import com.torntrading.security.Role
import com.torntrading.security.User
import com.torntrading.security.UserRole
import com.torntrading.legacy.Supplier
import com.torntrading.portal.OfferDetail
import com.torntrading.portal.OfferHeader
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_SALES', 'ROLE_SUPPLIER'])
class OrdersAndStoreController {
    def springSecurityService
    def list(Integer max) { 
        System.out.println("Controller List <<<<<<<<")
        def orders = Orders.list()
        def offerDetails = OfferDetail.list()

//        System.out.println("Offerdetails count: "+offerDetails.size)
        //        def prodList = ProdBuffer.list()
        //        respond ProdBuffer.list(params), model:[prodListCount: ProdBuffer.count()]
        //         respond Orders.list(params), model:[ordersCount: Orders.count()]

        def List<String> millList = getMills()
        [orders: orders, prodBuffer: getBufferList(), offerDetails: offerDetails, millList: millList]
        /*        params.max = Math.min(max ?: 10, 100)
        if (params.paginate == 'prodBuffer') {
        def prodBufferPagination = [max: params.max, offset: params.offset]
        session.prodBufferPagination = prodBufferPagination
        } else if (params.paginate == 'orders') {
        def ordersPagination = [max: params.max, offset: params.offset]
        session.ordersPagination = ordersPagination
        }
        def ordersList = Orders.list(session.ordersPagination ?: [max: 10, offset: 0])
        def prodBufferList = ProdBuffer.list(session.prodBufferPagination ?: [max: 10, offset: 0])
        //This is to stop the paginate using params.offset/max to calculate current step and use the offset/max attributes instead    
        params.offset = null
        params.max = null
        [prodBufferList: prodBufferList, totalProds: ProdBuffer.count(), totalOrders: Orders.count(), ordersList: ordersList]
         */   }
           
    def availableProducts() {
        System.out.println(params)
        def List<ProdBuffer> prodBuffer = getBufferList()
        render(template:"AvailableProductData", model:[prodBuffer: prodBuffer])
    }
    def listOffers(){
        System.out.println("#--#"+params)
        params.id=1
        def List<OfferDetail> offerDetails = getOfferList()
        render(template:"ListOffers", model:[offerDetails: offerDetails])
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
        ProdBuffer.executeQuery("SELECT DISTINCT sawMill FROM ProdBuffer")
    }
    
    def List<OfferDetail> getOfferList() {
        System.out.println(" getOfferList>>>" + params)
        def User user
        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        def mill = (us != null) ? us.supplierName :''
        def roles = springSecurityService.getPrincipal().getAuthorities()
        def ol = OfferDetail.list()//OfferDetail.findAll(it.millOfferId==params.id)
        
        return ol.findAll({it.millOfferID==params.id})
    }
    def List<ProdBuffer> getBufferList() {
        def User user
        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        def mill = (us != null) ? us.supplierName :''
        def roles = springSecurityService.getPrincipal().getAuthorities()
        def prodBuffer = ProdBuffer.list()
        def List<ProdBuffer> myList
        def List<ProdBuffer> tempList
        System.out.println(" GetBufferList>>>" + params)
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
                myList = tempList.findAll{mill == it.sawMill}
                break
            }
        }
        myList.each(){
            it.initiateVolumes()
            it.fillWeekList()
        }
        return myList
    }
    
        
    
    def createOffer(){
        int count=0
        def ofh = createOfferHeader()
        if (ofh != null) {
            
            for( n in params.list('toOffer')) {
                if (n.isInteger()) {
                    int value = n as Integer
                    createOfferDetail(ofh, value)
                    count = count +1
                }
            }
            def user = springSecurityService.isLoggedIn() ?
            springSecurityService.loadCurrentUser() :
            null
            flash.message = "${count} " +  "${message(code:'offerRequested.label')}" + "User Id: ${user.id}" 
            redirect action:"list", method:"GET"
        } else {
            flash.message = "Could not create offer due to systemerror" 
            redirect action:"list", method:"GET"
            
        }
    }
    
    def handleCertprices(ProdBuffer pb, OfferDetail ofd) {
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
        
        if (count == 1){
            ofd.endPrice = price
            ofd.choosedCert = cert
        }
    }
    
    def createOfferDetail(OfferHeader ofh, int id) {
        def ProdBuffer pb
        pb = ProdBuffer.get(id)
        
        def OfferDetail ofd
        ofd = new OfferDetail(offerHeader: ofh)
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
        
        if (count == 1){
            ofd.endPrice = price
            ofd.choosedCert = cert
        }
        ofd.product = pb.product
        ofd.volumeOffered = pb.volumeOffered
        ofd.weekEnd = pb.weekEnd
        ofd.weekStart = pb.weekStart
        ofd.offerType='o'
        ofd.millOfferID = id
        ofd.save(failOnError: true)
        return ofd
    }
    
    def OfferHeader createOfferHeader() {
        // Check all is same sawmill and grab sawmill name and id
        def ProdBuffer pb
        def String mill = null
        def String nextMill=null
        def int error = 0
        for( n in params.list('toOffer')) {
            if (n.isInteger()) {
                int id = n as Integer
                int clientNo
                pb = ProdBuffer.get(id)
                nextMill = pb.sawMill
                System.out.println("Verk: " + nextMill)
                if (( mill != null) && (nextMill != mill)) {
                    flash.message='Mixed sawmills!'
                    error = id
                    exit
                }
                mill = nextMill
                
                //createOfferFromBuffer(value)
            }
        }
        System.out.println("mill: " + mill)
        def Supplier supplier = Supplier.findBySearchName(mill)//(URLEncoder.encode(mill, "UTF-8"))
        def int clientNo = supplier.clientNo
        System.out.println(">>> SawMill: "+ supplier.searchName+" ClientNo: "+ supplier.clientNo)
        def OfferHeader ofh = new OfferHeader(sawMill: mill, termsOfDelivery: 'Fritt kunden', volumeUnit: pb.volumeUnit, currency: pb.currency).save(failOnError: true)
        return ofh
    }
}
