<table style="width:100%">
    <colgroup>
        <col width="60%"/>
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
            <td>Produkty:</td><td id="sum_right">${offerHeader.offerDetails.sum{it.endPrice}}</td><td></td>
        </tr>
        <tr>
            <td></td><td>Wysylka:</td><td id="sum_right">${offerHeader?.freight}</td><td></td>
        </tr>
        <tr><td></td><td></td><td></td><td></td></tr>
        <tr>
            <td></td><td>Wartosc:</td><td id="sum_right">${offerHeader.offerDetails.sum{it.endPrice}+offerHeader.freight}</td><td></td>
        </tr>
        
    </tbody>
</table>