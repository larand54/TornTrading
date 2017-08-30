package com.buffer

import static org.springframework.http.HttpStatus.*
import com.torntrading.security.User
import grails.converters.JSON
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.buffer.OrdersAndStoreController
import com.torntrading.portal.PlannedVolume
import com.torntrading.legacy.*

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
        def suppliers = Supplier.list()
        respond new ProdBuffer(params), model:[sawMills: suppliers]
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
        addVolumes(prodBuffer)

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
        def suppliers = Supplier.list()
        def plannedVolumes = prodBuffer.plannedVolumes
        println("%%% PlannedVolumes: "+plannedVolumes)
        respond prodBuffer, model:[plannedVolumes: plannedVolumes, sawMills:suppliers]
    }

    @Transactional
    def update(ProdBuffer prodBuffer) {
        println('UPDATE: '+params.id+' profBuffer.mill: '+prodBuffer.sawMill)
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
        
        if (prodBuffer.isDirty('volumeInStock')) {
            // If inStock changed manually - adjust volume available accordingly
            def newVol = prodBuffer.volumeInStock
            def oldVol = prodBuffer.getPersistentValue('volumeInStock')
            def diffVol = newVol - oldVol
            println(">>> IsDirty! newVol: "+newVol+" oldVol: "+ oldVol)
            if (newVol != oldVol) {
                prodBuffer.volumeAvailable = prodBuffer.volumeAvailable + diffVol
                prodBuffer.volumeInitial = newVol
            }
        }

        addVolumes(prodBuffer)
        
        println('UPDATE SAVE: '+params.id+' profBuffer.mill: '+prodBuffer.sawMill)
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

    
    def validateInput() {
        flash.Message = 'Test'
        respond
    }
    
    
    def addVolumes(ProdBuffer prodBuffer) {
        def pvl = prodBuffer.plannedVolumes
        def Double totalVolChange = params.vol1.toDouble() + params.vol2.toDouble() + params.vol3.toDouble() + 
                    params.vol4.toDouble() + params.vol5.toDouble() + params.vol6.toDouble() + params.vol7.toDouble() +
                    params.vol8.toDouble() + params.vol9.toDouble() + params.vol10.toDouble() + params.vol11.toDouble() + params.vol12.toDouble()
                    
        for (pv in pvl) {
            totalVolChange = totalVolChange - pv.volume 
        }
        
        pvl[0].volume = params.vol1.toDouble()
        pvl[0].initialVolume = pvl[0].volume
        println("XXXX volume: "+pvl[0].volume)
        pvl[0].save(failOnError:true)

        pvl[1].volume = params.vol2.toDouble()
        pvl[1].initialVolume = pvl[1].volume
        println("XXXX volume: "+pvl[1].volume)
        pvl[1].save(failOnError:true)

        pvl[2].volume = params.vol3.toDouble()
        pvl[2].initialVolume = pvl[2].volume
        pvl[2].save(failOnError:true)

        pvl[3].volume = params.vol4.toDouble()
        pvl[3].initialVolume = pvl[3].volume
        pvl[3].save(failOnError:true)

        pvl[4].volume = params.vol5.toDouble()
        pvl[4].initialVolume = pvl[4].volume
        pvl[4].save(failOnError:true)

        pvl[5].volume = params.vol6.toDouble()
        pvl[5].initialVolume = pvl[5].volume
        pvl[5].save(failOnError:true)

        pvl[6].volume = params.vol7.toDouble()
        pvl[6].initialVolume = pvl[6].volume
        pvl[6].save(failOnError:true)

        pvl[7].volume = params.vol8.toDouble()
        pvl[7].initialVolume = pvl[7].volume
        pvl[7].save(failOnError:true)

        pvl[8].volume = params.vol9.toDouble()
        pvl[8].initialVolume = pvl[8].volume
        pvl[8].save(failOnError:true)

        pvl[9].volume = params.vol10.toDouble()
        pvl[9].initialVolume = pvl[9].volume
        pvl[9].save(failOnError:true)

        pvl[10].volume = params.vol11.toDouble()
        pvl[10].initialVolume = pvl[10].volume
        pvl[10].save(failOnError:true)

        pvl[11].volume = params.vol12.toDouble()
        pvl[11].initialVolume = pvl[11].volume
        pvl[11].save(failOnError:true)

        println(">>>> VolumeList: "+pvl)
        prodBufferService.addPlannedVolume(prodBuffer, totalVolChange)        
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
    
    def fix() {
        def ProdBuffer pb = ProdBuffer.get(params.id)
        println('!!!!!!!! FIX !!!!!!' + pb.volumeAvailable)
        def Double vol = pb.volumeInitial - pb.volumeOffered - pb.volumeOnOrder
        for (pv in pb.plannedVolumes) {
            vol = vol + pv.initialVolume
        }
        println('!!!!!! Available volume: '+vol)
        redirect(action:"edit",id:params.id)
    }
}



class OfferCommand {
    List<Integer> toOffer
}