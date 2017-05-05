package com.buffer

import static org.springframework.http.HttpStatus.*
import com.torntrading.security.User
import grails.converters.JSON
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
        def plannedVolumes = prodBuffer.plannedVolumes
        println("%%% PlannedVolumes: "+plannedVolumes)
        respond prodBuffer, model:[plannedVolumes: plannedVolumes]
    }

    @Transactional
    def update(ProdBuffer prodBuffer) {
        println('UPDATE: '+params.id)
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

        println('UPDATE SAVE: '+params.id)
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
        prodBuffer = ProdBuffer.get(params.pid)
        def pvl = prodBuffer.plannedVolumes
        
//        def PlannedVolume p = prodBuffer.find{plannedVolumes.week==5}
//        println("&&& Volume: "+p.volume)
        def Double totalVolChange = params.Vol1.toDouble() + params.Vol2.toDouble() + params.Vol3.toDouble() + 
                    params.Vol4.toDouble() + params.Vol5.toDouble() + params.Vol6.toDouble() + params.Vol7.toDouble() +
                    params.Vol8.toDouble() + params.Vol9.toDouble() + params.Vol10.toDouble() + params.Vol11.toDouble() + params.Vol12.toDouble()
                    
        for (pv in pvl) {
            totalVolChange = totalVolChange - pv.volume 
        }
        
        pvl[0].volume = params.Vol1.toDouble()
        println("XXXX Volume: "+pvl[0].volume)
        pvl[0].save(failOnError:true)

        pvl[1].volume = params.Vol2.toDouble()
        println("XXXX Volume: "+pvl[1].volume)
        pvl[1].save(failOnError:true)

        pvl[2].volume = params.Vol3.toDouble()
        pvl[2].save(failOnError:true)

        pvl[3].volume = params.Vol4.toDouble()
        pvl[3].save(failOnError:true)

        pvl[4].volume = params.Vol5.toDouble()
        pvl[4].save(failOnError:true)

        pvl[5].volume = params.Vol6.toDouble()
        pvl[5].save(failOnError:true)

        pvl[6].volume = params.Vol7.toDouble()
        pvl[6].save(failOnError:true)

        pvl[7].volume = params.Vol8.toDouble()
        pvl[7].save(failOnError:true)

        pvl[8].volume = params.Vol9.toDouble()
        pvl[8].save(failOnError:true)

        pvl[9].volume = params.Vol10.toDouble()
        pvl[9].save(failOnError:true)

        pvl[10].volume = params.Vol11.toDouble()
        pvl[10].save(failOnError:true)

        pvl[11].volume = params.Vol12.toDouble()
        pvl[11].save(failOnError:true)

        println(">>>> VolumeList: "+pvl)
        prodBufferService.addPlannedVolume(prodBuffer, totalVolChange)
        flash.message = 'Volumes updated' 
        notFound()
        return
    }
    
    @Transactional
    def delete(ProdBuffer prodBuffer) {
        println("ProdBuffer - delete ID:"+prodBuffer.id)
        if (prodBuffer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        prodBuffer.delete flush:true

        request.withFormat {
        println("ProdBuffer - withformat - delete ID:"+prodBuffer.id)
            form multipartForm {
        println("ProdBuffer - redirect - delete ID:"+prodBuffer.id)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'prodBuffer.label', default: 'ProdBuffer'), prodBuffer.id])
                redirect controller:"ordersAndStore", action:"list", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
        println("ProdBuffer - After - delete ID:"+prodBuffer.id)
    }

    def myUpdate() {
        println(">>>  myUpdate   <<<")
//        myService.update( params )
        render (['success'] as JSON )
    }

    def myDelete() {
        println(">>>  myDelete   <<<")
//        myService.delete( params )
        render (['success'] as JSON )
    }
    
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'prodBuffer.label', default: 'ProdBuffer'), params.id])
                redirect controller:"ordersAndStore", action:"list", method: "GET"
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