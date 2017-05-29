package com.torntrading.security

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class UserSettingsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserSettings.list(params), model:[userSettingsCount: UserSettings.count()]
    }

    def show(UserSettings userSettings) {
        respond userSettings
    }

    def create() {
        respond new UserSettings(params)
    }

    @Transactional
    def save(UserSettings userSettings) {
        if (userSettings == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userSettings.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userSettings.errors, view:'create'
            return
        }

        userSettings.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userSettings.label', default: 'UserSettings'), userSettings.id])
                redirect userSettings
            }
            '*' { respond userSettings, [status: CREATED] }
        }
    }

    def edit(UserSettings userSettings) {
        respond userSettings
    }

    @Transactional
    def update(UserSettings userSettings) {
        if (userSettings == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userSettings.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userSettings.errors, view:'edit'
            return
        }

        userSettings.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userSettings.label', default: 'UserSettings'), userSettings.id])
                redirect userSettings
            }
            '*'{ respond userSettings, [status: OK] }
        }
    }

    @Transactional
    def delete(UserSettings userSettings) {

        if (userSettings == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userSettings.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userSettings.label', default: 'UserSettings'), userSettings.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userSettings.label', default: 'UserSettings'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
