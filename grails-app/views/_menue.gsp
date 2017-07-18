<content tag="nav">
<!--
    <li class="dropdown"> 
       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><g:message code="quickRequest.label"/>..<span class="caret"></span> </a>
        <ul class="dropdown-menu">
            <li><a href="/quickRequest/create"><g:message code="quickRequest.create.label"/></a></li>
            <li><a href="/quickRequest/index"><g:message code="quickRequest.list.label"/></a></li>
        </ul>
    </li>

    <li class="dropdown">
        <a href="/request1/index">Request</a>

    </li>
-->
    <li >
    <sec:ifAnyGranted roles="ROLE_ADMIN"> 
        <a href="/user">Users</a>
    </sec:ifAnyGranted>
    </li>
    <li >
    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
        <a href="/offerHeader/index">Offers</a>
    </sec:ifAnyGranted>
    </li>
    <li>
    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
        <a href="/stocknote/index">Stock notes</a>
    </sec:ifAnyGranted>
    </li>
    <li>
        <a href="/ordersAndStore/list">Available volumes</a>
    </li>
    <li>
    <sec:ifNotLoggedIn>
        <g:link controller="login" action="auth">Login</g:link>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
        <g:link controller="logout">sign out</g:link>
        </sec:ifLoggedIn>
    </li>
</content>
