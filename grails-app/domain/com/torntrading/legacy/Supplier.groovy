package com.torntrading.legacy

class Supplier {
    int         clientNo
    String      name
    String     searchName
    
    static mapping = {
        table 'dbo.vw_supplier'
        version false
        id name: 'clientNo', sqltype:'int'
        clientNo        column: 'ClientNo', sqltype:'int'
        name            column: 'ClientName',    sqltype: 'char', length: 80
        searchName      column: 'SearchName',    sqltype: 'char', length: 80
        sort "searchName"
    }
    static constraints = {
    }
}
