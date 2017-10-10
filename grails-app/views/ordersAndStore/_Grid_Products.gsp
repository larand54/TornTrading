<table id="gridProducts" class="display">
    <colgroup>
        <col width="1em"/> <!-- Del -->
        <col width="1%"/> <!-- ID -->
        <col width="1%"/> <!-- Mill -->
        <col width="3%"/> <!-- Species -->
        <col width="8%"/> <!-- Dimension -->
        <col width="4%"/> <!-- Length -->
        <col width="5%"/> <!-- Grade -->
        <col width="3%"/> <!-- KD -->
        <col width="3%"/> <!-- Currency -->
        <col width="3%"/> <!-- FSC -->
        <col width="3%"/> <!-- PEFC -->
        <col width="3%"/> <!-- CW -->
        <col width="3%"/> <!-- UC -->
        <col width="3%"/> <!-- InStock -->
        <col width="3%"/> <!-- Sold -->
        <col width="3%"/> <!-- Offered -->
        <col width="3%"/> <!-- Available -->
        <col width="3%"/> <!-- W01 -->
        <col width="3%"/> <!-- W02 -->
        <col width="3%"/> <!-- W03 -->
        <col width="3%"/> <!-- W04 -->
        <col width="3%"/> <!-- W05 -->
        <col width="3%"/> <!-- W06 -->
        <col width="3%"/> <!-- W07 -->
        <col width="3%"/> <!-- W08 -->
        <col width="3%"/> <!-- W09 -->
        <col width="3%"/> <!-- W10 -->
        <col width="3%"/> <!-- W11 -->
        <col width="3%"/> <!-- W12 -->
    </colgroup>
    <thead>
        <tr>
            <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES,ROLE_SUPPLIER"> 
                <th>Del</th>
            </sec:ifAnyGranted>    
            <th>Id</th>
            <th>Mill</th>
            <th>Species</th>
            <th>Dimension</th>
            <th>Length</th>
            <th>Grade</th>
            <th>KD(%)</th>
            <th>Cur</th>
            <th>FSC</th>
            <th>PEFC</th>
            <th>CW</th>
            <th>UC</th>
            <th>InStock</th>
            <th>Sold</th>
            <th>Offer</th>
            <th>Avail(m3)</th>
            <th>${myTag.weekNo(offset: "1")}</th>
            <th>${myTag.weekNo(offset: "2")}</th>
            <th>${myTag.weekNo(offset: "3")}</th>
            <th>${myTag.weekNo(offset: "4")}</th>
            <th>${myTag.weekNo(offset: "5")}</th>
            <th>${myTag.weekNo(offset: "6")}</th>
            <th>${myTag.weekNo(offset: "7")}</th>
            <th>${myTag.weekNo(offset: "8")}</th>
            <th>${myTag.weekNo(offset: "9")}</th>
            <th>${myTag.weekNo(offset: "10")}</th>
            <th>${myTag.weekNo(offset: "11")}</th>
            <th>${myTag.weekNo(offset: "12")}</th>
        </tr>
    </thead>
    <tbody>

        <g:each in="${prodBuffer}" status="i" var="pb"> 
            <tr  class="${ (i % 2) == 0 ? 'even': 'odd'}">
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES, ROLE_SUPPLIER"> 
                    <td>
                        <g:link action="deleteProduct" controller="ordersAndStore" params="[prodID:pb.id]"
                        onclick="return confirm('Are you sure?')">
                        X
                        </g:link>
                    </td>
                </sec:ifAnyGranted>    
                <td> <g:link action="edit" controller="prodBuffer" params="[id:pb.id]"> ${pb.id}</g:link></td>
                <td>${pb.sawMill?:'UnDefined'}</td>
                <td>${pb.species}</td>
                <td>${pb.shortDim}</td>
                <td>${pb.shortLength}</td>
                <td>${pb.grade}</td>
                <td>${pb.kd}</td>
                <td>${pb.currency}</td>
                <td>${pb.priceFSC}</td>
                <td>${pb.pricePEFC}</td>
                <td>${pb.priceCW}</td>
                <td>${pb.priceUC}</td>
                <td>${pb.volumeInStock}</td>
                <td>${pb.volumeOnOrder}</td>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
                    <td><div id="${pb.id}" class="offers" >${pb.volumeOffered}</div></td>
                </sec:ifAnyGranted>    
                <sec:ifNotGranted roles="ROLE_ADMIN,ROLE_SALES">    
                    <td>${pb.volumeOffered}</td>
                </sec:ifNotGranted>    
                <td>${pb.volumeAvailable}</td>
                <g:each in="${pb.plannedVolumes}" status="j" var="pv">
                    <td>${pv.volume}</td>
                </g:each>
            </tr>
        </g:each>
    </tbody>
</table>
