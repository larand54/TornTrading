<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value='Orders och Lager' />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-orders" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <g:set var="entityName" value='Lager' />
		<div id="list-prodBuffer" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<table>
    <thead>
         <tr>
			<g:sortableColumn property="sawMill" title='Verk' />
			<g:sortableColumn property="product" title='Produkt' />
			<g:sortableColumn property="length" title='Längd' />
			<g:sortableColumn property="packageSize" title='Paketstorlek' />
			<g:sortableColumn property="store" title='Lager' />
			<g:sortableColumn property="onOrder" title='På order' />
			<g:sortableColumn property="delivered" title='Levererat' />
			<g:sortableColumn property="rest" title='Rest' />
			<g:sortableColumn property="availW01" title='V01' />
			<g:sortableColumn property="availW02" title='V02' />
			<g:sortableColumn property="availW03" title='V03' />
			<g:sortableColumn property="availW04" title='V04' />
			<g:sortableColumn property="availW05" title='V05' />
			<g:sortableColumn property="availW06" title='V06' />
			<g:sortableColumn property="availW07" title='V07' />
			<g:sortableColumn property="availW08" title='V08' />
			<g:sortableColumn property="availW09" title='V09' />
			<g:sortableColumn property="availW10" title='V10' />
        </tr>
    </thead>
    <tbody>
				<g:each in="${prodBuffer}" status="i" var="pb">
				<tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
				<td><g:link action="show-buffer" id="${pb.id}">${pb.sawMill?.encodeAsHTML()}</g:link></td>
				<td>${pb.product?.encodeAsHTML()}</td>
				<td>${pb.length?.encodeAsHTML()}</td>
				<td>${pb.packageSize?.encodeAsHTML()}</td>
				<td>${pb.store?.encodeAsHTML()}</td>
				<td>${pb.onOrder?.encodeAsHTML()}</td>
				<td>${pb.delivered?.encodeAsHTML()}</td>
				<td>${pb.rest?.encodeAsHTML()}</td>
				<td>${pb.availW01?.encodeAsHTML()}</td>
				<td>${pb.availW02?.encodeAsHTML()}</td>
				<td>${pb.availW03?.encodeAsHTML()}</td>
				<td>${pb.availW04?.encodeAsHTML()}</td>
				<td>${pb.availW05?.encodeAsHTML()}</td>
				<td>${pb.availW06?.encodeAsHTML()}</td>
				<td>${pb.availW07?.encodeAsHTML()}</td>
				<td>${pb.availW08?.encodeAsHTML()}</td>
				<td>${pb.availW09?.encodeAsHTML()}</td>
				<td>${pb.availW10?.encodeAsHTML()}</td>
				</g:each>
				</tr>
    </tbody>
</table>
            <div class="pagination">
                <g:paginate total="${prodBufferCount ?: 0}" />
            </div>
        </div>
		<g:set var="entityName" value='Orders' />
        <div id="list-orders" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
			<table>
			<thead>
			<tr>
				<g:sortableColumn property="sawMill" title='Verk' />
				<g:sortableColumn property="customer" title='Kund' />
				<g:sortableColumn property="orderNo" title='Ordernr' />
				<g:sortableColumn property="destination" title='Destination' />
				<g:sortableColumn property="period" title='Period' />
				<g:sortableColumn property="product" title='Produkt' />
				<g:sortableColumn property="length" title='Längd' />
				<g:sortableColumn property="packetSize" title='Paketstorlek' />
				<g:sortableColumn property="quantity" title='Kvantitet' />
				<g:sortableColumn property="price" title='Pris' />
				
			</tr>
			</thead>
			<tbody>
				<g:each in="${orders}" status="i" var="order">
				<tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
				<td>${order.sawMill?.encodeAsHTML()}</td>
				<td>${order.customer?.encodeAsHTML()}</td>
				<td><g:link action="show" id="${order.id}">${order.orderNo?.encodeAsHTML()}</g:link></td>
				<td>${order.destination?.encodeAsHTML()}</td>
				<td>${order.period?.encodeAsHTML()}</td>
				<td>${order.product?.encodeAsHTML()}</td>
				<td>${order.length?.encodeAsHTML()}</td>
				<td>${order.packetSize?.encodeAsHTML()}</td>
				<td>${order.quantity?.encodeAsHTML()}</td>
				<td>${order.price?.encodeAsHTML()}</td>
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