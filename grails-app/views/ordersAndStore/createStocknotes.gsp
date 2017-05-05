<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stockNotes.label', default: 'StockNotes')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#create-stockNotes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" controller="stocknote" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-stockNotes" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.stockNotes}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.stockNotes}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form controller="OrdersAndStore" action="createStocknote">
                    <table style="width:25%">
                    <g:each in="${sawMills}" status="i" var="sm">
                        <tr  class="${ (i % 2) == 0 ? 'even': 'odd'}">
                            <td><g:checkBox name="sawMill" value="${sm}" checked="false"  /></td>
                            <td>${sm}</td>
                            <td>Species:  <g:select name="species" from="${['RedWood','WhiteWood']}"/> </td>
                        </tr>
                    </g:each>
                    </table>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
