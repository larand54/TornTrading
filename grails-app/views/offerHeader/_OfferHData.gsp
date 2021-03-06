
<%@ page contentType="text/html;charset=UTF-8" %>

<fieldset class="form">
    Set status: <g:select name="status" from="${offerHeader.constrainedProperties.status.inList}" 
    value="${offerHeader?.status}" required="Y"/>
    Info: <g:textArea name="info" value="${offerHeader?.info}" rows="4" cols="125" maxlength="450" style="width:60em; height: 8em" />
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
        <tr>
            <td>Payment terms:<g:field name="termsOfPayment" type="text" value="${offerHeader.termsOfPayment}" size="40" maxlength="40"/></td>
            <td>Week(s) of delivery:<g:field name="weekOfDelivery" type="text" value="${offerHeader.weekOfDelivery}" size="12" maxlength="12"/></td>
        </tr>
    </table>
</fieldset>
<fieldset class="form">
    <legend>Contact info</legend>
    <table style="width:100%">
        <tr>
            <td>Select company: <g:select id="${offerHeader.id}" class="company" name="company-name" from="${customers}"
                value="${offerHeader?.company}" noSelection = "${['':'Select company']}"/></td></td>
            <div id="updateCompany">
            <td>
                Company: <g:field class="elements" id="company" type="text" name="company" value="${offerHeader?.company}" size="50"/>
            </td>
            <td>
                country: <g:field class="elements" id="country" type="text" name="country" value="${offerHeader?.country}" size="50"/>
            </td>
            <td>
                City:  <g:field class="elements" id="city" type="text" name="city"  value="${offerHeader?.city}" size="50"/>
            </td>
            </div>
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
