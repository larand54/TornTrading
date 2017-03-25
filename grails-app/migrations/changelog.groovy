databaseChangeLog = {

    changeSet(author: "Lars (generated)", id: "1490167304217-1") {
        createTable(tableName: "orders") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "ordersPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "varchar(3)") {
                constraints(nullable: "false")
            }

            column(name: "customer", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "destination", type: "varchar(80)") {
                constraints(nullable: "false")
            }

            column(name: "lengthDescr", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "orderNo", type: "varchar(20)") {
                constraints(nullable: "false")
            }

            column(name: "packetSize", type: "varchar(15)") {
                constraints(nullable: "false")
            }

            column(name: "period", type: "varchar(4)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "numeric(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "product", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "quantity", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "sawMill", type: "varchar(80)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "varchar(11)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-2") {
        createTable(tableName: "offer_detail") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "offer_detailPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "created_by", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "cw_price", type: "numeric(19, 2)") {
                constraints(nullable: "true")
            }

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "true")
            }

            column(name: "end_price", type: "numeric(19, 2)") {
                constraints(nullable: "true")
            }

            column(name: "freight", type: "numeric(19, 2)") {
                constraints(nullable: "true")
            }

            column(name: "fsc_price", type: "numeric(19, 2)") {
                constraints(nullable: "true")
            }

            column(name: "grade", type: "varchar(8)") {
                constraints(nullable: "true")
            }

            column(name: "kd", type: "varchar(4)") {
                constraints(nullable: "true")
            }

            column(name: "lengthDescr", type: "varchar(50)") {
                constraints(nullable: "true")
            }

            column(name: "markup", type: "numeric(19, 2)") {
                constraints(nullable: "true")
            }

            column(name: "mill_offerid", type: "int") {
                constraints(nullable: "true")
            }

            column(name: "offer_header_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "pefc_price", type: "numeric(19, 2)") {
                constraints(nullable: "true")
            }

            column(name: "product", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "requestid", type: "int") {
                constraints(nullable: "true")
            }

            column(name: "uc_price", type: "numeric(19, 2)") {
                constraints(nullable: "true")
            }

            column(name: "volumeOffered", type: "float(53)") {
                constraints(nullable: "true")
            }

            column(name: "weekEnd", type: "varchar(4)") {
                constraints(nullable: "true")
            }

            column(name: "weekStart", type: "varchar(4)") {
                constraints(nullable: "true")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-3") {
        createTable(tableName: "RoleType") {
            column(autoIncrement: "true", name: "RoleType", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "RoleTypePK")
            }

            column(name: "CopytoXOR", type: "int")

            column(name: "CreatedUser", type: "int")

            column(name: "DateCreated", type: "datetime")

            column(name: "ModifiedUser", type: "int")

            column(name: "RoleDescription", type: "varchar(20)")

            column(name: "SequenceNo", type: "int")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-4") {
        createTable(tableName: "message") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "messagePK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "locale", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "text", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-5") {
        createTable(tableName: "offer") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "offerPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "varchar(50)")

            column(name: "company", type: "varchar(50)")

            column(name: "contactEmail", type: "varchar(100)")

            column(name: "contact_person", type: "varchar(50)")

            column(name: "contactPhone", type: "varchar(25)")

            column(name: "country", type: "varchar(20)")

            column(name: "created_by", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "varchar(3)")

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "fsc", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "grade", type: "varchar(8)")

            column(name: "kd", type: "varchar(4)")

            column(name: "lengthDescr", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "mill_offerid", type: "int")

            column(name: "price", type: "numeric(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "product", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "requestid", type: "int")

            column(name: "sawMill", type: "varchar(80)")

            column(name: "status", type: "varchar(11)")

            column(name: "terms_of_delivery", type: "varchar(18)") {
                constraints(nullable: "false")
            }

            column(name: "volumeOffered", type: "float(53)")

            column(name: "volumeUnit", type: "varchar(6)") {
                constraints(nullable: "false")
            }

            column(name: "weekEnd", type: "varchar(4)")

            column(name: "weekStart", type: "varchar(4)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-6") {
        createTable(tableName: "Client") {
            column(name: "ClientNo", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "ClientCode", type: "char(3)")

            column(name: "SequenceNo", type: "int")

            column(name: "ClientName", type: "varchar(80)")

            column(name: "SalesRegionNo", type: "int")

            column(name: "Percent_Commision", type: "float(53)")

            column(name: "Terms_Commision", type: "int")

            column(name: "URL", type: "varchar(100)")

            column(name: "VATNo", type: "varchar(20)")

            column(name: "SpecialText", type: "varchar(MAX)")

            column(name: "SearchName", type: "varchar(80)")

            column(name: "MarketRegionNo", type: "int")

            column(name: "PktNrLevKod", type: "varchar(5)")

            column(name: "OrgNr", type: "varchar(14)")

            column(name: "Bankgiro", type: "varchar(12)")

            column(name: "PostGiro", type: "varchar(12)")

            column(name: "DateCreated", type: "datetime")

            column(name: "DateModified", type: "datetime")

            column(name: "CreatedUser", type: "int")

            column(name: "ModifiedUser", type: "int")

            column(name: "ClientID", type: "varchar(10)")

            column(name: "PyramidMapp", type: "varchar(255)")

            column(name: "AvdelningsNr", type: "int")

            column(name: "PartyIdentifier", type: "varchar(50)")

            column(name: "PaymentInstruction_1", type: "varchar(MAX)")

            column(name: "PaymentInstruction_2", type: "varchar(MAX)")

            column(name: "IBAN_Text", type: "varchar(255)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-7") {
        createTable(tableName: "mill_offer") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "mill_offerPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-8") {
        createTable(tableName: "quick_request") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "quick_requestPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "contactEmail", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "contact_person", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "contactPhone", type: "varchar(25)")

            column(name: "specReq", type: "varchar(1000)") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "varchar(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-9") {
        createTable(tableName: "registration_code") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "registration_codePK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-10") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-11") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-12") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-13") {
        createTable(tableName: "ClientRole") {
            column(name: "ClientNo", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "RoleType", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "CreatedUser", type: "int")

            column(name: "DateCreated", type: "datetime")

            column(name: "idXOR", type: "varchar(12)")

            column(name: "ModifiedUser", type: "int")

            column(name: "typeXOR", type: "int")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-14") {
        createTable(tableName: "user_settings") {
            column(autoIncrement: "true", name: "id", type: "int") {
                constraints(primaryKey: "true", primaryKeyName: "user_settingsPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "varchar(3)")

            column(name: "supplier_id", type: "int")

            column(name: "supplier_name", type: "varchar(255)")

            column(name: "user_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "volume_unit", type: "varchar(3)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-15") {
        createTable(tableName: "offer_header") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "offer_headerPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "varchar(50)")

            column(name: "company", type: "varchar(50)")

            column(name: "contactEmail", type: "varchar(100)")

            column(name: "contact_person", type: "varchar(50)")

            column(name: "contactPhone", type: "varchar(25)")

            column(name: "country", type: "varchar(20)")

            column(name: "created_by", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "currency", type: "varchar(3)")

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "sawMill", type: "varchar(80)")

            column(name: "status", type: "varchar(11)")

            column(name: "terms_of_delivery", type: "varchar(18)") {
                constraints(nullable: "false")
            }

            column(name: "volumeUnit", type: "varchar(6)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-16") {
        createTable(tableName: "LOBuffertv2") {
            column(autoIncrement: "true", name: "id", type: "int") {
                constraints(primaryKey: "true", primaryKeyName: "LOBuffertv2PK")
            }

            column(name: "actual_lengthmm", type: "float(53)")

            column(name: "Appid", type: "int")

            column(name: "Period1", type: "float(53)")

            column(name: "Period2", type: "float(53)")

            column(name: "Period3", type: "float(53)")

            column(name: "Period4", type: "float(53)")

            column(name: "Period5", type: "float(53)")

            column(name: "Period6", type: "float(53)")

            column(name: "Period7", type: "float(53)")

            column(name: "Period8", type: "float(53)")

            column(name: "Period9", type: "float(53)")

            column(name: "Period10", type: "float(53)")

            column(name: "Changed", type: "int")

            column(name: "currency", type: "varchar(3)")

            column(name: "Delivered", type: "float(53)")

            column(name: "kd", type: "varchar(4)")

            column(name: "Length", type: "varchar(25)")

            column(name: "LOBuffertNo", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "MakeInquiry", type: "float(53)")

            column(name: "OnOrder", type: "float(53)")

            column(name: "PackageSize", type: "int")

            column(name: "package_sizename", type: "varchar(255)")

            column(name: "PkgArticleNo", type: "int")

            column(name: "pricecw", type: "numeric(19, 2)")

            column(name: "pricefsc", type: "numeric(19, 2)")

            column(name: "pricepefc", type: "numeric(19, 2)")

            column(name: "priceuc", type: "numeric(19, 2)")

            column(name: "Produkt", type: "varchar(150)")

            column(name: "ProductNo", type: "int")

            column(name: "sawMill", type: "varchar(80)")

            column(name: "status", type: "varchar(11)")

            column(name: "volume_available", type: "float(53)")

            column(name: "volume_offered", type: "float(53)")

            column(name: "volume_rest", type: "float(53)")

            column(name: "volume_rest_incl_offers", type: "float(53)")

            column(name: "volumeUnit", type: "varchar(6)")

            column(name: "week_end", type: "varchar(255)")

            column(name: "week_start", type: "varchar(255)")
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-17") {
        createTable(tableName: "request1") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(primaryKey: "true", primaryKeyName: "request1PK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "company", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "contactEmail", type: "varchar(100)") {
                constraints(nullable: "false")
            }

            column(name: "contact_person", type: "varchar(50)") {
                constraints(nullable: "false")
            }

            column(name: "contactPhone", type: "varchar(25)")

            column(name: "country", type: "varchar(20)") {
                constraints(nullable: "false")
            }

            column(name: "credit_rate", type: "int") {
                constraints(nullable: "false")
            }

            column(name: "dateCreated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "free_text", type: "varchar(1000)") {
                constraints(nullable: "false")
            }

            column(name: "fsc", type: "bit") {
                constraints(nullable: "false")
            }

            column(name: "kd", type: "varchar(4)") {
                constraints(nullable: "false")
            }

            column(name: "length", type: "varchar(80)") {
                constraints(nullable: "false")
            }

            column(name: "offerid", type: "int")

            column(name: "quality", type: "varchar(8)") {
                constraints(nullable: "false")
            }

            column(name: "species", type: "varchar(20)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "varchar(12)") {
                constraints(nullable: "false")
            }

            column(name: "terms_of_delivery", type: "varchar(18)") {
                constraints(nullable: "false")
            }

            column(name: "thickness", type: "float(53)") {
                constraints(nullable: "false")
            }

            column(name: "volume_requested", type: "float(53)") {
                constraints(nullable: "false")
            }

            column(name: "week_end", type: "varchar(4)") {
                constraints(nullable: "false")
            }

            column(name: "week_start", type: "varchar(4)") {
                constraints(nullable: "false")
            }

            column(name: "width", type: "float(53)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-18") {
        createView("""SELECT     dbo.client.ClientNo, dbo.client.ClientName, dbo.client.SearchName
FROM         dbo.client INNER JOIN
                      dbo.ClientRole ON dbo.client.ClientNo = dbo.ClientRole.ClientNo AND dbo.ClientRole.RoleType = 2""", fullDefinition: "false", viewName: "dbo.vw_supplier")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-19") {
        addUniqueConstraint(columnNames: "ClientID", constraintName: "IX_Client_1", tableName: "Client")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-20") {
        addUniqueConstraint(columnNames: "ClientName", constraintName: "IX_Client_ClientName", tableName: "Client")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-21") {
        addPrimaryKey(columnNames: "ClientNo", constraintName: "PK_Client", tableName: "Client")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-22") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-23") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FKfgsgxvihks805qcq8sq26ab7c", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-24") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-25") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-26") {
        addPrimaryKey(columnNames: "ClientNo, RoleType", constraintName: "ClientRolePK", tableName: "ClientRole")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-27") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_settings", constraintName: "FKkjfp4kaj23y2p4yertq3dfhau", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-28") {
        addForeignKeyConstraint(baseColumnNames: "offer_header_id", baseTableName: "offer_detail", constraintName: "FKiho6rka9uu57ytuqgddah4k7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "offer_header")
    }

    changeSet(author: "Lars (generated)", id: "1490167304217-29") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FKa68196081fvovjhkek5m97n3y", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role")
    }
}
