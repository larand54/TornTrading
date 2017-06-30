package com.buffer

class TimeTagLib {
    def springSecurityService
    static final namespace = 'myTag'
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def weekNo = {attrs, body ->
        Date date = new Date()
        def calendar = date.toCalendar()
        calendar.add(Calendar.DATE,7*(attrs.offset as int))
        def week = calendar.get(Calendar.WEEK_OF_YEAR)
        out  << String.format("W%02d", week)
    }
    def weekNo_polish = {attrs, body ->
        Date date = new Date()
        def calendar = date.toCalendar()
        calendar.add(Calendar.DATE,7*(attrs.offset as int))
        def week = calendar.get(Calendar.WEEK_OF_YEAR)
        out  << String.format("T%02d", week)
    }
    def yearWeekNo = {attrs, body ->
        Date date = new Date()
        def calendar = date.toCalendar()
        calendar.add(Calendar.DATE,7*(attrs.offset as int))
        def week = calendar.get(Calendar.WEEK_OF_YEAR)
        def year = calendar.get(Calendar.YEAR) - 2000
        week = year*100+week
        out  << String.format("%04d", week)
    }
    def userCompany = { attrs, body ->
        def loggedInUser = springSecurityService.currentUser
        def uc = loggedInUser.getUserSettings().supplierName
        out << uc
    }
    def userVolumeUnit = { attrs, body ->
        def loggedInUser = springSecurityService.currentUser
        def uvu = loggedInUser.getUserSettings().volumeUnit
        out << uvu
    }
    def userCurrency = { attrs, body ->
        def loggedInUser = springSecurityService.currentUser
        def uc = loggedInUser.getUserSettings().currency
        out << uc
    }
}
