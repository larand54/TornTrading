<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Request" /><!--"${message(code: 'request1.label', default: 'Request1')}" />-->
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/menue"/>
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
                    <legend>Dimensions</legend>
                    Thickness: <g:field disabled="true" type="text" name="thickness" required="y" value="${fieldValue(bean: request1, field: 'thickness')}"  style="width: 4em"/>
					<span>
                    Width: <g:field disabled="true" type="text" name="width" required="y" value="${fieldValue(bean: request1, field: 'width')}"  style="width: 4em"/>
					</span>
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Other product specifications</legend>
                    Grade:   <g:field disabled="true" type="text" name="quality" required="y" value="${request1?.quality}" size="8"/>
                    Species: <g:field disabled="true" type="text" name="species" required="y" value="${request1?.species}" size="12"/>
                    KD%:      <g:field disabled="true" type="text" name="kd" required="Y" value="${request1?.kd}" size="4"/>
                    Volume    <g:field disabled="true" type="number decimal" name="volumeRequested" min="1" max="10000" required="Y" value="${fieldValue(bean: request1, field: 'volumeRequested')}" style="width: 4em"/>
                    Length:   <g:field disabled="true" type="text" size="60" name="length" min="1200" max="7200" required="Y" value="${fieldValue(bean: request1, field: 'length')}" style="width: auto"/>
                    FSC:      <g:field disabled="true" type="checkbox" name="fsc" value="${request1?.fsc}" checked="${request1?.fsc}" />
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Delivery period, week</legend>
                    From: <g:field disabled="true" type="text" name="weekStart" required="y" value="${request1?.weekStart}" size="3"/>
                    To: <g:field disabled="true" type="text" name="weekEnd" required="Y" value="${request1?.weekEnd}" size="3"/>
                </fieldset>
                </td>
                </tr>
                </table>
                <fieldset class="form">
                    <legend>Contact info</legend>
                <table style="width:100%">
                <tr>
                <td>
                    Country: <g:field disabled="true" type="text" name="country" required="y" value="${request1?.country}" size="50"/>
                </td>
                <td>
                    City:  <g:field disabled="true" type="text" name="city" required="y" value="${request1?.city}" size="50"/>
                </td>
                </tr>
                <tr>
                <td>
                    Contactperson:  <g:field disabled="true" type="text" name="contactPerson" required="y" value="${request1?.contactPerson}" size="50"/>
                </td>
                <td>
                    Phone:  <g:field disabled="true" type="text" name="contactPhone" required="y" value="${request1?.contactPhone}" size="50"/>
                </td>
                <td>
                    Email:  <g:field disabled="true" type="text" name="contactEmail" required="y" value="${request1?.contactEmail}" size="50"/>
                </td>
                </tr>
                <tr>
                    <td>
                        Delivery terms: <g:field disabled="true" type="text" name="termsOfDelivery" value="${request1?.termsOfDelivery}"/>
                    </td>
                    <td>
                        Creditability: <g:field disabled="true" style="background-color:${request1.txtColorOfCreditRate()}; color:#000000;" type="text" name="creditRate" value="${request1?.creditRate}" size="1"/>
                </tr>
                </table>
                </fieldset>
				<fieldset>
					<legend> Free text</legend>
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
