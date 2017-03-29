package com.torntrading.portal
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_SALES'])

class StockNotesController {

    def index() { render "Under construction" }
}
