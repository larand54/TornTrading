<fieldset class="form">
    <h2>Produkty</h2>

    <table style="width:100%">
        <colgroup>
        </colgroup>
        <thead>
            <tr>
                <th id="is_header1">Wymiary</th>
                <th id="is_header1">Dlugosc</th>
                <th id="is_header1">Ilosc (m3)</th>
                <th id="is_header1">Wilgotnosc (%)</th>
                <th id="is_header1">Cena (m3)</th>
                <th id="is_header1">Klasa</th>
                <th id="is_header1">Certyfikat</th>
                <th id="of_header1">Cena</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${offerHeader.offerDetails}" status="i" var="od">
                <tr>
                    <td id="is_center">${od?.dimension}</td>
                    <td id="is_center">${od?.lengthDescr}</td>
                    <td id="is_center">${od?.volumeOffered}</td>
                    <td id="is_center">${od?.kd}</td>
                    <td id="is_center"><g:endPriceM3 offerDetail="${od}"></g:endPriceM3></td>
                    <td id="is_center">${od?.grade}</td>
                    <td id="is_center">${od?.choosedCert}</td>
                    <td id="of_detail1">${od?.endPrice}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
    <h1></h1>
</fieldset>
