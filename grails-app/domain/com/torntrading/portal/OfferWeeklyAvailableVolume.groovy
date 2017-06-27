package com.torntrading.portal

class OfferWeeklyAvailableVolume implements Comparable{

    static belongsTo = [offerDetail: OfferDetail]
    Integer week
    Double  volume
    
    static mapping = {
        table 'wt_offer_week_avail_volume'
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
