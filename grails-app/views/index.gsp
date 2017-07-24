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
    <g:render template="/menue"/>

    <div class="svg" role="presentation">
        <g:if test="${flash.error}">
            <div class="alert alert-error" style="display: block">${flash.error}</div>
        </g:if>
        <g:if test="${flash.message}">
            <div class="message" style="display: block">${flash.message}</div>
        </g:if>

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
