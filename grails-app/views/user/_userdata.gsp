<g:if test="${motherview=='create'}">
<div class='fieldcontain required'>
    <label for='password'>Password
        <span class='required-indicator'>*</span>
    </label><input type="password" name="password" required="" value="" id="password" />
</div><div class='fieldcontain required'>
    <label for='username'>Username
        <span class='required-indicator'>*</span>
    </label><input type="text" name="username" value="" required="" id="username" />
</div>
<div class='fieldcontain'>
    <label for='sawMill'>SawMill</label><g:select name="sawMill" from="${millList.searchName}" value=""  noSelection = "${['':'Choose company']}" />
</div><div class='fieldcontain'>
    <label for='currency'>Currency</label><g:select name="currency" from="${cyList}" value=""/>
</div>
</g:if>
<g:else>
<div class='fieldcontain'>
    <label for='sawMill'>SawMill</label><g:select name="sawMill" from="${millList.searchName}" value="${user?.us?.supplierName}"  noSelection = "${['':'Choose company']}" />
</div><div class='fieldcontain'>
    <label for='currency'>Currency</label><g:select name="currency" from="${cyList}" value="${user?.us?.currency}"/>
</div>
</g:else>

</div><div class='fieldcontain'>
    <label for='uName'>Users real name</label><input type="text" name="uName" value="${user?.us?.name}" id="uName" />
</div><div class='fieldcontain'>
    <label for='uCompany'>Users company</label><input type="text" name="uCompany" value="${user?.us?.company}" id="uName" />
</div><div class='fieldcontain'>
    <label for='uEmail'>Email</label><input type="email" name="uEmail" value="${user?.us?.email}" id="uName" />
</div><div class='fieldcontain'>
    <label for='uTel'>Tel</label><input type="text" name="uTel" value="${user?.us?.tel}" id="uName" />
</div><div class='fieldcontain'>
    <label for='uPhone'>Phone</label><input type="text" name="uPhone" value="${user?.us?.phone}" id="uName" />
</div><div class='fieldcontain'>
    <label for='uMobile'>Mobile</label><input type="text" name="uMobile" value="${user?.us?.mobile}" id="uName" />
</div>


<div class='fieldcontain'>
    <label for='accountLocked'>Account Locked</label><input type="hidden" name="_accountLocked" /><input type="checkbox" name="accountLocked" id="accountLocked"  />
</div><div class='fieldcontain'>
    <label for='accountExpired'>Account Expired</label><input type="hidden" name="_accountExpired" /><input type="checkbox" name="accountExpired" id="accountExpired"  />
</div><div class='fieldcontain'>
    <label for='passwordExpired'>Password Expired</label><input type="hidden" name="_passwordExpired" /><input type="checkbox" name="passwordExpired" id="passwordExpired"  />
</div><div class='fieldcontain'>
    <label for='enabled'>Enabled</label><input type="hidden" name="_enabled" /><input type="checkbox" name="enabled" checked="checked" id="enabled"  />
</div>
