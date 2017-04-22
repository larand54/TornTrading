package com.buffer

import static org.springframework.http.HttpStatus.*
import com.torntrading.security.User
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.buffer.OrdersAndStoreController
import com.torntrading.portal.PlannedVolume

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_USER','ROLE_SALES','ROLE_SUPPLIER'])
class ProdBufferController {
    def springSecurityService
    def prodBufferService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def prodBuffer = getBufferList()
        respond ProdBuffer.list(params), model:[prodBufferCount: ProdBuffer.count()]
    }

    def show(ProdBuffer prodBuffer) {
        respond prodBuffer
    }

    def create() {
        respond new ProdBuffer(params)
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
                //                redirect prodBuffer
                redirect controller: 'ordersAndStore', action: 'list'
            }
            //           '*' { respond prodBuffer, [status: CREATED] }
            ///                render(view:"/orders_and_Store/list")
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
                redirect controller: 'ordersAndStore', action: 'list'
                //                redirect prodBuffer
            }
            //            '*'{ respond prodBuffer, [status: OK] }
        }
    }

    @Transactional
    def addVolume(ProdBuffer prodBuffer) {
        println(params)
        prodBuffer = ProdBuffer.get(params.pid)
        println(prodBuffer.dimension)
        prodBuffer.addToPlannedVolumes(new PlannedVolume(week:params.fromWeek, volume: params.addVol) )
        prodBufferService.addPlannedVolume(prodBuffer, params.addVol as Double, params.fromWeek as Integer)
        prodBufferService.updateWeekList(prodBuffer)
      flash.message = 'Vill du uppdatera volymer?' 
      notFound()
      return
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
    
    def List<ProdBuffer> getBufferList() {
        def User user
        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        def mill = (us != null) ? us.supplierName :''
        def roles = springSecurityService.getPrincipal().getAuthorities()
        def prodBuffer = ProdBuffer.list()
        def List<ProdBuffer> myList
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