<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stocknote.report.label', default: 'Stocknote Report')}" />
        <title><g:message code="default.stocknote.report.label" args="[entityName]" /></title>
       <style type="text/css">
           #cert_img {
                font-weight: bold;
                font-size: 1.2em;
                text-align: center;
           }
           #center {
                text-align: center;
           }
       </style>    

    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#show-stocknoteHeader" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><a class="print" href="${createLink(action:'createPDF', params:[id:this.offerHeader.id])}"><g:message code="stockNote.create.pdf.label"/></a></li>
    </ul>
        </div>
        <div id="show-offerHeader" class="content scaffold-show" role="main">
                <h1>Stocknote ${this.offerHeader.sawMill}</h1>
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
                    <g:render template="StocknoteHeader" model="[offerHeader:offerHeader]"/>

            <g:render template="StocknoteDetail" model="[offerDetail:offerDetail]"/>
<div class="break"/>
        </div>
    </body>
</html>
