package com.torntrading.jobs
class DailyCheckJob {
    def dailyCheckService
    static triggers = {
      //simple repeatInterval: 5000l // execute job once in 5 seconds
//      cron name: 'newDay', cronExpression: "0 1-59 * ? * *"
        cron name: 'newDay', cronExpression: "0 10 0 ? * MON-SUN" // Varje dag kl 00:10:00
    }

    def execute() {
        println("Time: "+new Date().toLocaleString())
       dailyCheckService.checkOffers() 
    }
}
