package com.torntrading.legacy

class Customer {
    int         clientNo
    String      name
    String      searchName
    String      cityName
    String      countryName
    
    static mapping = {
        table 'dbo.vwt_customer'
        version false
        id name: 'clientNo', sqltype:'int'
        clientNo        column: 'ClientNo', sqltype:'int'
        name            column: 'ClientName',    sqltype: 'char', length: 80
        searchName      column: 'SearchName',    sqltype: 'char', length: 80
        cityName      column: 'CityName',    sqltype: 'char', length: 50
        countryName      column: 'CountryName',    sqltype: 'char', length: 30
        sort "searchName"
    }
    static constraints = {
    }
}
