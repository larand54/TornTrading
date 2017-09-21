<div id="nono">
    <fieldset>
        <legend>Product specification</legend>
        <table style="width:100%">
            <g:field type="hidden" name="offerHeader" value="${offerDetail?.offerHeader?.id}"/>
            <tr>
                <td>
                    Wood:  <g:field name="species"  readonly="Y"  value="${offerDetail?.species}"/>
                    Dimension:  <g:field class="elements" type="text" name="dimension" readonly="Y"  value="${offerDetail?.dimension}" size="60"/>
                    Length:     <g:field class="elements" type="text" name="lengthDescr"  readonly="Y"  value="${offerDetail?.lengthDescr}" size="30"/>
                    Grade:  <g:field name="grade"   readonly="Y" value="${offerDetail?.grade}"/>
                    KD(%):       <g:field class="elements" type="text" name="kd" readonly="Y" value="${offerDetail?.kd}" size="4"/>

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
                <col width="5%"/>
                <col width="95%"/>
            </colgroup>
            <tr>
                <td>
                    Volume(AM3):             
                </td>
                <td>
                    <g:field class="volumeOffered" id="${offerDetail.id}" type="number decimal" name="volumeOffered" min="1" max="10000" required="Y" value="${fieldValue(bean: offerDetail, field: 'volumeOffered')}" style="width: 6em"/>
                </td>
                <td>
                    Available: <g:field class="volumeAvailable" id="${offerDetail.id}" type="number decimal" name="volumeAvailable" min="1" max="10000" readonly="Y" value="${prodBuffer.volumeAvailable}" style="width: 6em"/>
                </td>
                    


            </tr>
        </table>
    </fieldset>

   <g:if test="${offerDetail.offerHeader?.status in ['Active','New']}">
    <label for="ckbWeeklyVolumes">
        <g:message message="Specify volumes weekly"/>
    </label>
    <g:checkBox id="${offerDetail.id}" class="useWeeklyVolumes" name="ckbWeeklyVolumes" value="${offerDetail?.useWeeklyVolumes}" />
   </g:if>

    <g:if test="${offerDetail?.useWeeklyVolumes}">
        <table>
            <tr>
                <td>
                    <fieldset>
                        <legend>Weekly distributed volumes(m3)</legend>
                        <table>
                            <thead>
                            <th></th>
                            <th>From stock</th>
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
                                    <td> Available </td>
                                    <td>${offerDetail.inStock} </td>
                                    <g:each status="i" in="${offerDetail.availableVolumes}" var="av">
                                        <td>${av?.volume}</td>
                                    </g:each>
                                </tr>
                                <tr>
                                    <td> Offered </td>
                                    <g:if test="${motherview=='create'}">
                                        <td><g:field  type="number decimal" name="fromStock"  value="${fieldValue(bean: offerDetail, field: 'fromStock')}" size="4"/></td>
                                        <g:each status="i" in="${(0..11)}" var="j">
                                            <td><g:formatNumber number="${0.0}" /></td>
                                        </g:each>
                                    </g:if>
                                    <g:else>
                                        <td><g:field  type="number decimal" name="fromStock" value="${fieldValue(bean: offerDetail, field: 'fromStock')}" size="4"/></td>
                                        <g:each in="${offerPlannedVolumes}" status="i" var="ov">
                                            <td><g:field  type="number decimal" name="vol${i+1}" value="${ov?.volume}" size="4"/></td>
                                        </g:each>
                                    </g:else>
                                </tr>
                            </tbody> 
                        </table>
                    </fieldset>
                </td>
            </tr>    
            </tr>
        </table>
    </g:if>
</div>