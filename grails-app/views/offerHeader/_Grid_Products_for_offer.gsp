<fieldset class="form">
    <legend>Available products</legend>
    <table id="gridProducts" class="display">
        <thead>
            <tr>
                <th>Add</th>
                <th>Species</th>
                <th>Dimension</th>
                <th>Length</th>
                <th>Grade</th>
                <th>KD(%)</th>
                <th>Cur</th>
                <th>FSC</th>
                <th>PEFC</th>
                <th>CW</th>
                <th>UC</th>
                <th>InStock</th>
                <th>Avail(m3)</th>
            </tr>
        </thead>
        <tbody>

            <g:each in="${products}" status="i" var="pb"> 
                <tr  class="${ (i % 2) == 0 ? 'even': 'odd'}">
                    <td>
                        <g:link action="addProduct" controller="offerHeader" style="text-decoration:none" params="[prodID: pb.id, offerID: offerHeader.id ]"
                            onclick="return confirm('Are you sure?')">
                            +
                        </g:link>
                    </td>
                    <td>${pb.species}</td>
                    <td>${pb.shortDim}</td>
                    <td>${pb.shortLength}</td>
                    <td>${pb.grade}</td>
                    <td>${pb.kd}</td>
                    <td>${pb.currency}</td>
                    <td>${pb.priceFSC}</td>
                    <td>${pb.pricePEFC}</td>
                    <td>${pb.priceCW}</td>
                    <td>${pb.priceUC}</td>
                    <td>${pb.volumeInStock}</td>
                    <td>${pb.volumeAvailable}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
</fieldset>