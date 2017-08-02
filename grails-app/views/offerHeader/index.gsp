<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'offerHeader.label', default: 'Offer')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script type="text/javascript">
            function filteredOffers(){
                var sWood = $('#wood').val();
                var sStatus = $('#status').val();
                var sCustomer = $('#customer').val();
                var sSawMill = $('#sawMill').val();
                
                //send fields to server
                $.post("/offerHeader/filteredOffers", { wood:sWood, status:sStatus, customer:sCustomer, sawMill:sSawMill }, function(data){
                    $( '#list-offerHeader' ).html( data ); });
     /*
     $.ajax({
                    url:'${g.createLink( controller:'offerHeader', action:'filteredOffers' )}',
                    data: [sawMill], data: [customer], data: [status], data: [wood],
                    type: 'get'
                }).success( function ( data ) { $( '#divToUpdate' ).html( data ); });
     */
     }
        </script> 
        
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#list-offerHeader" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
            <table>
                <thead>
                    <tr>
                        <th> <h1><g:message code="default.list.label" args="[entityName]" /></h1> </th>
                        <th>Customer: <g:select name="customer" from="${customerList}" value="" onchange="filteredOffers(customer)" noSelection = "${['':'All']}" /></th>
                        <th>Supplier: <g:select name="sawMill" from="${millList}" value="" onchange="filteredOffers(sawMill)" noSelection = "${['':'All']}" /></th>
                        <th>Wood: <g:select name="wood" from="${woodList}" value="" onchange="filteredOffers(wood)" noSelection = "${['':'All']}" /></th>
            <!--            <th>Product: <g:select name="product" from="${productList}" value="" onchange="filteredOffers(products)" noSelection = "${['':'All']}" /></th>-->
                        <th>Status: <g:select name="status" from="${statusList}" value="" onchange="filteredOffers(status)" noSelection = "${['':'All']}" /></th>
                    </tr>
                </thead>    
            </table>
        <g:render template="index"/>
    </body>
</html>