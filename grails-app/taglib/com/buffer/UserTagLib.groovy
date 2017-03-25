package com.buffer

class UserTagLib {
    static final namespace = 'myTag'
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def springSecurityService

    def isOwner = { attrs, body ->
        def loggedInUser = springSecurityService.currentUser
        def owner = attrs?.owner

        if(loggedInUser?.id == owner?.id) {
            out << body()
        }
    }

    def userCompany = { attrs, body ->
        def loggedInUser = springSecurityService.currentUser
        def uc = loggedInUser.getUserSettings().supplierName
        out << uc
    }
}
