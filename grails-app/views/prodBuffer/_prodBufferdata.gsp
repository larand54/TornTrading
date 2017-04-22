<fieldset class="form">
    Set status: <g:select name="status" from="${prodBuffer?.constrainedProperties.status.inList}" 
    value="${prodBuffer?.status}" required="Y"/>
</fieldset>
<input type="hidden" name="sawMill" value="${myTag.userCompany()}" />
<table style="width:100%">
    <tr>
        <td>
            <fieldset>
                <legend>Product specifications</legend>
                Species:  <g:select name="species" from="${prodBuffer?.constrainedProperties.species.inList}"/> 
                Dimension:  <g:field class="elements" type="text" name="dimension"  value="${prodBuffer?.dimension}" size="60"/>
                Length:     <g:field class="elements" type="text" name="length"   value="${prodBuffer?.length}" size="30"/>
                KD:     <g:field class="elements" type="text" name="kd"   value="${prodBuffer?.kd}" size="2"/>
                Grade:  <g:select name="grade" from="${prodBuffer?.constrainedProperties.grade.inList}" value="${prodBuffer?.grade}"/>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td>
            <fieldset>
                <legend>Volumes</legend>
                Volumeunit:<g:field class="elements" type="text" name="volumeUnit" required="y" value="${myTag.userVolumeUnit()}" size="2"/>
                In stock : <g:field class="elements" type="number decimal" name="volumeInStock" min="1" max="10000" required="Y" value="${fieldValue(bean: prodBuffer, field: 'volumeInStock')}" style="width: 6em"/>
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
<!--            <fieldset>
                <legend>Planned production</legend>
                From: <g:field class="elements" type="text" name="weekStart" required="y" value="${prodBuffer?.weekStart}" size="4"/>
                From: <g:field class="elements" type="number decimal" name="plannedVolume" required="y" value="${plannedVolume}" size="4"/>
            </fieldset> -->
        </td>
    </tr>    
    </tr>
</table>
