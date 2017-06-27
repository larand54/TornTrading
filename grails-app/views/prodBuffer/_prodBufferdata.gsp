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
                Wood:  <g:select name="species" from="${prodBuffer?.constrainedProperties.species.inList}" value="${prodBuffer?.species}"/> 
                Dimension:  <g:field class="elements" type="text" name="dimension"  value="${prodBuffer?.dimension}" size="100", maxlength="150"/>
                Length:     <g:field class="elements" type="text" name="length"   value="${prodBuffer?.length}" size="40", maxlength="255"/>
                KD(%):     <g:field class="elements" type="text" name="kd"   value="${prodBuffer?.kd}" size="2"/>
                Grade:  <g:select name="grade" from="${prodBuffer?.constrainedProperties.grade.inList}" value="${prodBuffer?.grade}"/>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td>
            <fieldset>
                <legend>Volumes(m3)</legend>
<!--                Volumeunit:<g:field class="elements" type="text" name="volumeUnit" required="y" value="${myTag.userVolumeUnit()}" size="2"/>-->
                In stock : <g:field class="elements" type="number decimal" name="volumeInStock" min="1" max="10000" required="Y" value="${fieldValue(bean: prodBuffer, field: 'volumeInStock')}" style="width: 6em"/>
                Available volume : <g:field class="elements" type="number decimal" name="volumeAvailable" readonly="Y" value="${prodBuffer?.volumeAvailable}" style="width: 6em"/>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td>
            <fieldset>
                <legend>Prices</legend>
                Valuta : <g:field class="elements" type="text" name="currency"  value="EUR" maxlength="3" size="2"/>
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
<table>
    <tr>
        <td>
            <fieldset>
                <legend>Planned production(m3)</legend>
                <table>
                    <thead>
                    <th>${myTag.weekNo(offset: 1)}</th>
                    <th>${myTag.weekNo(offset: 2)}</th>
                    <th>${myTag.weekNo(offset: 3)}</th>
                    <th>${myTag.weekNo(offset: 4)}</th>
                    <th>${myTag.weekNo(offset: 5)}</th>
                    <th>${myTag.weekNo(offset: 6)}</th>
                    <th>${myTag.weekNo(offset: 7)}</th>
                    <th>${myTag.weekNo(offset: 8)}</th>
                    <th>${myTag.weekNo(offset: 9)}</th>
                    <th>${myTag.weekNo(offset:10)}</th>
                    <th>${myTag.weekNo(offset:11)}</th>
                    <th>${myTag.weekNo(offset:12)}</th>
                    </thead>
                    <tbody>
                        <tr> 
                            <g:if test="${motherview=='create'}">
                                <g:each status="i" in="${(0..11)}" var="j">
                                    <td><g:field class="plannedVolume" type="number decimal" name="vol${i+1}" required="y" value="0.0" size="4"/></td>
                                </g:each>
                            </g:if>
                            <g:else>
                                <g:each in="${plannedVolumes}" status="i" var="pv">
                                    <td><g:field class="plannedVolume" type="number decimal" name="vol${i+1}" required="y" value="${pv.volume}" size="4"/></td>
                                </g:each>
                            </g:else>

                        </tr>
                    </tbody>    
            </fieldset>
        </td>
    </tr>    
</tr>
</table>
