package com.buffer

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)

@Secured('ROLE_ADMIN')
class Request1Controller {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('ROLE_USER')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Request1.list(params), model:[request1Count: Request1.count()]
    }

    def show(Request1 request1) {
        respond request1
    }

    def create() {
        respond new Request1(params)
    }

    @Transactional
    def save(Request1 request1) {
        if (request1 == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (request1.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond request1.errors, view:'create'
            return
        }

        request1.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'request1.label', default: 'Request1'), request1.id])
                redirect request1
            }
            '*' { respond request1, [status: CREATED] }
        }
    }

    def edit(Request1 request1) {
        respond request1
    }

    @Transactional
    def update(Request1 request1) {
        if (request1 == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (request1.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond request1.errors, view:'edit'
            return
        }

        request1.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'request1.label', default: 'Request1'), request1.id])
                redirect request1
            }
            '*'{ respond request1, [status: OK] }
        }
    }

    @Transactional
    def delete(Request1 request1) {

        if (request1 == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        request1.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'request1.label', default: 'Request1'), request1.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'request1.label', default: 'Request1'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
