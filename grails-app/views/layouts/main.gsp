<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="TörnTrading"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="datatables.min.css"/>
    <asset:stylesheet src="select.dataTables.min.css"/>
    <asset:stylesheet src="buttons.dataTables.min.css"/>
    <asset:stylesheet src="fixedColumns.dataTables.min.css"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="fxHeader_0.6.min.js"/>
    <asset:javascript src="datatables.min.js"/>   
    <asset:javascript src="dataTables.select.min.js"/>   
    <asset:javascript src="dataTables.buttons.min.js"/>   
    <asset:javascript src="buttons.html5.min.js"/>   
    <asset:javascript src="dataTables.fixedColumns.min.js"/>   
    <g:layoutHead/>
</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                </button>
                <a class="navbar-brand" href="/">
                    <i class="fa grails-icon">
                        <asset:image src="Torntrading.png"/>
                    </i>
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                </ul>
            </div>
        </div>
    </div>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>
    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    </body>
</html>
