<table style="width:100%">
<!--    <td>Välj offert: <g:select name="offerHeader" from="${offerDetail.offerHeader}"/> -->
    <g:field type="hidden" name="offerHeader" value="${offerDetail?.offerHeader?.id}"/>
    <tr>
        <td>
            <fieldset>
                <legend>Product specificationes</legend>
                Product:  <g:field class="elements" type="text" name="product" required="y" value="${offerDetail?.product}" size="60"/>
                Length:     <g:field class="elements" type="text" name="lengthDescr"  required="Y" value="${offerDetail?.lengthDescr}" size="30"/>
                Grade:  <g:field class="elements" type="text" name="grade"  value="${offerDetail?.grade}" size="8"/>
                KD:       <g:field class="elements" type="text" name="kd"  value="${offerDetail?.kd}" size="4"/>
            </fieldset>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td>
            <fieldset>
                <legend>Prices</legend>
                markup <g:field class="elements" type="number decimal" name="markup"  value="${fieldValue(bean: offerDetail, field: 'markup')}" style="width: 6em"/>
                freight <g:field class="elements" type="number decimal" name="freight"  value="${fieldValue(bean: offerDetail, field: 'freight')}" style="width: 6em"/>   
                <g:select name="availableCert" from="${offerDetail.availableCert}" value="${offerDetail.choosedCert}" />    
                
                priceFSC: ${offerDetail.priceFSC}
                priceUC: ${offerDetail.priceUC}
                priceCW: ${offerDetail.priceCW}
                pricePEFC: ${offerDetail.pricePEFC}
                endPrice: ${offerDetail.endPrice}

            </fieldset>
        </td>
    </tr>
</table>
<fieldset class="form">
    <legend>Delivery</legend>
    <table>
        <tr>
            <td>
                Volume : <g:field class="elements" type="number decimal" name="volumeOffered" min="1" max="10000" required="Y" value="${fieldValue(bean: offerDetail, field: 'volumeOffered')}" style="width: 6em"/>
            </td>
        <td>
            <fieldset>
                <legend>Delivery period, week</legend>
                From: <g:field class="elements" type="text" name="weekStart" value="${offerDetail?.weekStart}" size="4"/>
                To: <g:field class="elements" type="text" name="weekEnd"  value="${offerDetail?.weekEnd}" size="4"/>
            </fieldset>
        </td>

        </tr>
    </table>
</fieldset>