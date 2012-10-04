/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function saveOffer(formId) {
    var form = document.getElementById(formId);
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"price": "1001", "description" : "tarjouksen teksti json"}',
        success: function(data){
            $(function() {
                    $( "#offerdialog" ).dialog('open');
            });
        },
        error: function(){
            alert("error");
        },
        type: 'POST',
        url: 'addOffer/14'
    });
}

function closeDialog(div) {
    $(div).dialog('close');
}