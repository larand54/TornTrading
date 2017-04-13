<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodBuffer.label', default: 'ProdBuffer')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <script type="text/javascript">
            $( document ).ready( function() {
                $( document ).on('click', '.addVolume', function ( event ){
                    alert("I'm here!");
                    var fromWeek =   $('#weekStart').val();
                    var addVol = $('#plannedVolume').val();
                    var pid = $('#pid').val();

                    //send fields to server
                    $.post("/prodBuffer/addVolume", { fromWeek: fromWeek, addVol:addVol, pid:pid }, function(data){
                    //do something with the return data from the server
                    });    
                });
            });
        </script>    
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#edit-prodBuffer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" controller="ordersAndStore" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="edit-prodBuffer" class="content scaffold-edit" role="main">
                <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.prodBuffer}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.prodBuffer}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                </ul>
            </g:hasErrors>
            <g:form resource="${this.prodBuffer}" method="PUT">
                <g:hiddenField name="version" value="${this.prodBuffer?.version}" />
                <g:hiddenField name="pid" value="${this.prodBuffer?.id}" />
                <fieldset class="form">
                    <g:render template="prodBufferdata" model="[prodBuffer:prodBuffer]"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
            <g:form resource="${this.prodBuffer}" method="PUT">
                <table>
                    <tr>
                        <td>
                            <fieldset>
                                <legend>Planned production</legend>
                                From: <g:field class="elements" type="text" name="weekStart" required="y" value="" size="4"/>
                                Volume: <g:field class="elements" type="number decimal" name="plannedVolume" required="y" value="" size="4"/>
                            </fieldset>
                        </td>
                    </tr>    
                    </tr>
                </table>
                <fieldset class="buttons">
                    <input class="addVolume" type="button" value="${message(code: 'default.button.update.label', default: 'AddVolume')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
