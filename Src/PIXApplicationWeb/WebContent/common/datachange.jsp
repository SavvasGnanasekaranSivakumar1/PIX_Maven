<script language="javascript">

	var dataChangeObject;
	var dataChangeCancel = true;

	function linkClick()
	{
		if(dataChangeObject != undefined){
			if( dataChangeObject.value == 'true' )
				//return confirm("You have changed the data. Are you sure you want to proceed?");
				return confirm("All unsaved data will be lost. Do you want to proceed?");
			return true;
		}
		return true;
	}

	function changedData( fn )
	{
		if(dataChangeObject != undefined){
			dataChangeObject.value = 'true';
			if( fn != undefined && typeof(fn) == "function" )
				return fn();
		}	
		return true;
	}

	function saveOnclick( fn )
	{
		var flag = true;
		if( fn != undefined && typeof(fn) == "function" )
			flag = fn();
		if( flag )
			dataChangeObject.value = 'false';
		return flag;
	}

	function resetOnclick( fn )
	{
		if(dataChangeObject != undefined){
			dataChangeObject.value = 'false';
			if( fn != undefined && typeof(fn) == "function" )
				return fn();
		}
		return true;
	}

	function linkOnclick( linkArray )
	{
		var length = ( linkArray != undefined ? linkArray.length : 0 );
		for(i=0;i<length;i++)
			if( ( pixMatch( linkArray[i].className, 'tabSub' ) || pixMatch( linkArray[i].id, 'link_' ) || linkArray[i].className == 'mainLink' || linkArray[i].className == 'tabMainText' || linkArray[i].className == 'subLinks' || linkArray[i].className == 'tabSubBg2' ) )
				linkArray[i].onclick = linkClick;
	}

	function imageOnclick( imageArray )
	{
		var length = ( imageArray != undefined ? imageArray.length : 0 );
		var imageAlt;
		for(i=0;i<length;i++)
		{
			imageAlt = imageArray[i].alt;
			if( imageAlt != undefined && imageAlt != '' )
			{
				if( pixMatch( imageAlt, 'Pick a date' ) )
					imageArray[i].onclick = changedData;
				else if( ( pixMatch( imageAlt, 'First Product' ) || pixMatch( imageAlt, 'Previous Product' ) || pixMatch( imageAlt, 'Next Product' ) || pixMatch( imageAlt, 'Last Product' ) ) )
					imageArray[i].onclick = navigationButtonOnclickFunction( imageArray[i].onclick );
			}
		}
	}

	function navigationButtonOnclick( fn )
	{
		var flag = linkClick();
		if( flag && fn != undefined && typeof(fn) == "function" )
			return fn();
		return flag;
	}

	function pixMatch( str, subString )
	{
		return ( str.toLowerCase().search(subString.toLowerCase()) == 0 );
	}

	function changedDataFunction( fn )
	{
		return function() { return changedData( fn ); };
	}

	function saveOnclickFunction( fn )
	{
		return function() { return saveOnclick( fn ); };
	}

	function resetOnclickFunction( fn )
	{
		return function() { return resetOnclick( fn ); };
	}

	function navigationButtonOnclickFunction( fn )
	{
		return function() { return navigationButtonOnclick( fn ); };
	}

	function changeToAll()
	{
		var fn;
		var index = 0;
		var length;
		var formArray = document.forms;
		var noOfForms = formArray.length;
		var formObject;
		var fieldArray;
		var fieldObject;
		var linkArray;
		var linkObject;
		for(i=0;i<noOfForms;i++) 
		{
			formObject = formArray[i];
			
			fieldArray = formObject.elements;
			length = ( fieldArray != undefined ? fieldArray.length : 0 );
			for(j=0;j<length;j++) 
			{
				fieldObject = fieldArray[j];
				if( fieldObject.type == 'text' || ( fieldObject.type == 'select-one' && fieldObject.className != 'textfieldDataChangeCancel' ) || fieldObject.type == 'textarea')
					fieldObject.onchange = changedDataFunction( fieldObject.onchange );
				else if( ( ( fieldObject.type == 'submit' || fieldObject.type == 'button' ) && ( pixMatch( fieldObject.value, 'Cancel' ) || pixMatch( fieldObject.value, 'delete' ) ) ) )
				{
					fieldObject.onclick = changedDataFunction( fieldObject.onclick );
				}
				else if( fieldObject.type == 'submit' && pixMatch( fieldObject.value, 'save' ) && !fieldObject.readOnly && !fieldObject.disabled )
				{
					dataChangeCancel = false;
					fieldObject.onclick = saveOnclickFunction( fieldObject.onclick );
				}
				else if( ( fieldObject.type == 'submit' || fieldObject.type == 'button' || fieldObject.type == 'reset' ) && pixMatch( fieldObject.value, 'reset' ) )
					fieldObject.onclick = resetOnclickFunction( fieldObject.onclick );
				else if( ( fieldObject.type == 'submit' || fieldObject.type == 'button' ) && fieldObject.value == 'Go' )
					fieldObject.onclick = navigationButtonOnclickFunction( fieldObject.onclick );
				else if( fieldObject.type == 'hidden' && fieldObject.name == 'dataChange' )
					dataChangeObject = fieldObject;
				index++;
			}
			
		}
		imageOnclick( document.images );
		linkOnclick( document.links );
	}

	changeToAll();
</script>
