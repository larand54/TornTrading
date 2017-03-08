<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quickRequest.label', default: 'QuickRequest')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-quickRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="list-quickRequest" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<!--            <f:table collection="${quickRequestList}" />   -->
            <table>
                <thead>
                    <tr>
                        <th> <g:message code="default.title.label"/></th>
                        <th> <g:message code="default.description.label"/></th>
                        <th> <g:message code="default.contactPerson.label"/></th>
                        <th> <g:message code="default.contactPhone.label"/></th>
                        <th> <g:message code="default.contactEmail.label"/></th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${quickRequestList}" status="i" var="quickRequest">
                    <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                        <td><g:link action="edit" id="${quickRequest.id}">${quickRequest.title?.encodeAsHTML()}</g:link></td>                        
                        <td>${quickRequest?.specReq}</td>
                        <td>${quickRequest?.contactPerson}</td>
                        <td>${quickRequest?.contactPhone}</td>
                        <td>${quickRequest?.contactEmail}</td>
                    </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${quickRequestCount ?: 0}" />
            </div>
        </div>
    </body>
</html>