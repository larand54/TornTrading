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
                <th>Cert</th>
                <th>End price</th>
<!--                <th>Price FSC</th>
                <th>Price PEFC</th>
                <th>Price UC</th>
                <th>Price CW</th> -->
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
                    <td>${od?.choosedCert}</td>
                    <td>${od?.endPrice}</td>
<!--                    <td>${od?.priceFSC}</td>
                    <td>${od?.pricePEFC}</td>
                    <td>${od?.priceUC}</td>
                    <td>${od?.priceCW}</td> -->
                </tr>
            </g:each>
        </tbody>
    </table>
</fieldset>
