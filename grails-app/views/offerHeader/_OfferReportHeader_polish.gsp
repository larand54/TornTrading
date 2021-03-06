<h3>
    <table style="width:100%">
        <tr>
            <td>Oferta nr : ${offerHeader?.id}</td>
            <td>Nabywca: <span class="normal">${offerHeader?.company}</span></td>
            <td>Wazna do: <span class="normal"><g:formatDate format="yyyy-MM-dd" date="${offerHeader?.validUntil}"/></span></td>
        </tr>
    </table>
</h3>
<table style="width:100%">
    <colgroup>
        <col width="30%"/>
        <col width="41%"/>
        <col width="20%"/>
        <col width="9%"/>
    </colgroup>
    <thead>
        <tr>
            <th><h2> Dostawa </h2></th>
            <th><span class="normal"> Tydzien ${offerHeader?.weekOfDelivery}</span></th>
        </tr>
        <tr>
            <th>Waluta</th>
            <th>Warunki dostawy (Incoterms)</th>
            <th id="sum_right">Koszt transportu</th>
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
    </tbody>
</table>

