/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Sätter readOnly på alla komponenter med klassen elements definierad*/
$(document).ready(function(){
$('.elements').attr('readonly',true);
$('.elements').prop('disabled',true);
});