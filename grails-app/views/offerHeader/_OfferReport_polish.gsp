<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'offerHeaderReport.label', default: 'Offer Report')}" />
        <title><g:message code="default.report.label" args="[entityName]" /></title>
<style type="text/css">
@page {
  //size: A4 landscape; //210mm 297mm; // A4 format 
  size: A4 portrait;
  margin: 1cm;
  @bottom-center { content: element(footer);} // if you want footer
  @top-center { content: element(header); } // if you want header
}

div.break {
  page-break-after:always;
}

div.summary {
    page-break-inside: avoid;
}
div.contact {
    page-break-inside: avoid;
}

span.normal {
  font-weight: normal;
}

.logotype {
    height: 57px; //80px;
    width: 712px; //1000px;
}

#sum_right {
                text-align: right; 
            }
            
#header_date{
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
                text-align: right; 
            }

</style>    
    </head>
    <body>
        <div id="list-offerHeader" class="content scaffold-list" role="main">
<!--
            <h1><g:message code="default.offer.report.title" args="[offerHeader.sawMill, offerHeader.species]" /></h1>
-->
    <rendering:inlinePng bytes="${imageBytes}" class="logotype"/>
            <table type="100%"> 
                <colgroup>
                    <col width="15%"/>
                    <col width="80%"/>
                    <col width="5%"/>  
                </colgroup>
                <thead>
                </thead>
                <tbody>
                <tr>    
                    <td><h1>Oferta</h1></td>
                    <td><h1>${offerHeader.sawMill}</h1></td>
                    
                    <td><h3><g:formatDate id="header_date" format="yyyy-MM-dd" date="${offerHeader.dateCreated}"/></h3></td>
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
                    <g:render template="OfferReportHeader_polish" model="[offerHeader:offerHeader]"/>

            <g:render template="OfferReportDetail_polish" model="[offerDetail:offerDetail]"/>
            <g:render template="OfferReportFooter_polish" model="[offerDetail:offerDetail]"/>
<!--<div class="break"/>-->
        </div>
    </body>
</html>
