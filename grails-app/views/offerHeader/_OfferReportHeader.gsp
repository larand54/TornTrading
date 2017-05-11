<h3>Offer no: ${offerHeader?.id}</h3>
<h2> Delivery </h2>
<table style="width:100%">
    <colgroup>
        <col width="20%"/>
        <col width="60%"/>
        <col width="11%"/>
        <col width="9%"/>
    </colgroup>
    <thead>
        <tr>
            <th>Currency</th>
            <th>Terms of delivery (Incoterms)</th>
            <th id="sum_right">Shipment</th>
            <th></th>
    </tr>
    </thead>
    <tbody>
        <tr>

            <td>${offerHeader?.currency}</td>
            <td>${offerHeader?.termsOfDelivery}</td>
            <td id="sum_right">${offerHeader?.freight}</td>
            <td></td>
        </tr>
    </tbody>
</table>

