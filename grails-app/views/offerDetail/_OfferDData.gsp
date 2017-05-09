<table style="width:100%">
<!--    <td>Välj offert: <g:select name="offerHeader" from="${offerDetail.offerHeader}"/> -->
    <g:field type="hidden" name="offerHeader" value="${offerDetail?.offerHeader?.id}"/>
    <tr>
        <td>
            <fieldset>
                <legend>Product specificationes</legend>
                Wood:  <g:select name="species" from="${offerDetail?.constrainedProperties.species.inList}" value="${offerDetail?.species}"/>
                Dimension:  <g:field class="elements" type="text" name="dimension" required="y" value="${offerDetail?.dimension}" size="60"/>
                Length:     <g:field class="elements" type="text" name="lengthDescr"  required="Y" value="${offerDetail?.lengthDescr}" size="30"/>
                Grade:  <g:select name="grade" from="${offerDetail?.constrainedProperties.grade.inList}" value="${offerDetail?.grade}"/>
                KD:       <g:field class="elements" type="text" name="kd"  value="${offerDetail?.kd}" size="4"/>
            </fieldset>
        </td>
    </tr>
</table>
<table>
        <colgroup>
            <col width="5%"/>
            <col width="20%"/>
            <col width="5%"/>
            <col width="5%"/>
            <col width="5%"/>
            <col width="5%"/>
            <col width="5%"/>
            <col width="40%"/>
        </colgroup>
    <tr>
            <fieldset>
                <legend>Prices</legend>
            </fieldset>
        <td></td>
        <td> markup <g:field class="elements" type="number decimal" name="markup"  value="${fieldValue(bean: offerDetail, field: 'markup')}" style="width: 6em"/>
                <g:select name="availableCert" from="${offerDetail.availableCert}" value="${offerDetail.choosedCert}" />    
        </td>        
        <td> FSC: ${offerDetail.priceFSC}</td>
        <td> UC: ${offerDetail.priceUC}</td>
        <td> CW: ${offerDetail.priceCW}</td>
        <td> PEFC: ${offerDetail.pricePEFC}</td>
        <td> EndPrice: ${offerDetail.endPrice}</td>


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