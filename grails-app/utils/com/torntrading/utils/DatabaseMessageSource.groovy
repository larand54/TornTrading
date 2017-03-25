/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.torntrading.utils

import com.torntrading.pub.Message
import java.text.MessageFormat
import org.springframework.context.support.AbstractMessageSource
/**
 *
 * @author Lars
 */
class DatabaseMessageSource extends AbstractMessageSource {
    protected MessageFormat resolveCode(String code, Locale locale) { 
        Message msg = Message.findByCodeAndLocale(code, locale)
        def format
        if(msg) {
            format = new MessageFormat(msg.text, msg.locale)
        }
        else {
            format = new MessageFormat(code, locale )   
        }
        return format;
    }
}


