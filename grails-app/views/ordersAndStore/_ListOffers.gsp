<g:if test = "${offerDetails != null}"> 
    <g:set var="entityName" value='Offer' />
    <div id="list-offers" class="content scaffold-list" role="main">
        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        <table style="width:100%">
            <thead>
                <tr>
                    <g:sortableColumn property="millOfferId" title='Prod' />
                    <g:sortableColumn property="species" title='Species' />
                    <g:sortableColumn property="dimension" title='Dimension' />
                    <g:sortableColumn property="lengthDescr" title='Length' />
                    <g:sortableColumn property="volumeOffered" title='Volume(m3)' />
                    <g:sortableColumn property="kd" title='KD(%)' />
                    <g:sortableColumn property="choosedCert" title='Cert' />
                    <g:sortableColumn property="markup" title='Agent fee' />
                    <g:sortableColumn property="endPrice" title='Price' />
                    <g:sortableColumn property="priceFSC" title='FSC' />
                    <g:sortableColumn property="pricePEFC" title='PEFC' />
                    <g:sortableColumn property="priceUC" title='UC' />
                    <g:sortableColumn property="priceCW" title='CW' />
                    <g:sortableColumn property="status" title='Status' />
                </tr>
            </thead>
            <tbody>
                <g:each in="${offerDetails}" status="i" var="od">
                    <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                        <td>[${od.millOfferID}]</td>
                        <td>[${od.species}]</td>
                        <td><g:link class="edit" action="edit" resource="offerDetail" id="${od?.id}"> ${od.dimension?.encodeAsHTML()}</g:link></td>
                        <td>${od?.lengthDescr}</td>
                        <td>${od?.volumeOffered}</td>
                        <td>${od?.kd}</td>
                        <td>${od?.choosedCert}</td>
                        <td>${od?.markup}</td>
                        <td>${od?.endPrice}</td>
                        <td>${od?.priceFSC}</td>
                        <td>${od?.pricePEFC}</td>
                        <td>${od?.priceUC}</td>
                        <td>${od?.priceCW}</td>
                        <td>${od?.offerHeader.status}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <g:paginate total="${offersCount ?: 0}" />
        </div>
    </div>
</g:if>

