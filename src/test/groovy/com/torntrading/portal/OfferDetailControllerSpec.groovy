package com.torntrading.portal

import grails.test.mixin.*
import spock.lang.*

@TestFor(OfferDetailController)
@Mock(OfferDetail)
class OfferDetailControllerSpec extends Specification {

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
            !model.offerDetailList
            model.offerDetailCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.offerDetail!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def offerDetail = new OfferDetail()
            offerDetail.validate()
            controller.save(offerDetail)

        then:"The create view is rendered again with the correct model"
            model.offerDetail!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            offerDetail = new OfferDetail(params)

            controller.save(offerDetail)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/offerDetail/show/1'
            controller.flash.message != null
            OfferDetail.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def offerDetail = new OfferDetail(params)
            controller.show(offerDetail)

        then:"A model is populated containing the domain instance"
            model.offerDetail == offerDetail
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def offerDetail = new OfferDetail(params)
            controller.edit(offerDetail)

        then:"A model is populated containing the domain instance"
            model.offerDetail == offerDetail
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/offerDetail/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def offerDetail = new OfferDetail()
            offerDetail.validate()
            controller.update(offerDetail)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.offerDetail == offerDetail

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            offerDetail = new OfferDetail(params).save(flush: true)
            controller.update(offerDetail)

        then:"A redirect is issued to the show action"
            offerDetail != null
            response.redirectedUrl == "/offerDetail/show/$offerDetail.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/offerDetail/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def offerDetail = new OfferDetail(params).save(flush: true)

        then:"It exists"
            OfferDetail.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(offerDetail)

        then:"The instance is deleted"
            OfferDetail.count() == 0
            response.redirectedUrl == '/offerDetail/index'
            flash.message != null
    }
}
