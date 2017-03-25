package com.torntrading.legacy

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_SALES'])

@Transactional(readOnly = true)
class RoleTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RoleType.list(params), model:[roleTypeCount: RoleType.count()]
    }

    def show(RoleType roleType) {
        respond roleType
    }

    def create() {
        respond new RoleType(params)
    }

    @Transactional
    def save(RoleType roleType) {
        if (roleType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roleType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roleType.errors, view:'create'
            return
        }

        roleType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'roleType.label', default: 'RoleType'), roleType.id])
                redirect roleType
            }
            '*' { respond roleType, [status: CREATED] }
        }
    }

    def edit(RoleType roleType) {
        respond roleType
    }

    @Transactional
    def update(RoleType roleType) {
        if (roleType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roleType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roleType.errors, view:'edit'
            return
        }

        roleType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'roleType.label', default: 'RoleType'), roleType.id])
                redirect roleType
            }
            '*'{ respond roleType, [status: OK] }
        }
    }

    @Transactional
    def delete(RoleType roleType) {

        if (roleType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        roleType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'roleType.label', default: 'RoleType'), roleType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'roleType.label', default: 'RoleType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
