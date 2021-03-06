        <div id="list-offerHeader" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form>
            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="id" title='Id' />
                        <g:sortableColumn property="company" title="Customer" />
                        <g:sortableColumn property="sawMill" title='Supplier' />
                        <g:sortableColumn property="species" title='Wood' />
                        <g:sortableColumn property="status" title='Status' />
                        <g:sortableColumn property="creatorsName" title='Created by' />
                        <g:sortableColumn property="dateCreated" title='Created' />
                        <g:sortableColumn property="Prod1" title='Prod #1'/>
                        <g:sortableColumn property="Prod2" title='Prod #2'/>
                        <g:sortableColumn property="Prod3" title='Prod #3'/>
                        <g:sortableColumn property="Prod4" title='Prod #4'/>
                    </tr>
                </thead>
                <tbody>

                    <g:each in="${offerHeaderList}" status="i" var="oh"> 
                        <tr  class="${ (i % 2) == 0 ? 'even': 'odd'}">
                            <td><g:link action="edit" id="${oh.id}">${oh.id}</g:link></td>
                            <td>${oh.company}</td>
                            <td>${oh.sawMill}</td>
                            <td>${oh.mixedWood}</td>
                            <td>${oh.status}</td>
                            <td>${oh.creatorsName}</td>
                            <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${oh.dateCreated}"/></td>
                            <td>${oh.prod1}</td>
                            <td>${oh.prod2}</td>
                            <td>${oh.prod3}</td>
                            <td>${oh.prod4}</td>
                        </tr>
                    </g:each>

                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${offerHeaderCount ?: 0}" />
            </div>
            </g:form>
        </div>
