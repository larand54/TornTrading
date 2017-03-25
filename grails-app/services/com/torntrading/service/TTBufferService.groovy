package com.torntrading.service

import grails.transaction.Transactional
import com.buffer.Offer
import com.buffer.ProdBuffer

@Transactional
class TTBufferService {

    def createOfferFromBuffer(int id) {
        def ProdBuffer pb
        pb = ProdBuffer.get(id)
        
        def Offer of
        of = new Offer()
        of.company = pb.sawMill
        of.lengthDescr = pb.length
        of.price = pb.price
        of.product = pb.product
        of.volumeOffered = pb.volumeRest
        of.volumeUnit = pb.volumeUnit
        of.weekEnd = pb.weekEnd
        of.weekStart = pb.weekStart
        of.save()
        return of
    }
}
