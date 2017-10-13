<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stocknote.Report.label', default: 'Stocknote Report')}" />
        <title><g:message code="default.stocknote.report.label" args="[entityName]" /></title>
        <style type="text/css">
            @page {
                size: A4 landscape; //210mm 297mm; // A4 format 
                @bottom-center { content: element(footer);} // if you want footer
                @top-center { content: element(header); } // if you want header
            }

            div.break {
                page-break-after:always;
            }
div.footer {
    page-break-inside: avoid;
}

span.normal {
  font-weight: normal;
}
            .StocknoteTitle {
                font-size: 1.8em;
                font-weight: bold;
                vertical-align: bottom
            }
            .StocknoteSpec {
                font-size: 1.2em;
                font-weight: bold;
                vertical-align: bottom
            }
            div.row {
                position: relative;
                clear: both;
            }
            table.tborder, th, td {
#                border: 1px solid black;
 #               border-collapse: collapse;
            }

            div.header_detail {
                font-size:  1.5em;
                font-weight: bold;
                margin-left: 24em;
            }
            #is_center {
                background-color: #dddddd;
                text-align: center; 
            }
            #pv_center {
                background-color: #e5e5e5;
                text-align: center; 
            }
            #is_header1 {
              background-color: #cccccc;  
            }
            #pv_header1 {
              background-color: #d5d5d5;  
            }
            .odd {
                background-color "dddddd;
            }
            .even {
                background-color "eeeeee;
            }
        </style>    
    </head>
    <body>
        <div id="report-Stocknote" class="content scaffold-show" role="main">
            <table style="width:75%">
                <tr>
                <td class="StocknoteTitle">In Stock and planned production</td>
                <td class="StocknoteSpec">    ${this.offerHeader.sawMill}</td>
                <td class="StocknoteSpec">    ${this.offerHeader.species}</td>
                </tr>
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
            <g:render template="StocknoteHeader" model="[offerHeader:offerHeader]"/>

            <g:render template="StocknoteDetail" model="[offerDetail:offerDetail]"/>
            <g:render template="StocknoteFooter" model="[offerDetail:offerDetail]"/>

<!--            <div class="break"/>-->
        </div>
    </body>
</html>
