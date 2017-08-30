<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'offerDetail.label', default: 'OfferDetail')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        
        <script>
            $(document).ready(function(){
                $( document ).on('change', '.useWeeklyVolumes', function ( event ){ 
                    $.ajax({
                        url: '${g.createLink( controller:'offerDetail', action:'useWeeklyVolumes' )}',
                        data: {ckbWeeklyVolumes:this.checked, id:this.id},
                        type: 'get'
                    }).success( function ( data ) { $( '#nono' ).html( data );     });
                });
            });
        </script>
        
        <script>
            $(document).ready(function(){
                $( document ).on('change', '.availableCert', function ( event ){ 
                    $.ajax({
                        url: '${g.createLink( controller:'offerDetail', action:'updatePrice' )}',
                        data: {availableCert:this.value, id:this.id},
                        type: 'get'
                    }).success( function ( data ) { $( '#updatePrice' ).html( data );     });
                });
            });
        </script>
        
        <script>
            $(document).ready(function(){
                $( document ).on('change', '.adjustPrice', function ( event ){ 
                    $.ajax({
                        url: '${g.createLink( controller:'offerDetail', action:'updatePrice' )}',
                        data: {adjustPrice:this.value, id:this.id},
                        type: 'get'
                    }).success( function ( data ) { $( '#updatePrice' ).html( data );     });
                });
            });
        </script>

        <script>
            $(document).ready(function(){
                $( document ).on('change', '.volumeOffered', function ( event ){ 
                    $.ajax({
                        url: '${g.createLink( controller:'offerDetail', action:'updatePrice' )}',
                        data: {volumeOffered:this.value, id:this.id},
                        type: 'get',
                        success:function ( data ) { $( '#updatePrice' ).html( data );     }), 
                        error: alert('!!! E R R O R !!!');
                        });
//                    }).success( function ( data ) { $( '#updatePrice' ).html( data );     });
                });
            });
        </script>

    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#edit-offerDetail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li> <a href = <g:createLink controller="offerHeader" action="edit" id="${this.offerDetail.offerHeader.id}"/>> OfferHeader</a></li>
 <!--               <g:link url="${request.getHeader('referer')}">Back</g:link>            -->
                </ul>
            </div>
            <div id="edit-offerDetail" class="content scaffold-edit" role="main">
                <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.offerDetail}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.offerDetail}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                </ul>
            </g:hasErrors>
            <g:form resource="${this.offerDetail}" method="PUT">
                <g:hiddenField name="version" value="${this.offerDetail?.version}" />
                <fieldset class="form">
                    <g:render template="OfferDData" model="[offerDetail:offerDetail]"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
