<div class="summary">
    <table style="width:100%">
        <colgroup>
            <col width="10%"/>
            <col width="11%"/>
            <col width="40%"/>
            <col width="11%"/>
            <col width="20%"/>
        </colgroup>
        <tbody>
            <tr><td colspan="2"><h2>Summary</h2></td><td></td><td colspan="2"><h2>Contact info</h2></td><td></td></tr>
            <tr>
                <td>Products:</td><td id="sum_right">${offerHeader.offerDetails.sum{it.endPrice}}</td>
                <td></td><td colspan="2">${us.company}</td>
            </tr>
            <tr>
                <td>Shipment:</td><td id="sum_right">${offerHeader?.freight}</td>
                <td></td><td>name:</td> <td>${us.name}</td>
            </tr>
            <tr><td></td><td></td><td></td><td>email:</td> <td>${us.email}</td></tr>
            <tr>
                <td>Total:</td><td id="sum_right">${offerHeader.offerDetails.sum{it.endPrice}+offerHeader.freight}</td>
                <td></td><td>tel:</td> <td>${us.tel}</td><td></td>
            </tr>
            <tr><td></td><td></td><td></td><td>phone:</td> <td>${us.phone}</td></tr>
            <tr><td></td><td></td><td></td><td>mobile:</td> <td>${us.mobile}</td></tr>

        </tbody>
    </table>
</div>
