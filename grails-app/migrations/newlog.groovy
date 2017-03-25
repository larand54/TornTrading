databaseChangeLog = {

    changeSet(author: "Lars (generated)", id: "1490339391291-1") {
        createTable(tableName: "supplier") {
            column(autoIncrement: "true", name: "ClientNo", type: "int") {
                constraints(primaryKey: "true", primaryKeyName: "supplierPK")
            }

            column(name: "ClientName", type: "varchar(80)") {
                constraints(nullable: "false")
            }

            column(name: "SearchName", type: "varchar(80)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-2") {
        dropForeignKeyConstraint(baseTableName: "ClientRole", constraintName: "FK_ClientRole_Client")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-3") {
        dropForeignKeyConstraint(baseTableName: "ClientRole", constraintName: "FK_ClientRole_RoleType")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-4") {
        dropUniqueConstraint(constraintName: "IX_Client_1", tableName: "Client")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-5") {
        dropUniqueConstraint(constraintName: "IX_Client_ClientName", tableName: "Client")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-6") {
        dropView(viewName: "vw_supplier")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-7") {
        addNotNullConstraint(columnDataType: "varchar(10)", columnName: "ClientID", tableName: "client")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-8") {
        addNotNullConstraint(columnDataType: "varchar(80)", columnName: "ClientName", tableName: "client")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-9") {
        addNotNullConstraint(columnDataType: "varchar(255)", columnName: "IBAN_Text", tableName: "client")
    }

    changeSet(author: "Lars (generated)", id: "1490339391291-10") {
        addNotNullConstraint(columnDataType: "varchar(18)", columnName: "terms_of_delivery", tableName: "offer_header")
    }
}
