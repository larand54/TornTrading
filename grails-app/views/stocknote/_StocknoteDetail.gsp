<fieldset class="form">
    <h2>Products</h2>

    <table style="width:100%">
        <thead>
            <tr>
                <th>Sawmill</th>
                <th>Product</th>
                <th>Length</th>
                <th>Volume</th>
                <th>KD</th>
                <th>Grade</th>
                <th id="center">FSC</th>
                <th id="center">PEFC</th>
                <th id="center">Unctfd</th>
                <th id="center">Ctrl Wood</th> 
            </tr>
        </thead>
        <tbody>
            <g:each in="${offerHeader.offerDetails}" status="i" var="od">
                <tr>
                    <td>${od?.sawMill}</td>
                    <td>${od?.product}</td>
                    <td>${od?.lengthDescr}</td>
                    <td>${od?.volumeOffered}</td>
                    <td>${od?.kd}</td>
                    <td>${od?.grade}</td>
                    <td><g:if test="${od?.priceFSC > 0.1}"><div id="cert_img">X</div></g:if></td>
                    <td><g:if test="${od?.pricePEFC > 0.1}"><div id="cert_img">X</div></g:if></td>
                    <td><g:if test="${od?.priceUC > 0.1}"><div id="cert_img">X</div></g:if></td>
                    <td><g:if test="${od?.priceCW > 0.1}"><div id="cert_img">X</div></g:if></td>
<!--                     <td><g:if test="${od?.priceFSC > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
                    <td><g:if test="${od?.pricePEFC > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
                    <td><g:if test="${od?.priceUC > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
                    <td><g:if test="${od?.priceCW > 0.1}"><asset:image src="checkOut16x16.png" width="16" height="16"/></g:if></td>
-->                </tr>
            </g:each>
        </tbody>
    </table>
</fieldset>
