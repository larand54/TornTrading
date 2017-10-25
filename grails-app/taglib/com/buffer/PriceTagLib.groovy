package com.buffer

import com.torntrading.portal.OfferDetail

class PriceTagLib {
    def springSecurityService
//    static final namespace = 'priceTag'
    static defaultEncodeAs = [taglib:'html']
    
    def endPrice = {attrs, body ->
        BigDecimal endPrice = attrs.price + attrs.freight + attrs.markUp
        out << endPrice
    }
    
    def endPriceM3 = {attrs, body ->
        OfferDetail od = attrs.offerDetail
        BigDecimal endPricePM3 = od.endPrice / (od.volumeOffered?od.volumeOffered:1)
        endPricePM3 = Math.round(endPricePM3+0.4999) 
        if (endPricePM3 < 1.001) {
            out << ''
        } else
            out << endPricePM3
    }
}
