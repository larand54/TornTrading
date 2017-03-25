<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'offerHeader.label', default: 'OfferHeader')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-offerHeader" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" id="${this.offerHeader.id}" action="addRow"><g:message code="offer.add.detail.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-offerHeader" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.offerHeader}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.offerHeader}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.offerHeader}" method="PUT">
                <g:hiddenField name="version" value="${this.offerHeader?.version}" />
                <fieldset class="form">
                    <g:render template="offerHData" model="[offerHeader:offerHeader]"/> 
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
            <fieldset class="form">
                <legend>Produkter</legend>
                <table style="width:100%">
                    <g:each in="${offerHeader.offerDetails}" status="i" var="od">
                        <tr>
                            <td>
                                <g:link class="edit" action="edit" resource="${od}"><g:message code="offer.add.detail.label" /></g:link>
                                </td>
                                <td>${od?.product}</td>

                        </tr>
                    </g:each>
                </table>
            </fieldset>
            <g:form resource="${od}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="create" controller="offerHeader" action="addRow" ><g:message code="default.button.edit.label" default="Edit" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
