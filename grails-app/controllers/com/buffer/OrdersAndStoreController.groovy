package com.buffer

import com.torntrading.portal.UserSettings
import com.torntrading.security.Role
import com.torntrading.security.User
import com.torntrading.security.UserRole
import com.torntrading.legacy.Supplier
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_USER','ROLE_SALES', 'ROLE_SUPPLIER'])
class OrdersAndStoreController {
    def springSecurityService
    def list(Integer max) { 
        System.out.println("Controller List <<<<<<<<")
        def orders = Orders.list()
//        def prodList = ProdBuffer.list()
//        respond ProdBuffer.list(params), model:[prodListCount: ProdBuffer.count()]
//         respond Orders.list(params), model:[ordersCount: Orders.count()]

        def List<String> millList = getMills()
        [orders: orders, prodBuffer: getBufferList(), millList: millList]
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
        createOfferHeader()
        //        render "Antal valda: ${params.toOffer.size()}"
        for( n in params.list('toOffer')) {
            //            render "${n}-"
            //            def offer = tTBufferService.createOfferFromBuffer(n)
            if (n.isInteger()) {
                int value = n as Integer
                createOfferFromBuffer(value)
            }
        }
        def user = springSecurityService.isLoggedIn() ?
        springSecurityService.loadCurrentUser() :
        null
        flash.message = "${params.toOffer?.size()} " +  "${message(code:'offerRequested.label')}" + "Användarid: ${user.id}" 
        redirect action:"list", method:"GET"
    }
    
    def createOfferFromBuffer(int id) {
        def ProdBuffer pb
        pb = ProdBuffer.get(id)
        
        def Offer of
        of = new Offer()
        of.sawMill = pb.sawMill
        //            of.company = '----'
        of.lengthDescr = pb.length
        of.price = pb.price
        of.product = pb.product
        of.volumeOffered = pb.volumeRest
        of.volumeUnit = pb.volumeUnit
        of.weekEnd = pb.weekEnd
        of.weekStart = pb.weekStart
        of.termsOfDelivery = 'Fritt kunden'
        //            of.kd = 'xxxx'
        //            of.grade = 'xxxx'
        //            of.status = 'Preliminary'
        of.millOfferID = id
        of.save(failOnError: true)
        return of
    }
    
    def createOfferHeader() {
      // Check all is same sawmill and grab sawmill name and id
        def ProdBuffer pb
        def String mill = null
        def int error = 0
        for( n in params.list('toOffer')) {
            if (n.isInteger()) {
                int id = n as Integer
                int clientNo
                pb = ProdBuffer.get(id)
                nextMill = pb.sawMill
                if (nextMill != mill) {
                    flash.message='Mixed sawmills!'
                    error = id
                    break
                }
                
                createOfferFromBuffer(value)
            }
        }
                supplier = Supplier.getBySearchName(mill)
                clientNo = supplier.clientNo
      
    }
}
