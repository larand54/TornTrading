package com.buffer

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_USER'])
class ProdBufferController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
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
}
