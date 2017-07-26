package com.buffer

import com.torntrading.portal.WtStatus
import grails.plugin.springsecurity.*//annotation.Secured

class LoginInterceptor {
    def prodBufferService
    def springSecurityService
    boolean before() { 
       true 
    }

    boolean after() {  true }

    void afterView() {
//        if (springSecurityService.isLoggedIn()) {
        if (true){
            // Update woodtrading status with new week and correct volumelist on weekchange
            prodBufferService.checkWeekStatus()
        }
        // no-op
    }
}
