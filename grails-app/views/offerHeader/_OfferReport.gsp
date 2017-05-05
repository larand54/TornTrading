<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'offerHeaderReport.label', default: 'Offer Report')}" />
        <title><g:message code="default.report.label" args="[entityName]" /></title>
<style type="text/css">
@page {
  size: A4 landscape; //210mm 297mm; // A4 format 
  @bottom-center { content: element(footer);} // if you want footer
  @top-center { content: element(header); } // if you want header
}

div.break {
  page-break-after:always;
}
</style>    

    </head>
    <body>
        <div id="list-offerHeader" class="content scaffold-list" role="main">
                <h1><g:message code="default.offer.report.label" args="[entityName]" /></h1>
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
                    <g:render template="OfferReportHeader" model="[offerHeader:offerHeader]"/>

            <g:render template="OfferReportDetail" model="[offerDetail:offerDetail]"/>
<!--<div class="break"/>-->
        </div>
    </body>
</html>
