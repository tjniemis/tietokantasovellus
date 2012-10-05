/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function saveOffer(formId) { 
    //alert(formId);
    var form = document.getElementById(formId);
    var price = form.price.value;
    var desc = form.pricetext.value;
    var json = '{"price": "'+price+'", "description" : "'+desc+'"}';
    //alert(json);
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: json,
        success: function(data){
		
            $(function() {
                    $( "#offerdialog" ).dialog('open');
            });
        },
        error: function (xhr, ajaxOptions, thrownError, data) {
        	alert(data.root);
      },
        type: 'POST',
        url: 'addOffer/'+formId
    });
}

function closeDialog(div) {
    $(div).dialog('close');
}

function saveQuestion() {
    var q_job = document.getElementById('q_job').value;
    var q_text = document.getElementById('q_text').value;
    var json = '{"question": "'+q_text+'"}';
    //alert(json);
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: json,
        success: function(data){
		var divid = "q_"+q_job;
		var ul_div = document.getElementById(divid).innerHTML;
		var new_content = '<li class="jobtext"><b class="jobtext">Kysymys:&nbsp;</b>'+data.question+'</li>';
		new_content += '<li class="jobtext"><b class="jobtext">Vastaus:&nbsp;</b><i>Työn tilaaja ei ole vielä vastannut kysymykseen.</i></li><hr>';    
		document.getElementById('q_'+q_job).innerHTML = new_content+ul_div;
		$(function() {
		    $( "#dialog2" ).dialog('close');
		});
		document.getElementById('q_text').value = "";
        },
        error: function (xhr, ajaxOptions, thrownError, data) {
        	alert(data.exception);
      },
        type: 'POST',
        url: 'addQuestion/'+q_job
    });
}

function saveAnswer() {
    var q_dialog_id = document.getElementById('q_dialog_id').value;
    var a_text = document.getElementById('a_text').value;
    var json = '{"answer": "'+a_text+'"}';
    //alert(json);
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: json,
        success: function(data){		
		var divid = "q_a_"+q_dialog_id;
		document.getElementById(divid).innerHTML = data.answer;		
		document.getElementById('a_text').value = "";
		$(function() {
		    $( "#dialog" ).dialog('close');
		});
        },
        error: function (xhr, ajaxOptions, thrownError, data) {
        	alert(data.exception);
      },
        type: 'POST',
        url: 'addAnswer/'+q_dialog_id
    });
}

function deleteQuestion(questionId) {
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: "",
        success: function(data){
		var divid = "question_id_"+questionId;
		document.getElementById(divid).innerHTML = "";	
        },
        error: function (xhr, ajaxOptions, thrownError, data) {
        	alert(data.exception);
      },
        type: 'POST',
        url: 'deleteQuestion/'+questionId
    });
}