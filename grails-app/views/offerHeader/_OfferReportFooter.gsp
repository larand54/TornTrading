<table style="width:100%">
    <colgroup>
        <col width="58%"/>
        <col width="22%"/>
        <col width="11%"/>
        <col width="11%"/>
    </colgroup>
    <thead>
        <tr></tr>
        <tr></tr>
    </thead>
    <tbody>
        <tr>
            <td></td>
            <td>Products:</td><td id="sum_right">${offerHeader.offerDetails.sum{it.endPrice}}</td><td></td>
        </tr>
        <tr>
            <td></td><td>Shipment:</td><td id="sum_right">${offerHeader?.freight}</td><td></td>
        </tr>
        <tr><td></td><td></td><td></td><td></td></tr>
        <tr>
            <td></td><td>Total:</td><td id="sum_right">${offerHeader.offerDetails.sum{it.endPrice}+offerHeader.freight}</td><td></td>
        </tr>
        
    </tbody>
</table>