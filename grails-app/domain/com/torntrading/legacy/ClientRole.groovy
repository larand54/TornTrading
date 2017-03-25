package com.torntrading.legacy

class ClientRole implements Serializable {
    int clientNo
    int roleType
    String idXOR
    Integer typeXOR
    Integer createdUser
    Integer modifiedUser
    Date dateCreated
    
    static mapping = {
        table 'ClientRole'
        version false
        id composite:['clientNo','roleType']
        
        clientNo        column: 'ClientNo'
        roleType        column: 'RoleType'
        idXOR           column: 'idXOR',        sqltype: 'char', length:12
        typeXOR         column: 'typeXOR'
        modifiedUser    column: 'ModifiedUser'
        createdUser     column: 'CreatedUser'
        dateCreated     column: 'DateCreated'
        dateCreated(sqltype: 'datetime')

    }
    static constraints = {
        idXOR               nullable:true
        typeXOR             nullable:true
        createdUser         nullable:true
        modifiedUser        nullable:true
        dateCreated         nullable:true
        
    }
}
