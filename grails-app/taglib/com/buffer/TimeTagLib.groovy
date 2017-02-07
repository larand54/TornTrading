package com.buffer

class TimeTagLib {
    static final namespace = 'myTag'
	static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	def weekNo = {attrs, body ->
		Date date = new Date()
		def calendar = date.toCalendar()
		def week = calendar.get(calendar.WEEK_OF_YEAR)
	    out  << String.format("V%02d", (week+(attrs.offset as Integer)))
	}
}
