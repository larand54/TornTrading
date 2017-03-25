<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<fieldset class="form">
    Ange status: <g:select name="status" from="${offerHeader.constrainedProperties.status.inList}" 
    value="${offerHeader?.status}" required="Y"/>
</fieldset>
<fieldset class="form">
    <legend>Leverans</legend>
    <table>
        <tr>
            <td>
                Sågverk:   <g:field class="elements" type="text" name="sawMill" required="y" value="${offerHeader?.sawMill}" size="8"/>
                Volymenhet:<g:field class="elements" type="text" name="volumeUnit" required="y" value="${offerHeader?.volumeUnit}" size="2"/>
                Valuta : <g:field class="elements" type="text" name="currency" required="y" value="${offerHeader?.currency}" size="2"/>
                Leveransvillkor: <g:select class="elements" name="termsOfDelivery" from="${offerHeader.constrainedProperties.termsOfDelivery.inList}" 
                value="${offerHeader?.termsOfDelivery}" required="N"/>
            </td>
        </tr>
    </table>
</fieldset>
<fieldset class="form">
    <legend>Kontaktuppgifter</legend>
    <table style="width:100%">
        <tr>
            <td>
                Företag: <g:field class="elements" type="text" name="company" required="y" value="${offerHeader?.company}" size="50"/>
            </td>
            <td>
                Land: <g:field class="elements" type="text" name="country" value="${offerHeader?.country}" size="50"/>
            </td>
            <td>
                Ort:  <g:field class="elements" type="text" name="city" required="y" value="${offerHeader?.city}" size="50"/>
            </td>
        </tr>
        <tr>
            <td>
                Kontaktperson:  <g:field class="elements" type="text" name="contactPerson" required="y" value="${offerHeader?.contactPerson}" size="50"/>
            </td>
            <td>
                Telefon:  <g:field class="elements" type="text" name="contactPhone"  value="${offerHeader?.contactPhone}" size="50"/>
            </td>
            <td>
                Email:  <g:field class="elements" type="text" name="contactEmail" required="y" value="${offerHeader?.contactEmail}" size="50"/>
            </td>
        </tr>	
    </table>
</fieldset>
