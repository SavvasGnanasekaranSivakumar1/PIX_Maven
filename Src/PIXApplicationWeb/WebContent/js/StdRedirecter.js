function StdRedirecter(ajCall, resParam) {
  if (resParam == null || resParam == "") {
	  
	  //Introduced as part of Fortify XSS:DOM Fix
	  var responseText = ajCall.xhr.responseText;
	  var validatedData = validateResponseText(responseText);
	  window.location.href = validatedData;
	  
	  //window.location.href = ajCall.xhr.responseText;
  } else {
    window.location.href = resParam;
  }
}

//Introduced as part of Fortify XSS:DOM Fix
function validateResponseText(unsafe_str){
	var validatedResponseText;
	if(unsafe_str!=null){
		validatedResponseText=unsafe_str;
	}
	return validatedResponseText;
}