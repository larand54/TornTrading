<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quickRequest.edit.label', default: 'QuickRequest')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-quickRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="edit-quickRequest" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.quickRequest}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.quickRequest}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.quickRequest}" method="PUT">
                <g:hiddenField name="version" value="${this.quickRequest?.version}" />
                <fieldset class="form">
                        <table>
                            <tr>
                                <div class='fieldcontain required'>
                                    <td>      
                                        <span class='required-indicator'>*</span>
                                        <g:message code="com.torntrading.pub.quickRequest.title.label"/> 
                                    </td>
                                    <td>
                                        <g:textArea name="title" value="${quickRequest?.title}" required="y" rows="2" cols="50" style="width:50em; height: 4em" /> 
                                    </td> 
                                </div>    
                            </tr>
                            <tr>
                                <div class='fieldcontain required'>
                                    <td>                                    
                                        <span class='required-indicator'>*</span>
                                        <g:message code="com.torntrading.pub.quickRequest.specReq.label"/> 
                                    </td>
                                    <td>
                                        <g:textArea name="specReq" value="${quickRequest?.specReq}" required="y" rows="2" cols="50" style="width:50em; height: 12em" /> 
                                    </td>
                                </div>
                            </tr>
                        </table>
                </fieldset>
                <fieldset>
                    <legend>Kontaktuppgifter</legend>
                    <table>
                        <tr>
                             <td>
                                <div class='fieldcontain required'>
                                    <span class='required-indicator'>*</span>
                                    <g:message code="com.torntrading.pub.quickRequest.contactPerson.label"/>  
									<g:field type="text" name="contactPerson" required="y" value="${quickRequest?.contactPerson}" size="50"/>
                                </div>
                            </td>
                            <td>
                                <div class='fieldcontain required'>
                                    <span class='required-indicator'>*</span>
                                    <g:message code="com.torntrading.pub.quickRequest.contactPhone.label"/>  
									<g:field type="text" name="contactPhone" required="y" value="${quickRequest?.contactPhone}" size="16"/>
                                </div>
                            </td>
                            <td>
                                <div class='fieldcontain required'>
                                    <span class='required-indicator'>*</span>
                                    <g:message code="com.torntrading.pub.quickRequest.contactEmail.label"/>  
									<g:field type="text" name="contactEmail" required="y" value="${quickRequest?.contactEmail}" size="50"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
