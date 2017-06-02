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
    <label for='sawMill'>Company</label><g:select name="sawMill" from="${millList.searchName}" value=""  noSelection = "${['':'Choose company']}" />
</div><div class='fieldcontain'>
    <label for='currency'>Currency</label><g:select name="currency" from="${cyList}" value=""/>
</div>
</g:if>
<g:else>
<div class='fieldcontain'>
    <label for='sawMill'>Company</label><g:select name="sawMill" from="${millList.searchName}" value="${user?.us?.supplierName}"  noSelection = "${['':'Choose company']}" />
</div><div class='fieldcontain'>
    <label for='currency'>Currency</label><g:select name="currency" from="${cyList}" value="${user?.us?.currency}"/>
</div>
</g:else>
<div class='fieldcontain'>
    <label for='accountLocked'>Account Locked</label><input type="hidden" name="_accountLocked" /><input type="checkbox" name="accountLocked" id="accountLocked"  />
</div><div class='fieldcontain'>
    <label for='accountExpired'>Account Expired</label><input type="hidden" name="_accountExpired" /><input type="checkbox" name="accountExpired" id="accountExpired"  />
</div><div class='fieldcontain'>
    <label for='passwordExpired'>Password Expired</label><input type="hidden" name="_passwordExpired" /><input type="checkbox" name="passwordExpired" id="passwordExpired"  />
</div><div class='fieldcontain'>
    <label for='enabled'>Enabled</label><input type="hidden" name="_enabled" /><input type="checkbox" name="enabled" checked="checked" id="enabled"  />
</div>
