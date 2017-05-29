package com.torntrading.portal

import grails.transaction.Transactional

@Transactional
class OfferDetailService {

    def Double getVolumeChange(OfferDetail aOD) {
        if ((aOD.volumeOffered > 0.0001) || (aOD.oldVolume > 0.0001)){
            double diff = aOD.volumeOffered - aOD.oldVolume
            if (Math.abs(diff) > 0.0001) {
                return diff
            } else {
                return 0.0
            }
        } else return 0.0

    }

    def Double getVolumeChangeFromForm(OfferDetail aOD, Double aNewVolume) {
        if ((aNewVolume > 0.0001) || (aOD.volumeOffered > 0.0001)){
            double diff = aNewVolume - aOD.volumeOffered
            if (Math.abs(diff) > 0.0001) {
                return diff
            } else {
                return 0.0
            }
        } else return 0.0

    }
}
