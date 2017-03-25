<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userSettings.label', default: 'UserSettings')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-userSettings" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="list-userSettings" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<!--            <f:table collection="${userSettingsList}" />   -->
            <table>
                <thead>
                    <tr>
                        <td> Username </td>
                        <td> Supplier </td>
                        <td> Currency </td>
                        <td> UnitVolume </td>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${userSettingsList}" status="i" var="userSettings">
                        <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                            <td><g:link action="show" id="${userSettings.id}">${userSettings.user.username?.encodeAsHTML()}</g:link></td>
                            <td>${userSettings?.supplierName}</td>
                            <td>${userSettings.currency?.encodeAsHTML()}</td>
                            <td>${userSettings.volumeUnit?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                </tbody>

                <div class="pagination">
                    <g:paginate total="${offerCount ?: 0}" />
                </div>
            </table>

            <div class="pagination">
                <g:paginate total="${userSettingsCount ?: 0}" />
            </div>
        </div>
    </body>
</html>