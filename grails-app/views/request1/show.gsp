<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Förfrågan" /><!--"${message(code: 'request1.label', default: 'Request1')}" />-->
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-request1" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-request1" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <fieldset>
			<h3>	Status:</h3> <g:field disabled="true" type="text" name="status" required="y" value="${request1?.status}"  style="width: 8em"/>
			</fieldset>
                <table style="width:100%">
                <tr>
                <td>
                <fieldset class="form">
                    <legend>Dimensioner</legend>
                    Tjocklek: <g:field disabled="true" type="text" name="thickness" required="y" value="${request1?.thickness}"  style="width: 4em"/>
					<span>
                    Bredd: <g:field disabled="true" type="text" name="width" required="y" value="${request1?.width}"  style="width: 4em"/>
					</span>
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Övriga produktspecifikationer</legend>
                    Kvalitet:  <g:field disabled="true" type="text" name="quality" required="y" value="${request1?.quality}" size="8"/>
                    KD%:       <g:field disabled="true" type="text" name="kd" required="Y" value="${request1?.kd}" size="4"/>
                    Volym AM3: <g:field disabled="true" type="number decimal" name="volumeRequested" min="1" max="10000" required="Y" value="${request1?.volumeRequested}" style="width: 4em"/>
                    Längd:     <g:field disabled="true" type="number decimal" name="length" min="1200" max="7200" required="Y" value="${request1?.length}" style="width: 4em"/>
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Leveransperiod, vecka</legend>
                    Från: <g:field disabled="true" type="text" name="weekStart" required="y" value="${request1?.weekStart}" size="3"/>
                    Till: <g:field disabled="true" type="text" name="weekEnd" required="Y" value="${request1?.weekEnd}" size="3"/>
                </fieldset>
                </td>
                </tr>
                </table>
                <fieldset class="form">
                    <legend>Kontaktuppgifter</legend>
                <table style="width:100%">
                <tr>
                <td>
                    Företag: <g:field disabled="true" type="text" name="company" required="y" value="${request1?.company}" size="50"/>
                </td>
                <td>
                    Land: <g:field disabled="true" type="text" name="country" required="y" value="${request1?.country}" size="50"/>
                </td>
                <td>
                    Ort:  <g:field disabled="true" type="text" name="city" required="y" value="${request1?.city}" size="50"/>
                </td>
                </tr>
                <tr>
                <td>
                    Kontaktperson:  <g:field disabled="true" type="text" name="contactPerson" required="y" value="${request1?.contactPerson}" size="50"/>
                </td>
                <td>
                    Telefon:  <g:field disabled="true" type="text" name="contactPhone" required="y" value="${request1?.contactPhone}" size="50"/>
                </td>
                <td>
                    Email:  <g:field disabled="true" type="text" name="contactEmail" required="y" value="${request1?.contactEmail}" size="50"/>
                </td>
                </tr>
                </table>
                </fieldset>
				<fieldset>
					<legend> Fritext</legend>
					<g:textArea disabled="true" name="freeText" value="${request1?.freeText}" rows="6" cols="150" style="width:50em; height: 12em" />
				</fieldset>
			
			
			<!--            <f:display bean="request1" />  -->
            <g:form resource="${this.request1}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.request1}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
