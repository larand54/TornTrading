<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Förfrågan" /><!--"${message(code: 'Request.label', default: 'Request')}" />-->
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-Request" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-Request" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
			<table>
			<thead>
			<tr>
				<td> Förfr. nr.</td>
				<td> Längd</td>
				<td> Bredd</td>
				<td> Tjocklek</td>
				<td> Volym</td>
				<td> Kvalitet</td>
				<td> KD</td>
				<td> Träslag</td>
				<td> FSC</td>
				<td> Lev.Villkor</td>
				<td> Start</td>
				<td> Slut</td>
				<td> Företag</td>
				<td> Land</td>
				<td> Kontakt</td>
				<td> Tel.</td>
				<td> Email</td>
				<td> Status</td>
				<td> Inkom</td>
				
			</tr>
			</thead>
			<tbody>
				<g:each in="${RequestList}" status="i" var="Request">
				<tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
				<td><g:link action="show" id="${Request.id}">${Request.id?.encodeAsHTML()}</g:link></td>
				<td>${Request.length?.encodeAsHTML()}</td>
				<td>${Request.width?.encodeAsHTML()}</td>
				<td>${Request.thickness?.encodeAsHTML()}</td>
				<td>${Request.volumeRequested?.encodeAsHTML()}</td>
				<td>${Request.quality?.encodeAsHTML()}</td>
				<td>${Request.kd?.encodeAsHTML()}</td>
				<td>${Request.species?.encodeAsHTML()}</td>
				<td><g:field disabled="true" type="checkbox" name="fsc" value="${Request?.fsc}" checked="${Request?.fsc}" />
                                <td>${Request.termsOfDelivery?.encodeAsHTML()}</td>
				<td>${Request.weekStart?.encodeAsHTML()}</td>
				<td>${Request.weekEnd?.encodeAsHTML()}</td>
				<td>${Request.company?.encodeAsHTML()}</td>
				<td>${Request.country?.encodeAsHTML()}</td>
				<td>${Request.contactPerson?.encodeAsHTML()}</td>
				<td>${Request.contactPhone?.encodeAsHTML()}</td>
				<td>${Request.contactEmail?.encodeAsHTML()}</td>
				<td>${Request.status?.encodeAsHTML()}</td>
				<td><g:formatDate format="yyyy-MM-dd" date="${Request.dateCreated}"/></td>
				</g:each>
				</tr>
			</tbody>                                                                                      
			</table>

            <div class="pagination">
                <g:paginate total="${RequestCount ?: 0}" />
            </div>
        </div>
    </body>
</html>