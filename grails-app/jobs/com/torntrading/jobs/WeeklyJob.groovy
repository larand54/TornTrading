package com.torntrading.jobs
import com.torntrading.portal.ProdBufferService

class WeeklyJob {
    def newWeekService
    def prodBufferService
    static triggers = {
      //simple repeatInterval: 5000l // execute job once in 5 seconds
      //cron name: 'newWeek', cronExpression: "0 11 * ? * *"
      cron name: 'newWeek', cronExpression: "0 05 0 ? * MON" // Varje måndag kl 00:05:00
    }

    def execute() {
        println("New week - Time: "+new Date().toLocaleString())
        prodBufferService.checkWeekStatus()
    }
}
