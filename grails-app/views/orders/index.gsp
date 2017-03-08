<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'orders.label', default: 'Orders')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-orders" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-orders" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

			<table>
			<thead>
			<tr>
				<th> Verk</th>
				<th> Kund</th>
				<th> Ordernr</th>
				<th> Destination</th>
				<th> Period</th>
				<th> produkt</th>
				<th> Längd</th>
				<th> Paketstorlek</th>
				<th> Kvantitet</th>
				<th> Pris</th>
				
			</tr>
			</thead>
			<tbody>
				<g:each in="${ordersList}" status="i" var="orders">
				<tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
				<td>${orders.sawMill?.encodeAsHTML()}</td>
				<td>${orders.customer?.encodeAsHTML()}</td>
				<td><g:link action="show" id="${orders.id}">${orders.orderNo?.encodeAsHTML()}</g:link></td>
				<td>${orders.destination?.encodeAsHTML()}</td>
				<td>${orders.period?.encodeAsHTML()}</td>
				<td>${orders.product?.encodeAsHTML()}</td>
				<td>${orders.lengthDescr?.encodeAsHTML()}</td>
				<td>${orders.packetSize?.encodeAsHTML()}</td>
				<td>${orders.quantity?.encodeAsHTML()}</td>
				<td>${orders.price?.encodeAsHTML()}</td>
				</g:each>
				</tr>
			</tbody>
			</table>
            <div class="pagination">
                <g:paginate total="${ordersCount ?: 0}" />
            </div>
        </div>
    </body>
</html>