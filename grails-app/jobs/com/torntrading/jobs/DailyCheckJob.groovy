package com.torntrading.jobs
class DailyCheckJob {
    def dailyCheckService
    static triggers = {
      //simple repeatInterval: 5000l // execute job once in 5 seconds
      cron name: 'newDay', cronExpression: "0 11 * ? * *"
    }

    def execute() {
        println("Time: "+new Date().toLocaleString())
       dailyCheckService.checkOffers() 
    }
}
