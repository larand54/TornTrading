<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Förfrågan"/> <!--value="${message(code: 'request1.label', default: 'Förfrågan')}"--> 
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-request1" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-request1" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.request1}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.request1}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.request1}" method="PUT">
                <g:hiddenField name="version" value="${this.request1?.version}" />
                <fieldset class="form">
            <fieldset class="form">
			Ange status: <g:select name="status" from="${request1.constrainedProperties.status.inList}" 
								value="${request1?.status}" required="Y"/>
			</fieldset>
                <table style="width:100%">
                <tr>
                <td>
                <fieldset class="form">
                    <legend>Dimensioner</legend>
					

<!-- <g:formatNumber number="${request1?.thickness}"  format="###,##0" /> -->
<!--<g:formatNumber number="${request1?.thickness}" format="###,##0"  locale="sv_SE"/>  -->


                    Tjocklek: <g:field type="number decimal" name="thickness" min="5" max="200" required="y" value="${request1?.thickness}" style="width: 5em"/>
                    Bredd: <g:field type="number decimal" name="width" min="20" max="300" required="Y" value="${request1?.width}" style="width: 4em"/>
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Övriga produktspecifikationer</legend>
                    Kvalitet:  <g:field type="text" name="quality" required="y" value="${request1?.quality}" size="8"/>
                    KD%:       <g:field type="text" name="kd" required="Y" value="${request1?.kd}" size="4"/>
                    Volym AM3: <g:field type="number decimal" name="volumeRequested" min="1" max="10000" required="Y" value="${request1?.volumeRequested}" style="width: 6em"/>
                    Längd:     <g:field type="number decimal" name="length" min="1200" max="7200" required="Y" value="${request1?.length}" style="width: 5em"/>
                </fieldset>
                </td>
                <td>
                <fieldset>
                    <legend>Leveransperiod, vecka</legend>
                    Från: <g:field type="text" name="weekStart" required="y" value="${request1?.weekStart}" size="4"/>
                    Till: <g:field type="text" name="weekEnd" required="Y" value="${request1?.weekEnd}" size="4"/>
                </fieldset>
                </td>
                </tr>
                </table>
                <fieldset class="form">
                    <legend>Kontaktuppgifter</legend>
                <table style="width:100%">
                <tr>
                <td>
                    Företag: <g:field type="text" name="company" required="y" value="${request1?.company}" size="50"/>
                </td>
                <td>
                    Land: <g:field type="text" name="country" required="y" value="${request1?.country}" size="50"/>
                </td>
                <td>
                    Ort:  <g:field type="text" name="city" required="y" value="${request1?.city}" size="50"/>
                </td>
                </tr>
                <tr>
                <td>
                    Kontaktperson:  <g:field type="text" name="contactPerson" required="y" value="${request1?.contactPerson}" size="50"/>
                </td>
                <td>
                    Telefon:  <g:field type="text" name="contactPhone" required="y" value="${request1?.contactPhone}" size="50"/>
                </td>
                <td>
                    Email:  <g:field type="text" name="contactEmail" required="y" value="${request1?.contactEmail}" size="50"/>
                </td>
                </tr>																		
                </table>
                </fieldset>
				<fieldset>
					<legend> Fritext</legend>
					<g:textArea name="freeText" value="${request1?.freeText}" rows="6" cols="150" style="width:50em; height: 12em" />
				</fieldset>
				
				
				<!--                    <f:all bean="request1"/>    -->
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
