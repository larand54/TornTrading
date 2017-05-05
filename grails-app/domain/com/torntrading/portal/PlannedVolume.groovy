package com.torntrading.portal
import com.buffer.*
class PlannedVolume implements Comparable{
    static belongsTo = [prodBuffer: ProdBuffer]
    Integer week
    Double  volume
    Double  initialVolume
    
    static mapping = {
        table 'wt_planned_volume'
    }
    static constraints = {
        volume             nullable:true
        week               nullable: true
        initialVolume      nullable:true
/*           ,validator: { val, obj -> 
              if (obj.id) {
                // don't check existing instances
                return
              }
              (val as int) >= obj.getCurrentWeek()}*/
    }
    
    int compareTo(obj) {
        week.compareTo(obj.week)
    }
    
    def beforeInsert() {
        initialVolume = 0.0
    }
    
    def beforeUpdate(){
        if (initialVolume == 0.0) {
           initialVolume = volume 
        }
    }

    def int getCurrentWeek() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.WEEK_OF_YEAR)        
    }
}
