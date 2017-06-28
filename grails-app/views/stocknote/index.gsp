<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stockNoteList.label', default: 'Stocknotes')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#list-offerHeader" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
                    <li><a class="create" href="${createLink(uri: '/ordersAndStore/createStocknotes')}"><g:message code="stockNote.create.label"/></a></li>
                </sec:ifAnyGranted>    
            </ul>
        </div>
        <div id="list-offerHeader" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<!--
            <g:checkBox name="polish" value="" checked="${params.polish}"/>
-->
            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="id" title='Stocknote no' />
                        <g:sortableColumn property="englishPDF" title="English" />
                        <g:sortableColumn property="polishPDF" title="Polish" />
                        <g:sortableColumn property="sawMill" title="Sawmill" />
                        <g:sortableColumn property="species" title="Wood" />
                        <g:sortableColumn property="dateCreated" title='Created' />
                        <g:sortableColumn property="createdBy" title='Created by' />
                        <g:sortableColumn property="status" title='Status' />
                    </tr>
                </thead>
                <tbody>

                    <g:each in="${offerHeaderList}" status="i" var="oh"> 
                        <tr  class="${ (i % 2) == 0 ? 'even': 'odd'}">
<!--                            <g:if test="${params.polish}">
                                <td><g:link action="createPolishPDF" id="${oh.id}">${oh.id}</g:link></td>
                            </g:if>
                            <g:else>
                                <td><g:link action="createPDF" id="${oh.id}" params.polish="true">${oh.id} </g:link></td>
                            </g:else>
-->
                            <td>${oh.id}</td>
                            <td><g:link action="createPDF" id="${oh.id}">PDF</g:link></td>
                            <td><g:link action="createPolishPDF" id="${oh.id}">PDF</g:link></td>
                            <td>${oh.sawMill}</td>
                            <td>${oh.species}</td>
                            <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${oh.dateCreated}"/></td>
                            <td>${oh.createdBy}</td>
                            <td>${oh.status}</td>
                        </tr>
                        </g:each>

                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${offerHeaderCount ?: 0}" />
            </div>
        </div>
    </body>
</html>