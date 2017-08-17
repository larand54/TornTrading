package com.torntrading.portal

import grails.transaction.Transactional
import com.torntrading.portal.OfferHeader
import com.torntrading.portal.OfferDetail
import com.buffer.ProdBuffer
@Transactional
class LogService {
    def OfferheaderService
    def OfferDetailService
    def ProdBufferService
    def logOfferHeader(String aClass, String aMethod, OfferHeader aOH) {
        String msg = 'OfferHeader-'+aClass+'-'+aMethod+' offerNo: '+aOH.id+' Status: '+aOH.status
        printLog(msg)
        
        msg = ''
        
        for (OfferDetail od in aOH.offerDetails) {
            def ProdBuffer pb = ProdBuffer.get(od.millOfferID)
            msg = '->offerDetail InStock: '+od.inStock+' Available: '+pb.volumeAvailable+' Offered: '+  od.volumeOffered 
            printLog(msg)
            
            if (od.useWeeklyVolumes) {

                msg = '-> FromStock: '+od.fromStock
                printLog(msg)
                    
                msg = '->availableVolumes: '
                for (odav in od.availableVolumes) {
                    msg = msg + ' | '+odav.volume
                }
                printLog(msg)
            
                msg = '->availableVolumes: '
                for (pbav in pb.plannedVolumes) {
                    msg = msg + ' | '+pbav.volume
                }
                printLog(msg)
            
                msg = '->offeredVolumes:   '
                for (odv in od.offerPlannedVolumes) {
                    msg = msg + ' | '+odv.volume
                }
                printLog(msg)
            }
        }
    }
    
    def logOfferDetailVolumes(String aClass, String aMethod, String aComment, OfferDetail aOD) {
        OfferHeader OH = aOD.offerHeader
        String msg = 'OfferDetail-'+aClass+'-'+aMethod+' offerNo: '+OH.id+' >>> '+aComment+' <<<' + ' Status: '+OH.status + ' OldVol: '+aOD.oldVolume
        printLog(msg)
        
        msg = '-> InStock: ' + aOD.inStock + ' FromStock: '+ aOD.fromStock
        printLog(msg)
        
        msg = '-> OPV: '
        def  opv = aOD.offerPlannedVolumes
        def  oav = aOD.availableVolumes
        for (pv in opv) {
            msg = msg + '| ' + pv.volume
        }
        printLog(msg)
    
        msg = '-> OAV: '
        for (av in oav) {
            msg = msg + '| ' + av.volume
        }
        printLog(msg)
    
    }
    
    def printLog(String aMsg) {
        def Date d = new Date()
        println(d.format('yyyy-MM-dd hh:mm:ss')+'  '+aMsg)
    }
}
