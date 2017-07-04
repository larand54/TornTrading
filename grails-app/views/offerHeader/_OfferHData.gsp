
<%@ page contentType="text/html;charset=UTF-8" %>

<fieldset class="form">
    Set status: <g:select name="status" from="${offerHeader.constrainedProperties.status.inList}" 
    value="${offerHeader?.status}" required="Y"/>
</fieldset>
<fieldset class="form">
    <legend>Delivery</legend>
    <table style="width:100%">
        <tr>
            
            <td>Sawmill:   <g:field class="elements" type="text" name="sawMill" readonly="y" value="${offerHeader?.sawMill}" size="24"/></td>
            <td>Currency : <g:field class="elements" type="text" name="currency" readonly="y" value="${offerHeader?.currency}" size="2"/></td>
            <td>Deliveryterms: <g:select class="elements" name="termsOfDelivery" from="${offerHeader.constrainedProperties.termsOfDelivery.inList}"
                value="${offerHeader?.termsOfDelivery}" required="N"/></td>
            <td>Freight <g:field class="elements" type="number decimal" name="freight" required="Y" value="${fieldValue(bean: offerHeader, field: 'freight')}" style="width: 6em"/>   
            <td>Fee(%) <g:field class="elements" min="0" max="25" type="number" name="agentFee"  value="${fieldValue(bean: offerHeader, field: 'agentFee')}" style="width: 4em"/>   
            <td>Valid until:<g:datePicker name="offerValidDate" value="${offerHeader.validUntil}" precision="day" default="${new Date().plus(30)}"/></td>

        </tr>
    </table>
</fieldset>
<fieldset class="form">
    <legend>Contact info</legend>
    <table style="width:100%">
        <tr>
            <td>
                Company: <g:field class="elements" type="text" name="company"  value="${offerHeader?.company}" size="50"/>
            </td>
            <td>
                country: <g:field class="elements" type="text" name="country" value="${offerHeader?.country}" size="50"/>
            </td>
            <td>
                City:  <g:field class="elements" type="text" name="city"  value="${offerHeader?.city}" size="50"/>
            </td>
        </tr>
        <tr>
            <td>
                Contact:  <g:field class="elements" type="text" name="contactPerson"  value="${offerHeader?.contactPerson}" size="50"/>
            </td>
            <td>
                Phone:  <g:field class="elements" type="text" name="contactPhone"  value="${offerHeader?.contactPhone}" size="50"/>
            </td>
            <td>
                Email:  <g:field class="elements" type="text" name="contactEmail"  value="${offerHeader?.contactEmail}" size="50"/>
            </td>
        </tr>	
    </table>
</fieldset>
