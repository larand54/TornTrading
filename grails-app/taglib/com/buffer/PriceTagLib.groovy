package com.buffer

class PriceTagLib {
    def springSecurityService
    static final namespace = 'priceTag'
    static defaultEncodeAs = [taglib:'html']
    
    def endPrice = {attrs, body ->
        BigDecimal endPrice = attrs.price + attrs.freight + attrs.markUp
        out << endPrice
    }
}
