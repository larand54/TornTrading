package com.torntrading.jobs
class WeeklyJob {
    def newWeekService
    static triggers = {
      //simple repeatInterval: 5000l // execute job once in 5 seconds
      cron name: 'newWeek', cronExpression: "0 11 * ? * *"
    }

    def execute() {
        println("New week - Time: "+new Date().toLocaleString())
        newWeekService.checkOffers() 
    }
}
