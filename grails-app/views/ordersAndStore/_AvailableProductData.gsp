<div id="divToUpdate">
    <table>
        <colgroup>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="1%"/>
            <col width="83%"/>
        </colgroup>
        <thead>
            <tr>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
                    <g:sortableColumn property='delete' title='Del' />
                    <g:sortableColumn property='toOffer' title='Offer' />
                </sec:ifAnyGranted>    
                <g:sortableColumn property="id" title='Id' />
                <g:sortableColumn property="sawMill" title='Mill' />
                <g:sortableColumn property="species" title='Species' />
                <g:sortableColumn property="dimension" title='Dimension' />
                <g:sortableColumn property="length" title='Length' />
                <g:sortableColumn property="currency" title='Cur' />
                <g:sortableColumn property="kd" title='KD' />
                <g:sortableColumn property="priceFSC" title='FSC' />
                <g:sortableColumn property="priceFSC" title='PEFC' />
                <g:sortableColumn property="priceFSC" title='CW' />
                <g:sortableColumn property="priceFSC" title='UC' />
                <g:sortableColumn property="volumeInStock" title='InStock' />
                <g:sortableColumn property="volumeOnOrder" title='Sold' />
                <g:sortableColumn property="volumeOffered" title='Offer' />
                <g:sortableColumn property="volumeAvailable" title='Avail' />
                <g:sortableColumn property="volumeUnit" title='Unit' />
                <g:sortableColumn property="availW01" title="${myTag.weekNo(offset: "1")}" />
                <g:sortableColumn property="availW02" title="${myTag.weekNo(offset: "2")}" />
                <g:sortableColumn property="availW03" title="${myTag.weekNo(offset: "3")}" />
                <g:sortableColumn property="availW04" title="${myTag.weekNo(offset: "4")}" />
                <g:sortableColumn property="availW05" title="${myTag.weekNo(offset: "5")}" />
                <g:sortableColumn property="availW06" title="${myTag.weekNo(offset: "6")}" />
                <g:sortableColumn property="availW07" title="${myTag.weekNo(offset: "7")}" />
                <g:sortableColumn property="availW08" title="${myTag.weekNo(offset: "8")}" />
                <g:sortableColumn property="availW09" title="${myTag.weekNo(offset: "9")}" />
                <g:sortableColumn property="availW10" title="${myTag.weekNo(offset: "10")}" />
                <g:sortableColumn property="availW11" title="${myTag.weekNo(offset: "11")}" />
                <g:sortableColumn property="availW12" title="${myTag.weekNo(offset: "12")}" />
            </tr>
        </thead>
        <tbody>

            <g:each in="${prodBuffer}" status="i" var="pb"> 
                <tr  class="${ (i % 2) == 0 ? 'even': 'odd'}">
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
                    <td>
                        <g:link action="deleteProduct" controller="ordersAndStore" params="[prodID:pb.id]"
                            onclick="return confirm('Are you sure?')">
                        X
                        </g:link>
                    </td>
                    <td><g:checkBox name="toOffer" value="${pb.id}" checked="false"  /></td>
                </sec:ifAnyGranted>    
                    <td><g:link action="edit_prodbuffer" id="${pb.id}">${pb.id}</g:link></td>
                    <td>${pb.sawMill}</td>
                    <td>${pb.species}</td>
                    <td>${pb.dimension}</td>
                    <td>${pb.length}</td>
                    <td>${pb.currency}</td>
                    <td>${pb.kd}</td>
                    <td>${pb.priceFSC}</td>
                    <td>${pb.pricePEFC}</td>
                    <td>${pb.priceCW}</td>
                    <td>${pb.priceUC}</td>
                    <td>${pb.volumeInStock}</td>
                    <td>${pb.volumeOnOrder}</td>
                    <td><div id="${pb.id}" class="offers" >${pb.volumeOffered}</div></td>
                    <td>${pb.volumeAvailable}</td>
                    <td>${pb.volumeUnit}</td>
                    <g:each in="${pb.plannedVolumes}" status="j" var="pv">
                        <td>${pv.volume}</td>
                    </g:each>
                </tr>
            </g:each>

        </tbody>
    </table>
</div>