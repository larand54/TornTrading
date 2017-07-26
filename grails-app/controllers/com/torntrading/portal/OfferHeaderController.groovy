package com.torntrading.portal

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import sun.util.calendar.LocalGregorianCalendar.Date

import grails.plugin.springsecurity.annotation.Secured
import com.torntrading.portal.OfferDetail
import com.torntrading.legacy.Customer

@Secured(['ROLE_ADMIN','ROLE_SALES'])
@Transactional(readOnly = true)
class OfferHeaderController {
    def offerHeaderService
    def prodBufferService
    def springSecurityService
    def assetResourceLocator
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def millList = getMills()
        def customerList = getCustomersFromOffers()
        def offerHeader = getOfferList()
        def woodList = getWoods() 
        def statusList = getStatusList()
        for (oh in offerHeader) println("OfferHeaderController - index: "+ oh.id  +" deliveryweeks: " + offerHeaderService.weeksOfDelivery(oh))
        respond offerHeader, model:[offerHeaderCount: offerHeader.totalCount, customerList: customerList, millList: millList, woodList:woodList, statusList:statusList]
    }

    
    def filteredOffers() {
        def offerHeaderList = getOfferList()
        render(template:"index", model: [offerHeaderList:offerHeaderList])
    }
    
    def List<OfferHeader> getOfferList() {
        def offerList = OfferHeader.createCriteria().list( params ) { eq ( "offerType", "o" )}
        if (params.status == null || params.status == '' || params.status == 'All') {
        } else{
            offerList = offerList.findAll({it.status==params.status})
        }
        if (params.customer != null && params.customer !='') {
            offerList = offerList.findAll({it.company == params.customer})
        }
        if (params.sawMill != null && params.sawMill !='') {
            offerList = offerList.findAll({it.sawMill == params.sawMill})
        }
        if (params.wood != null && params.wood !='') {
            offerList = offerList.findAll({it.species == params.wood})
        }  
        return offerList
    }
    
    def List<String> getMills() {
        OfferHeader.executeQuery("SELECT DISTINCT sawMill FROM OfferHeader WHERE sawMill is not NULL")
    }

    def List<String> getCustomersFromOffers() {
        OfferHeader.executeQuery("SELECT DISTINCT company FROM OfferHeader WHERE company is not NULL OR company <> ''")
    }
    
    def getCustomers() {
       Customer.executeQuery("SELECT DISTINCT name FROM Customer WHERE name is not NULL AND name <> ''") 
    }

    def List<String> getWoods() {
       OfferHeader.executeQuery("SELECT DISTINCT species FROM OfferHeader WHERE species is not NULL AND species <> ''") 
    }
    
    def List<String> getStatusList() {
        ["New", "Active", "Sold", "Rejected"]
    }
    
    def show(OfferHeader offerHeader) {
        respond offerHeader
    }

    def create() {
        respond new OfferHeader(params)
    }
    
    def addRow() {
        forward(controller:"offerDetail", action:"create")
    }

    def renderEdit() {
        println("%%%% OfferHeaderController - renderEdit - Params: "+params)
        def customers = getCustomers()
        render(view:"edit", model:[offerHeader:offerHeader, customers:customers])
    }
    @Transactional
    def save(OfferHeader offerHeader) {
        if (offerHeader == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (offerHeader.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond offerHeader.errors, view:'create'
            return
        }

        offerHeader.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'offerHeader.label', default: 'OfferHeader'), offerHeader.id])
                redirect offerHeader
            }
            '*' { respond offerHeader, [status: CREATED] }
        }
    }

    def edit(OfferHeader offerHeader) {
        def customers = getCustomers()

        respond offerHeader, model: [customers:customers]
    }

    @Transactional
    def update(OfferHeader offerHeader) {
        println("offerHeaderController - update, params: "+params)
        if (offerHeader == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (offerHeader.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return
        }
        println("OfferHeader: status: "+offerHeader.status)
        
        def String oldStatus = offerHeader.getPersistentValue('status')
        println("OfferHeader: old status: "+oldStatus)
        def Boolean volumeOk = offerHeaderService.allOfferDetailsVolumeOK(offerHeader)
        println("OfferHeader: volumeok: "+volumeOk)
        
        if ((oldStatus != 'Active') && (oldStatus != 'New')) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Not Active or New! Can not be modified!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return            
        }

        if (params.status == 'Active' && (oldStatus != 'New') && (oldStatus != 'Active')) {
            transactionStatus.setRollbackOnly()
            flash.message = 'You can not back status to active!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return            
        }

        if (params.status == 'New' && oldStatus != 'New') {
            transactionStatus.setRollbackOnly()
            flash.message = 'You can not back status to New!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return            
        }

        if ((oldStatus == 'Sold' || oldStatus == 'Rejected') && params.status != oldStatus) {
            transactionStatus.setRollbackOnly()
            flash.message = 'You can not change from this status!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return            
        }

        if (params.status == 'Active' && oldStatus == 'New' && !volumeOk) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Volume is 0 or more than available - status can not be set active!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return            
        } else if (params.status == 'Active' && oldStatus == 'New') {
            offerHeaderService.addOfferVolume(offerHeader)
            offerHeaderService.setEndPrices(offerHeader)
        }

        if (params.status == 'New' && oldStatus == 'New') {
            offerHeaderService.setEndPrices(offerHeader)
        }

        if (params.status == 'Sold' && oldStatus == 'Active' && offerHeaderService.volumeOkToSell(offerHeader)) {
          offerHeaderService.soldOfferVolume(offerHeader)  
        } else if (params.status == 'Sold') {
            transactionStatus.setRollbackOnly()
            flash.message = 'Volume is 0 or status not Active - status can not be set Sold!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return            
        }

        if (params.status == 'Rejected' && oldStatus in ['Active', 'New']) {
          offerHeaderService.rejectOfferVolume(offerHeader)  
        } else if (params.status == 'Rejected') {
            transactionStatus.setRollbackOnly()
            flash.message = 'Status not Active - status can not be set Rejected!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return            
        }
        println("OfferHeaderController - date: "+params.offerValidDate)
        offerHeader.validUntil = params.date('offerValidDate', 'yyyy-MM-dd')
        offerHeader.info = params.info
        offerHeader.save flush:true

        request.withFormat {
            form multipartForm {
                System.out.println("withFormat")
                flash.message = message(code: 'default.updated.message', args: [message(code: 'offerHeader.label', default: 'OfferHeader'), offerHeader.id])
                redirect controller: "offerHeader", action: "edit", id: "${offerHeader.id}"
            }
        }
    }

    @Transactional
    def delete(OfferHeader offerHeader) {

        if (offerHeader == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        offerHeader.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'offerHeader.label', default: 'OfferHeader'), offerHeader.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'offerHeader.label', default: 'OfferHeader'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def report() {
        def file = assetResourceLocator?.findAssetForURI( 'TornTrading-DalaTrading.png' )?.getInputStream()?.bytes 
        def OfferHeader offerHeader = OfferHeader.get(params.id)
        if (offerHeader.status != 'Active') {
            flash.message = 'Status not Active!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return
        }
        
        if (offerHeaderService.useWeeklyVolumes(offerHeader)) {
            offerHeader.weekOfDelivery = offerHeaderService.weeksOfDelivery(offerHeader)
        } else {
            if (offerHeader.weekOfDelivery == null) {
                flash.message = 'Week of delivery not entered!'
                respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
                return
            }

//            if (!offerHeader.weekOfDelivery.startsWith('Week')) {
//                offerHeader.weekOfDelivery = 'Week ' + offerHeader.weekOfDelivery
//            }
        }
        
        def currentUser = springSecurityService.currentUser
        def us = currentUser.userSettings
        if (offerHeader.freight == null) {
            flash.message = 'Shipment not entered!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return
        }
        println(">>> Offerheader: "+offerHeader.sawMill)
//        render(template: "/offerHeader/OfferReport", model: [offerHeader: offerHeader])
//        renderPdf(template: "/offerHeader/OfferReport", model: [offerHeader: offerHeader, us:us],   filename: "offertrapport-"+params.id+".pdf")
        renderPdf(template: "/offerHeader/OfferReport", model: [offerHeader: offerHeader, us:us,imageBytes: file],   filename: "offertrapport-"+params.id+".pdf")
//        notFound()
    }

    def reportpolish() {
        def file = assetResourceLocator?.findAssetForURI( 'TornTrading-DalaTrading.png' )?.getInputStream()?.bytes 
        def OfferHeader offerHeader = OfferHeader.get(params.id)
        if (offerHeader.status != 'Active') {
            flash.message = 'Status not Active!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return
        }
        if (offerHeaderService.useWeeklyVolumes(offerHeader)) {
            offerHeader.weekOfDelivery = offerHeaderService.weeksOfDelivery(offerHeader)
        } else {
            if (offerHeader.weekOfDelivery == null) {
                flash.message = 'Week of delivery not entered!'
                respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
                return
            }

//            if (!offerHeader.weekOfDelivery.startsWith('Week')) {
//                offerHeader.weekOfDelivery = 'Week ' + offerHeader.weekOfDelivery
//            }
        }
        //offerHeader.weekOfDelivery = offerHeader.weekOfDelivery.replace("Week", "Tydzien")
        def currentUser = springSecurityService.currentUser
        def us = currentUser.userSettings
        if (offerHeader.freight == null) {
            flash.message = 'Shipment not entered!'
            respond offerHeader.errors, view:'edit', model: [customers:getCustomers()]
            return
        }
        println(">>> Offerheader: "+offerHeader.sawMill)
//        render(template: "/offerHeader/OfferReport", model: [offerHeader: offerHeader])
        renderPdf(template: "/offerHeader/OfferReport_polish", model: [offerHeader: offerHeader, us:us,imageBytes: file],   filename: "offert_polish-"+params.id+".pdf")
//        notFound()
    }
}
