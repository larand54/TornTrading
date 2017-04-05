<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodBuffer.label', default: 'ProdBuffer')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#list-prodBuffer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-prodBuffer" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form action="createOffer">
			<table>
				<thead>
					<tr>
                                                <th> Välj</th>
						<th> ID</th>
						<th> Verk</th>
						<th> produkt</th>
						<th> Längd</th>
						<th> Offererad volym</th>
						<th> Bokad volym</th>
						<th> Kvar att boka</th>
						<th> Valuta</th>
						<th> Pris</th>
						<th> vecka start</th>
						<th> Vecka slut</th>
						<th> W01</th>
						<th> W02</th>
						<th> W03</th>
						<th> W04</th>
						<th> W05</th>
						<th> W06</th>
						<th> W07</th>
						<th> W08</th>
						<th> W09</th>
						<th> W10</th>
					</tr>
				</thead>
			<tbody>				
				<g:each in="${prodBufferList}" status="i" var="prodBuffer">
					<tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                                                <td><g:checkBox name="toOffer" value="${prodBuffer.id}" checked="false"  /></td>
                                                <td>${prodBuffer.id}</td>
						<td>${prodBuffer.sawMill}</td>
						<td>${prodBuffer.product}</td>
						<td>${prodBuffer.length}</td>
						<td>${prodBuffer.volumeOffered}</td>
						<td>${prodBuffer.onOrder}</td>
						<td>${prodBuffer.volumeAvailable}</td>
						<td>${prodBuffer.currency}</td>
						<td>${prodBuffer.priceFSC}</td>
						<td>${prodBuffer.weekStart}</td>
						<td>${prodBuffer.weekEnd}</td>
						<td>${prodBuffer.availW01}</td>
						<td>${prodBuffer.availW02}</td>
						<td>${prodBuffer.availW03}</td>
						<td>${prodBuffer.availW04}</td>
						<td>${prodBuffer.availW05}</td>
						<td>${prodBuffer.availW06}</td>
						<td>${prodBuffer.availW07}</td>
						<td>${prodBuffer.availW08}</td>
						<td>${prodBuffer.availW09}</td>
						<td>${prodBuffer.availW10}</td>
					</tr>
				</g:each>
			</tbody>
		</table>
<!--            <f:table collection="${prodBufferList}" />  -->

            <div class="pagination">
                <g:paginate total="${prodBufferCount ?: 0}" />
            </div>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'offer.create.from.buffer.label', default: 'Create')}" />
                </fieldset>
         </g:form>
       </div>
    </body>
</html>