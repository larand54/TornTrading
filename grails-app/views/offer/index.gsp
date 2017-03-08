<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Offert"<!--value="${message(code: 'offer.label', default: 'Offer')}" /> -->
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-offer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="list-offer" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<!--            <f:table collection="${offerList}" />   -->
            <table>
                <thead>
                    <tr>
                        <th> <g:message code="ID"/> </th>
                        <th> <g:message code="default.status.label"/> </th>
                        <th> <g:message code="default.sawMill.label"/> </th>
                        <th> <g:message code="default.company.label"/> </th>
                        <th> <g:message code="default.product.label"/> </th>
                        <th> <g:message code="default.lengthDescr.label"/> </th>
                        <th> <g:message code="default.grade.label"/> </th>
                        <th> <g:message code="default.kd.label"/> </th>
                        <th> <g:message code="FSC"/> </th>
                        <th> <g:message code="default.price.label"/> </th>
                        <th> <g:message code="default.currency.label"/> </th>
                        <th> <g:message code="default.volumeUnit.short.label"/> </th>
                        <th> <g:message code="default.volumeOffered.short.label"/> </th>
                        <th> <g:message code="default.weekStart.short.label"/> </th>
                        <th> <g:message code="default.weekEnd.short.label"/> </th>
                        <th> <g:message code="delivery.terms.label"/> </th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${offerList}" status="i" var="offer">
                        <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                            <td><g:link action="show" id="${offer.id}">${offer.id?.encodeAsHTML()}</g:link></td>
                            <td>${offer?.status}</td>
                            <td>${offer.sawMill?.encodeAsHTML()}</td>
                            <td>${offer.company?.encodeAsHTML()}</td>
                            <td>${offer.product?.encodeAsHTML()}</td>
                            <td>${offer.lengthDescr?.encodeAsHTML()}</td>
                            <td>${offer.grade?.encodeAsHTML()}</td>
                            <td>${offer.kd?.encodeAsHTML()}</td>
                            <td><g:checkBox name="fsc" value="${offer?.fsc}" DISABLED="true"/></td>
                            <td>${offer.price?.encodeAsHTML()}</td>
                            <td>${offer.currency?.encodeAsHTML()}</td>
                            <td>${offer.volumeUnit?.encodeAsHTML()}</td>
                            <td>${offer.volumeOffered?.encodeAsHTML()}</td>
                            <td>${offer.weekStart?.encodeAsHTML()}</td>
                            <td>${offer.weekEnd?.encodeAsHTML()}</td>
                            <td>${offer.termsOfDelivery?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                </tbody>

                <div class="pagination">
                    <g:paginate total="${offerCount ?: 0}" />
                </div>
            </table>
        </div>
    </body>
</html>