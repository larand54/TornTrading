/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Återställer från readOnly på alla komponenter med klassen elements definierad*/
$(document).ready(function(){
$('.elements').attr('readonly',false);
$('.elements').prop('disabled',false);
});

