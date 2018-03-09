package com.torntrading.security

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import com.torntrading.legacy.Supplier

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def changePassword() {
        def user = User.get(params.id)
        respond user 
    }
    
    def roles() {
        def user = User.get(params.id)
        def userRoles = user.getAuthorities()
        def roles = Role.findAll()
        respond user, model:[roles:roles, userRoles:userRoles] 
    }
    
    def userroles() {
        def user = User.get(params.id)
        List<String> userRoles = roleNamesFromUser(user)
        List<String> uRoles = roleNamesFromParams()
        println(">> ROLES >>> params: "+uRoles)
        println(">> ROLES >>> userRoles: "+userRoles)
        
        for (uRole in uRoles) {
            println("#### Params: "+uRole)
            if (uRole in userRoles) {
                println("#### Role already used: "+uRole)
                
            } else { 
                println("#### userRole to be Created1: "+uRole)
                def role = Role.findByAuthority(uRole)
//                println("#### userRole to be Created2: "+role.authority)
//                UserRole.create user, role
                def ur = new UserRole(user:user, role: role).save(flush:true, failOnError:true)
                println("#### Role Created: "+ur.role.authority+" - username: "+ur.user.username)
            }
        }
        for (role in userRoles) {
            if (role in uRoles) {
                println("#### Role already exist: "+role)
                
            } else {
                UserRole.get(user.id,Role.findByAuthority(role).id).delete()
                println("#### Role deleted: "+role)
            }
        }
        userRoles = roleNamesFromUser(user)
        for (role in userRoles) {
           println("#### After update - Role: "+role) 
        }

        
        UserRole.withSession {
            it.flush()
            it.clear()
        }

        redirect action:"index"
    }
    protected List<String> roleNamesFromParams() {
	params.keySet().findAll { it.contains('ROLE_') && params[it] == 'on' } as List
    }
    protected List<String> roleNamesFromUser(User user) {
        def ur = user.getAuthorities().toList()
        def List<String> ul = new ArrayList<String>()
        def String auth
        for(int i=0; i<ur.size; i++){
            auth = ur[i].authority
            ul.add(auth)
        }
        return ul
    }
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userCount: User.count()]
    }

    def show(User user) {
        respond user
    }

    def create() {
        def millList = Supplier.list()
        def currencyList = UserSettings.constrainedProperties.currency.inList
        respond new User(params), model:[millList:millList, cyList:currencyList]
    }

    @Transactional
    def save(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'create'
            return
        }

        user.us = new UserSettings(supplierName:params.sawMill, currency:params.currency, volumeUnit:'AM3')
        user.us.name = params.uName
        user.us.company = params.uCompany
        user.us.email = params.uEmail
        user.us.tel = params.uTel
        user.us.phone = params.uPhone
        user.us.mobile = params.uMobile
        user.us.save flush:true
        user.save flush:true
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect controller: "user", action: "edit", id: "${user.id}"
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(User user) {
        def millList = Supplier.list()
        def currencyList = UserSettings.constrainedProperties.currency.inList
        respond user, model:[millList:millList, cyList:currencyList]
    }

    @Transactional
    def update(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        user.us.supplierName = params.sawMill?params.sawMill:(user.us.supplierName?:'noSupplier')
        user.us.currency = params.currency?params.currency:user.us.currency
        user.us.name = params.uName?params.uName:user.us.name
        user.us.company = params.uCompany?params.uCompany:user.us.company
        user.us.email = params.uEmail?params.uEmail:user.us.email
        user.us.tel = params.uTel?params.uTel:user.us.tel
        user.us.phone = params.uPhone?params.uPhone:user.us.phone
        user.us.mobile = params.uMobile?params.uMobile:user.us.mobile
        user.us.save flush:true
        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect controller: "user", action: "edit", id: "${user.id}"
            }
            redirect controller: "user", action: "edit", id: "${user.id}"
//            '*'{ respond user, [status: OK] }
        }
    }

    @Transactional
    def delete(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        UserRole.removeAll(user)

        user.delete flush:true
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
        redirect action:"index", method:"GET"
    }

    def deleteUser(User user) {
        delete(user)
        return
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
