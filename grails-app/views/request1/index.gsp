<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Förfrågan" /><!--"${message(code: 'request1.label', default: 'Request1')}" />-->
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-request1" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-request1" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<!--            <f:table collection="${request1List}" />      -->
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
				<g:each in="${request1List}" status="i" var="request1">
				<tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
				<td><g:link action="show" id="${request1.id}">${request1.id?.encodeAsHTML()}</g:link></td>
				<td>${request1.length?.encodeAsHTML()}</td>
				<td>${request1.width?.encodeAsHTML()}</td>
				<td>${request1.thickness?.encodeAsHTML()}</td>
				<td>${request1.volumeRequested?.encodeAsHTML()}</td>
				<td>${request1.quality?.encodeAsHTML()}</td>
				<td>${request1.kd?.encodeAsHTML()}</td>
				<td>${request1.species?.encodeAsHTML()}</td>
				<td><g:field disabled="true" type="checkbox" name="fsc" value="${request1?.fsc}" checked="${request1?.fsc}" />
				<td>${request1.weekStart?.encodeAsHTML()}</td>
				<td>${request1.weekEnd?.encodeAsHTML()}</td>
				<td>${request1.company?.encodeAsHTML()}</td>
				<td>${request1.country?.encodeAsHTML()}</td>
				<td>${request1.contactPerson?.encodeAsHTML()}</td>
				<td>${request1.contactPhone?.encodeAsHTML()}</td>
				<td>${request1.contactEmail?.encodeAsHTML()}</td>
				<td>${request1.status?.encodeAsHTML()}</td>
				<td><g:formatDate format="yyyy-MM-dd" date="${request1.dateCreated}"/></td>
				</g:each>
				</tr>
			</tbody>                                                                                      
			</table>

            <div class="pagination">
                <g:paginate total="${request1Count ?: 0}" />
            </div>
        </div>
    </body>
</html>