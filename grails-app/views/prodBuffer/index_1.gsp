<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodBuffer.label', default: 'ProdBuffer')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
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
			<table>
				<thead>
					<tr>
						<td> ID</td>
						<td> Verk</td>
						<td> produkt</td>
						<td> Längd</td>
						<td> Offererad volym</td>
						<td> Bokad volym</td>
						<td> Kvar att boka</td>
						<td> Valuta</td>
						<td> Pris</td>
						<td> vecka start</td>
						<td>Vecka slut</td>
						<td> W01</td>
						<td> W02</td>
						<td> W03</td>
						<td> W04</td>
						<td> W05</td>
						<td> W06</td>
						<td> W07</td>
						<td> W08</td>
						<td> W09</td>
						<td> W10</td>
					</tr>
				</thead>
                                <a href="../../migrations/changelog - kopia.groovy"></a>
			<tbody>				
				<g:each in="${prodBufferList}" status="i" var="prodBuffer">
					<tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
						<td><g:checkBox name="statements.${statement.id}" value="true" checked="${contractInstance.statements.contains(statement)?:''}" /></td>
						<td>${prodBuffer.id}</td>
						<td>${prodBuffer.sawMill}</td>
						<td>${prodBuffer.product}</td>
						<td>${prodBuffer.length}</td>
						<td>${prodBuffer.volumeOffered}</td>
						<td>${prodBuffer.volumeBooked}</td>
                                <a href="../../migrations/changelog - kopia.groovy"></a>
						<td>${prodBuffer.volumeAvailable}</td>
						<td>${prodBuffer.currency}</td>
						<td>${prodBuffer.price}</td>
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
        </div>
    </body>
</html>