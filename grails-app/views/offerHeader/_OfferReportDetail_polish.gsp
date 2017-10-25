<fieldset class="form">
    <h2>Produkty</h2>

    <table style="width:100%">
        <colgroup>
        </colgroup>
        <thead>
            <tr>
                <th id="is_header1">Wymiary</th>
                <th id="is_header1">Dlugosc</th>
                <th id="is_header1">Drewno</th>
                <th id="is_header1">Ilosc (m3)</th>
                <th id="is_header1">Wilgotnosc (%)</th>
                <th id="is_header1">Cena (m3)</th>
                <th id="is_header1">Klasa</th>
                <th id="is_header1">Certyfikat</th>
                <th id="of_header1">Cena</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${offerHeader.offerDetails.sort{it.dimension}}" status="i" var="od">
                <tr>
                    <td id="is_center">${od?.dimension}</td>
                    <td id="is_center">${od?.lengthDescr}</td>
                    <g:if test="${od.species == 'Redwood'}">
                        <td id="is_center">Sosna</td>
                    </g:if>
                    <g:elseif test="${od.species == 'Whitewood'}">
                        <td id="is_center">Swierk</td>
                    </g:elseif>
                    <g:else>
                        <td id="is_center">${od.species}</td>
                    </g:else>
                     <td id="is_center">${od?.volumeOffered}</td>
                    <td id="is_center">${od?.kd}</td>
                    <td id="is_center"><g:endPriceM3 offerDetail="${od}"></g:endPriceM3></td>
                    <td id="is_center">${od?.grade}</td>
                    <td id="is_center">${od?.choosedCert}</td>
                    <td id="of_detail1">${od?.endPriceNoDecimal}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
    <h1></h1>
</fieldset>
