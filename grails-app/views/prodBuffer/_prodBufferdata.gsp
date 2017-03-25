<fieldset class="form">
    Ange status: <g:select name="status" from="${prodBuffer?.constrainedProperties.status.inList}" 
    value="${prodBuffer?.status}" required="Y"/>
</fieldset>
<input type="hidden" name="sawMill" value="${myTag.userCompany()}" />
<table style="width:100%">
<!--    <tr>
        <td>
            Sågverk:   <g:field class="elements" type="text" name="sawMill" required="y" value="${prodBuffer?.sawMill}" size="8"/>
            LoBuffNo:   <g:field class="elements" type="numeric" name="loBuffNo" required="y" value="${prodBuffer?.loBuffertNo}" size="8"/>
        </td>
    </tr>
-->
    <tr>
        <td>
            <fieldset>
                <legend>Product specifications</legend>
                Produkt:  <g:field class="elements" type="text" name="product" required="y" value="${prodBuffer?.product}" size="60"/>
                Längd:     <g:field class="elements" type="text" name="length"  required="y" value="${prodBuffer?.length}" size="30"/>
                KD:     <g:field class="elements" type="text" name="kd"  required="y" value="${prodBuffer?.kd}" size="2"/>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td>
            <fieldset>
                <legend>Volumes</legend>
                Volumeunit:<g:field class="elements" type="text" name="volumeUnit" required="y" value="${myTag.userVolumeUnit()}" size="2"/>
                Volume : <g:field class="elements" type="number decimal" name="volumeAvailable" min="1" max="10000" required="Y" value="${fieldValue(bean: prodBuffer, field: 'volumeAvailable')}" style="width: 6em"/>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td>
            <fieldset>
                <legend>Prices</legend>
                Valuta : <g:field class="elements" type="text" name="currency"  value="${myTag.userCurrency()}" size="2"/>
                Price FSC:<g:field class="elements" type="number decimal" name="priceFSC"   value="${fieldValue(bean: prodBuffer, field: 'priceFSC')}" style="width: 6em"/>
                Price PEFC:<g:field class="elements" type="number decimal" name="pricePEFC"  value="${fieldValue(bean: prodBuffer, field: 'pricePEFC')}" style="width: 6em"/>
                Price CW:<g:field class="elements" type="number decimal" name="priceCW"  value="${fieldValue(bean: prodBuffer, field: 'priceCW')}" style="width: 6em"/>
                Price UC:<g:field class="elements" type="number decimal" name="priceUC"   value="${fieldValue(bean: prodBuffer, field: 'priceUC')}" style="width: 6em"/>
            </fieldset>
        </td>
    <tr>
        <td>
            <fieldset>
                <legend>Leveransperiod, vecka</legend>
                From: <g:field class="elements" type="text" name="weekStart" required="y" value="${prodBuffer?.weekStart}" size="4"/>
                To: <g:field class="elements" type="text" name="weekEnd" required="Y" value="${prodBuffer?.weekEnd}" size="4"/>
            </fieldset>
        </td>
    </tr>    
    </tr>
</table>
