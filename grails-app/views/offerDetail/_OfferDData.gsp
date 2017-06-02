<fieldset>
    <legend>Product specification</legend>
    <table style="width:100%">
        <g:field type="hidden" name="offerHeader" value="${offerDetail?.offerHeader?.id}"/>
        <tr>
            <td>
                Wood:  <g:select name="species" from="${offerDetail?.constrainedProperties.species.inList}" value="${offerDetail?.species}"/>
                Dimension:  <g:field class="elements" type="text" name="dimension" required="y" value="${offerDetail?.dimension}" size="60"/>
                Length:     <g:field class="elements" type="text" name="lengthDescr"  required="Y" value="${offerDetail?.lengthDescr}" size="30"/>
                Grade:  <g:select name="grade" from="${offerDetail?.constrainedProperties.grade.inList}" value="${offerDetail?.grade}"/>
                KD(%):       <g:field class="elements" type="text" name="kd"  value="${offerDetail?.kd}" size="4"/>

            </td>
        </tr>
    </table>
</fieldset>
<fieldset>
    <legend>Prices</legend>

    <table>
        <colgroup>
            <col width="5%"/>
            <col width="6%"/>
            <col width="8%"/>
            <col width="8%"/>
            <col width="8%"/>
            <col width="8%"/>
            <col width="8%"/>
            <col width="8%"/>
            <col width="5%"/>
        </colgroup>
        <tr> 
            <td>Certificate:</td>
            <td>
                <g:select id="${offerDetail.id}" class="availableCert" name="availableCert" from="${offerDetail.availableCert}" value="${offerDetail.choosedCert}" />    
            </td> 

            <td>Price adjust(m3):<td > <g:field  id="${offerDetail.id}" class="adjustPrice" type="number decimal" name="priceAdjust"  value="${fieldValue(bean: offerDetail, field: 'priceAdjust')}" style="width: 6em"/> </td>
            <td> FSC: ${offerDetail.priceFSC}</td>
            <td> UC: ${offerDetail.priceUC}</td>
            <td> CW: ${offerDetail.priceCW}</td>
            <td> PEFC: ${offerDetail.pricePEFC}</td>
            <td>EndPrice:</td>
            <td>  
                  <div id="updatePrice">    ${offerDetail.endPrice} </div>
            </td>


        </tr>
    </table>
</fieldset>
<fieldset>
    <legend>Delivery</legend>
    <table>
        <colgroup>
            <col width="5%"/>
            <col width="95%"/>
        </colgroup>
        <tr>
            <td>
                Volume(m3):             
            </td>
            <td>
                <g:field class="volumeOffered" id="${offerDetail.id}" type="number decimal" name="volumeOffered" min="1" max="10000" required="Y" value="${fieldValue(bean: offerDetail, field: 'volumeOffered')}" style="width: 6em"/>
            </td>


        </tr>
    </table>
</fieldset>