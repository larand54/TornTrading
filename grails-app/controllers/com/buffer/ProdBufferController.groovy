package com.buffer

import static org.springframework.http.HttpStatus.*
import com.torntrading.security.User
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.torntrading.service.TTBufferService

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_USER','ROLE_SELLER','ROLE_SUPPLIER'])
class ProdBufferController {
    def springSecurityService
    def tTBufferService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond ProdBuffer.list(params), model:[prodBufferCount: ProdBuffer.count()]
        respond getBufferList(), model:[prodBufferCount: ProdBuffer.count()]
    }

    def show(ProdBuffer prodBuffer) {
        respond prodBuffer
    }

    def create() {
        respond new ProdBuffer(params)
    }
    
    
    def createOffer(){
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
        redirect action:"index", method:"GET"
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
            of.kd = 'xxxx'
            of.grade = 'xxxx'
            of.status = 'Preliminär'
            of.millOfferID = id
            of.save(failOnError: true)
            return of
        }
    

    @Transactional
    def save(ProdBuffer prodBuffer) {
        if (prodBuffer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (prodBuffer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond prodBuffer.errors, view:'create'
            return
        }

        prodBuffer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'prodBuffer.label', default: 'ProdBuffer'), prodBuffer.id])
                redirect prodBuffer
            }
            '*' { respond prodBuffer, [status: CREATED] }
        }
    }

    def edit(ProdBuffer prodBuffer) {
        respond prodBuffer
    }

    @Transactional
    def update(ProdBuffer prodBuffer) {
        if (prodBuffer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (prodBuffer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond prodBuffer.errors, view:'edit'
            return
        }

        prodBuffer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'prodBuffer.label', default: 'ProdBuffer'), prodBuffer.id])
                redirect prodBuffer
            }
            '*'{ respond prodBuffer, [status: OK] }
        }
    }

    @Transactional
    def delete(ProdBuffer prodBuffer) {

        if (prodBuffer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        prodBuffer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'prodBuffer.label', default: 'ProdBuffer'), prodBuffer.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'prodBuffer.label', default: 'ProdBuffer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def String getMillName(){
        def User user
        user = springSecurityService.isLoggedIn() ?
        springSecurityService.getCurrentUser() :
        null
        def us = user.getUserSettings()
        def mill = (us != null) ? us.suppliername :''
        return mill
    }
    
    def ArrayList<ProdBuffer> getBufferList() {
        def User user
        user = springSecurityService.isLoggedIn() ?
        springSecurityService.getCurrentUser() :
        null
        def us = user.getUserSettings()
        def mill = (us != null) ? us.supplierName :''
        def roles = springSecurityService.getPrincipal().getAuthorities()
        def prodBuffer = ProdBuffer.list()
        def ArrayList<ProdBuffer> myList
        for(def role in roles){ if(role.getAuthority() == "ROLE_ADMIN") {
                myList = prodBuffer
                break
            }else if(role.getAuthority() == "ROLE_SALES") {
                myList = prodBuffer
                break
            }else if(role.getAuthority() == "ROLE_SUPPLIER") {
                myList = prodBuffer.findAll{mill == it.sawMill}
                break
            }
        }
        return myList
    }
}

class OfferCommand {
    List<Integer> toOffer
}