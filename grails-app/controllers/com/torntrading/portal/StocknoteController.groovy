package com.torntrading.portal

import static org.springframework.http.HttpStatus.*
import com.buffer.ProdBuffer
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.torntrading.portal.OfferDetail
import com.torntrading.portal.OfferHeader

@Secured(['ROLE_ADMIN','ROLE_SALES'])
@Transactional(readOnly = true)

class StocknoteController {
    def prodBufferService
    def assetResourceLocator
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
/*        def List<OfferHeader> offerHeader = OfferHeader.findAllByOfferType('s')
        println("Stocknotes: "+offerHeader)
        [offerHeader:offerHeader]
*/
        def offerHeader = OfferHeader.createCriteria().list( params ) { eq ( "offerType", "s" )}
        respond offerHeader, model:[offerHeaderCount: offerHeader.totalCount]
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
    
    def createPDF() {
        def file = assetResourceLocator.findAssetForURI( 'Checkout16x16.png' )
        assetResourceLocator?.findAssetForURI('Checkout16x16.png')?.getInputStream()?.bytes
        def OfferHeader offerHeader = OfferHeader.get(params.id)
        for (od in offerHeader.offerDetails) {
            od.plannedVolumes = ProdBuffer.get(od.millOfferID).plannedVolumes 
        }
        
//        def millId = offerHeader.offerDetails.millOfferID
//        def ProdBuffer prodBuffer = ProdBuffer.get(millId)
//        println(">>> Offerheader: "+offerHeader.sawMill)
//        renderPdf(template: "/stocknote/Stocknote", model: [offerHeader: offerHeader, prodBuffer:prodBuffer],   filename: "Stocknote-"+params.id+".pdf")
        renderPdf(template: "/stocknote/Stocknote", model: [offerHeader: offerHeader,imageBytes: assetResourceLocator?.findAssetForURI('Checkout16x16.png')?.getInputStream()?.bytes],   filename: "Stocknote-"+params.id+".pdf")
    }
}
