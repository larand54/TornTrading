package com.torntrading.portal
import com.buffer.*
class PlannedVolume {
    static belongsTo = [prodBuffer: ProdBuffer]
    Integer week
    Double  volume
    
    static mapping = {
        table 'wt_planned_volume'
    }
    static constraints = {
        week               nullable: false,
           validator: { val, obj -> 
              if (obj.id) {
                // don't check existing instances
                return
              }
              (val as int) >= obj.getCurrentWeek()}
    }

    def int getCurrentWeek() {
	Date date = new Date()
	def calendar = date.toCalendar()
	return calendar.get(calendar.WEEK_OF_YEAR)        
    }
}
