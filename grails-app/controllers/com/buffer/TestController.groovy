package com.buffer

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_USER','ROLE_SALES', 'ROLE_SUPPLIER'])
class TestController {

    def index() { redirect (action: 'list2' )}
}
