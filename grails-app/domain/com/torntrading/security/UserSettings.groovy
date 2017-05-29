package com.torntrading.security

class UserSettings {
    int id
    int SupplierId
    String supplierName
    String currency
    String volumeUnit
   
    static belongsTo = [user : User]
    static mapping = {
      id column: 'id', type: 'integer'  
//      currency defaultValue: 'SEK'
//      volumeUnit defaultValue: 'AM3'
    }
    
    static constraints = {
        supplierName()
        currency(inList: ['SEK', 'EUR', 'USD', 'GBP'])
        volumeUnit(inList: ['AM3', 'PKG', 'AM1', 'AM2'])
        
        supplierId      nullable:true
        currency        nullable:true
        volumeUnit      nullable:true
        supplierName    nullable:true

    }
}
