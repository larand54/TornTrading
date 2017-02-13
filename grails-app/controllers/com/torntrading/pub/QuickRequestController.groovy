package com.torntrading.pub

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuickRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QuickRequest.list(params), model:[quickRequestCount: QuickRequest.count()]
    }

    def show(QuickRequest quickRequest) {
        respond quickRequest
    }

    def create() {
        respond new QuickRequest(params)
    }

    @Transactional
    def save(QuickRequest quickRequest) {
        if (quickRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quickRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quickRequest.errors, view:'create'
            return
        }

        quickRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quickRequest.label', default: 'QuickRequest'), quickRequest.id])
                redirect quickRequest
            }
            '*' { respond quickRequest, [status: CREATED] }
        }
    }

    def edit(QuickRequest quickRequest) {
        respond quickRequest
    }

    @Transactional
    def update(QuickRequest quickRequest) {
        if (quickRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quickRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quickRequest.errors, view:'edit'
            return
        }

        quickRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quickRequest.label', default: 'QuickRequest'), quickRequest.id])
                redirect quickRequest
            }
            '*'{ respond quickRequest, [status: OK] }
        }
    }

    @Transactional
    def delete(QuickRequest quickRequest) {

        if (quickRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        quickRequest.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quickRequest.label', default: 'QuickRequest'), quickRequest.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quickRequest.label', default: 'QuickRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
