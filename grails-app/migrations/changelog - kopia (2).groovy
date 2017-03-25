databaseChangeLog = {

    changeSet(author: "Lars (generated)", id: "1490027207616-1") {
        createTable(tableName: "ClientRole") {
            column(name: "ClientNo", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "RoleType", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "CreatedUser", type: "INT")

            column(name: "DateCreated", type: "datetime")

            column(name: "idXOR", type: "VARCHAR(12)")

            column(name: "ModifiedUser", type: "INT")

            column(name: "typeXOR", type: "INT")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-2") {
        createTable(tableName: "LOBuffertv2") {
            column(autoIncrement: "true", name: "id", type: "INT") {
                constraints(primaryKey: "true", primaryKeyName: "LOBuffertv2PK")
            }

            column(name: "actual_lengthmm", type: "DOUBLE precision")

            column(name: "Appid", type: "INT")

            column(name: "Period1", type: "DOUBLE precision")

            column(name: "Period2", type: "DOUBLE precision")

            column(name: "Period3", type: "DOUBLE precision")

            column(name: "Period4", type: "DOUBLE precision")

            column(name: "Period5", type: "DOUBLE precision")

            column(name: "Period6", type: "DOUBLE precision")

            column(name: "Period7", type: "DOUBLE precision")

            column(name: "Period8", type: "DOUBLE precision")

            column(name: "Period9", type: "DOUBLE precision")

            column(name: "Period10", type: "DOUBLE precision")

            column(name: "Changed", type: "INT")

            column(name: "currency", type: "VARCHAR(3)")

            column(name: "Delivered", type: "DOUBLE precision")

            column(name: "kd", type: "VARCHAR(4)") {
                constraints(nullable: "true")
            }

            column(name: "Length", type: "VARCHAR(25)")

            column(name: "LOBuffertNo", type: "INT") {
                constraints(nullable: "true")
            }

            column(name: "MakeInquiry", type: "DOUBLE precision")

            column(name: "OnOrder", type: "DOUBLE precision")

            column(name: "PackageSize", type: "INT")

            column(name: "package_sizename", type: "VARCHAR(255)")

            column(name: "PkgArticleNo", type: "INT")

            column(name: "pricecw", type: "NUMBER(19, 2)")

            column(name: "pricefsc", type: "NUMBER(19, 2)")

            column(name: "pricepefc", type: "NUMBER(19, 2)")

            column(name: "priceuc", type: "NUMBER(19, 2)")

            column(name: "Produkt", type: "VARCHAR(150)")

            column(name: "ProductNo", type: "INT")

            column(name: "sawMill", type: "VARCHAR(80)")

            column(name: "status", type: "VARCHAR(11)")

            column(name: "volume_available", type: "DOUBLE precision")

            column(name: "volume_offered", type: "DOUBLE precision")

            column(name: "volume_rest", type: "DOUBLE precision")

            column(name: "volume_rest_incl_offers", type: "DOUBLE precision")

            column(name: "volumeUnit", type: "VARCHAR(6)")

            column(name: "week_end", type: "VARCHAR(255)")

            column(name: "week_start", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-3") {
        createTable(tableName: "RoleType") {
            column(autoIncrement: "true", name: "RoleType", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "RoleTypePK")
            }

            column(name: "CopytoXOR", type: "INT")

            column(name: "CreatedUser", type: "INT")

            column(name: "DateCreated", type: "datetime")

            column(name: "ModifiedUser", type: "INT")

            column(name: "RoleDescription", type: "VARCHAR(20)")

            column(name: "SequenceNo", type: "INT")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-4") {
        createTable(tableName: "Supplier") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "SupplierPK")
            }

            column(name: "ClientNo", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "ClientName", type: "VARCHAR(80)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-5") {
        createTable(tableName: "client") {
            column(autoIncrement: "true", name: "ClientNo", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "clientPK")
            }

            column(name: "AvdelningsNr", type: "INT")

            column(name: "BankGiro", type: "VARCHAR(12)")

            column(name: "ClientCode", type: "VARCHAR(3)")

            column(name: "ClientID", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "ClientName", type: "VARCHAR(80)") {
                constraints(nullable: "false")
            }

            column(name: "CreatedUser", type: "INT")

            column(name: "DateCreated", type: "datetime")

            column(name: "DateModified", type: "datetime")

            column(name: "MarketRegionNo", type: "INT")

            column(name: "ModifiedUser", type: "INT")

            column(name: "OrgNr", type: "VARCHAR(14)")

            column(name: "PartyIdentifier", type: "VARCHAR(50)")

            column(name: "PaymentInstruction_1", type: "CLOB")

            column(name: "PaymentInstruction_2", type: "CLOB")

            column(name: "Percent_Commision", type: "DOUBLE precision")

            column(name: "PktNrLevKod", type: "VARCHAR(5)")

            column(name: "PostGiro", type: "VARCHAR(12)")

            column(name: "PyramidMapp", type: "VARCHAR(255)")

            column(name: "IBAN_Text", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "SalesRegionNo", type: "INT")

            column(name: "SearchName", type: "VARCHAR(80)")

            column(name: "SequenceNo", type: "INT")

            column(name: "SpecialText", type: "CLOB")

            column(name: "Terms_Commision", type: "INT")

            column(name: "URL", type: "VARCHAR(100)")

            column(name: "VATNo", type: "VARCHAR(20)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-6") {
        createTable(tableName: "message") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "messagePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "locale", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "text", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-7") {
        createTable(tableName: "mill_offer") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "mill_offerPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-8") {
        createTable(tableName: "offer") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "offerPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "VARCHAR(50)")

            column(name: "company", type: "VARCHAR(50)")

            column(name: "contactEmail", type: "VARCHAR(100)")

            column(name: "contact_person", type: "VARCHAR(50)")

            column(name: "contactPhone", type: "VARCHAR(25)")

            column(name: "country", type: "VARCHAR(20)")

            column(name: "created_by", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "VARCHAR(3)")

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "fsc", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "grade", type: "VARCHAR(8)")

            column(name: "kd", type: "VARCHAR(4)")

            column(name: "lengthDescr", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "mill_offerid", type: "INT")

            column(name: "price", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "product", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "requestid", type: "INT")

            column(name: "sawMill", type: "VARCHAR(80)")

            column(name: "status", type: "VARCHAR(11)")

            column(name: "terms_of_delivery", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }

            column(name: "volumeOffered", type: "DOUBLE precision")

            column(name: "volumeUnit", type: "VARCHAR(6)") {
                constraints(nullable: "false")
            }

            column(name: "weekEnd", type: "VARCHAR(4)")

            column(name: "weekStart", type: "VARCHAR(4)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-9") {
        createTable(tableName: "offer_detail") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "offer_detailPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "created_by", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "cw_price", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "end_price", type: "NUMBER(19, 2)")

            column(name: "freight", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "fsc_price", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "grade", type: "VARCHAR(8)")

            column(name: "kd", type: "VARCHAR(4)")

            column(name: "lengthDescr", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "markup", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "mill_offerid", type: "INT")

            column(name: "offer_header_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "pefc_price", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "product", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "requestid", type: "INT")

            column(name: "uc_price", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "volumeOffered", type: "DOUBLE precision")

            column(name: "weekEnd", type: "VARCHAR(4)")

            column(name: "weekStart", type: "VARCHAR(4)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-10") {
        createTable(tableName: "offer_header") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "offer_headerPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "VARCHAR(50)")

            column(name: "company", type: "VARCHAR(50)")

            column(name: "contactEmail", type: "VARCHAR(100)")

            column(name: "contact_person", type: "VARCHAR(50)")

            column(name: "contactPhone", type: "VARCHAR(25)")

            column(name: "country", type: "VARCHAR(20)")

            column(name: "created_by", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "VARCHAR(3)")

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "sawMill", type: "VARCHAR(80)")

            column(name: "status", type: "VARCHAR(11)")

            column(name: "terms_of_delivery", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }

            column(name: "volumeUnit", type: "VARCHAR(6)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-11") {
        createTable(tableName: "orders") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "ordersPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "VARCHAR(3)") {
                constraints(nullable: "false")
            }

            column(name: "customer", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "destination", type: "VARCHAR(80)") {
                constraints(nullable: "false")
            }

            column(name: "lengthDescr", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "orderNo", type: "VARCHAR(20)") {
                constraints(nullable: "false")
            }

            column(name: "packetSize", type: "VARCHAR(15)") {
                constraints(nullable: "false")
            }

            column(name: "period", type: "VARCHAR(4)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "product", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "quantity", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "sawMill", type: "VARCHAR(80)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(11)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-12") {
        createTable(tableName: "quick_request") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "quick_requestPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "contactEmail", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "contact_person", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "contactPhone", type: "VARCHAR(25)")

            column(name: "specReq", type: "VARCHAR(1000)") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-13") {
        createTable(tableName: "registration_code") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "registration_codePK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-14") {
        createTable(tableName: "request1") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "request1PK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "company", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "contactEmail", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "contact_person", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "contactPhone", type: "VARCHAR(25)")

            column(name: "country", type: "VARCHAR(20)") {
                constraints(nullable: "false")
            }

            column(name: "credit_rate", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "free_text", type: "VARCHAR(1000)") {
                constraints(nullable: "false")
            }

            column(name: "fsc", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "kd", type: "VARCHAR(4)") {
                constraints(nullable: "false")
            }

            column(name: "length", type: "VARCHAR(80)") {
                constraints(nullable: "false")
            }

            column(name: "offerid", type: "INT")

            column(name: "quality", type: "VARCHAR(8)") {
                constraints(nullable: "false")
            }

            column(name: "species", type: "VARCHAR(20)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(12)") {
                constraints(nullable: "false")
            }

            column(name: "terms_of_delivery", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }

            column(name: "thickness", type: "DOUBLE precision") {
                constraints(nullable: "false")
            }

            column(name: "volume_requested", type: "DOUBLE precision") {
                constraints(nullable: "false")
            }

            column(name: "week_end", type: "VARCHAR(4)") {
                constraints(nullable: "false")
            }

            column(name: "week_start", type: "VARCHAR(4)") {
                constraints(nullable: "false")
            }

            column(name: "width", type: "DOUBLE precision") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-15") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-16") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-17") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-18") {
        createTable(tableName: "user_settings") {
            column(autoIncrement: "true", name: "id", type: "INT") {
                constraints(primaryKey: "true", primaryKeyName: "user_settingsPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "VARCHAR(3)")

            column(name: "supplier_id", type: "INT")

            column(name: "supplier_name", type: "VARCHAR(255)")

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "volume_unit", type: "VARCHAR(3)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-19") {
        addPrimaryKey(columnNames: "ClientNo, RoleType", constraintName: "ClientRolePK", tableName: "ClientRole")
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-20") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-21") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-22") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-23") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FKa68196081fvovjhkek5m97n3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-24") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FKfgsgxvihks805qcq8sq26ab7c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-25") {
        addForeignKeyConstraint(baseColumnNames: "offer_header_id", baseTableName: "offer_detail", constraintName: "FKiho6rka9uu57ytuqgddah4k7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "offer_header")
    }

    changeSet(author: "Lars (generated)", id: "1490027207616-26") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_settings", constraintName: "FKkjfp4kaj23y2p4yertq3dfhau", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }
    
    changeSet(author: "Lars (generated)", id: "1490002519504-99", contexts: 'WoodTrading') {
        createView(selectQuery = """
            SELECT     dbo.Client.ClientNo, dbo.Client.ClientName
            FROM       dbo.Client INNER JOIN
                       dbo.ClientRole ON dbo.Client.ClientNo = dbo.ClientRole.ClientNo AND dbo.ClientRole.RoleType = 2
                       """, viewName: 'dbo.vw_supplier'
                       )
    }

}
