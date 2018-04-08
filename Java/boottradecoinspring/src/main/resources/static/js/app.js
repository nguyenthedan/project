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
    		sellPriceAbove :  $("#sellPriceAbove").val(),
    		sellPriceBelow :  $("#sellPriceBelow").val(),
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
//					$("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" + 
//												"Post Successfully! <br>" +
//												"---> Customer's Info: FirstName = " + 
//												result.data.firstname + " ,LastName = " + result.data.lastname + "</p>");
//				}else{
//					$("#postResultDiv").html("<strong>Error</strong>");
//				}
//				console.log(result);
				console.log('POST: ' + result);
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
    	$("#sellPriceAbove").val(null);
    	$("#sellPriceBelow").val(null);
    	$("#amount").val(null);
    }
})