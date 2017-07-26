package com.buffer
import grails.plugin.springsecurity.*//annotation.Secured


class LogoutInterceptor {
    def springSecurityService

    boolean before() { 
//        redirect(uri:'/')
        true 
    }
    boolean after() { 
        println("***** " + springSecurityService.getPrincipal().username + " LOGGED OUT ! ******" + new Date())
        true
    }
    void afterView() {
        // no-op
    }
}
