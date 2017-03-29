<div id="divToUpdate">
    <table>
        <thead>
            <tr><g:sortableColumn property='toOffer' title='x' />
                <g:sortableColumn property="Id" title='Id' />
                <g:sortableColumn property="sawMill" title='Mill' />
                <g:sortableColumn property="product" title='Product' />
                <g:sortableColumn property="length" title='Length' />
                <g:sortableColumn property="volumeAvailable" title='Orig.' />
                <g:sortableColumn property="volumeOffered" title='Offer.' />
                <g:sortableColumn property="onOrder" title='Bkd' />
                <g:sortableColumn property="volumeRest" title='Avail' />
                <g:sortableColumn property="volumeRestInclOffers" title='InclOff' />
                <g:sortableColumn property="volumeUnit" title='Unit' />
                <g:sortableColumn property="currency" title='Cur' />
                <g:sortableColumn property="kd" title='KD' />
                <g:sortableColumn property="priceFSC" title='FSC' />
                <g:sortableColumn property="priceFSC" title='PEFC' />
                <g:sortableColumn property="priceFSC" title='CW' />
                <g:sortableColumn property="priceFSC" title='UC' />
                <g:sortableColumn property="weekStart" title='Start' />
                <g:sortableColumn property="weekEnd" title='End' />
                <g:sortableColumn property="status" title='Status' />
                <g:sortableColumn property="availW01" title="${myTag.weekNo(offset: "0")}" />
                <g:sortableColumn property="availW02" title="${myTag.weekNo(offset: "1")}" />
                <g:sortableColumn property="availW03" title="${myTag.weekNo(offset: "2")}" />
                <g:sortableColumn property="availW04" title="${myTag.weekNo(offset: "3")}" />
                <g:sortableColumn property="availW05" title="${myTag.weekNo(offset: "4")}" />
                <g:sortableColumn property="availW06" title="${myTag.weekNo(offset: "5")}" />
                <g:sortableColumn property="availW07" title="${myTag.weekNo(offset: "6")}" />
                <g:sortableColumn property="availW08" title="${myTag.weekNo(offset: "7")}" />
                <g:sortableColumn property="availW09" title="${myTag.weekNo(offset: "8")}" />
                <g:sortableColumn property="availW10" title="${myTag.weekNo(offset: "9")}" />
            </tr>
        </thead>
        <tbody>

            <g:each in="${prodBuffer}" status="i" var="pb"> 
                <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                    <td><g:checkBox name="toOffer" value="${pb.id}" checked="false"  /></td>
                    <td><g:link action="edit_prodbuffer" id="${pb.id}">${pb.id}</g:link></td>
                    <td>${pb.sawMill}</td>
                    <td>${pb.product}</td>
                    <td>${pb.length}</td>
                    <td>${pb.volumeAvailable}</td>
                    <td><a id="${pb.id}" onclick="listOffers(${pb.id})">${pb.volumeOffered}</a></td> 
                    <td>${pb.onOrder}</td>
                    <td>${pb.volumeRest}</td>
                    <td>${pb.volumeRestInclOffers}</td>
                    <td>${pb.volumeUnit}</td>
                    <td>${pb.currency}</td>
                    <td>${pb.kd}</td>
                    <td>${pb.priceFSC}</td>
                    <td>${pb.pricePEFC}</td>
                    <td>${pb.priceCW}</td>
                    <td>${pb.priceUC}</td>
                    <td>${pb.weekStart}</td>
                    <td>${pb.weekEnd}</td>
                    <td>${pb.status}</td>
                    <td>${pb.availW01}</td>
                    <td>${pb.availW02}</td>
                    <td>${pb.availW03}</td>
                    <td>${pb.availW04}</td>
                    <td>${pb.availW05}</td>
                    <td>${pb.availW06}</td>
                    <td>${pb.availW07}</td>
                    <td>${pb.availW08}</td>
                    <td>${pb.availW09}</td>
                    <td>${pb.availW10}</td>
                </tr>
            </g:each>

        </tbody>
    </table>
</div>