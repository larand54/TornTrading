package com.torntrading.portal

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.torntrading.portal.OfferDetail

@Secured(['ROLE_ADMIN','ROLE_SALES'])
@Transactional(readOnly = true)
class OfferHeaderController {
    def offerHeaderService
    def prodBufferService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def offerHeader = OfferHeader.createCriteria().list( params ) { eq ( "offerType", "o" )}
        respond offerHeader, model:[offerHeaderCount: offerHeader.totalCount]
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
        println("%%%% Params: "+params)
        render(view:"edit", model:[offerHeader:offerHeader])
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
        respond offerHeader
    }

    @Transactional
    def update(OfferHeader offerHeader) {
        if (offerHeader == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (offerHeader.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond offerHeader.errors, view:'edit'
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
            respond offerHeader.errors, view:'edit'
            return            
        }

        if (params.status == 'Active' && oldStatus != 'New') {
            transactionStatus.setRollbackOnly()
            flash.message = 'You can not back status to active!'
            respond offerHeader.errors, view:'edit'
            return            
        }

        if (params.status == 'New') {
            transactionStatus.setRollbackOnly()
            flash.message = 'You can not back status to New!'
            respond offerHeader.errors, view:'edit'
            return            
        }

        if ((oldStatus == 'Sold' || oldStatus == 'Rejected') && params.status != oldStatus) {
            transactionStatus.setRollbackOnly()
            flash.message = 'You can not change from this status!'
            respond offerHeader.errors, view:'edit'
            return            
        }

        if (params.status == 'Active' && oldStatus != 'Active' && !volumeOk) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Volume is 0 or more than available - status can not be set active!'
            respond offerHeader.errors, view:'edit'
            return            
        } else if (params.status == 'Active' && oldStatus != 'Active') {
          offerHeaderService.addOfferVolume(offerHeader)
        }

        if (params.status == 'Sold' && oldStatus == 'Active' && volumeOk) {
          offerHeaderService.soldOfferVolume(offerHeader)  
        } else if (params.status == 'Sold') {
            transactionStatus.setRollbackOnly()
            flash.message = 'Volume is 0 or status not Active - status can not be set Sold!'
            respond offerHeader.errors, view:'edit'
            return            
        }

        if (params.status == 'Rejected' && oldStatus == 'Active') {
          offerHeaderService.rejectOfferVolume(offerHeader)  
        } else if (params.status == 'Rejected') {
            transactionStatus.setRollbackOnly()
            flash.message = 'Status not Active - status can not be set Rejected!'
            respond offerHeader.errors, view:'edit'
            return            
        }
        offerHeader.save flush:true

        request.withFormat {
            form multipartForm {
                System.out.println("withFormat")
                flash.message = message(code: 'default.updated.message', args: [message(code: 'offerHeader.label', default: 'OfferHeader'), offerHeader.id])
                redirect controller: "offerHeader", action: "edit", id: "${offerHeader.id}"
                //redirect offerHeader
            }
//               redirect controller: "offerHeader", action: "edit", id: "${offerHeader.id}"

            //            '*'{ respond offerHeader, [status: OK] }
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
        println "Report params: "+params
        def OfferHeader offerHeader = OfferHeader.get(params.id)
        println(">>> Offerheader: "+offerHeader.sawMill)
//        render(template: "/offerHeader/OfferReport", model: [offerHeader: offerHeader])
        renderPdf(template: "/offerHeader/OfferReport", model: [offerHeader: offerHeader],   filename: "offertrapport-"+params.id+".pdf")
//        notFound()
    }
}
