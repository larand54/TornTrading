package com.torntrading.portal

import static org.springframework.http.HttpStatus.*
import com.buffer.ProdBuffer
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_SALES'])
@Transactional(readOnly = true)
class OfferDetailController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond OfferDetail.list(params), model:[offerDetailCount: OfferDetail.count()]
    }

    def show(OfferDetail offerDetail) {
        respond offerDetail
    }

    def create() {
        System.out.println(params)
        respond new OfferDetail(params)
    }

    @Transactional
    def save(OfferDetail offerDetail) {
        if (offerDetail == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (offerDetail.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond offerDetail.errors, view:'create'
            return
        }

        offerDetail.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'offerDetail.label', default: 'OfferDetail'), offerDetail.id])
                redirect offerDetail
            }
            '*' { respond offerDetail, [status: CREATED] }
        }
    }

    def edit(OfferDetail offerDetail) {
        respond offerDetail
    }

    @Transactional
    def update(OfferDetail offerDetail) {
        if (offerDetail == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (offerDetail.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond offerDetail.errors, view:'edit'
            return
        }
        String status =  offerDetail.offerHeader.status 
        if ((status == 'Sold') || (status == 'Rejected')) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Offer can not be changed (Sold/Rejected)'
            return
        }
        
        offerDetail.choosedCert = params.availableCert
        offerDetail.save flush:true

        if ((offerDetail.volumeOffered > 0.0001) && (offerDetail.oldVolume > 0.0001)) {
            double diff = offerDetail.volumeOffered - offerDetail.oldVolume
            if (Math.abs(diff) > 0.0001) {
                ProdBuffer pb = ProdBuffer.findById(offerDetail.millOfferID)
                pb.volumeOffered = pb.volumeOffered + diff
                pb.volumeRestInclOffers = pb.volumeAvailable - pb.volumeOffered - pb.onOrder
                pb.save()
            }
        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'offerDetail.label', default: 'OfferDetail'), offerDetail.id])
                redirect controller: "offerHeader", action: "edit", id: "${offerDetail.offerHeader.id}"
            }
            //            '*'{ respond offerDetail, [status: OK] }
        }
    }

    @Transactional
    def delete(OfferDetail offerDetail) {

        if (offerDetail == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        offerDetail.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'offerDetail.label', default: 'OfferDetail'), offerDetail.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'offerDetail.label', default: 'OfferDetail'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
