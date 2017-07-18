/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.torntrading.portal

/**
 *
 * @author Lars
 */
class WeekOrder {
    Integer index
    Integer week
    Integer Year
    
    WeekOrder(int aIndex, int aWeek, int aYear) {
      this.index = aIndex
      this.week = aWeek
      if (aYear >1899 && aYear < 2200) this.year = aYear
    }
    
    String toString(String aPrefix) {
//       return aPrefix + week.toString()
        return String.format("%s%02d",aPrefix,week)
    }
    Integer yearWeek() {
      if (year==null)  return  week
      if (year > 1999) return  (year-2000) * 100 + week
      else return (year-1900) * 100 + week
    }
}

