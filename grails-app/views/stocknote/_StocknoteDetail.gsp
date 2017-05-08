<fieldset class="form">
    <h2>Products</h2>
    <table class="tborder" style="width:100%">
        <colgroup>
            <col width="10%"/>
            <col width="19%"/>
            <col width="4%"/>
            <col width="6%"/>
            <col width="6%"/>
            <col width="6%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
            <col width="3%"/>
        </colgroup>
        <thead>
            <tr>
                <th colspan="9">In Stock</th>
                <th colspan="12">Planned Production for sale</th>
            </tr>
            <tr>
                <th>Dim [mm]</th>
                <th>Length</th>
                <th>Volume</th>
                <th>KD</th>
                <th>Grade</th>
                <th id="nocenter">FSC</th>
                <th id="nocenter">PEFC</th>
                <th id="nocenter">Un Certified</th>
                <th id="nocenter">Ctrl Wood</th> 
                <g:each in="${prodBuffer.plannedVolumes}" status="j" var="pv">
                    <th>${myTag.weekNo(offset: j+1)}</th>            
                </g:each>
            </tr>
        </thead>
        <tbody>
            <g:each in="${offerHeader.offerDetails}" status="i" var="od">
                <tr>
                    <td>${od?.dimension}</td>
                    <td>${od?.lengthDescr}</td>
                    <td>${od?.volumeOffered}</td>
                    <td>${od?.kd}</td>
                    <td>${od?.grade}</td>

                    <g:if test="${imageBytes!= null}">
                        <td><g:if test="${od?.priceFSC > 0.1}"><rendering:inlinePng bytes="${imageBytes}" /></g:if></td>
                        <td><g:if test="${od?.pricePEFC > 0.1}"><rendering:inlinePng bytes="${imageBytes}" /></g:if></td>
                        <td><g:if test="${od?.priceUC > 0.1}"><rendering:inlinePng bytes="${imageBytes}" /></g:if></td>
                        <td><g:if test="${od?.priceCW > 0.1}"><rendering:inlinePng bytes="${imageBytes}" /></g:if></td>
                    </g:if>
                    <g:else>
                        <td><g:if test="${od?.priceFSC > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
                        <td><g:if test="${od?.pricePEFC > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
                        <td><g:if test="${od?.priceUC > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
                        <td><g:if test="${od?.priceCW > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
                    </g:else>
                    <g:each in="${prodBuffer.plannedVolumes}" status="j" var="pv">
                        <td>${pv.volume}</td>
                    </g:each>
                </tr>
            </g:each>
        </tbody>
    </table>
</fieldset>
