/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function saveOffer(formId) { 
    var form = document.getElementById(formId);
    var price = form.price.value;
    var desc = form.pricetext.value;
    if (price==null || price == "") {
	alert('Hinta on pakollinen');
	return;	
    }
    var json = '{"price": "'+price+'", "description" : "'+desc+'"}';
    //alert(json);
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: json,
        success: function(data){
	    //document.getElementById('offer_'+formId).innerHTML = '<br><i>Olet jo jättänyt työlle tarjouksen. Voit tarkastella tarjoustasi ja halutessasi poistaa sen Omat tiedot-osiosta.</i>';
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
    location.href = 'personalData';
}

function saveQuestion() {
    var q_job = document.getElementById('q_job').value;
    var q_text = document.getElementById('q_text').value;
    if (q_text==null || q_text == "") {
	alert('Teksti on pakollinen');
	return;	
    }
    var json = '{"question": "'+q_text+'"}';
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
    if (a_text==null || a_text == "") {
	alert('Teksti on pakollinen');
	return;	
    }
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

function reviewJob() {
    var user_id = document.getElementById('user_id').value;
    var job_id = document.getElementById('job_id').value;
    var r_text = document.getElementById('r_text').value;
    var r_rating = $("input:radio[name=r_rating]:checked").val();
    var json = '{"rating": "'+r_rating+'", "review" : "'+r_text+'"}';
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: json,
        success: function(data){		
		
		$(function() {
		    $( "#dialog" ).dialog('close');
		});
		window.location.reload();
        },
        error: function (xhr, ajaxOptions, thrownError, data) {
        	alert(data.exception);
      },
        type: 'POST',
        url: 'reviewUser/'+user_id+'/'+job_id
    });
}

function getUserReviews(userId) {
    var json = '';
    //alert(json);
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: json,
        success: function(data){
		var reviews = '<ul>';
                document.getElementById('review_target').innerHTML = '<h3>Arviot käyttäjästä '+data.user.name+'</h3>';	
		jQuery.each(data.reviews, function() {
			reviews += '<li class=\"jobtext\">';
			var stars = '';
			for (var i=0; i<this.rating; i++) {
				stars += '&#9733;';
			}
			reviews += '<b>Tähdet:</b> '+stars+'<br>';
			reviews += '<b>Arvio:</b> '+this.review;
			reviews += '<hr></li>';
		});	
		reviews += '</ul>';
		document.getElementById('reviews').innerHTML = reviews;
        },
        error: function (xhr, ajaxOptions, thrownError, data) {
        	alert(data.exception);
      },
        type: 'POST',
        url: 'getReviews/'+userId
    });
}