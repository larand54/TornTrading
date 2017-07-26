/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.torntrading.portal

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
/**
 *
 * @author Lars
 */
class AuthenticationSuccessEventListener implements    
                       ApplicationListener<InteractiveAuthenticationSuccessEvent> {


    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        println(springSecurityService.getPrincipal().username + "***** LOGGED IN ! ******" + new Date())    
    }
}

