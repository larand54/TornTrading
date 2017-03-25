package com.torntrading.legacy

class RoleType {
    String roleDescription
    Integer copyToXOR
    Integer sequenceNo
    Integer createdUser
    Integer modifiedUser
    Date dateCreated
    
    static mapping = {
        table 'RoleType'
        version false
        id name: 'id', sqltype:'int'//, generator:'assigned'
        id        column: 'RoleType'
        roleDescription column: 'RoleDescription', sqltype: 'char', length:20
        copyToXOR       column: 'CopytoXOR', sqltype: 'int'
        sequenceNo      column: 'SequenceNo'
        modifiedUser    column: 'ModifiedUser'
        createdUser     column: 'CreatedUser'
        dateCreated     column: 'DateCreated', sqltype: 'datetime'

    }
    static constraints = {
        roleDescription     nullable:true
        copyToXOR           nullable:true
        sequenceNo          nullable:true
        createdUser         nullable:true
        modifiedUser        nullable:true
        dateCreated         nullable:true
        
    }
}
