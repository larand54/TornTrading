<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Förfrågan" /><!--"${message(code: 'Request.label', default: 'Request')}" />-->
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-Request" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-Request" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <fieldset>
			<h3>	Status:</h3> <g:field disabled="true" type="text" name="status" required="y" value="${Request?.status}"  style="width: 8em"/>
			</fieldset>
                <table style="width:100%">
                <tr>
                <td>
                <fieldset class="form">
                    <legend>Dimensioner</legend>
                    Tjocklek: <g:field disabled="true" type="text" name="thickness" required="y" value="${fieldValue(bean: Request, field: 'thickness')}"  style="width: 4em"/>
					<span>
                    Bredd: <g:field disabled="true" type="text" name="width" required="y" value="${fieldValue(bean: Request, field: 'width')}"  style="width: 4em"/>
					</span>
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Övriga produktspecifikationer</legend>
                    Kvalitet:  <g:field disabled="true" type="text" name="quality" required="y" value="${Request?.quality}" size="8"/>
                    Träslag:  <g:field disabled="true" type="text" name="species" required="y" value="${Request?.species}" size="12"/>
                    KD%:       <g:field disabled="true" type="text" name="kd" required="Y" value="${Request?.kd}" size="4"/>
                    Volym AM3: <g:field disabled="true" type="number decimal" name="volumeRequested" min="1" max="10000" required="Y" value="${fieldValue(bean: Request, field: 'volumeRequested')}" style="width: 4em"/>
                    Längd:     <g:field disabled="true" type="text" size="60" name="length" min="1200" max="7200" required="Y" value="${fieldValue(bean: Request, field: 'length')}" style="width: auto"/>
                    FSC:       <g:field disabled="true" type="checkbox" name="fsc" value="${Request?.fsc}" checked="${Request?.fsc}" />
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Leveransperiod, vecka</legend>
                    Från: <g:field disabled="true" type="text" name="weekStart" required="y" value="${Request?.weekStart}" size="3"/>
                    Till: <g:field disabled="true" type="text" name="weekEnd" required="Y" value="${Request?.weekEnd}" size="3"/>
                </fieldset>
                </td>
                </tr>
                </table>
                <fieldset class="form">
                    <legend>Kontaktuppgifter</legend>
                <table style="width:100%">
                <tr>
                <td>
                    Företag: <g:field disabled="true" type="text" name="company" required="y" value="${Request?.company}" size="50"/>
                </td>
                <td>
                    Land: <g:field disabled="true" type="text" name="country" required="y" value="${Request?.country}" size="50"/>
                </td>
                <td>
                    Ort:  <g:field disabled="true" type="text" name="city" required="y" value="${Request?.city}" size="50"/>
                </td>
                </tr>
                <tr>
                <td>
                    Kontaktperson:  <g:field disabled="true" type="text" name="contactPerson" required="y" value="${Request?.contactPerson}" size="50"/>
                </td>
                <td>
                    Telefon:  <g:field disabled="true" type="text" name="contactPhone" required="y" value="${Request?.contactPhone}" size="50"/>
                </td>
                <td>
                    Email:  <g:field disabled="true" type="text" name="contactEmail" required="y" value="${Request?.contactEmail}" size="50"/>
                </td>
                </tr>
                <tr>
                    <td>
                        Leveransvillkor: <g:field disabled="true" type="text" name="termsOfDelivery" value="${Request?.termsOfDelivery}"/>
                    </td>
                    <td>
                        Kreditvärdighet: <g:field disabled="true" style="background-color:${Request.txtColorOfCreditRate()}; color:#000000;" type="text" name="creditRate" value="${Request?.creditRate}" size="1"/>
                </tr>
                </table>
                </fieldset>
				<fieldset>
					<legend> Fritext</legend>
					<g:textArea disabled="true" name="freeText" value="${Request?.freeText}" rows="6" cols="150" style="width:50em; height: 12em" />
				</fieldset>
			
			
			<!--            <f:display bean="Request" />  -->
            <g:form resource="${this.Request}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.Request}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
