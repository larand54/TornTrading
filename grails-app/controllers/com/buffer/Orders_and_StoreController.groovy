package com.buffer

import com.torntrading.portal.UserSettings
import com.torntrading.security.Role
import com.torntrading.security.User
import com.torntrading.security.UserRole
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_USER','ROLE_SALES', 'ROLE_SUPPLIER'])
class Orders_and_StoreController {
    def springSecurityService
    def list(Integer max) { 
        //        params.max = Math.min(max ?: 10, 100)
        //       respond Orders.list(params), model:[ordersCount: Orders.count(), ProdBuffer.list(params), model:[prodBufferCount: ProdBuffer.count()]]
        def User user
        user = springSecurityService.isLoggedIn() ? springSecurityService.getCurrentUser() : null
        def us = user.getUserSettings()
        def mill = (us != null) ? us.supplierName :''
        def roles = springSecurityService.getPrincipal().getAuthorities()
        def prodBuffer = ProdBuffer.list()
        def ArrayList<ProdBuffer> myList
        for(def role in roles){ if(role.getAuthority() == "ROLE_ADMIN") {
                myList = prodBuffer
                break
            }else if(role.getAuthority() == "ROLE_SALES") {
                myList = prodBuffer
                break
            }else if(role.getAuthority() == "ROLE_SUPPLIER") {
                myList = prodBuffer.findAll{mill == it.sawMill}
                break
            }
        }
        myList.each(){
            it.initiateVolumes()
            it.fillWeekList()
        }
        def orders = Orders.list()
        [orders: orders, prodBuffer: myList]
    }
           

	
    def show_prodbuffer() {
        redirect(controller: "prodBuffer",action: "show", id: params.id)
    }

    def edit_prodbuffer() {
        redirect(controller: "prodBuffer",action: "edit", id: params.id)
    }

    def show_order() {
        redirect(controller: "orders",action: "show", id: params.id)
    }
	
    def add_prodBuffer() {
        redirect(controller: "prodBuffer", action: "create", id: params.id)
    }
}
