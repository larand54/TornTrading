<fieldset class="form">
    <h2>Produkty</h2>
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
                <th id="is_header1" colspan="9">Na Magazynie</th>
                <th id="pv_header1" colspan="12">Planowana produkcja na sprzedaz</th>
            </tr>
            
            <tr>
                <th id="is_center">Wymiar (mm)</th>
                <th id="is_center">Dlugosc</th>
                <th id="is_center">Wilgotnosc (%)</th>
                <th id="is_center">Klasa</th>
                <th id="is_center">FSC</th>
                <th id="is_center">PEFC</th>
                <th id="is_center">Un Certified</th>
                <th id="is_center">Ctrl Wood</th> 
                <th id="is_center">Ilosc (m3)</th>
                <g:each var="week" in="${(0..11)}">
                    <th id="pv_center">${myTag.weekNo_polish(offset: week+1)}</th>            
                </g:each>
            </tr>
            
        </thead>
        <tbody>
            <g:each in="${offerHeader.offerDetails.sort{it.dimension}}" status="i" var="od">
                <tr>
                    
                    <td id="is_center">${od?.dimension}</td>
                    <td id="is_center">${od?.lengthDescr}</td>
                    <td id="is_center">${od?.kd}</td>
                    <td id="is_center">${od?.grade}</td>
                        <td id="is_center"><g:if test="${od?.priceFSC > 0.1}"><div id="cert_img">X</div></g:if></td>
                        <td id="is_center"><g:if test="${od?.pricePEFC > 0.1}"><div id="cert_img">X</div></g:if></td>
                        <td id="is_center"><g:if test="${od?.priceUC > 0.1}"><div id="cert_img">X</div></g:if></td>
                        <td id="is_center"><g:if test="${od?.priceCW > 0.1}"><div id="cert_img">X</div></g:if></td>
                    <td id="is_center">${od?.volumeOffered}</td>

            
<!--
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
-->
                    
                    <g:each in="${od.availableVolumes}" status="j" var="pv">
                        <td id="pv_center">${pv.volume ?: ''}</td>
                    </g:each>
                    
                </tr>
            </g:each>
        </tbody>
    </table>
</fieldset>
