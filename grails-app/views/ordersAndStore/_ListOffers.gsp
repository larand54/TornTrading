<div id="offerList">
    <g:set var="entityName" value='Offers' />
    <div id="list-offers" class="content scaffold-list" role="main">
        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        <table style="width:100%">
            <thead>
                <tr>
                    <g:sortableColumn property="product" title='Product' />
                    <g:sortableColumn property="lengthDescr" title='Length' />
                    <g:sortableColumn property="volumeOffered" title='Volume' />
                    <g:sortableColumn property="kd" title='KD' />
                    <g:sortableColumn property="choosedCert" title='Cert' />
                    <g:sortableColumn property="markup" title='Markup' />
                    <g:sortableColumn property="freight" title='Freight' />
                    <g:sortableColumn property="endPrice" title='Price' />
                    <g:sortableColumn property="priceFSC" title='FSC' />
                    <g:sortableColumn property="pricePEFC" title='PEFC' />
                    <g:sortableColumn property="priceUC" title='UC' />
                    <g:sortableColumn property="priceCW" title='CW' />
                </tr>
            </thead>
            <tbody>
                <g:each in="${offerDetails}" status="i" var="od">
                    <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                        <td><g:link class="edit" action="edit" resource="offerDetail" id="${od?.id}"> ${od.product?.encodeAsHTML()}</g:link></td>
                        <td>${od?.lengthDescr}</td>
                        <td>${od?.volumeOffered}</td>
                        <td>${od?.kd}</td>
                        <td>${od?.choosedCert}</td>
                        <td>${od?.markup}</td>
                        <td>${od?.freight}</td>
                        <td>${od?.endPrice}</td>
                        <td>${od?.priceFSC}</td>
                        <td>${od?.pricePEFC}</td>
                        <td>${od?.priceUC}</td>
                        <td>${od?.priceCW}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <g:paginate total="${offersCount ?: 0}" />
        </div>
    </div>
</div>
