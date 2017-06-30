<fieldset class="form">
    <h2>Products</h2>

    <table style="width:100%">
        <colgroup>
        </colgroup>
        <thead>
            <tr>
                <th id="is_header1">Dimension</th>
                <th id="is_header1">Length</th>
                <th id="is_header1">Volume(${offerHeader.volumeUnit})</th>
                <th id="is_header1">KD(%)</th>
                <th id="is_header1">Price m3</th>
                <th id="is_header1">Grade</th>
                <th id="is_header1">Cert</th>
                <th id="of_header1">Price</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${offerHeader.offerDetails.sort{it.dimension}}" status="i" var="od">
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
