<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.roles.label" args="[entityName]" default="User roles"/></title>
    </head>
    <body>
        <a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="edit-user" class="content scaffold-edit" role="main">
                <h1><g:message code="default.edit.label" args="[entityName]" />: "${user.username}"</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.user}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                </ul>
            </g:hasErrors>
            <g:form action="userroles">
                <g:hiddenField name="version" value="${this.user?.version}" />
                <g:hiddenField name="id" value="${this.user?.id}" />
                <fieldset class="form">
                    <div class='fieldcontain required'>
                        <table>
                          <colgroup>
                            <col width="6%"/>
                             <col width="10%"/>
                             <col width="24%"/>
                          </colgroup>

                        <g:each in="${roles}" status="i" var="role">                        
                        <tr>
                            <td><label for='role'>"${role?.authority}"</label></td>
                           <td><g:checkBox name="${role.authority}" value="${role in userRoles}"   /></td>
                        </tr>
                        </g:each>
                        </table>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
