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
class FirstLastWeek {
    WeekOrder firstWeek
    WeekOrder lastWeek
    
    def addWeek(int index, int week, int year) {
        WeekOrder tmpWeek = new WeekOrder(index, week, year)
        if (firstWeek == null) {
            firstWeek = tmpWeek
        } else if (lastWeek == null){
            if (firstWeek.yearWeek() > tmpWeek.yearWeek()) {
                lastWeek = firstWeek
                firstWeek = tmpWeek
            } else {
                lastWeek = tmpWeek
            }
        } else {
            if (firstWeek.yearWeek() > tmpWeek.yearWeek()) {
                firstWeek = tmpWeek 
            } else if ((lastWeek.yearWeek() < tmpWeek.yearWeek())) {
                lastWeek = tmpWeek
            }
        }
    }
    
    def 
    String toString() {
        if (firstWeek == null) return ''
        if ((lastWeek==null) || (lastWeek.yearWeek()==firstWeek.yearWeek()))
            return firstWeek.toString('Week ')
        else 
            return firstWeek.toString('Week ') + '-' + lastWeek.toString('')
    }
}


