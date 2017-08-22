<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
    </head>
    <body>
<table>
    <thead>
        <tr><th>Name</th><th>Value</th></tr>
    </thead>
    <tbody>
        <tr><td>Application</td><td><g:meta name="info.app.name"/></td></tr>
        <tr><td>App version</td><td><g:meta name="info.app.version"/></td></tr>
        <tr><td>Grails version</td><td><g:meta name="info.app.grailsVersion"/></td></tr>
        <tr><td>Build time</td><td><g:meta name="build.time"/></td></tr>
        <tr><td>Build java version</td><td><g:meta name="build.java.version"/></td></tr>
        <tr><td>Build host</td><td><g:meta name="build.host"/></td></tr>
        <tr><td>Grails env</td><td><g:meta name="grails.env"/></td></tr>
    </tbody>
</table>    </body>
</html>
