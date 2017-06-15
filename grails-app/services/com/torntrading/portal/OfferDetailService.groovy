package com.torntrading.portal

import grails.transaction.Transactional
import com.buffer.*

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
    
    def Double addWeekVolumes( OfferDetail aOD, params) {
        println("AddWeeklyVolumes - params: "+params)

        Double[] availableVolumeAtWeek = new Double[12]// Totalt tillgänglig volym för vecka
        Double[] usedVolumeAtWeek = new Double[12]     // Offeread volym för vecka
        Double[] restVolumeAtWeek = new Double[12]     // Rest av planerad volym för vecka

        def pb = ProdBuffer.get(aOD.millOfferID)

        println("addWeekVolumes - dim: "+pb.dimension)
        println("addWeekVolumes - volumeInitial:: "+pb.volumeInitial)
        println("addWeekVolumes - volumeInStock:: "+pb.volumeInStock)

        //pb.volumeInStock = pb.volumeInitial            // Återställ utgångsvärdet för volymen
        println("addWeekVolumes > volumeInStock:: "+pb.volumeInStock)
        def Double volumeInStock = pb.volumeInStock
        def Double availableVolume = new Double(pb.volumeInStock)
        def pvl = pb.plannedVolumes
        int i = 0
        for (pv in pvl) {
            pv.volume = pv.initialVolume
            println("addWeekVolumes - pv:ID: "+pv.id+" volume: "+pv.volume)
            availableVolume = availableVolume + pv.volume
            availableVolumeAtWeek[i] = availableVolume
            restVolumeAtWeek[i] = pv.volume
            i++
        }
        Double[] uVol = new Double[12]
        uVol[0] = params.vol1.toDouble()
        uVol[1] = params.vol2.toDouble()
        uVol[2] = params.vol3.toDouble()
        uVol[3] = params.vol4.toDouble()
        uVol[4] = params.vol5.toDouble()
        uVol[5] = params.vol6.toDouble()
        uVol[6] = params.vol7.toDouble()
        uVol[7] = params.vol8.toDouble()
        uVol[8] = params.vol9.toDouble()
        uVol[9] = params.vol10.toDouble()
        uVol[10] = params.vol11.toDouble()
        uVol[11] = params.vol12.toDouble()

        Double usedVolume = aOD.fromStock
        println("addWeekVolumes - FromStock: "+usedVolume)
        println("addWeekVolumes - InStock: "+pb.volumeInStock)
        
        if (usedVolume > pb.volumeInStock) {
            return -1.0
        }
        pb.volumeInStock = pb.volumeInStock - usedVolume
        availableVolume = pb.volumeInStock
        def Double restVol
        for (i=0; i < 12; i++) {
            if (uVol[i] > 0.0){
                usedVolume = usedVolume + uVol[i]
                if (usedVolume > availableVolumeAtWeek[i]) {
                    return -1.0
                } else if (pb.volumeInStock > 0.0) {
                    if (pb.volumeInStock >= uVol[i]) {
                        pb.volumeInStock = pb.volumeInStock - uVol[i]
                        restVol = 0.0
                    } else {
                        restVol = uVol[i] - pb.volumeInStock
                        pb.volumeInStock = 0.0
                    }
                }
                if (restVol > 0.0) {
                    for (int j=0; j<i+1; j++) {
            println("addWeekVolumes - restVol: "+restVol)
            println("addWeekVolumes - restVolAtWeek: "+restVolumeAtWeek[j]+" Index: "+j)
                        if (restVolumeAtWeek[j] > 0.0) {
                            if (restVolumeAtWeek[j] > restVol) {
                                restVolumeAtWeek[j] = restVolumeAtWeek[j] - restVol
                                restVol = 0.0
                            } else if (restVol == restVolumeAtWeek[j]){
                                restVol = 0.0
                                restVolumeAtWeek[j] = 0.0
                            } else {
                                restVol = restVol - restVolumeAtWeek[j]
                                restVolumeAtWeek[j] = 0.0
                            }
                        }
                    }
                } 
                
                if (restVolumeAtWeek[i] < 0.0) {
                    return -3.0
                }
            }

            
            println("addWeekVolumes - uVol: "+uVol[i]+" Index: "+i)
            println("addWeekVolumes - rest: "+restVolumeAtWeek[i])
            println("addWeekVolumes - avai: "+availableVolumeAtWeek[i])
            println("addWeekVolumes - Stock: "+pb.volumeInStock)
        }
        println("addWeekVolumes - uVol: "+usedVolume)
        i = 0
        for (pv in pvl) {
            pv.volume = restVolumeAtWeek[i]
            i++
        }
        
        i = 0
        for (odpv in aOD.offerPlannedVolumes) {
            odpv.volume = uVol[i]
            i++
        }
        return usedVolume
    }
    /*
    def addVolumes(ProdBuffer prodBuffer) {
        def pvl = prodBuffer.plannedVolumes
        def Double totalVolChange = params.vol1.toDouble() + params.vol2.toDouble() + params.vol3.toDouble() + 
                    params.vol4.toDouble() + params.vol5.toDouble() + params.vol6.toDouble() + params.vol7.toDouble() +
                    params.vol8.toDouble() + params.vol9.toDouble() + params.vol10.toDouble() + params.vol11.toDouble() + params.vol12.toDouble()
                    
        for (pv in pvl) {
            totalVolChange = totalVolChange - pv.volume 
        }
        
        pvl[0].volume = params.vol1.toDouble()
        println("XXXX volume: "+pvl[0].volume)
        pvl[0].save(failOnError:true)

        pvl[1].volume = params.vol2.toDouble()
        println("XXXX volume: "+pvl[1].volume)
        pvl[1].save(failOnError:true)

        pvl[2].volume = params.vol3.toDouble()
        pvl[2].save(failOnError:true)

        pvl[3].volume = params.vol4.toDouble()
        pvl[3].save(failOnError:true)

        pvl[4].volume = params.vol5.toDouble()
        pvl[4].save(failOnError:true)

        pvl[5].volume = params.vol6.toDouble()
        pvl[5].save(failOnError:true)

        pvl[6].volume = params.vol7.toDouble()
        pvl[6].save(failOnError:true)

        pvl[7].volume = params.vol8.toDouble()
        pvl[7].save(failOnError:true)

        pvl[8].volume = params.vol9.toDouble()
        pvl[8].save(failOnError:true)

        pvl[9].volume = params.vol10.toDouble()
        pvl[9].save(failOnError:true)

        pvl[10].volume = params.vol11.toDouble()
        pvl[10].save(failOnError:true)

        pvl[11].volume = params.vol12.toDouble()
        pvl[11].save(failOnError:true)

        println(">>>> VolumeList: "+pvl)
        prodBufferService.addPlannedVolume(prodBuffer, totalVolChange)        
    }
*/
}
