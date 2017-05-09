<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'offerHeader.label', default: 'Offer')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#edit-offerHeader" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><a class="print" href="${createLink(action:'report', params:[id:this.offerHeader.id])}"><g:message code="default.offer.report.label"/></a></li>
                <g:link url="${request.getHeader('referer')}">Back</g:link> 
            </ul>
            </div>
            <div id="edit-offerHeader" class="content scaffold-edit" role="main">
                <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
            <g:form resource="${this.offerHeader}" method="PUT">
                <g:hiddenField name="version" value="${this.offerHeader?.version}" />
                <fieldset class="form">
                    <g:render template="OfferHData" model="[offerHeader:offerHeader]"/> 
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
            <fieldset class="form">
                <legend>Products</legend>

                <table style="width:100%">
                    <thead>
                        <tr>
                            <g:sortableColumn property="${offerHeader.offerDetails.species}" title='Wood' />
                            <g:sortableColumn property="${offerHeader.offerDetails.dimension}" title='Dimension' />
                            <g:sortableColumn property="${offerHeader.offerDetails.lengthDescr}" title='Length' />
                            <g:sortableColumn property="${offerHeader.offerDetails.volumeOffered}" title='Volume' />
                            <g:sortableColumn property="${offerHeader.offerDetails.kd}" title='KD' />
                            <g:sortableColumn property="${offerHeader.offerDetails.grade}" title='Grade' />
                            <g:sortableColumn property="${offerHeader.offerDetails.choosedCert}" title='Cert' />
                            <g:sortableColumn property="${offerHeader.offerDetails.markup}" title='Markup' />
                            <g:sortableColumn property="${offerHeader.offerDetails.endPrice}" title='End price' />
                            <g:sortableColumn property="${offerHeader.offerDetails.priceFSC}" title='Price FSC' />
                            <g:sortableColumn property="${offerHeader.offerDetails.pricePEFC}" title='Price PEFC' />
                            <g:sortableColumn property="${offerHeader.offerDetails.priceUC}" title='Price UC' />
                            <g:sortableColumn property="${offerHeader.offerDetails.priceCW}" title='Price CW' />
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${offerHeader.offerDetails}" status="i" var="od">
                            <tr>
                                <td>${od?.species}</td>
                                <td><g:link class="edit" action="edit" resource="offerDetail" id="${od?.id}"> ${od.dimension?.encodeAsHTML()}</g:link></td>
                                <td>${od?.lengthDescr}</td>
                                <td>${od?.volumeOffered}</td>
                                <td>${od?.kd}</td>
                                <td>${od?.grade}</td>
                                <td>${od?.choosedCert}</td>
                                <td>${od?.markup}</td>
                                <td>${od?.endPrice}</td>
                                <td>${od?.priceFSC}</td>
                                <td>${od?.pricePEFC}</td>
                                <td>${od?.priceUC}</td>
                                <td>${od?.priceCW}</td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </fieldset>
        </div>
    </body>
</html>
