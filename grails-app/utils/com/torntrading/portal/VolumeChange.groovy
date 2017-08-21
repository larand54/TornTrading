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
class VolumeChange {
    Double volume
    Boolean allowed
    
    VolumeChange(Double aVol, Boolean aAllowed) {
        volume = aVol
        allowed = aAllowed        
    }
}

