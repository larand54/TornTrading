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

#sum_right {
                text-align: right; 
            }

#is_center {
                background-color: #dddddd;
                text-align: center; 
            }
#is_header1 {
              background-color: #c0c0c0;  
              text-align: center; 
            }
#of_header1 {
              background-color: #c0c0c0;  
            }
#of_detail1 {
                background-color: #dddddd;
            }

</style>    

    </head>
    <body>
        <div id="list-offerHeader" class="content scaffold-list" role="main">
<!--
            <h1><g:message code="default.offer.report.title" args="[offerHeader.sawMill, offerHeader.species]" /></h1>
-->
            <table> 
                <colgroup>
                    <col width="15%"/>
                    <col width="35%"/>
                    <col width="45%"/>
                    <col width="5%"/>  
                </colgroup>
                <thead>
                </thead>
                <tbody>
                <tr>
                    
                        <td><h1>Offer</h1></td>
                        <td><h1>${offerHeader.sawMill}</h1></td>
                        <td><h1>${offerHeader.species}</h1></td>
                    
                    <td><h3><g:formatDate format="yyyy-MM-dd" date="${offerHeader.dateCreated}"/></h3></td>
                </tr>
                </tbody>
            </table>
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
            <g:render template="OfferReportFooter" model="[offerDetail:offerDetail]"/>
<!--<div class="break"/>-->
        </div>
    </body>
</html>
