package com.torntrading.legacy

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_SALES'])

@Transactional(readOnly = true)
class ClientRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ClientRole.list(params), model:[clientRoleCount: ClientRole.count()]
    }

    def show(ClientRole clientRole) {
        respond clientRole
    }

    def create() {
        respond new ClientRole(params)
    }

    @Transactional
    def save(ClientRole clientRole) {
        if (clientRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clientRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clientRole.errors, view:'create'
            return
        }

        clientRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clientRole.label', default: 'ClientRole'), clientRole.id])
                redirect clientRole
            }
            '*' { respond clientRole, [status: CREATED] }
        }
    }

    def edit(ClientRole clientRole) {
        respond clientRole
    }

    @Transactional
    def update(ClientRole clientRole) {
        if (clientRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clientRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clientRole.errors, view:'edit'
            return
        }

        clientRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'clientRole.label', default: 'ClientRole'), clientRole.id])
                redirect clientRole
            }
            '*'{ respond clientRole, [status: OK] }
        }
    }

    @Transactional
    def delete(ClientRole clientRole) {

        if (clientRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        clientRole.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'clientRole.label', default: 'ClientRole'), clientRole.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clientRole.label', default: 'ClientRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
