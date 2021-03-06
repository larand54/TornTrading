<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodBuffer.label', default: 'ProdBuffer')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <script type="text/javascript">
            $( document ).ready( function() {
                $( document ).on('click', '.addVolume', function ( event ){
                    var Vol1 = $('#vol1').val();
                    var Vol2 = $('#vol2').val();
                    var Vol3 = $('#vol3').val();
                    var Vol4 = $('#vol4').val();
                    var Vol5 = $('#vol5').val();
                    var Vol6 = $('#vol6').val();
                    var Vol7 = $('#vol7').val();
                    var Vol8 = $('#vol8').val();
                    var Vol9 = $('#vol9').val();
                    var Vol10 = $('#vol10').val();
                    var Vol11 = $('#vol11').val();
                    var Vol12 = $('#vol12').val();
                    var pid = $('#pid').val();

            //send fields to server
                $.post("/prodBuffer/addVolume", { Vol1:Vol1,Vol2:Vol2,Vol3:Vol3,Vol4:Vol4,Vol5:Vol5,Vol6:Vol6,Vol7:Vol7,Vol8:Vol8,Vol9:Vol9,Vol10:Vol10,Vol11:Vol11,Vol12:Vol12, pid:pid }, function(data){
                    window.setTimeout( function(){ location = '/ordersAndStore/list' }, 2000 )
                });    
            });
        });

        $(document).ready(function(){
            $('.delete-button').on('click', function(event){                
                event.preventDefault();  //1
                var deleteTarget = $(this).prop('href'); //2            

                if(confirm("Are you sure?")){ //3
                    $.post( //4
                        deleteTarget, 
                        function successhandler(responseData){
                            $('#results').html(responseData);
                        }
                    );
                }
            });
        });

 

            $( document ).ready( function() {
                $('#delete_prod').on('click',function(event) {
                    if ( confirm( 'Are you sure?' )) {
                        var id=$(this).data('id');
                        var url="${createLink(controller: 'prodBuffer',action:'delete')}/"+id
                        $.ajax({
                            type: 'DELETE',
                            url: url,
                            success: function(data){
                                $('#resultdiv').html(data);
                            }
                        });
                    }
                });
            });

    $( document ).ready( function() {
        $( '.itemAction' ).click(function (event) {
            if ( confirm( 'Are you sure?' )) {
                $.ajax({
                    url: "/prodBuffer/" + this.id + '/' + $( '#id' ).val(),
                    type: "POST",
                    data: $( '#myForm' ).serialize(),
                    success: function ( data ) {
                        $( '#resultdiv' ).html( 'Success' );
                        window.setTimeout( function(){ location = '/ordersAndStore/list' }, 2000 )
                    },
                    error: function(j, status, eThrown) { console.log( 'Error ' + eThrown ) },
                    complete: function() { console.log( 'Complete' ) }
                });
            }
        });
    });

        </script>    
    </head>
    <body>
        <g:render template="/menue"/>
        <a href="#edit_2-prodBuffer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" controller="ordersAndStore" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="edit_2-prodBuffer" class="content scaffold-edit" role="main">
                <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.prodBuffer}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.prodBuffer}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                </ul>
            </g:hasErrors>
           <g:form resource="${this.prodBuffer}" method="PUT">
                <g:hiddenField name="version" value="${this.prodBuffer?.version}" />
                <g:hiddenField name="pid" value="${this.prodBuffer?.id}" />
                <fieldset class="form">
                    <g:render template="prodBufferdata" model="[prodBuffer:prodBuffer, motherview:'edit']"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
