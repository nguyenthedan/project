$( document ).ready(function() {
	
	// SUBMIT FORM
    $("#form-sell").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		pair : $("#pair").val(),
    		priceAbove :  $("#priceAbove").val(),
    		priceBelow :  $("#priceBelow").val(),
    		amount :  $("#amount").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location + "api/sell",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
//				if(result.status == "Done"){
				$("#tbl_posts").append("<tr><td><span class='sn'>1</span></td><td>"+result.pair+"</td><td>"+result.priceAbove+"</td><td>"+result.priceBelow+"</td><td>"+result.amount+"</td><td><a class='btn btn-xs delete-record' data-id='1'><i class='glyphicon glyphicon-trash'></i></a></td></tr>");
//				}else{
//					$("#postResultDiv").html("<strong>Error</strong>");
//				}
//				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();
 
    }
    
    function resetData(){
    	$("#pair").val("");
    	$("#priceAbove").val(null);
    	$("#priceBelow").val(null);
    	$("#amount").val(null);
    }
})