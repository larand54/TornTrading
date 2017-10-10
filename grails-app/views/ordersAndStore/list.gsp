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
<!---->

        <style>
            .offers {
                color: #ff0000;
            }
            
            #prodID {
                color: #ff0000;
            }
            #deletep{
                background-image: url(../images/skin/database_delete.png);
            }
            
            #gridProducts {
                white-space: nowrap;
            }
        </style>
        
        <script type="text/javascript">
        </script>

        <script type="text/javascript">
        </script>
        
        <script type="text/javascript">            
            $(document).ready(function() {
            // Setup - add a text input to each footer cell
                $('#gridProducts tfoot th').each( function () {
                    var title = $(this).text();
                    if (title=='Tag') {
                        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
                    } else {
                        $(this).html('');
                    }
                });
 
                // DataTable
                var table = $('#gridProducts').DataTable({
                    "scrollY": "600px",
                    "scrollX": "1000px",
                    "scrollCollapse": true,
                    "paging": false,
                    "searching": true,
                    "fixedColumns": false,
                    "dom": '<"top"i>rt<"bottom"lp><"clear">',
                    "select": true /*,
  ,"columnDefs": [
    { "width": "10%", "targets":  1}
  ]

                   "buttons": [
                        {
                            text: 'Create offer from selected products',
                            action: function ( e, dt, node, config ) {
                                alert( 'Button activated' );
                            }
                        }

                    ]  */             
                });

    
                // Apply the search
                table.columns().every( function () {
                    var that = this;
 
                    $( 'input', this.footer() ).on( 'keyup change', function () {
                        if ( that.search() !== this.value ) {
                            that
                            .search( this.value )
                            .draw();
                        }
                    } );
        
        
                } );
                $( '#sawMill').on( 'change', function() {
                    table
                    .columns( 2 )
                    .search( this.value )
                    .draw();
                });
        
                $( '#createOffer').on( 'click', function() {
           
                    var data = table
                        .rows({selected:true})
                        .data();
 
                    if (data.length < 1 ) {
                        alert('No products selected!');
                        return true;
                    }
                    var arr = [];
                    $.each(table.rows('.selected').data(), function() {
                        var str = this[1];
                        console.log(str);
                        var firstIndex = str.indexOf('>')+1;
                        var lastIndex = str.lastIndexOf('<');
                        str = str.substring(firstIndex,lastIndex);
                        alert(Number(str));
                        arr.push(str);
                    });
                    alert('Data:'+arr);
                    $.ajax({
                        url: '${g.createLink( controller:'ordersAndStore', action:'createOffer' )}',
                        data: {id:arr},
                        type: 'get',
                        success: function ( data ) {window.location = "${createLink(controller:'offerHeader',action:'edit')}"+"/"+data},
                        error: function (jqXHR) {alert(jqXHR.responseText)}
                    });
                });
            });
        </script> 
        <script type="text/javascript">
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
            </ul>
        </div>
        <g:set var="entityName" value='Product' />
        <div id="scroll1 class scrolls">
            <div id="list-prodBuffer" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <div class="filters">
                <g:form action="createOffer">
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
                    <div id="selectMill">
                        Select mill:
                        <g:select class="selected" name="sawMill" from="${millList}" value="${filters?.sawMill}"  noSelection = "${['':'All']}" optionValue="" optionKey=""/>
                    </div>
                </sec:ifAnyGranted>
                <div id="grid">
                    <g:render template="Grid_Products" model="model"/>
                </div>
<!--                    <div class="pagination">
                        <g:paginate total="${prodBufferCount ?: 0}" max="24"/> 
                    </div>
                    
-->
                    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SALES"> 
                        <fieldset class="buttons">
<!--                            <input class="save" type="submit" value="${message(code: 'offer.create.from.buffer.label', default: 'Create')}" />-->
                            <input class="save" type="button" id="createOffer" value="Create offer from selected products"/>
                        </fieldset>
                    </sec:ifAnyGranted>    
                </g:form>
                </div>
            </div>
        </div>


        <div id="offerList"></div>

        </div>
    </body>
</html>