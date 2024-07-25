

//function to validate supplier 
function validateSupplier()
{
	var formObj = eval('document.supplierForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	var name = /^[a-zA-Z ]*$/;
	var email = /^.+@.+\..{2,3}$/;
	objError.innerHTML= "";
	
	if(formObj.elements["party.san"].value=="")
	{
		errorString=errorString+'<LI>Supplier San Number is required</LI>';
	}
	else if(formObj.elements["party.san"].value.indexOf("<")!= -1 || formObj.elements["party.san"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>San No. cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.name1"].value=="")
	{
		errorString=errorString+'<LI>Supplier Name is required</LI>';
	}
	else if(formObj.elements["party.name1"].value.indexOf("<")!= -1 || formObj.elements["party.name1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Publisher Name cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.activeFlag"].value=="")
	{
		errorString=errorString+'<LI>Supplier Status is not selected</LI>';
	}
	if(!name.test(formObj.elements["party.contactFirstName"].value))
	{
		errorString=errorString+'<LI>FirstName should contain only alphabets</LI>';
	}
	if(!name.test(formObj.elements["party.contactLastName"].value))
	{
		errorString=errorString+'<LI>LastName should contain only alphabets</LI>';
	}
	if(formObj.elements["party.phone1"].value.indexOf("<")!= -1 || formObj.elements["party.phone1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.phone2"].value.indexOf("<")!= -1 || formObj.elements["party.phone2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.fax1"].value.indexOf("<")!= -1 || formObj.elements["party.fax1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.fax2"].value.indexOf("<")!= -1 || formObj.elements["party.fax2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.mobile"].value.indexOf("<")!= -1 || formObj.elements["party.mobile"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Mobile cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address1"].value.indexOf("<")!= -1 || formObj.elements["party.address1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address2"].value.indexOf("<")!= -1 || formObj.elements["party.address2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address3"].value.indexOf("<")!= -1 || formObj.elements["party.address3"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address3 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address4"].value.indexOf("<")!= -1 || formObj.elements["party.address4"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address4 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.state"].value.indexOf("<")!= -1 || formObj.elements["party.state"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>State cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.city"].value.indexOf("<")!= -1 || formObj.elements["party.city"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>City cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.postalCode"].value.indexOf("<")!= -1 || formObj.elements["party.postalCode"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Zip cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.countryDetail.countryCode"].value=="")
	{
		errorString=errorString+'<LI>Country is not selected</LI>';
	}
	if(formObj.elements["party.email"].value == "" )
	{
		errorString=errorString+'<LI>Email Id is required</LI>';
	}
	else if(!isValidemail(formObj))
	{
		errorString=errorString+'<LI>Email entered is invalid</LI>';
	}
	else if(!email.test(formObj.elements["party.email"].value))
	{
		errorString=errorString+'<LI>Email entered is invalid</LI>';
	}
	if(formObj.elements["party.website"].value.indexOf("<")!= -1 || formObj.elements["party.website"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>WebSite cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	
	if(formObj.elements["party.partyType"].value=="")
	{
		errorString=errorString+'<LI>Supplier Type is not selected</LI>';
	}
	if(formObj.elements["party.transportDetail.accessMethodDetail.refId"].value=="")
	{
		errorString=errorString+'<LI>Please select ACCESS METHOD from the Transport Details tab</LI>';
	}
		
	if(formObj.elements["party.transportDetail.serverName"]!=undefined)
	{
		if((formObj.elements["party.transportDetail.serverName"].value.indexOf("<")!= -1 || formObj.elements["party.transportDetail.serverName"].value.indexOf(">")!= -1))
		{
			errorString=errorString+'<LI>ServerName cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}
	}
	if(formObj.elements["party.transportDetail.login"]!=undefined)
	{
		if((formObj.elements["party.transportDetail.login"].value.indexOf("<")!= -1 || formObj.elements["party.transportDetail.login"].value.indexOf(">")!= -1))
		{
			errorString=errorString+'<LI>FTP USer cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}
	}
	if(formObj.elements["party.transportDetail.password"]!=undefined)
	{
		if((formObj.elements["party.transportDetail.password"].value.indexOf("<")!= -1 || formObj.elements["party.transportDetail.password"].value.indexOf(">")!= -1))
		{
			errorString=errorString+'<LI>Password cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}
	}
	if(formObj.elements["party.transportDetail.folder"]!=undefined)
	{
		if((formObj.elements["party.transportDetail.folder"].value.indexOf("<")!= -1 || formObj.elements["party.transportDetail.folder"].value.indexOf(">")!= -1))
		{
			errorString=errorString+'<LI>FTP GET cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}
	}
	if(formObj.elements["party.transportDetail.putFolder"]!=undefined)
	{
		if((formObj.elements["party.transportDetail.putFolder"].value.indexOf("<")!= -1 || formObj.elements["party.transportDetail.putFolder"].value.indexOf(">")!= -1))
		{
			errorString=errorString+'<LI>FTP PUT cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}
	}
	
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
		formObj.action="#error_div";
		return false;
	}
	
}

//Email validation for party
function isValidemail(formname) 
{
	var email = formname.elements["party.email"].value
	var count = 0
	if ( (email != "") && ((formname.elements["party.email"].value.indexOf("@")==-1) ||(formname.elements["party.email"].value.indexOf(".")==-1)))
	{
		return false;
	}
	if((email.substring(0,1)<"a" ||email.substring(0,1)>"z") && (email.substring(0,1)<"A" || email.substring(0,1)>"Z"))
	{
		return false;
	}
	for (var i = 1; i < email.length; i=i+1) 
	{
		var ch = email.substring(i, i + 1);
		
		if ( ((ch < "a" || "z" < ch) && (ch < "A" || "Z" < ch)) && (ch < "0" || "9" < ch) && (ch != '_') && (ch != '.') && (ch != '@')&& (ch != '-')) 
		{
			return false;
		}
		if(ch == '@')
		{
			count = count + 1
		}
	}
	if(count>1)
	{
		return false;
	}
	return true;
}

//email validation for user
function isValidemailuser(formname) 
{
	var email = formname.elements["user.email"].value
	var count = 0
	if ( (email != "") && ((formname.elements["user.email"].value.indexOf("@")==-1) ||(formname.elements["user.email"].value.indexOf(".")==-1)))
	{
		return false;
	}
	if((email.substring(0,1)<"a" ||email.substring(0,1)>"z") && (email.substring(0,1)<"A" || email.substring(0,1)>"Z"))
	{
		return false;
	}
	for (var i = 1; i < email.length; i=i+1) 
	{
		var ch = email.substring(i, i + 1);
		
		if ( ((ch < "a" || "z" < ch) && (ch < "A" || "Z" < ch)) && (ch < "0" || "9" < ch) && (ch != '_') && (ch != '.') && (ch != '@')&& (ch != '-')) 
		{
			return false;
		}
		if(ch == '@')
		{
			count = count + 1
		}
	}
	if(count>1)
	{
		return false;
	}
	return true;
}

//function to validate publisher
function validatePublisher()
{
	var formObj = eval('document.addPubUnitForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	var name = /^[a-zA-Z ]*$/;
	var email = /^.+@.+\..{2,3}$/;
	objError.innerHTML= "";
	
	if(formObj.elements["party.san"].value=="")
	{
		errorString=errorString+'<LI>Publisher San Number is required</LI>';
	}
	else if(formObj.elements["party.san"].value.indexOf("<")!= -1 || formObj.elements["party.san"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>San No. cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	
	if(formObj.elements["party.name1"].value=="")
	{
		errorString=errorString+'<LI>Publisher Name is required</LI>';
	}
	else if(formObj.elements["party.name1"].value.indexOf("<")!= -1 || formObj.elements["party.name1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Publisher Name cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	
	if(formObj.elements["party.activeFlag"].value=="")
	{
		errorString=errorString+'<LI>Publisher Status is not selected</LI>';
	}
	if(!name.test(formObj.elements["party.contactFirstName"].value))
	{
		errorString=errorString+'<LI>FirstName should contain only alphabets</LI>';
	}
	if(!name.test(formObj.elements["party.contactLastName"].value))
	{
		errorString=errorString+'<LI>LastName should contain only alphabets</LI>';
	}
	if(formObj.elements["party.phone1"].value.indexOf("<")!= -1 || formObj.elements["party.phone1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.phone2"].value.indexOf("<")!= -1 || formObj.elements["party.phone2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.fax1"].value.indexOf("<")!= -1 || formObj.elements["party.fax1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.fax2"].value.indexOf("<")!= -1 || formObj.elements["party.fax2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.mobile"].value.indexOf("<")!= -1 || formObj.elements["party.mobile"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Mobile cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address1"].value.indexOf("<")!= -1 || formObj.elements["party.address1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address2"].value.indexOf("<")!= -1 || formObj.elements["party.address2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address3"].value.indexOf("<")!= -1 || formObj.elements["party.address3"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address3 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.address4"].value.indexOf("<")!= -1 || formObj.elements["party.address4"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address4 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.state"].value.indexOf("<")!= -1 || formObj.elements["party.state"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>State cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.city"].value.indexOf("<")!= -1 || formObj.elements["party.city"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>City cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.postalCode"].value.indexOf("<")!= -1 || formObj.elements["party.postalCode"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Zip cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["party.countryDetail.countryCode"].value=="")
	{
		errorString=errorString+'<LI>Country is not selected</LI>';
	}
	
	if(formObj.elements["party.email"].value == "" )
	{
		errorString=errorString+'<LI>Email Id is required</LI>';
	}
	else if(!isValidemail(formObj))
	{
		errorString=errorString+'<LI>Email entered is invalid</LI>';
	}
	else if(!email.test(formObj.elements["party.email"].value))
	{
		errorString=errorString+'<LI>Email entered is invalid</LI>';
	}
	
	if(formObj.elements["party.website"].value.indexOf("<")!= -1 || formObj.elements["party.website"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>WebSite cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	
	
	
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
		formObj.action="#error_div";
		return false;
	}
	
}

//function to validate user
function validateUser(changePass)
{
	var formObj = eval('document.userForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	var invalid = " "; // Invalid character is a space
	var minLength = 7; // Minimum length	
//	var passwd = formObj.elements["user.password"].value;
    //var flag=false;
    var messgFlag=0;
  //  var changePassword=document.getElementById("changePass").value;
	var name = /^[a-zA-Z ]*$/;
	var email = /^.+@.+\..{2,3}$/;
	objError.innerHTML= "";
	
	if(formObj.elements["user.ssoid"].value=="")
	{
		errorString=errorString+'<LI>ssoid is required</LI>';
	}

	if(formObj.elements["user.login"].value=="" || formObj.elements["user.login"].value == null)
	{
		errorString=errorString+'<LI>UserId is required</LI>';
	}
	else if(formObj.elements["user.login"].value.indexOf("<")!= -1 || formObj.elements["user.login"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Login cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	
	if(formObj.elements["user.firstName"].value=="")
	{
		errorString=errorString+'<LI>FirstName is required</LI>';
	}	
	else if(!name.test(formObj.elements["user.firstName"].value))
	{
		errorString=errorString+'<LI>FirstName should contain only alphabets</LI>';
	}
	
	if(formObj.elements["user.lastName"].value=="")
	{
		errorString=errorString+'<LI>LastName is required</LI>';
	}
	else if(!name.test(formObj.elements["user.lastName"].value))
	{
		errorString=errorString+'<LI>LastName should contain only alphabets</LI>';
	}
	if(formObj.elements["user.roleTypeDetail.roleType"].value=="")
	{
		errorString=errorString+'<LI>Account Type is not selected</LI>';
	}
	if(formObj.elements["user.activeFlag"].value=="")
	{
		errorString=errorString+'<LI>Account Status is not selected</LI>';
	}
/*	if(changePassword=="Y")
	{		
		errorString=errorString+'<LI>You cannot set the password same as the previous password.</LI>';		
	}
		
		
	if(formObj.elements["user.password"].value=="")
	{
		errorString=errorString+'<LI>Password is required</LI>';
	}
	else if (passwd.length < minLength)
	{
		errorString=errorString+'<LI>Password must be at least 7 characters long.</LI>';
	}
	else if (formObj.elements["user.password"].value.indexOf(invalid) > -1)
	{
		errorString=errorString+'<LI>Password should not contain spaces.</LI>';
	}
	else if (!(passwd.match(/\d/)))
	{            
        messgFlag=1;    
    }
    else if (!(passwd.match(/[A-Z]/)))
    {
        messgFlag=1;
    }
    else if (!(passwd.match(/[a-z]/)))
    { //messg="Password must include at least one Lowercase letter.\n";
        messgFlag=1;
    }
    else if (!(passwd.match(/[\W+_]/)))
    {//messg="Password must include at least one Special character.\n";
        messgFlag=1;
    }  
    
	if(messgFlag==1)
	{
	   errorString=errorString+'<LI>Password should contain atleast one uppercase, one lowercase, one numeric, and one special character.</LI>';
	}	
	
	if(formObj.elements["user.passwordExpiry"].value=="")
	{
		errorString=errorString+'<LI>Password Expires is not selected</LI>';
	}
	*/
	if(formObj.elements["user.phone1"].value.indexOf("<")!= -1 || formObj.elements["user.phone1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.phone2"].value.indexOf("<")!= -1 || formObj.elements["user.phone2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.fax1"].value.indexOf("<")!= -1 || formObj.elements["user.fax1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.fax2"].value.indexOf("<")!= -1 || formObj.elements["user.fax2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address1"].value.indexOf("<")!= -1 || formObj.elements["user.address1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address2"].value.indexOf("<")!= -1 || formObj.elements["user.address2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address3"].value.indexOf("<")!= -1 || formObj.elements["user.address3"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address3 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address4"].value.indexOf("<")!= -1 || formObj.elements["user.address4"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address4 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.state"].value.indexOf("<")!= -1 || formObj.elements["user.state"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>State cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.city"].value.indexOf("<")!= -1 || formObj.elements["user.city"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>City cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.postalCode"].value.indexOf("<")!= -1 || formObj.elements["user.postalCode"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Zip cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	
	if(formObj.elements["user.countryDetail.countryCode"].value=="")
	{
		errorString=errorString+'<LI>Country is not selected</LI>';
	}
		
	if(formObj.elements["user.email"].value=="")
	{
		errorString=errorString+'<LI>Email is required</LI>';
	}
	else if(!isValidemailuser(formObj))
	{
		errorString=errorString+'<LI>Email entered is invalid</LI>';
	}
	else if(!email.test(formObj.elements["user.email"].value))
	{
		errorString=errorString+'<LI>Email entered is invalid</LI>';
	}
	
	if(formObj.elements["user.mobile"].value.indexOf("<")!= -1 || formObj.elements["user.mobile"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Mobile cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.website"].value.indexOf("<")!= -1 || formObj.elements["user.website"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>WebSite cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= errorString;
		formObj.action="#error_div";
		return false;
	}
	
}

//function to validateUser during Profile edit
function validateUserProfile()
{
	var formObj = eval('document.userForm');
	var errorString = "";
	var invalid = " "; // Invalid character is a space
	var minLength = 7; // Minimum length	
	var passwd = formObj.elements["user.password"].value;
    //var flag=false;
    var messgFlag=0;	   
	var objError=document.getElementById("error_div");
	var name = /^[a-zA-Z ]*$/;
	var email = /^.+@.+\..{2,3}$/;
	objError.innerHTML= "";
		
	if(passwd=="")
	{
		errorString=errorString+'<LI>Password is required</LI>';
	}

	else if (passwd.length < minLength)
	{
		errorString=errorString+'<LI>Password must be at least 7 characters long.</LI>';
	}
	else if (formObj.elements["user.password"].value.indexOf(invalid) > -1)
	{
		errorString=errorString+'<LI>Password should not contain spaces.</LI>';
	}
	else if (!(passwd.match(/\d/)))
	{            
        messgFlag=1;    
    }
    else if (!(passwd.match(/[A-Z]/)))
    {
        messgFlag=1;
    }
    else if (!(passwd.match(/[a-z]/)))
    { //messg="Password must include at least one Lowercase letter.\n";
        messgFlag=1;
    }
 
    else if (!(passwd.match(/[\W+_]/)))
    {//messg="Password must include at least one Special character.\n";
        messgFlag=1;
    }
    
	if(messgFlag==1)
	{
	   errorString=errorString+'<LI>Password should contain atleast one uppercase, one lowercase, one numeric, and one special character.</LI>';
	}
	
	//----------------------------------------------------------------------------------------------
	if(formObj.elements["user.phone1"].value.indexOf("<")!= -1 || formObj.elements["user.phone1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.phone2"].value.indexOf("<")!= -1 || formObj.elements["user.phone2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.fax1"].value.indexOf("<")!= -1 || formObj.elements["user.fax1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.fax2"].value.indexOf("<")!= -1 || formObj.elements["user.fax2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address1"].value.indexOf("<")!= -1 || formObj.elements["user.address1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address2"].value.indexOf("<")!= -1 || formObj.elements["user.address2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address3"].value.indexOf("<")!= -1 || formObj.elements["user.address3"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address3 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address4"].value.indexOf("<")!= -1 || formObj.elements["user.address4"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address4 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.state"].value.indexOf("<")!= -1 || formObj.elements["user.state"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>State cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.city"].value.indexOf("<")!= -1 || formObj.elements["user.city"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>City cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.postalCode"].value.indexOf("<")!= -1 || formObj.elements["user.postalCode"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Zip cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.countryDetail.countryCode"].value=="")
	{
		errorString=errorString+'<LI>Country is not selected</LI>';
	}
	
	if(formObj.elements["user.mobile"].value.indexOf("<")!= -1 || formObj.elements["user.mobile"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Mobile cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.website"].value.indexOf("<")!= -1 || formObj.elements["user.website"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>WebSite cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= errorString;
		formObj.action="#error_div";
		return false;
	}
	
}

//
function validateUserProf(changePass)
{
	var formObj = eval('document.userForm');
	var errorString = "";
	var invalid = " "; // Invalid character is a space
	var minLength = 7; // Minimum length	
//	var passwd = formObj.elements["user.password"].value;
    //var flag=false;
    var messgFlag=0;	
//    var changePassword=document.getElementById("changePass").value;
	var objError=document.getElementById("error_div");
	var name = /^[a-zA-Z ]*$/;
	var email = /^.+@.+\..{2,3}$/;
	objError.innerHTML= "";
/*	
	if(changePassword=="Y")
	{	
		errorString=errorString+'<LI>You cannot set the password same as the previous password.</LI>';		
	}
	if(passwd=="")
	{
		errorString=errorString+'<LI>Password is required</LI>';
	}

	else if (passwd.length < minLength)
	{
		errorString=errorString+'<LI>Password must be at least 7 characters long.</LI>';
	}
	else if (formObj.elements["user.password"].value.indexOf(invalid) > -1)
	{
		errorString=errorString+'<LI>Password should not contain spaces.</LI>';
	}
	else if (!(passwd.match(/\d/)))
	{            
        messgFlag=1;    
    }
    else if (!(passwd.match(/[A-Z]/)))
    {
        messgFlag=1;
    }
    else if (!(passwd.match(/[a-z]/)))
    { //messg="Password must include at least one Lowercase letter.\n";
        messgFlag=1;
    }

    else if (!(passwd.match(/[\W+_]/)))
    {//messg="Password must include at least one Special character.\n";
        messgFlag=1;
    }
    
	if(messgFlag==1)
	{
	   errorString=errorString+'<LI>Password should contain atleast one uppercase, one lowercase, one numeric, and one special character.</LI>';
	}
	*/
	//----------------------------------------------------------------------------------------------
	if(formObj.elements["user.phone1"].value.indexOf("<")!= -1 || formObj.elements["user.phone1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.phone2"].value.indexOf("<")!= -1 || formObj.elements["user.phone2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Phone2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.fax1"].value.indexOf("<")!= -1 || formObj.elements["user.fax1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.fax2"].value.indexOf("<")!= -1 || formObj.elements["user.fax2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Fax2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address1"].value.indexOf("<")!= -1 || formObj.elements["user.address1"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address1 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address2"].value.indexOf("<")!= -1 || formObj.elements["user.address2"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address2 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address3"].value.indexOf("<")!= -1 || formObj.elements["user.address3"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address3 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.address4"].value.indexOf("<")!= -1 || formObj.elements["user.address4"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Address4 cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.state"].value.indexOf("<")!= -1 || formObj.elements["user.state"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>State cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.city"].value.indexOf("<")!= -1 || formObj.elements["user.city"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>City cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.postalCode"].value.indexOf("<")!= -1 || formObj.elements["user.postalCode"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Zip cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.countryDetail.countryCode"].value=="")
	{
		errorString=errorString+'<LI>Country is not selected</LI>';
	}
	
	if(formObj.elements["user.mobile"].value.indexOf("<")!= -1 || formObj.elements["user.mobile"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Mobile cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["user.website"].value.indexOf("<")!= -1 || formObj.elements["user.website"].value.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>WebSite cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= errorString;
		formObj.action="#error_div";
		return false;
	}
	
}


//function to validate supplier during deletion when no supplier is selected
function validateDeleteSupplier()
{
	formname = eval('document.userForm');
	if(formname.radioSupplier == null || formname.radioSupplier == undefined)
	{
		alert('No Supplier record exists in the page to delete');
	}
	else
	{
		var radioSupplierTotal = formname.radioSupplier.length;
		
		if(radioSupplierTotal==undefined)
		{
			radioSupplierTotal = 1;
		}
		if(radioSupplierTotal>1)
		{
			for(j=0;j<radioSupplierTotal;j=j+1)
    		{
    			if(formname.radioSupplier[j].checked)
				{
					formname.supplierIds.value = 1;
    				return true;
				}
			}
    		alert('Please select a Supplier to delete.. Click on Radio button to select SUPPLIER');
    	}
    	else if(radioSupplierTotal==1)
    	{
    		if(formname.radioSupplier.checked)
			{
				formname.supplierIds.value = 1;
    			return true;
			}
			else
			{
    			alert('Please select a Supplier to delete.. Click on Radio button to select SUPPLIER');
    		}
    	}
    	
    }
    formname.supplierIds.value = 1;
}

//function to validate supplier during deletion when no or one or more than one supplier is selected.
function validateDeleteSupp()
{
	formname = eval('document.userForm');
	//var supp = document.getElementById('suppIds').value;
	if(formname.radioSupplier == null || formname.radioSupplier == undefined)
	{
		alert('No Supplier record exists in the page to delete');
	}
	else
	{
		var checkBoxTotal = formname.radioSupplier.length;
		
		var flag=0;
		if(checkBoxTotal==undefined)
		{
			checkBoxTotal = 1;
		}
		var selectedArr = "";
		if(checkBoxTotal>1)
		{
			var selectedCounter = 0;
			for(j=0;j<checkBoxTotal;j=j+1)
			{				
				if(formname.radioSupplier[j].checked)
				{
					
	    			selectedCounter = selectedCounter + 1;
	    			
	    			if(flag==0)
					{
						selectedArr = formname.radioSupplier[j].value;
						flag=1;
						
					}
					else if(flag!=0)
					{
						selectedArr = selectedArr + "," + formname.radioSupplier[j].value;
						
					}	
					else{
						alert("Please select a Supplier to delete.");
						return false;
					}
	    		}
			}
    		if(selectedCounter == 0)
			{
				alert("Please select a Supplier to delete.");
				return false;
			}
			formname.supplierIds.value=selectedArr;	
			return true;
		}
		else if(checkBoxTotal==1)
		{
			if(formname.radioSupplier.checked)
			{
    			if(flag==0)
				{
					selectedArr = formname.radioSupplier.value;
					flag=1;
				}
				else if(flag!=0)
				{
					selectedArr = selectedArr + "," + formname.radioSupplier[j].value;
				}
				else{
					alert("Please select a Supplier to delete.");
					return false;
				}
    		}
    		else
    		{
    			alert("Please select a Supplier to delete.");
    			return false;
    		}
    		formname.supplierIds.value=selectedArr;
    		return true;	
		}
		
	}	
}


//function to validate publisher during deletion when no publisher is selected
function validateDeletePublisher()
{
	formname = eval('document.userForm');
	if(formname.radioPublisher == null || formname.radioPublisher == undefined)
	{
		alert('No Publisher record exists in the page to delete');
	}
	else
	{
		var radioPublisherTotal = formname.radioPublisher.length;
		if(radioPublisherTotal==undefined)
		{
			radioPublisherTotal = 1;
		}
		if(radioPublisherTotal>1)
		{
			for(j=0;j<radioPublisherTotal;j=j+1)
    		{
    			if(formname.radioPublisher[j].checked)
				{
					formname.publisherIds.value = 1;
    				return true;
				}
			} 
			alert('Please select a Publisher to delete.. Click on Radio button to select PUBLISHER');
		}
		else if(radioPublisherTotal==1)
    	{
    		if(formname.radioPublisher.checked)
			{
				formname.publisherIds.value = 1;
    			return true;
			}
			else
			{
    			alert('Please select a Publisher to delete.. Click on Radio button to select PUBLISHER');
    		}
    	}
	}
	formname.publisherIds.value = 1;
}

//function to validate publisher during deletion when no or one or more than one Publisher is selected.
function validateDeletePub()
{
	formname = eval('document.userForm');	
	if(formname.radioPublisher == null || formname.radioPublisher == undefined)
	{
		alert('No Publisher record exists in the page to delete');
	}
	else
	{
		var checkBoxTotal = formname.radioPublisher.length;
		
		var flag=0;
		if(checkBoxTotal==undefined)
		{
			checkBoxTotal = 1;
		}
		var selectedArr = "";
		if(checkBoxTotal>1)
		{
			var selectedCounter = 0;
			for(j=0;j<checkBoxTotal;j=j+1)
			{				
				if(formname.radioPublisher[j].checked)
				{
					
	    			selectedCounter = selectedCounter + 1;
	    			
	    			if(flag==0)
					{
						selectedArr = formname.radioPublisher[j].value;
						flag=1;
						
					}
					else if(flag!=0)
					{
						selectedArr = selectedArr + "," + formname.radioPublisher[j].value;
						
					}	
					else{
						alert("Please select a Publisher to delete.");
						return false;
					}
	    		}
			}
    		if(selectedCounter == 0)
			{
				alert("Please select a Publisher to delete.");
				return false;
			}
			formname.publisherIds.value=selectedArr;	
			return true;
		}
		else if(checkBoxTotal==1)
		{
			if(formname.radioPublisher.checked)
			{
    			if(flag==0)
				{
					selectedArr = formname.radioPublisher.value;
					flag=1;
				}
				else if(flag!=0)
				{
					selectedArr = selectedArr + "," + formname.radioPublisher[j].value;
				}
				else{
					alert("Please select a Publisher to delete.");
					return false;
				}
    		}
    		else
    		{
    			alert("Please select a Publisher to delete.");
    			return false;
    		}
    		formname.publisherIds.value=selectedArr;
    		return true;	
		}
		
	}	
}


//function to validate user during deletion when no user is selected
function validateDeleteUser()
{
	formname = eval('document.userForm');
	if(formname.radioUser == null || formname.radioUser == undefined)
	{
		alert('No User record exists in the page to delete');
	}
	else
	{
		
		var radioUserTotal = formname.radioUser.length;
		if(radioUserTotal==undefined)
		{
			radioUserTotal = 1;
		}
		if(radioUserTotal>1)
		{
			for(j=0;j<radioUserTotal;j=j+1)
    		{
    			if(formname.radioUser[j].checked)
				{
					if(formname.activeFlag[j].value == 'Disabled')
					{
						alert("User is already Disabled..");
						return false;
					}
					else
					{
						if(confirm("Are you sure you want to disable this user?"))
						{
    						return true;
    					}
    					else
    					{
    						return false;
    					}
    				}
				}
			} 
			alert('Please select a USER to disable.. Click on Radio button to select USER');
		}
		else if(radioUserTotal==1)
    	{
    		if(formname.radioUser.checked)
			{
				if(formname.activeFlag.value == 'Disabled')
				{
					alert("User is already Disabled..");
					return false;
				}
				else
				{
					if(confirm("Are you sure you want to disable this user?"))
					{
    					return true;
    				}
    			}
			}
			else
			{
    			alert('Please select a USER to disable.. Click on Radio button to select USER');
    		}
    	}
    	
	}
}

//function to return true when click on the password field.
function passwordClick()
{		
	var formObj = eval('document.userForm');
	var onform 	= formObj.elements["user.password"].value;	
	var password = document.getElementById('PassWord').value;	
	if(event.keyCode!=9)
	{
		if(onform == password)
		{		
			document.getElementById('changePass').value="Y";		
		}
		else
		{
			document.getElementById('changePass').value="N";		
		}
	}
}	
//
function passwordClickAdmin()
{		
	var formObj = eval('document.userForm');	
	var onform 	= formObj.elements["user.password"].value;	
	var password = document.getElementById('PassWord').value;		
	if(event.keyCode!=9)
	{
		if(onform == password)
		{		
			document.getElementById('changePass').value="Y";		
		}
		else
		{
			document.getElementById('changePass').value="N";		
		}
	}
	
}	

//function to validate B2B supplier
function submitActionForSupplier(path)
{
	var status;
	formname = eval('document.supplierForm');
	status = formname.access[formname.access.selectedIndex].text;
	if(status == 'B2B')
	{
		if(formname.serverName.value == "" || formname.login.value == "" || formname.password.value == "" || formname.folder.value == "" || formname.putFolder.value == "")
		{
			alert('To add a B2B supplier, please fill all the fields in the Transport Details Tab');
		}
		else
		{
			formname.action = path;
     		formname.submit();
     		document.body.style.cursor = "wait";
    		buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
    		
     	}
	}
	else
	{
		formname.action = path;
		formname.submit();
		
		document.body.style.cursor = "wait";
    	buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
    	
	}
}



//function to get the string from the supplier popUp
function checkvalidate(path)
{
	formname = eval('document.userForm');
	var j;
	var checkBoxTotal = formname.supplierId.length;
	var flag=0;
	if(checkBoxTotal==undefined)
	{
		checkBoxTotal = 1;
	}
	var supplierIdArr = "";
	if(checkBoxTotal>1)
	{
		var selectedCounter = 0;
		for(j=0;j<checkBoxTotal;j=j+1)
    	{
    		if(formname.supplierId[j].checked)
			{
				selectedCounter = selectedCounter + 1;
    			if(flag==0)
				{
					supplierIdArr = formname.supplierId[j].value;
					flag=1;
				}
				else if(flag!=0)
					supplierIdArr = supplierIdArr + "," + formname.supplierId[j].value;
				else
				{
					alert("Please select atleast one Supplier.");
					return;
				}
			}
    	} 
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one Supplier.");
			return;
		}
    }
    else if(checkBoxTotal==1)
	{
		if(formname.supplierId.checked)
		{
    		if(flag==0)
			{
				supplierIdArr = formname.supplierId.value;
				flag=1;
			}
			else if(flag!=0)
				supplierIdArr = selectedArr + "," + formname.supplierId[j].value;
			else{
				alert("Please select atleast one Supplier.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one Supplier.");
    		return;
    	}
	}
    
    window.opener.document.forms[0].supplierIds.value = supplierIdArr;
    window.opener.document.forms[0].action=path;
	window.opener.document.forms[0].submit();
	window.close();
		
}

//function to get the string from the publisher popUp
function checkvalidatepublisher(path)
{

	formname = eval('document.userForm');
	var checkBoxTotal = formname.publisherId.length;
	var flag=0;
	if(checkBoxTotal==undefined)
	{
		checkBoxTotal = 1;
	}
	
	var publisherIdArr ="";
	if(checkBoxTotal>1)
	{
		var selectedCounter = 0;
		for(j=0;j<checkBoxTotal;j=j+1)
    	{
    		if(formname.publisherId[j].checked)
			{
				selectedCounter = selectedCounter + 1;
    			if(flag==0)
				{
					publisherIdArr = formname.publisherId[j].value;
					flag=1;
				}
				else if(flag!=0)
					publisherIdArr = publisherIdArr + "," + formname.publisherId[j].value;
				else
					alert("Please select atleast one Publisher.");
			}
    	}
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one Publisher.");
			return;
		}
    } 
    else if(checkBoxTotal==1)
	{
		if(formname.publisherId.checked)
		{
    		if(flag==0)
			{
				publisherIdArr = formname.publisherId.value;
				flag=1;
			}
			else if(flag!=0)
				publisherIdArr = publisherIdArr + "," + formname.publisherId[j].value;
			else{
				alert("Please select atleast one Publisher.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one Publisher.");
    		return;
    	}
	}
    
    window.opener.document.forms[0].publisherIds.value = publisherIdArr;
    window.opener.document.forms[0].action=path;
	window.opener.document.forms[0].submit();
	window.close();
}

//function to submit user
function submitActionUser(path)
{
	formname = eval('document.userForm');
    formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}

//function to submit publisher
function submitActionPublisher(path)
{
	formname = eval('document.addPubUnitForm');
    formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}

//function to submit supplier
function submitActionSupplier(path)
{
	formname = eval('document.supplierForm');
    formname.action = path;
    formname.submit();
    document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}

//function for cancel functionality for user
function submitUserCancelAction(path)
{
	formname = eval('document.userForm');
	if(confirm("This action will exit from the current page. Are you sure want to proceed?"))
	{
		formname.action = path;
		formname.submit();
    }
    
}

//function for cancel functionality for Publisher
function submitPublisherCancelAction(path)
{
	formname = eval('document.addPubUnitForm');
	if(confirm("This action will exit from the current page. Are you sure want to proceed?"))
	{
		formname.action = path;
		formname.submit();
    }
    
}

//function for cancel functionality for supplier
function submitSupplierCancelAction(path)
{
	formname = eval('document.supplierForm');
	if(confirm("This action will exit from the current page. Are you sure want to proceed?"))
	{
		formname.action = path;
		formname.submit();
    }
    
}
//function for filtering with previous next for publisher
function listfilterPublisher(path)
{
	formname = eval('document.addPubUnitForm');
	formname.action=path;
	formname.submit();
}

//function for filtering with previous next for supplier
function listfilterSupplier(path)
{
	formname = eval('document.supplierForm');
	formname.action=path;
	formname.submit();
}

//function for filtering with previous next for user
function listfilterUser(path)
{
	formname = eval('document.userForm');
	formname.action=path;
	formname.submit();
}

//function to open a pop up
function MM_openAdminBrWindow(theURL,winName,features) { 
	var newWindow = window.open(theURL,winName,features);
  	
	if (!document.all) {

		document.captureEvents (Event.CLICK);
	}
	document.onclick = function() {
		if (newWindow != null && !newWindow.closed) 
		{ 
			newWindow.focus();
		}
	}
	return false;  
}

//function to change privilege of user depending on whether user is admin,buyer or vendor
function changeUserPrivilege(obj)
{
	formname = eval('document.userForm');
	var roleType = obj.options[obj.selectedIndex].text;
	if(roleType=="ADMIN" || roleType=="BUYER")
	{
		formname.elements['planningPost'].options[1].selected = true;
		formname.elements['bookSpecPost'].options[1].selected = true;
		formname.elements['purchaseOrderPost'].options[1].selected = true;
		formname.elements['orderConfirmPost'].options[1].selected = true;
		formname.elements['orderStatusPost'].options[1].selected = true;
		formname.elements['deliveryMesgPost'].options[1].selected = true;
		formname.elements['goodsReceiptPost'].options[1].selected = true;
		formname.elements['usagePost'].options[1].selected = true;
		formname.elements['inventoryPost'].options[1].selected = true;
	}
	else
	{
		formname.elements['planningPost'].options[0].selected = true;
		formname.elements['bookSpecPost'].options[0].selected = true;
		formname.elements['purchaseOrderPost'].options[0].selected = true;
		formname.elements['orderConfirmPost'].options[0].selected = true;
		formname.elements['orderStatusPost'].options[0].selected = true;
		formname.elements['deliveryMesgPost'].options[0].selected = true;
		formname.elements['goodsReceiptPost'].options[0].selected = true;
		formname.elements['usagePost'].options[0].selected = true;
		formname.elements['inventoryPost'].options[0].selected = true;
	}
}

   function checkUncheckAllSup(theElement) {     
	var theForm = theElement.form, z = 0;		
    for(z=0; z<theForm.length;z++){
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'checkall' && theForm[z].name!= 'radioPublisher' && theForm[z].name!= 'checkallpub'){
	        theForm[z].checked = theElement.checked;
        }
    }
}
 
   
   function checkUncheckAllPub(theElement) {   
	var theForm1 = theElement.form, z = 0;	
    for(z=0; z<theForm1.length;z++){    	
		if(theForm1[z].type == 'checkbox' && theForm1[z].name!= 'checkallpub' && theForm1[z].name!= 'radioSupplier' && theForm1[z].name!= 'checkall'){
		    theForm1[z].checked = theElement.checked;
        }
    }
}
