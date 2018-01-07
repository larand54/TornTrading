/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.torntrading.portal
import groovy.sql.Sql
/**
 *
 * @author Lars
 */
class SqlService {
    def dataSource
    def sp() {
        Sql sql = new Sql(dataSource)

        def sqlCall = "exec sp_concat :param1, :param2"
        final paramMap = [param1: "First", param2: "Last"]
        log.info "Running: $sqlCall with params $paramMap"
        def rows = []
        try {
            rows = sql.rows(sqlCall, paramMap)
        } catch (Exception e) {
            log.warn "Could not execute ${sqlCall} with params ${paramMap}: ${e.getMessage()}", e
        }
        println("Rows:"+rows)
        rows
    }
}

