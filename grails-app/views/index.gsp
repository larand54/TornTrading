<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Törn Trading</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
<!--  Detta css-avsnitt tillsammans med lite html/gsp kan lägga text/knapper etc på nästan godtycklig plats 
    <Style>
        .content {
        position: relative;
        }
        #right-panel-link {
        position: absolute;
        top: 0;
        right: 0;
        }
    </style>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
    <div class="content">
        <h1>TraceMySteps</h1>
        <button type="button" class="btn btn-primary" id="right-panel-link" href="#right-panel">Visualizations Panel</button>
    </div>

-->
</head>
<body>
<content tag="nav">
    <li class="dropdown"> 
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><g:message code="quickRequest.label"/>..<span class="caret"></span> </a>
        <ul class="dropdown-menu">
            <li><a href="quickRequest/create"><g:message code="quickRequest.create.label"/></a></li>
            <li><a href="quickRequest/index"><g:message code="quickRequest.list.label"/></a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Förfrågningar <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="request1/index">Förfrågan..</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Offerter <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="offer/index">Offerter..</a></li>
            <li><a href="offerHeader/index">Offerter..Ny</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Order & Buffert <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="orders/index">Ordrar..</a></li>
            <li><a href="prodBuffer/index">Buffert..</a></li>
            <li><a href="orders_and_Store/list">Ordrar & Buffert..</a></li>
        </ul>
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

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
    <div class="title">
        </div
        <div class="summary">
        </div>

    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
        </section>
    </div>
</body>
</html>
