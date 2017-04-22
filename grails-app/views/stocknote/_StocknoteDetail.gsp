<fieldset class="form">
    <h2>Products</h2>
    <table style="width:100%">
        <thead>
            <tr>
                <th>Species</th>
                <th>Dimension</th>
                <th>Length</th>
                <th>Volume</th>
                <th>KD</th>
                <th>Grade</th>
                <th id="nocenter">FSC</th>
                <th id="nocenter">PEFC</th>
                <th id="nocenter">Unctfd</th>
                <th id="nocenter">Ctrl Wood</th> 
            </tr>
        </thead>
        <tbody>
            <g:each in="${offerHeader.offerDetails}" status="i" var="od">
                <tr>
                    <td>${od?.species}</td>
                    <td>${od?.dimension}</td>
                    <td>${od?.lengthDescr}</td>
                    <td>${od?.volumeOffered}</td>
                    <td>${od?.kd}</td>
                    <td>${od?.grade}</td>
<!--                     <td><g:if test="${od?.priceFSC > 0.1}"><div id="cert_img">X</div></g:if></td>
                    <td><g:if test="${od?.pricePEFC > 0.1}"><div id="cert_img">X</div></g:if></td>
                    <td><g:if test="${od?.priceUC > 0.1}"><div id="cert_img">X</div></g:if></td>
                    <td><g:if test="${od?.priceCW > 0.1}"><div id="cert_img">X</div></g:if></td>
-->
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

                </tr>
            </g:each>
        </tbody>
    </table>
</fieldset>
