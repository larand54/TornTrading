package com.torntrading.portal

import static org.springframework.http.HttpStatus.*
import com.buffer.ProdBuffer
import grails.converters.JSON
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_SALES'])
@Transactional(readOnly = true)
class OfferDetailController {
    def prodBufferService
    def offerDetailService
    def offerHeaderService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def updatePrice() {
        println("OfferDeatilController.updatePrice, params: "+params)
        def OfferDetail od
        if (params.id != null){
            od = OfferDetail.get(params.id)
        }
        String status =  od.offerHeader.status 
        if ((status == 'Sold') || (status == 'Rejected')) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Offer can not be changed (Sold/Rejected)'
            redirect(action:"edit", id:params.id)
        } else {
        if (params.availableCert) {
            od.choosedCert = params.availableCert
        } else if (params.adjustPrice) {
            od.priceAdjust = params.adjustPrice.toBigDecimal()
            println("OfferDeatilController.updatePrice - adjustPrice: "+od.priceAdjust)
        } else if (params.volumeOffered) {
            println("Before: volumeOffered: "+params.volumeOffered)
            params.volumeOffered.replaceAll("\\s","") // remove whitespace that can occure when size > 999 e.g. 1 279
            println("After: volumeOffered: "+params.volumeOffered)
            if (changeVolumeOffered(od, params.volumeOffered.toDouble()) )
            od.volumeOffered = params.volumeOffered.toDouble()
            else {
                flashMessage='Offer not updated! Volume set to high!'
                redirect(action:"edit", id:params.id)
               //render ([message: 'Offer not updated! Volume set to high!'] as JSON)
            }            
        } else {} 
        od.save(flush: true, failOnError: true)
        //      render template: "OfferDData", model: [offerDetail:od] 
        render { div ( od.endPrice ) }
        
        }
    }
    
    def boolean changeVolumeOffered(OfferDetail od, Double newVolume) {
        def Double volumeChange = offerDetailService.getVolumeChangeFromForm(od, newVolume)
        ProdBuffer pb = ProdBuffer.findById(od.millOfferID)
        if (Math.abs(volumeChange) > 0.0) {
            if (offerHeaderService.okToAddVolume(pb, volumeChange)) {
                println("Volume OK! " + volumeChange)
                if (od.offerHeader.status == 'Active') {
                    prodBufferService.addOfferVolume(pb, volumeChange)
                }
                return true
            } else {
                println("Volume NOT OK! " + volumeChange)
                return false                            
            }
            
        }

    }
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond OfferDetail.findAll(offerType=='o'), model:[offerDetailCount: OfferDetail.count()]
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
            println("Add available volume record: ")
        for (int i=0; i<12; i++) {
            def oav = new OfferWeeklyAvailableVolume(week:i+1 as Integer, volume:0 as Double)
            offerDetail.addToAvailableVolumes(oav).save(flush:true)
            println("Add available volume record: "+i)
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
/*        offerDetail.plannedVolumes = ProdBuffer.get(offerDetail.millOfferID).plannedVolumes
        offerDetail.inStock = ProdBuffer.get(offerDetail.millOfferID).volumeInStock
*/
        if(offerDetail.useWeeklyVolumes) {
            prodBufferService.checkWeekStatus()
            offerDetailService.setAvailableVolumes(offerDetail)
        }
        def ProdBuffer pb = ProdBuffer.get(offerDetail.millOfferID)
        respond offerDetail, model:[offerPlannedVolumes:offerDetail.offerPlannedVolumes, availableVolumes:offerDetail.availableVolumes, prodBuffer:pb]
    }

    @Transactional
    def update(OfferDetail offerDetail) {
        println("Update - Params: "+params)
        if (offerDetail == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (offerDetail.hasErrors()) {
            transactionStatus.setRollbackOnly()
//            respond offerDetail.errors, view:'edit'
                redirect controller: "offerDetail", action:"edit", id:params.id

            return
        }
        String status =  offerDetail.offerHeader.status 
        if ((status == 'Sold') || (status == 'Rejected')) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Offer can not be changed (Sold/Rejected)'
            respond offerDetail.errors, view:'edit'
            return
        }
        
        ProdBuffer pb = ProdBuffer.findById(offerDetail.millOfferID)
        if (pb == null) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Offer not created! Connected product does not exist!'
            respond offerDetail.errors, view:'edit'
            return            
        }
        
        if (offerDetail.useWeeklyVolumes) {
            offerDetailService.checkOldVolumesAndCorrect(offerDetail)
            println("OfferDetailController.update, fromStock: "+params.fromStock)
            def Double offerVolume = offerDetailService.addWeekVolumes(offerDetail, params)
            if (offerVolume >= 0.0) {
                offerDetail.volumeOffered = offerVolume
            } else {
                println("offerDetailController - Error volume calc: "+ offerVolume)
                transactionStatus.setRollbackOnly()
                flash.message = 'Offered volume not available!'
                redirect controller: "offerDetail", action:"edit", id:params.id
                return                            
            }
        }
        
        def Double volumeChange = offerDetailService.getVolumeChange(offerDetail)
        if (Math.abs(volumeChange) > 0.0) {
            if (offerHeaderService.okToAddVolume(pb, volumeChange)) {
                println("Volume OK! " + volumeChange)
                if (offerDetail.offerHeader.status == 'Active') {
                    prodBufferService.addOfferVolume(pb, volumeChange)
                }
            } else {
                println("Volume NOT OK! " + volumeChange)
                transactionStatus.setRollbackOnly()
                flash.message = 'Offer not updated! Volume set to high!'
                respond offerDetail.errors, view:'edit'
                return                            
            }
            
        }
        
        offerDetail.choosedCert = params.availableCert
        
        offerDetail.save flush:true

        
        
    
        
        System.out.println("OfferDetailUpdated, oldVolume: "+offerDetail.oldVolume+" Volume offered: "+ offerDetail.volumeOffered)
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
    
    def useWeeklyVolumes() {
        println("useWeeklyVolumes - params: "+params)
        def boolean ckb = params.ckbWeeklyVolumes.toBoolean()
        def od = OfferDetail.get(params.id)
        od.useWeeklyVolumes = ckb
        od.save(flush:true)
        if (od.useWeeklyVolumes) {
            od.volumeOffered = 0.0
//            od.plannedVolumes = ProdBuffer.get(od.millOfferID).plannedVolumes
//            od.inStock = ProdBuffer.get(od.millOfferID).volumeInStock
              offerDetailService.setAvailableVolumes(od)
            println("useWeeklyVolumes - ON: ")
        } else {
            println("useWeeklyVolumes - OFF: ")
            od.inStock = 0.0
            od.volumeOffered = 0.0
            for (odv in od.offerPlannedVolumes) {
                odv.volume = 0.0
            }
            OfferPlannedVolume.withSession {
                it.flush()
                it.clear()
            }
            od.save(flush:true)
        }
       def ProdBuffer pb = ProdBuffer.get(od.millOfferID)
        render(template: "OfferDData", model:[offerDetail:od,offerPlannedVolumes:od.offerPlannedVolumes,availableVolumes: OfferWeeklyAvailableVolume, prodBuffer:pb])
    }
    
}
