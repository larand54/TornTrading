<%@ page import="com.torntrading.security.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userSettings.label', default: 'UserSettings')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-userSettings" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-userSettings" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.userSettings}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.userSettings}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <div class='fieldcontain required'>
                        <label for='user'>User
                            <span class='required-indicator'>*</span>
                        </label>                        
                        <g:select id="user" name="user.id" from="${User.listOrderByusername()}" optionKey="id"  noSelection="[null:'Välj användare']" />  
                    </div>
                    <div class='fieldcontain'>
                        <label for='supplier'>Leverantör 
                        </label>
                        <g:field  type="text" name="supplierName"  required="N" value="${userSettings?.supplierName}" size="4"/>
                    </div>
                    <div class='fieldcontain'>
                        <label for='currency'>Valuta 
                        </label>
                        <g:select  name="currency" from="${userSettings.constrainedProperties.currency.inList}" value="${userSettings?.currency}" required="N"/>
                    </div>
                    <div class='fieldcontain'>
                        <label for='volumeUnit'>Volymenhet
                        </label>
                        <g:select  name="volumeUnit" from="${userSettings.constrainedProperties.volumeUnit.inList}" value="${userSettings?.volumeUnit}" required="N"/>
                    </div>
<!--                    <f:all bean="userSettings"/>-->
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
