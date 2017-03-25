<fieldset class="form">
    Ange status: <g:select name="status" from="${offer.constrainedProperties.status.inList}" 
    value="${offer?.status}" required="Y"/>
</fieldset>
<table style="width:100%">
    <tr>
        <td>
            <fieldset>
                <legend>Produktspecifikationer</legend>
                Produkt:  <g:field class="elements" type="text" name="product" required="y" value="${offer?.product}" size="60"/>
                Längd:     <g:field class="elements" type="text" name="lengthDescr"  required="Y" value="${offer?.lengthDescr}" size="30"/>
                Kvalitet:  <g:field class="elements" type="text" name="grade"  value="${offer?.grade}" size="8"/>
                KD%:       <g:field class="elements" type="text" name="kd"  value="${offer?.kd}" size="4"/>
                FSC:       <g:checkBox class="elements" name="fsc" value="${offer.fsc}"/> 
            </fieldset>
        </td>
    </tr>
</table>
<fieldset class="form">
    <legend>Leverans</legend>
    <table>
        <tr>
            <td>
                Sågverk:   <g:field class="elements" type="text" name="sawMill" required="y" value="${offer?.sawMill}" size="8"/>
                Volymenhet:<g:field class="elements" type="text" name="volumeUnit" required="y" value="${offer?.volumeUnit}" size="2"/>
                Volym : <g:field class="elements" type="number decimal" name="volumeOffered" min="1" max="10000" required="Y" value="${fieldValue(bean: offer, field: 'volumeOffered')}" style="width: 6em"/>
                Valuta : <g:field class="elements" type="text" name="currency" required="y" value="${offer?.currency}" size="2"/>
                Pris:<g:field class="elements" type="number decimal" name="price"  required="Y" value="${fieldValue(bean: offer, field: 'price')}" style="width: 6em"/>
                Leveransvillkor: <g:select class="elements" name="termsOfDelivery" from="${offer.constrainedProperties.termsOfDelivery.inList}" 
                value="${offer?.termsOfDelivery}" required="Y"/>
            </td>
        <td>
            <fieldset>
                <legend>Leveransperiod, vecka</legend>
                Från: <g:field class="elements" type="text" name="weekStart" value="${offer?.weekStart}" size="4"/>
                Till: <g:field class="elements" type="text" name="weekEnd"  value="${offer?.weekEnd}" size="4"/>
            </fieldset>
        </td>

        </tr>
    </table>
</fieldset>
<fieldset class="form">
    <legend>Kontaktuppgifter</legend>
    <table style="width:100%">
        <tr>
            <td>
                Företag: <g:field class="elements" type="text" name="company" required="y" value="${offer?.company}" size="50"/>
            </td>
            <td>
                Land: <g:field class="elements" type="text" name="country" value="${offer?.country}" size="50"/>
            </td>
            <td>
                Ort:  <g:field class="elements" type="text" name="city" required="y" value="${offer?.city}" size="50"/>
            </td>
        </tr>
        <tr>
            <td>
                Kontaktperson:  <g:field class="elements" type="text" name="contactPerson" required="y" value="${offer?.contactPerson}" size="50"/>
            </td>
            <td>
                Telefon:  <g:field class="elements" type="text" name="contactPhone"  value="${offer?.contactPhone}" size="50"/>
            </td>
            <td>
                Email:  <g:field class="elements" type="text" name="contactEmail" required="y" value="${offer?.contactEmail}" size="50"/>
            </td>
        </tr>	
    </table>
</fieldset>
