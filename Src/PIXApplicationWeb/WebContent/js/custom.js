$(function(){
		   
		   
//$("table.invntTable tr:odd").css("background-color", "#ffffff");		   
//$("table.invntTable tr:even").css("background-color", "#f6f6f6");

$("table.invntTable tr").each(function(){
		$(this).parent().find("tr:even").css("background-color", "#ffffff");		   
		$(this).parent().find("tr:odd").css("background-color", "#F0F0F0");		   
		$(this).parent().find("td.merged").css("background-color", "#F0F0F0");		   
	});

		   
		   });

