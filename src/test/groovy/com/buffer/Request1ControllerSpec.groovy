package com.buffer

import grails.test.mixin.*
import spock.lang.*

@TestFor(Request1Controller)
@Mock(Request1)
class Request1ControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.request1List
            model.request1Count == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.request1!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def request1 = new Request1()
            request1.validate()
            controller.save(request1)

        then:"The create view is rendered again with the correct model"
            model.request1!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            request1 = new Request1(params)

            controller.save(request1)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/request1/show/1'
            controller.flash.message != null
            Request1.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def request1 = new Request1(params)
            controller.show(request1)

        then:"A model is populated containing the domain instance"
            model.request1 == request1
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def request1 = new Request1(params)
            controller.edit(request1)

        then:"A model is populated containing the domain instance"
            model.request1 == request1
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/request1/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def request1 = new Request1()
            request1.validate()
            controller.update(request1)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.request1 == request1

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            request1 = new Request1(params).save(flush: true)
            controller.update(request1)

        then:"A redirect is issued to the show action"
            request1 != null
            response.redirectedUrl == "/request1/show/$request1.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/request1/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def request1 = new Request1(params).save(flush: true)

        then:"It exists"
            Request1.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(request1)

        then:"The instance is deleted"
            Request1.count() == 0
            response.redirectedUrl == '/request1/index'
            flash.message != null
    }
}
