<h3>
    <table style="width:100%">
        <tr>
            <td>Offer no: <span class="normal"> ${offerHeader?.id}</span></td>
            <td>To: <span class="normal">${offerHeader?.company}</span></td>
            <td>Valid until: <span class="normal"><g:formatDate format="yyyy-MM-dd" date="${offerHeader?.validUntil}"/></span></td>
        </tr>
    </table>
</h3>
<table style="width:100%">
    <colgroup>
        <col width="30%"/>
        <col width="50%"/>
        <col width="11%"/>
        <col width="9%"/>
    </colgroup>
    <thead>
        <tr>
            <th><h2> Delivery </h2></th>
            <th><span class="normal"> Week ${offerHeader?.weekOfDelivery}</span></th>
        </tr>
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
            <td>${offerHeader?.termsOfDelivery}  ${offerHeader?.city},${offerHeader?.country}</td>
            <td id="sum_right">${offerHeader?.freight}</td>
            <td></td>
        </tr>
        <tr>
            <td><h2>Payment terms</h2></td><td>${offerHeader?.termsOfPayment}</td>
        </tr>
    </tbody>
</table>

