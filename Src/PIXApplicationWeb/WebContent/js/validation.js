
// ***************************************** START OF correctETAs FUNCTION ****************************************
//This function is used for validating the supplier's delivery schedule dates.
//The schedule dates should be in order (ETA1 < ETA2 < ETA3 < ETA4)

function correctETAs(eta1,eta2,eta3,eta4)
{
if(eta1 != "" || eta2 != "" || eta3 != "" || eta4 != "" )
{
	var eta = new Array(4);
	var etaLength = 0;

	if(eta1 != "")
	{
		eta[etaLength] = eta1 ; etaLength++;
	}
	if(eta2 != "")
	{
		eta[etaLength] = eta2; etaLength++;
	}
	if(eta3 != "")
	{
		eta[etaLength] = eta3; etaLength++;
	}
	if(eta4 != "")
	{
		eta[etaLength] = eta4; etaLength++;
	}

	var ok = true;
	for(i = 0 ; i < etaLength - 1 && ok; i++)
	{
		if(dateCheck(eta[i], eta[i+1], ">="))
		{
			ok = false;
		}
	}
	if(!ok)
	{
		return false;
	}
	else
	{
		return true;
	}
} // end of if(eta1 != "" || eta2 != "" || eta3 != "" || eta4 != "" )
 return true;
} // end of function correctETAs


// ***************************************** START OF numbersonly FUNCTION ****************************************
//This function is used to validate whether the value entered in a field
//is a number or not.

function numbersonly()
{
	if((event.keyCode==8) || (event.keyCode==63272) || (event.keyCode==63234) || (event.keyCode==63235)){
		return true;
	}
	else
	{
	if (((event.keyCode<48||event.keyCode>57)&&event.keyCode!=46) || event.keyCode==46)
    { 
        return false;
    }}
}
 
 //This function is used to validate whether the value entered in a field
//is a number(-ve and +ve) or not.
 function numbersonlyNew(text)
{
   var flag=0;
   if(text.length!=0&&event.keyCode==45){
   flag=1;
   }
	if(((event.keyCode==8)||(event.keyCode==45) || (event.keyCode==63272) || (event.keyCode==63234) || (event.keyCode==63235))&&flag==0){
	     
		return true;
	}
	else
	{
	if (((event.keyCode<48||event.keyCode>57)&&event.keyCode!=46) || event.keyCode==46)
    { 
        
        return false;
    }}
}


  function numbersonlyNewDM(id)
{
   var flag=0;
   var text=document.getElementById('dmQuantity'+id).value
   if(event.keyCode==45){
   flag=1;
   }
	if(((event.keyCode==8)||(event.keyCode==45) || (event.keyCode==63272) || (event.keyCode==63234) || (event.keyCode==63235))&&flag==0){
	    
		return true;
	}
	else
	{
	if (((event.keyCode<48||event.keyCode>57)&&event.keyCode!=46) || event.keyCode==46)
    { 
        return false;
    }}
}



// ***************************************** START OF TRIMMING FUNCTION ****************************************
// This function is used to trim the blank spaces from beginning & end of the string 
// This function is mostly called from another function like isFloat( ) etc. 
// Call this function as : Example -> As called from isFloat() and emailValidate( ) 

function trimming(fname)
{
	while(fname.charAt(0)==" ")
	{
			fname=fname.slice(1);
	}
	var len=fname.length-1;
	while(fname.charAt(len)==" ")
	{
		fname=fname.substr(0,len);
		len=fname.length-1;
	}
	return fname;
  }
  
// ****************************** START OF FLOAT FUNCTION **********************************************************

// This functions on click of Save(submit) converts the field value to a float number 
// if the user has entered even the integer value by appending the afdec no. of zero's after the decimal  
// This function takes 4 parameters :
// field ->name of the field ; befdec -> no. of digits before decimal 
// afdec -> no of digits after digits ; label -> label of the field to display in alert 
// Call this function as : Example ->isFloat(document.<FormName>.<FieldName>,<BeforeDecimal>,<AfterDecimal>,'<99.99>','<Label>','nullAllowed')


function isFloat(field,befdec,afdec,format,label,nullallowed)
{
	var dec;
	var decplace;
	var flag;
	var fval=field.value;
	fval=trimming(fval);
        
    if(fval > format)
    {
        alert(label+" should be in "+format+" format.");
		field.focus();
		return false;    
    }        
	if((fval=="") && (typeof nullAllowed != 'undefined'))
    {
			alert(label+ " must be entered.");
			field.focus();
			return false;
	}
	if(isCharsInBag(fval,"0123456789.")==false)
	{
			alert(label+" contains non numeric values.");
			field.focus();
			return false;
	}
	
	flag=0;
	for(dec=0;dec<fval.length;dec++)
	{
		if(fval.charAt(dec)=='.')
		{
			flag=flag+1;
			decplace=dec;
		}
		
		if(flag>1)
		{
			alert(label+" is not a valid value.")
			field.focus();
			return false;
			}
		}
	
        if ((flag==0) && (typeof nullAllowed != 'undefined'))
		{
			if(fval.length>befdec)
			{
		  	  alert(label+" should be in "+format+" format.");
				field.focus();
				return false;
			}
			if(fval=='')
			{
				fval='0';
			}
			fval=fval + '.';
	
			for(dec=0;dec<afdec;dec++)
			{
				fval=fval + '0';
			}
			field.value=fval;
			return true;
		
		}
	if(flag==1)
    {
		var fhalf=fval.substr(0,decplace);
		var shalf=fval.substr(decplace+1,fval.length);
	
		if(fhalf=='')
		{
			if(shalf=='')
			{
				alert(label+" must be entered. ");
				field.focus();
                return false;
            }
            fhalf='0';
        }
		
        if(fhalf.length>befdec)
        {	
            alert(label+" should be in "+format+" format.");
            field.focus();
            return false;
        }
		
        var len=afdec-shalf.length;
    
        if(shalf.length<afdec)
        {
            for(dec=0;dec<len;dec++)
            {
                shalf=shalf + '0';
            }
        }
        var slen=shalf.length
        if(slen>afdec)
        {
        
            alert(label+" should be in "+format+" format.");
            field.focus();
            return false ;
        
        }
        fval=fhalf +'.'+ shalf;
        field.value=fval;
    }
	return true;
 }
 
 // *************************************** END OF FLOAT FUNCTION ****************************************************

   
// **************************** START OF NAME CHECK FUNCTION  **************************************************

// This function checks whether the field passed as a parameter has a special character or not .
// It returns False if the value has a special character otherwise returns True .
// It takes one parameter .
// fname -> name of the field 
// Call this function as :Example -> nameChek(document.<FormName>.<FieldName>,<'label'>)
// NOTE: spaces are allowed in name

function NameCheck(fname)
{
        var FieldName= fname.value;
        var isAlphaType;
        var isSpecialType;
        
        isSpecialType = isCharsInBag( FieldName, "!@#$%^&()_+{}[]|\=-~`?/><.,;:*'0123456789" );
        isAlphaType   = isCharsInBag( FieldName, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz " );
        
        return !isSpecialType && isAlphaType;
}  
  
 // **************************** END OF NAME CHECK  FUNCTION  *************************************************
 
    
// **************************** START OF NUMERIC CHECK FUNCTION  **************************************************

// This function checks whether the field passed as a parameter has a Number or not .
// It returns False if the value doesnt have a number otherwise returns True .
// It takes one parameter .
// fname -> name of the field 
// Call this function as :Example -> CheckNumeric(document.<FormName>.<FieldName>)


function checkNumeric(value)
{
    if (value >= 0 ) return true;
    else return false;
}


// **************************** END OF NUMERIC CHECK  FUNCTION  *************************************************

//************************START OF CHECK PHONE FUNCTION **********************************
//This function is used to validate the phone numbers and fax numbers 
//entered by the user.
function CheckPhone(fname)
{
    var FieldName= fname.value;
    if (!isCharsInBag( FieldName, " ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890._-()" ))
        {
          return false;
        }
    return true;
}

// **************************** END OF CHECK PHONE  FUNCTION  *************************************************
 
 
  
// ************************************** START  OF E_MAIL FUNCTION *************************************************


// This function checks for the correct format (a@b.c) / (a.b@c.d) of the email entered by the user   
// It also checks that first character of the email should not be @ or . 
//  This function takes one parameter . 
//  fieldname -> name of the field 
// Call this function as : Example -> emailValidate(document.<FormName>.<FieldName>,<'label'>)

function _emailValidate(mail)
{
    if (mail != "")
        {
            var flag = 1;
            var i_count; 
            var j_count; 
            var fhalf;
            var shalf;
            var diff;
            var len;
        
            if ((mail.charAt(0)=='@') || (mail.charAt(0)=='.'))
            {
                // First letter cannot be '@' or '.'
                flag = 0;  
            }
        
            // Location of '@' symbol :: if symbol not present - invalid address        
            if(flag ==1)
            {
                flag = 0;
                for(i_count=0;i_count < mail.length; i_count++)
                {
                    if(mail.charAt(i_count)=='@')
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                                     
                }
            }
    
            // Special characters in Email address
            if(flag == 1)
            {
        
                flag=0;
                fhalf=mail.substring(0,i_count);
                shalf=mail.substring(i_count+1,(mail.length));
                fhalf=fhalf.toUpperCase();
                if(isCharsInBag(fhalf,"ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890._-'")==false)
                {
                    
                }
                shalf=shalf.toUpperCase();
                if(isCharsInBag(shalf,"ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890._-")==false)
                {
                  
                }
                // Location of '.' in second half    
                for(j_count=0;j_count<shalf.length;j_count++)
                {
                    if(shalf.charAt(j_count)==".")
                        {
                            flag=1;
                            break;
                        }
                }
                
                if(flag==1)
                {
                   
                }
            }
    
            // Check location of DOT in second half
            if(flag == 1)
            {
                diff=(j_count+fhalf.length)-i_count;
                len=shalf.length;
                if((diff<1)||((shalf.charAt(len-1))=='.'))
                    {
                    
                        flag = 0;
                    }
            }   
        
            //Check the location of (DOT DOT) (DOT @) 
            if(flag == 1)
                {
                    len = mail.length;
                    for(j_count=0; j_count < len-1; j_count++)
                    {
                        if ((mail.charAt(j_count)==".") && (mail.charAt(j_count+1)=="@"))
                        {
                             flag=0;
                            
                             break;
                        }
                        if ((mail.charAt(j_count)==".") && (mail.charAt(j_count+1)=="."))    
                        {
                                flag=0;
                               
                                break;
                        }
                    }
                }
     } // Endif mail = "" 
     
    if(flag == 0)
    {
        return false;
    }else 
        return true;
}



// ************************************* END  OF E_MAIL FUNCTION ****************************************************


    

// ***************************************** START OF IS CHAR IN BAG  FUNCTION ****************************************

    
// This function checks search through string's characters one by one.
// If character is not in bag, retruns false
// This function is mostly called from another function like CheckSpecialChars() etc. 
// It takes two parameters .
// fval -> value of the field 
// bag -> collection of characters which should be in string .
// Call this function as :Example -> As called from  emailValidate( ) 	
    
     
function isCharsInBag (fval, bag)
 {  
    var i_count;
    
    fval=trimming(fval);
    if(fval=="")
        {	
            return true;
        }
        
                
    for (i_count = 0; i_count < fval.length; i_count++)
        {   
               
                var charac = fval.charAt(i_count);
                if (bag.indexOf(charac) == -1) return false;
        }
    return true;
 }
    
// ***************************************** END OF IS CHAR IN BAG  FUNCTION ****************************************




// ***************************************** START OF  IN BAG  FUNCTION ****************************************


// This function checks search through string's characters one by one.
// If character is in bag, return false
// This function is mostly called from another function like CheckSpecialChars() etc. 
// It takes two parameters .
// fval -> value of the field 
// bag -> collection of characters which should not be in string .
// Call this function as :Example -> As called from  emailValidate( ) 	
        
function inBag (fval, bag)
{  
var i_count;

fval=trimming(fval);
if(fval=="")
    {	
        return true;
    }
    
  
for (i_count = 0; i_count < fval.length; i_count++)
    {   
    
           var charac = fval.charAt(i_count);
           if (bag.indexOf(charac) != -1) return false;
    }
    return true;
}

    
// ***************************************** END OF  IN BAG  FUNCTION ********************************************
  
  
  
// **************************** START OF REPLACE CHAR FUNCTION  *************************************************

// This function replaces ' with ` for Varchar fields  .
// It takes one parameter .
// entry -> value of the field .
// This function is called from checkSpecialChars().
  
   
function replaceChars(entry) 
{
    out = "'"; // replace this
    add = "`"; // with this
    temp = "" + entry; // temporary holder

    while (temp.indexOf(out)>-1)
     {
        pos= temp.indexOf(out);
        temp = "" + (temp.substring(0, pos) + add + 
        temp.substring((pos + out.length), temp.length));
     }
    return temp ;
}
    
// **************************** END OF REPLACE CHAR FUNCTION  *************************************************






// **************************** START OF DATE FORMAT FUNCTION  **************************************************

// This function checks for the correct format of the date (dd/mm/yyyy) .
// It takes two parameters .
// fdate -> name of the field 
// label -> label of the field to be displayed in alert  .
// Call this function as :Example -> validDate(document.<FormName>.<FieldName1>,'label')


function isValidDate(year, month)
{

     var days=new Array(31,30,31,30,31,30,31,30,31,31,30,31);

     month=month-1;
  
     if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
     {
       days[1] = 29;
     }
     else
     {
       days[1] = 28;
     }

     return days[month];
 
   }



function validDate(dateObject,label)
{

     var date;
     var month;
     var year;
     var dateValue=dateObject.value;
          
     dateValue=trimming(dateValue);
     if(dateValue=="")
        {	
            return true;
        }
 
     var dateLength=dateValue.length;
     var countForSlash=0;
 
     var possibleValues="0123456789/";
     var dateAlertFlag=false;
 
     for(i=0;i<dateLength;i++)
     {
  
       if(dateValue.substring(i,i+1)=="/" && dateValue.substring(i+1,i+2)=="/")
       {
           alert(label+ " should be in dd/mm/yyyy format.");
           dateObject.focus();
           return false;
       }
     }

     for(i=0;i<dateLength;i++)
     {
       dateAlertFlag=false;
       var temp=dateValue.substring(i,i+1);

       for(j=0;j<possibleValues.length;j++)
       {

         if((temp == possibleValues.substring(j,j+1)) )
         {
          dateAlertFlag=true;
          break;
        }
       }

        if(!dateAlertFlag)
        {
          alert(label+" contains non numeric values.");
          dateObject.focus();
          return false;
        }
    }


    for(i=0;i<dateLength;i++)
    {

      if(dateValue.substring(i,i+1)=="/")
      {
        countForSlash++;
      }
    }

    if(countForSlash==0 || countForSlash==1 || countForSlash >2)
    {

           alert(label+ " should be in dd/mm/yyyy format.");
           dateObject.focus();
           return false;
    }


    date=dateValue.substring(0,dateValue.indexOf("/"));
    month=dateValue.substring(dateValue.indexOf("/")+1,dateValue.lastIndexOf("/"));
    year=dateValue.substring(dateValue.lastIndexOf("/")+1,dateValue.length);

    


     if(year.length==3 )
     {
           alert("Enter valid year in "+label+".");
           dateObject.focus();
           return false;
     }
    
     if(year.length==1 )
     {
           alert("Enter valid year in "+label+".");
           dateObject.focus();
           return false;
     }


     if(year.length>4 )
     {
           alert(label+ " should be in dd/mm/yyyy format.");
           dateObject.focus();
           return false;
     }

    if(isNaN(year)|| year.length==0)
    {
            alert("Enter valid year in "+label+".");
            dateObject.focus();
            return false;
    }

    var dateTempYear=new Date().getFullYear();
     
    dateTempYear=""+dateTempYear;
     
    
    if(year.length==2)
      year=dateTempYear.substring(0,2)+year;


    if(date==0)
    {
        alert("Enter valid date in "+label+".")
        dateObject.focus();
        return false;
    }
 
    if(month==2 &&  date>isValidDate(year, month) )
    {
        alert("Enter valid date for February(Leap Year) for "+label)
        dateObject.focus();
        return false;
    
    }

    if(date>isValidDate(year, month))
    {
      alert("Enter valid date for this month for "+label)
      dateObject.focus();
      return false;
    }
    else if(isNaN(date) || date.length==0)
    {
       alert("Enter valid date in "+label+".")
      dateObject.focus();
      return false;
    }
 
    if(month==0)
    {
         alert("Enter valid month in "+label+".");
        dateObject.focus();
        return false;
    }
 
 
    if(month>12 || isNaN(month) || month.length==0)
    {
      alert("Enter valid month in "+label+".");
      dateObject.focus();
      return false;
    }


    

    if(dateValue.length > 10)
    {
      alert(label+ " should be in dd/mm/yyyy format.");
      dateObject.focus();
      return false;
    }


   if(date.length==1)
    date="0"+date;

   if(month.length==1)
    month="0"+month;
 

   dateObject.value=date+"/"+month+"/"+year;
   return true;
 
 } 

 
 // **************************** END OF DATE FORMAT FUNCTION  **************************************************
   
   

  
  
// **************************** START OF DATE COMPARISION FUNCTION  *******************************************


// This function checks that labelname2(second date) should be lesser than labelname1(first date)
// This function takes four parameters 
// fDate -> First Date ;  sDate-> Second Date
// labelname2->Label of second date to be displayed in alert
// labelname1-> Label of first date to be displayed in alert
//  Call this function as : 
//  Case 1: If user want to make comparision between two general dates : 
//  		dateCheck(document.<FormName>.<Date1>.value,document.<FormName>.<Date2>.value,'>=','LabelDate1','LabelDate2'>
//  Case 2: If user want to make comparision between general date & today's date
//			var todaysdate=todaysDate();   // Today's Date returns Current Date
//		    "dateCheck(document.<FormName>.<Date2>.value,todaysdate,'>=','Label2','LabelTodaysDate')"
            
           
                  
function dateCheck(fDate,sDate,type,label1,label2)
{
            if(fDate=="" && sDate!="")
			{
				alert('End date is also Required');
				return false;
			}
			else if(sDate=="" && fDate!="")
			{
				alert('Start date is also required');
				return false;
			}
			else if(fDate=="" && sDate=="")
			{
				return true;
			}
			else
			{
			
            	var d1=fDate.split('/');
            	var d2=sDate.split('/');
            
            	var f_date=new Date(d1[2],d1[0]-1,d1[1]);
            	var s_date=new Date(d2[2],d2[0]-1,d2[1]);
        
            	if ( type=='>=')
                {
                	if(f_date >= s_date)
                    {
                    	return true;
                    }
                    else
                    {   
                    	alert(label1+'less than '+label2);
                        return false;
                    }											
                }				

            	if ( type=='>')
                {
                	if(f_date > s_date)		
                    	return true;
                    else
                    {
                        return false;
                    }											
                }
            
            	if ( type=='<=')
                {
            		if(f_date <= s_date)		
                    	return true;
                    else
                    {
                        return false;
                    }
                }
           }
       }
        
        
        
  // **************************** END OF DATE COMPARISION FUNCTION  ***********************************************
  
        
  // **************************** START OF CURRENT DATE FUNCTION  *************************************************
    
  // This function returns the current date 
  // Call this function as : Example ->	var todaysdate=todaysDate(); 	
        
        function todaysDate()
        {
            ndate=new Date();
            var day=ndate.getDate();
            var month=ndate.getMonth()+1;
            var year=ndate.getFullYear();
            var Tdate=day+"/"+month+"/"+year;
            return Tdate;
        }
        

  // **************************** END OF CURRENT DATE FUNCTION  *************************************************
  
  
 
 
// ********************************** START OF TRIMMING FIELD FUNCTION  **************************************

// This function is used to trim the blank spaces from beginning & end of the string 
//  This function is mostly called from another function like emptyField( ) etc. 


function trimmingField(tname)
{
        var fname=tname.value;
        len=fname.length;
        while(fname.charAt(0)==" ")
            {
                  fname=fname.slice(1,len);
            }
        var len=fname.length;
        while(fname.charAt(len-1)==" ")
            {
                 fname=fname.substr(0,len-1);
                 len=fname.length;
            }
        tname.value=fname;
    }
 // ********************************** END OF TRIMMING FIELD  FUNCTION  ******************************************
 
 
 //*************************************START OF SELECT OPTION ****************************************************
 
 // This function checks for  options in the combobox and matches with the chosenValue returned by the bean and selects that particular option
 // Call this function as : Example -> selectOption(document.<Form Name>.<Combo Name>,chosenValue)


function selectOption(combofield,chosenValue)
{

       var iOplen=combofield.options.length;
        var iCnt=0;
        while(iCnt<iOplen)
        {
            var flag=0;
            
            var strOptionValue=combofield.options[iCnt].value;
            
            for(var iCount=0;iCount<strOptionValue.length;iCount++)
                {
                    if((strOptionValue.charAt(iCount)==':')&&(strOptionValue.charAt(iCount+1)==':'))
                        {
                
                          var strOptionText=strOptionValue.substring(0,iCount);
               
                          if(strOptionText==chosenValue)
                            {
                    
                                  combofield.options.selectedIndex=iCnt;	
                           }
                        }
                }
           
                iCnt=iCnt+1;
            }



}
 //*********************************************END OF SELECT FUNCTION*****************************************



  /******************************************************************
    round(number,X) :
     function used to round the decimal number to 
            the decimal places specified
  
 *******************************************************************/

function round(number,X) {
    // rounds number to X decimal places, defaults to 2
    X = (!X ? 2 : X); 			// checks for false or 0 and defaults to 2
    return Math.round(number*Math.pow(10,X))/Math.pow(10,X);
}

/******************************************************************
    function MM_findObj(n, d)
     function used to find the particular element. this function is called 
     from MM_showHideLayers, MM_MoveLeft which is in turn called by printForm
   
 *******************************************************************/

function MM_findObj(n, d) { 
  var p,i,x;
    if(!d) d=document;
    if((p=n.indexOf("?"))>0&&parent.frames.length) 
    {
        d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
    }
  if(!(x=d[n])&&d.all) x=d.all[n]; 
  for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); 
  return x;
}

 /******************************************************************
  function MM_showHideLayers()
     function used to show or hide the specific layers
    parameters : layername, alternate layer name, purpose
                  "MENU", "","show"
  
 *******************************************************************/
function MM_showHideLayers() { 
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) 
  if ((obj=MM_findObj(args[i]))!=null) 
  { 
    v=args[i+2];
    if (obj.style) 
    { 
        obj=obj.style; 
        v=(v=='show')?'visible':(v='hide')?'hidden':v; 
    }
    obj.visibility=v; 
  }
}
/******************************************************************
    function MM_MoveLeft()
     function used to move the specific location
    parameters : layername, alternate layer name, purpose
                  "MENU", "","5px"
*******************************************************************/
function MM_MoveLeft() { 
  var i,p,v,obj,args=MM_MoveLeft.arguments;
  for (i=0; i<(args.length-2); i+=3)
   if ((obj=MM_findObj(args[i]))!=null) 
    {
         v=args[i+2];
         if (obj.style) 
         {
             obj=obj.style; 
         }
        obj.left=''+v; 
    }
}
/******************************************************************
    function MM_findObj(n, d)
     function used to invoke the print order which in turns hides the menu 
     and shifts the content to left and after printing shifts to normal position
     when a content to be printed call this without any parameter
 
 *******************************************************************/
function printForm()
{
    MM_showHideLayers('MENU','','hide');
    MM_MoveLeft('BODY','','5px');
    window.print();	
    setMode('Print');
    MM_MoveLeft('BODY','','250px');
    MM_showHideLayers('MENU','','show');
}

//function validates the ean code entered by the user


function chkencode(str)
{
      str = trimming(str);
      if(isCharsInBag(str.toUpperCase(),"0123456789")==false)
        {
            return false;
        }else if(str.length < 6 )
        {
            return true;
        } else if (str.length > 14 )
        {
            return false;
        } else
        {
             while (  str.length  < 18 ) {
                str  =  '0' + str ;
                     }
        chk_value = str.substring(0,str.length-1);
        factor = 3;
        sum = 0;
        for (index = chk_value.length; index > 0; --index) {
             sum = sum + chk_value.substring (index-1, index) * factor;
             factor = 4 - factor;
            }
        cc = ((1000 - sum) % 10);

        if  ( cc == str.substring( 17 , 18) ) {
                    return true;
                } else {
                      return false;
                }
        } 
}

function InBag (s, bag)
{  
     var i;
     // Search through string's characters one by one.
     // If character is in bag, append to returnString.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) != -1) return false;
    }
    return true;
  }
    
    
 //****************************************CHECK VALID DATE **********************************
    
    function checkValidDate(dateStr) {
    // dateStr must be of format month day year with either slashes
    // or dashes separating the parts. Some minor changes would have
    // to be made to use day month year or another format.
    // This function returns True if the date is valid.
    var slash1 = dateStr.indexOf("/");
    // if no slashes or dashes, invalid date
    if (slash1 == -1) { return false; }
    
    var dateDay = dateStr.substring(0, slash1);
     if(dateStr.charAt(2) != "/" || dateStr.charAt(5) != "/"){return false;}
    
    var dateMonthAndYear = dateStr.substring(slash1+1, dateStr.length);
    var slash2 = dateMonthAndYear.indexOf("/");
    if (slash2 == -1) { slash2 = dateMonthAndYear.indexOf("-"); }
    // if not a second slash or dash, invalid date
    if (slash2 == -1) { return false; }
    
    var dateMonth = dateMonthAndYear.substring(0, slash2);
    
    
    var dateYear = dateMonthAndYear.substring(slash2+1, dateMonthAndYear.length);
   
    
    if ( (dateMonth == "") || (dateDay == "") || (dateYear == "") ) { return false; }
    // if any non-digits in the month, invalid date
    for (var x=0; x < dateMonth.length; x++) {
        var digit = dateMonth.substring(x, x+1);
        if ((digit < "0") || (digit > "9")) { return false; }
    }
    // convert the text month to a number
    var numMonth = 0;
    for (var x=0; x < dateMonth.length; x++) {
        digit = dateMonth.substring(x, x+1);
        numMonth *= 10;
        numMonth += parseInt(digit);
    }
    if ((numMonth <= 0) || (numMonth > 12)) { return false; }
    // if any non-digits in the day, invalid date
    for (var x=0; x < dateDay.length; x++) {
        digit = dateDay.substring(x, x+1);
        if ((digit < "0") || (digit > "9")) { return false; }
    }
    // convert the text day to a number
    var numDay = 0;
    for (var x=0; x < dateDay.length; x++) {
        digit = dateDay.substring(x, x+1);
        numDay *= 10;
        numDay += parseInt(digit);
    }
    if ((numDay <= 0) || (numDay > 31)) { return false; }
    // February can't be greater than 29 (leap year calculation comes later)
    if ((numMonth == 2) && (numDay > 29)) { return false; }
    // check for months with only 30 days
    if ((numMonth == 4) || (numMonth == 6) || (numMonth == 9) || (numMonth == 11)) { 
        if (numDay > 30) { return false; } 
    }
    // if any non-digits in the year, invalid date
    for (var x=0; x < dateYear.length; x++) {
        digit = dateYear.substring(x, x+1);
        if ((digit < "0") || (digit > "9")) { return false; }
    }
    // convert the text year to a number
    var numYear = 0;
    for (var x=0; x < dateYear.length; x++) {
        digit = dateYear.substring(x, x+1);
        numYear *= 10;
        numYear += parseInt(digit);
    }
    
    /*
    // Year must be a 2-digit year or a 4-digit year
    if ( (dateYear.length != 2) && (dateYear.length != 4) ) { return false; }
    // if 2-digit year, use 50 as a pivot date
    if ( (numYear < 50) && (dateYear.length == 2) ) { numYear += 2000; }
    if ( (numYear < 100) && (dateYear.length == 2) ) { numYear += 1900; }
    if ((numYear <= 0) || (numYear > 9999)) { return false; }
    
    */
    
     // Year must be a 4-digit year
    if (dateYear.length != 4)  { return false; }
    if ((numYear <= 0) || (numYear > 9999)) { return false; }
    
    
    // check for leap year if the month and day is Feb 29
    if ((numMonth == 2) && (numDay == 29)) {
        var div4 = numYear % 4;
        var div100 = numYear % 100;
        var div400 = numYear % 400;
        // if not divisible by 4, then not a leap year so Feb 29 is invalid
        if (div4 != 0) { return false; }
        // at this point, year is divisible by 4. So if year is divisible by
        // 100 and not 400, then it's not a leap year so Feb 29 is invalid
        if ((div100 == 0) && (div400 != 0)) { return false; }
    }
    // date is valid
    return true;
}

/* next two functions calculates the total
   up to correct decimal places
   */        
function round_decimals(original_number, decimals)
    {
    var result1 = original_number * Math.pow(10, decimals)
    var result2 = Math.round(result1)
    var result3 = result2 / Math.pow(10, decimals)
    return pad_with_zeros(result3, decimals)
}

function pad_with_zeros(rounded_value, decimal_places) {

    // Convert the number to a string
    var value_string = rounded_value.toString()
    
    // Locate the decimal point
    var decimal_location = value_string.indexOf(".")

    // Is there a decimal point?
    if (decimal_location == -1) {
        
        // If no, then all decimal places will be padded with 0s
        decimal_part_length = 0
        
        // If decimal_places is greater than zero, tack on a decimal point
        value_string += decimal_places > 0 ? "." : ""
    }
    else {

        // If yes, then only the extra decimal places will be padded with 0s
        decimal_part_length = value_string.length - decimal_location - 1
    }
    
    // Calculate the number of decimal places that need to be padded with 0s
    var pad_total = decimal_places - decimal_part_length
    
    if (pad_total > 0) {
        
        // Pad the string with 0s
        for (var counter = 1; counter <= pad_total; counter++) 
            value_string += "0"
        }
    return value_string
    }
 
 

function emailValidation(fieldID)
{
   
    mail = fieldID.value
    var flag = 1;
    if (mail != "")
        {
            var i_count; 
            var j_count; 
            var fhalf;
            var shalf;
            var diff;
            var len;
            
          
        
            if ((mail.charAt(0)=='@') || (mail.charAt(0)=='.'))
            {
                // First letter cannot be '@' or '.'
                flag = 0;  
            }
        
            // Location of '@' symbol :: if symbol not present - invalid address        
            if(flag ==1)
            {
                flag = 0;
                for(i_count=0;i_count < mail.length; i_count++)
                {
                    if(mail.charAt(i_count)=='@')
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                                  
                }
            }
    
            // Special characters in Email address
            if(flag == 1)
            {
        
                flag=1;
                fhalf=mail.substring(0,i_count);
                shalf=mail.substring(i_count+1,(mail.length));
                fhalf=fhalf.toUpperCase();
                if(isCharsInBag(fhalf,"ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890._-'")==false)
                {
                    flag = 0;
                   
                }
                shalf=shalf.toUpperCase();
                if(isCharsInBag(shalf,"ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890._-")==false)
                {
                    flag = 0;
                    
                }
                // Location of '.' in second half    
            }
            // Presence of DOT 
            if(flag==1)
            {
                flag = 0;
                for(j_count=0;j_count<shalf.length;j_count++)
                {
                    if(shalf.charAt(j_count)==".")
                        {
                            flag=1;
                            break;
                        }
                }
            }
    
            // Check location of DOT in second half
            if(flag == 1)
            {
                diff=(j_count+fhalf.length)-i_count;
                len=shalf.length;
                if((diff<1)||((shalf.charAt(len-1))=='.'))
                    {
              
                        flag = 0;
                    }
            }   
        
            //Check the location of (DOT DOT) (DOT @) 
            if(flag == 1)
                {
                    len = mail.length;
                    for(j_count=0; j_count < len-1; j_count++)
                    {
                        if ((mail.charAt(j_count)==".") && (mail.charAt(j_count+1)=="@"))
                        {
                             flag=0;
                             break;
                        }
                        if ((mail.charAt(j_count)==".") && (mail.charAt(j_count+1)=="."))    
                        {
                                flag=0;
                                break;
                        }
                    }
                }
     } // Endif mail = "" 
     
    if(flag == 1)
    {
        return true;
    }else 
      {
        return false;
      }
}

function dotrimming(fname)
{
    if(fname.length>0)
    {
           
        while(fname.charAt(0)==" ")
        {
                fname=fname.slice(1);
        }
        var len=fname.length-1;
        while(fname.charAt(len)==" ")
        {
            fname=fname.substr(0,len);
            len=fname.length-1;
        }
        return fname;
      }
      
      return "";
}

function generateUploadAttachmentAlerts(attachmentName,attachmentPath)
{
    if (attachmentName == "")
      {
        alert("Please enter Attachment name");
        document.body.style.cursor = "auto";
        return false;
      }
      if (attachmentPath == "")
      {
        alert("Please select a file to be attached");
        document.body.style.cursor = "auto";
        return false;
      }
	  
	 attachmentPath = attachmentPath.toUpperCase();

	 if (attachmentPath.lastIndexOf(".EXE") != -1 || attachmentPath.lastIndexOf(".BAT") != -1 || attachmentPath.lastIndexOf(".SH") != -1 || attachmentPath.lastIndexOf(".SCR") != -1 || attachmentPath.lastIndexOf(".WCS") != -1 || attachmentPath.lastIndexOf(".VBS") != -1 || attachmentPath.lastIndexOf(".JS") != -1)
	 {
		alert("This file type is not allowed for upload. Please choose another file type.");
        document.body.style.cursor = "auto";
        return false;
	 }
    return true;
}

function loadAttachment(attachmentName,contextPath)
{
    document.body.style.cursor = "wait";
    
    document.forms[0].method="POST";
    document.forms[0].action=contextPath + "/filedownload?fileName=" + attachmentName;
    document.forms[0].submit();
    document.body.style.cursor = "auto";    
}

function tildAndCarotHandler(e) {
    
    if (document.all) {
        e = window.event;
    }
    if(e.keyCode==126 || e.keyCode==94)
    {
        e.returnValue = false

    }

   
}