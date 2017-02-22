                <fieldset class="form">
                        <table>
                            <tr>
                                <div class='fieldcontain required'>
                                    <td>      
                                        <span class='required-indicator'>*</span>
                                        <g:message code="com.torntrading.pub.quickRequest.title.label"/> 
                                    </td>
                                    <td>
                                        <g:textArea name="title" value="${quickRequest?.title}" required="y" rows="2" cols="50" style="width:50em; height: 4em" /> 
                                    </td> 
                                </div>    
                            </tr>
                            <tr>
                                <div class='fieldcontain required'>
                                    <td>                                    
                                        <span class='required-indicator'>*</span>
                                        <g:message code="com.torntrading.pub.quickRequest.specReq.label"/> 
                                    </td>
                                    <td>
                                        <g:textArea name="specReq" value="${quickRequest?.specReq}" required="y" rows="2" cols="50" style="width:50em; height: 12em" /> 
                                    </td>
                                </div>
                            </tr>
                        </table>
                </fieldset>
                <fieldset>
                    <legend>Kontaktuppgifter</legend>
                    <table>
                        <tr>
                             <td>
                                <div class='fieldcontain required'>
                                    <span class='required-indicator'>*</span>
                                    <g:message code="com.torntrading.pub.quickRequest.contactPerson.label"/>  
                                    <g:field type="text" name="contactPerson" required="y" value="${quickRequest?.contactPerson}" size="50"/>
                                </div>
                            </td>
                            <td>
                                <div class='fieldcontain required'> 
                    <!--                <span class='required-indicator'>*</span>   -->
                                    <g:message code="com.torntrading.pub.quickRequest.contactPhone.label"/>  
                                    <g:field type="text" name="contactPhone"  value="${quickRequest?.contactPhone}" size="16"/>
                               </div>  
                            </td>
                            <td>
                                <div class='fieldcontain required'>
                                    <span class='required-indicator'>*</span>
                                    <g:message code="com.torntrading.pub.quickRequest.contactEmail.label"/>  
                                    <g:field type="text" name="contactEmail" required="y" value="${quickRequest?.contactEmail}" size="50"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset>
