package com.torntrading.portal

class OfferPlannedVolume implements Comparable{
    static belongsTo = [offerDetail: OfferDetail]
    Integer week
    Double  volume
    
    static mapping = {
        table 'wt_planned_offer_volume'
    }
    static constraints = {
        volume             nullable:true
        week               nullable: true
/*           ,validator: { val, obj -> 
              if (obj.id) {
                // don't check existing instances
                return
              }
              (val as int) >= obj.getCurrentWeek()}
               */
    }
    
    int compareTo(obj) {
        week.compareTo(obj.week)
    }
    
}
