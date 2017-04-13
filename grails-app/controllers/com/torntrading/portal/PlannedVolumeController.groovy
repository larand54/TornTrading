package com.torntrading.portal

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlannedVolumeController {
    def productBufferService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PlannedVolume.list(params), model:[plannedVolumeCount: PlannedVolume.count()]
    }

    def show(PlannedVolume plannedVolume) {
        respond plannedVolume
    }

    def create() {
        respond new PlannedVolume(params)
    }

    @Transactional
    def save(PlannedVolume plannedVolume) {
        if (plannedVolume == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (plannedVolume.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond plannedVolume.errors, view:'create'
            return
        }

        plannedVolume.save flush:true
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'plannedVolume.label', default: 'PlannedVolume'), plannedVolume.id])
                redirect plannedVolume
            }
            '*' { respond plannedVolume, [status: CREATED] }
        }
    }

    def edit(PlannedVolume plannedVolume) {
        respond plannedVolume
    }

    @Transactional
    def update(PlannedVolume plannedVolume) {
        if (plannedVolume == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (plannedVolume.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond plannedVolume.errors, view:'edit'
            return
        }

        plannedVolume.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'plannedVolume.label', default: 'PlannedVolume'), plannedVolume.id])
                redirect plannedVolume
            }
            '*'{ respond plannedVolume, [status: OK] }
        }
    }

    @Transactional
    def delete(PlannedVolume plannedVolume) {

        if (plannedVolume == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        plannedVolume.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'plannedVolume.label', default: 'PlannedVolume'), plannedVolume.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'plannedVolume.label', default: 'PlannedVolume'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
