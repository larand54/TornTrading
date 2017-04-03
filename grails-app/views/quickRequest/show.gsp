<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quickRequest.show.label', default: 'QuickRequest')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#show-quickRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="show-quickRequest" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

                <fieldset class="form">
                        <table>
                            <tr>
                                    <td>      
                                        <g:message code="com.torntrading.pub.quickRequest.title.label"/> 
                                    </td>
                                    <td>
                                    <g:textArea disabled="true" name="title" value="${quickRequest?.title}" required="y" rows="2" cols="50" style="width:50em; height: 4em" /> 
                                    </td> 
                                </div>    
                            </tr>
                            <tr>
                                    <td>                                    
                                        <g:message code="com.torntrading.pub.quickRequest.specReq.label"/> 
                                    </td>
                                    <td>
                               <g:textArea disabled="true" name="specReq" value="${quickRequest?.specReq}" required="y" rows="2" cols="50" style="width:50em; height: 12em" /> 
                                    </td>
                                </div>
                            </tr>
                        </table>
<!--                    <f:all bean="quickRequest"/> -->
                </fieldset>
                <fieldset>
                    <legend><g:message code="default.contact.info"/></legend>
                    <table>
                        <tr>
                             <td>
                                <g:message code="com.torntrading.pub.quickRequest.contactPerson.label"/>
                                <g:field disabled="true" type="text" name="contactPerson" required="y" value="${quickRequest?.contactPerson}" size="50"/>
                            </td>
                            <td>             
								<g:message code="com.torntrading.pub.quickRequest.contactPhone.label"/>
                                <g:field disabled="true" type="text" name="contactPhone" required="y" value="${quickRequest?.contactPhone}" size="16"/>
                            </td>
                            <td>
								<g:message code="com.torntrading.pub.quickRequest.contactEmail.label"/>
                                <g:field disabled="true" type="text" name="contactEmail" required="y" value="${quickRequest?.contactEmail}" size="50"/>
                            </td>                        
						</tr>
                    </table>
                 </fieldset>
            <g:form resource="${this.quickRequest}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.quickRequest}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
