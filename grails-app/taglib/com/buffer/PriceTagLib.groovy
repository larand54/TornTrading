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
        BigDecimal endPriceM3 = od.endPrice / (od.volumeOffered?od.volumeOffered:1)
        endPriceM3 = Math.round(endPriceM3 * 100) / 100
        out << endPriceM3
    }
}
