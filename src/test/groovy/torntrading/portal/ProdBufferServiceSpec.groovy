package com.torntrading.portal

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import spock.lang.*

@Integration
@Rollback
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ProdBufferService)
class ProdBufferServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testCheckWeekStatus"() {
        expect:"fix me"
//            checkWeekStatus()
            true == false
    }
}
