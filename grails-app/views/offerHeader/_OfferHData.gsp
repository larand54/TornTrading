
<%@ page contentType="text/html;charset=UTF-8" %>

<fieldset class="form">
    Set status: <g:select name="status" from="${offerHeader.constrainedProperties.status.inList}" 
    value="${offerHeader?.status}" required="Y"/>
</fieldset>
<fieldset class="form">
    <legend>Delivery</legend>
    <table style="width:50%">
        <tr>
            
            <td>Sawmill:   <g:field class="elements" type="text" name="sawMill" required="y" value="${offerHeader?.sawMill}" size="24"/></td>
            <td>Volumeunit:<g:field class="elements" type="text" name="volumeUnit" required="y" value="${offerHeader?.volumeUnit}" size="2"/></td>
            <td>Currency : <g:field class="elements" type="text" name="currency" required="y" value="${offerHeader?.currency}" size="2"/></td>
            <td>Deliveryterms: <g:select class="elements" name="termsOfDelivery" from="${offerHeader.constrainedProperties.termsOfDelivery.inList}"
                value="${offerHeader?.termsOfDelivery}" required="N"/></td>
            <td>Freight <g:field class="elements" type="number decimal" name="freight"  value="${fieldValue(bean: offerHeader, field: 'freight')}" style="width: 6em"/>   
            
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
