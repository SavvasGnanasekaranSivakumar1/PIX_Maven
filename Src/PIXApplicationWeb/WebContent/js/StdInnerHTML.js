function StdInnerHTML(ajCall, resParam) {
  var a = resParam.split(",");
  
  for (var i = 0; i < a.length; i++) {
	  
	//Introduced as part of Fortify XSS:DOM Fix
    /*var unsafe_str = ajCall.xhr.responseText;
    var validatedData = validateResponseText(unsafe_str);
    document.getElementById(a[i]).innerHTML = validatedData;*/
    
    var tempResTxt = ajCall.xhr.responseText;
	var validatedResTxt = validateResTxt(tempResTxt);
	document.getElementById(a[i]).innerHTML = validatedResTxt;
    
    //document.getElementById(a[i]).innerHTML = ajCall.xhr.responseText;
  }
  
}

//Introduced as part of Fortify XSS:DOM Fix
/*function validateResponseText(unsafe_str){
	var validatedResponseText;
	if(unsafe_str!=null){
		validatedResponseText=unsafe_str;
	}
	return validatedResponseText;
}*/
		 
function validateResTxt(tempFilePath) {
	if(tempFilePath == null) {
		return null;
	}
		    	
	var aString = tempFilePath;
	var cleanPath = "";
	for (var i = 0; i < aString.length; ++i) {
		cleanPath += sanitizeElements(aString.charAt(i));
	}
	
	System.out.println("cleanPath: "+cleanPath);
	return cleanPath;
}

function sanitizeElements(aChar) {	    	
	var asciiValue = aChar.charCodeAt(0); // Get the ASCII value of the special character
	return String.fromCharCode(asciiValue); // Return the character corresponding to the ASCII value
}