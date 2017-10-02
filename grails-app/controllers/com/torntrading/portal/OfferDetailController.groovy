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
    def logService
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def updatePrice() {
        println("OfferDetailController.updatePrice, params: "+params)
        
        if(!params.adjustPrice.isNumber()) {
            println('params.adjustPrice not a number')
            return render(status: 400, text:"AdjustPrice not numeric - use '.' not ','")   
        }
        def OfferDetail od
        if (params.id != null){
            od = OfferDetail.get(params.id)
        }
        String status =  od.offerHeader.status 
        if ((status == 'Sold') || (status == 'Rejected')) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Offer can not be changed (Sold/Rejected)'
            redirect controller: "offerDetail", action:"edit", id:params.id
            return

        } else {
            if (params.availableCert) {
                od.choosedCert = params.availableCert
            } else if (params.adjustPrice) {
                od.priceAdjust = params.adjustPrice.toBigDecimal()
                println("OfferDeatilController.updatePrice - adjustPrice: "+od.priceAdjust)
            } else if (params.volumeOffered) {
                if (status == 'Active' && od.useWeeklyVolumes) {
                    flashMessage='Offer not updated! Offer is active and uses weekly volumes!'
                    redirect(action:"edit", id:params.id)                   
                }
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
            def adjustedPrice = od.endPrice/od.volumeOffered/(1.0+od.offerHeader.agentFee/100)
            render { div ( id: "updatePrice", od.endPrice) }
            
        
        }
    }
    
    def boolean changeVolumeOffered(OfferDetail od, Double newVolume) {
        def Double volumeChange = offerDetailService.getVolumeChangeFromForm(od, newVolume)
        ProdBuffer pb = ProdBuffer.findById(od.millOfferID)
        if (Math.abs(volumeChange) > 0.0) {
            if (offerDetailService.okToAddVolume(pb, volumeChange)) {
                println("Volume OK! " + volumeChange)
                if (od.offerHeader.status == 'Active') {
//                    prodBufferService.addOfferVolume(pb, od, volumeChange)
            logService.logOfferDetailVolumes('CTRL','changeVolumeOffered','Before newOfferedVolume',od)
                    offerDetailService.newOfferedVolume(newVolume, od, pb)
            logService.logOfferDetailVolumes('CTRL','changeVolumeOffered','After newOfferedVolume',od)
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
            redirect controller: "offerDetail", action:"edit", id:params.id
            return
        }
        String status =  offerDetail.offerHeader.status 
        if ((status == 'Sold') || (status == 'Rejected')) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Offer can not be changed (Sold/Rejected)'
            redirect controller: "offerDetail", action:"edit", id:params.id
            return
        }
        
        ProdBuffer pb = ProdBuffer.findById(offerDetail.millOfferID)
        if (pb == null) {
            transactionStatus.setRollbackOnly()
            flash.message = 'Offer not created! Connected product does not exist!'
            redirect controller: "offerDetail", action:"edit", id:params.id
            return            
        }
        
        if (offerDetail.useWeeklyVolumes) {
            def Double oldFromStock = offerDetail.getPersistentValue('fromStock')
            offerDetail.inStock = offerDetail.inStock + oldFromStock
            logService.logOfferDetailVolumes('CTRL','update','Before addWeekVolumes',offerDetail)
            println("OfferDetailController.update, fromStock: "+params.fromStock)
            def Double offerVolume = offerDetailService.addWeekVolumes(offerDetail, params)
            logService.logOfferDetailVolumes('CTRL','update','After addWeekVolumes',offerDetail)
            println('offerDetail - update - offerVolume: ' + offerVolume)
            if (offerVolume >= 0.0) {
                offerDetail.volumeOffered = offerVolume
            } else {
                println("offerDetailController - Error volume calc: "+ offerVolume)
                transactionStatus.setRollbackOnly()
                flash.message = 'Offered volume not available!'
                redirect controller: "offerDetail", action:"edit", id:params.id
                return                            
            }
        } else {
            VolumeChange vc = offerDetailService.addOfferVolume(offerDetail, pb, params.volumeOffered.toDouble())
            if (vc.allowed){
            } else {
                transactionStatus.setRollbackOnly()
                flash.message = 'Offer not updated! Volume set to high!'
                redirect controller: "offerDetail", action:"edit", id:params.id
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
        def od = OfferDetail.get(params.id)
        String status =  od.offerHeader.status 
        if ((status == 'Sold') || (status == 'Rejected')) {
            transactionStatus.setRollbackOnly()
            return render(status: 400, text:"Offer can not be changed (Sold/Rejected)") 
        }
        def boolean ckb = params.ckbWeeklyVolumes.toBoolean()
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
