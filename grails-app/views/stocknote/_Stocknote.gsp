<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stocknote.Report.label', default: 'Stocknote Report')}" />
        <title><g:message code="default.stocknote.report.label" args="[entityName]" /></title>
        <style type="text/css">
            @page {
                size: A4 landscape //210mm 297mm; // A4 format 
                @bottom-center { content: element(footer);} // if you want footer
                @top-center { content: element(header); } // if you want header
            }

            div.break {
                page-break-after:always;
            }
        </style>    
    </head>
    <body>
        <div id="report-Stocknote" class="content scaffold-show" role="main">
            <h1>Stocknote ---- ${this.offerHeader.sawMill}</h1>
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
<!--            <div class="break"/>-->
        </div>
    </body>
</html>
