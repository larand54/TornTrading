<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'supplier.label', default: 'Supplier')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-supplier" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="createStockNotes"><g:message code="stocknote.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-supplier" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<!--            <f:table collection="${supplierList}" />-->
            <g:form action="createOffer">
		<table>
                    <thead>
			<tr>
                            <th> <g:message code="default.select.label" /></th>
                            <th> Supplier</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${supplierList}" status="i" var="sup">
                            <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                                <td><g:checkBox name="toOffer" value="${sup.id}" checked="false"  /></td>
                                <td>${sup.searchName}</td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:form>
            <div class="pagination">
                <g:paginate total="${supplierCount ?: 0}" />
            </div>
        </div>
    </body>
</html>