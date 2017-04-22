<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value='Orders och Lager' />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style>
            #selectMill {
            margin-left: 170px;
            }
        </style>
        <style type="text/css">
            table {
            display: block;
            overflow-x: auto;
            }
        </style>
        <style>
            .offers {
            color: #ff0000
            }
            </</style>
        <script type="text/javascript">
            function availableProducts(){
            $.ajax({
                    url:'${g.createLink( controller:'ordersAndStore', action:'availableProducts' )}',
                    data: [sawMill],
                    type: 'get'
                }).success( function ( data ) { $( '#divToUpdate' ).html( data ); });
            }


            $( document ).ready( function() {
                $( document ).on('click', '.offers', function ( event ){
                    $.ajax({
                        url: '${g.createLink( controller:'ordersAndStore', action:'listOffers' )}',
                        data: {id:this.id},
                        type: 'get'
                    }).success( function ( data ) { $( '#offerList' ).html( data );     });
                });
            });

        </script>    
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#list-orders" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><a class="create" href="${createLink(uri: '/ordersAndStore/add_prodBuffer')}"><g:message code="prodBuffer.create.label"/></a></li>
                <li><a class="create" href="${createLink(uri: '/ordersAndStore/createStocknotes')}"><g:message code="stockNote.create.label"/></a></li>
            </ul>
        </div>
        <g:set var="entityName" value='Product' />
        <div id="scroll1 class scrolls">
            <div id="list-prodBuffer" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:form action="createOffer">
                    <div id="selectMill">
                        Select mill:
                        <g:if test="selectedMill">
                            <g:select name="sawMill" from="${millList}" value="" onchange="availableProducts(sawMill)" noSelection = "${['':'All']}" />
                        </g:if>                        
                        <g:else>
                            <g:select name="sawMill" from="${millList}" value="All" onchange="availableProducts(sawMill)" optionValue="All" optionKey="All"/>
                        </g:else>

                    </div>
                    <g:render template="AvailableProductData" model="[prodBuffer:prodBuffer]"/>
                    <div class="pagination">
                        <g:paginate total="${prodBufferCount ?: 0}" /> 
                    </div>

                    <fieldset class="buttons">
                        <input class="save" type="submit" value="${message(code: 'offer.create.from.buffer.label', default: 'Create')}" />
                    </fieldset>
                </g:form>
            </div>
        </div>


        <div id="offerList"></div>

        </div>
    </body>
</html>