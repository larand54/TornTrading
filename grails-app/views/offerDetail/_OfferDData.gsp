<table style="width:100%">
<!--    <td>Välj offert: <g:select name="offerHeader" from="${offerDetail.offerHeader}"/> -->
<g:field type="hidden" name="offerHeader" value="${offerDetail?.offerHeader?.id}"/>
    <tr>
        <td>
            <fieldset>
                <legend>Produktspecifikationer</legend>
                Produkt:  <g:field class="elements" type="text" name="product" required="y" value="${offerDetail?.product}" size="60"/>
                Längd:     <g:field class="elements" type="text" name="lengthDescr"  required="Y" value="${offerDetail?.lengthDescr}" size="30"/>
                Kvalitet:  <g:field class="elements" type="text" name="grade"  value="${offerDetail?.grade}" size="8"/>
                KD%:       <g:field class="elements" type="text" name="kd"  value="${offerDetail?.kd}" size="4"/>
            </fieldset>
        </td>
    </tr>
</table>
<fieldset class="form">
    <legend>Leverans</legend>
    <table>
        <tr>
            <td>
                Volym : <g:field class="elements" type="number decimal" name="volumeOffered" min="1" max="10000" required="Y" value="${fieldValue(bean: offerDetail, field: 'volumeOffered')}" style="width: 6em"/>
            </td>
        <td>
            <fieldset>
                <legend>Leveransperiod, vecka</legend>
                Från: <g:field class="elements" type="text" name="weekStart" value="${offerDetail?.weekStart}" size="4"/>
                Till: <g:field class="elements" type="text" name="weekEnd"  value="${offerDetail?.weekEnd}" size="4"/>
            </fieldset>
        </td>

        </tr>
    </table>
</fieldset>