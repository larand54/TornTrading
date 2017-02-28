package com.buffer

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)

@Secured('ROLE_ADMIN')
class RequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Request.list(params), model:[RequestCount: Request.count()]
    }

    def show(Request Request) {
        respond Request
    }

    def create() {
        respond new Request(params)
    }

    @Transactional
    def save(Request Request) {
        if (Request == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (Request.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond Request.errors, view:'create'
            return
        }

        Request.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'Request.label', default: 'Request'), Request.id])
                redirect Request
            }
            '*' { respond Request, [status: CREATED] }
        }
    }

    def edit(Request Request) {
        respond Request
    }

    @Transactional
    def update(Request Request) {
        if (Request == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (Request.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond Request.errors, view:'edit'
            return
        }

        Request.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Request.label', default: 'Request'), Request.id])
                redirect Request
            }
            '*'{ respond Request, [status: OK] }
        }
    }

    @Transactional
    def delete(Request Request) {

        if (Request == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        Request.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Request.label', default: 'Request'), Request.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'Request.label', default: 'Request'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
