var uFile= document.location.href;
var nPos;
var mLen;
	
mLen=uFile.length;
nPos=uFile.lastIndexOf("/");
uFile=uFile.substr(nPos+1,mLen);
uFile=uFile.substr(0,uFile.indexOf("."));



var menu2=new Array()
menu2[0]='<a href="admin/manage_users.html">Manage Users</a>'
menu2[1]='<a href="admin/supplier_list.html">Manage Suppliers</a>'
menu2[2]='<a href="admin/listpub.do?PAGE_ACTION=P&PAGE_VALUE=1">Manage Pub Unit</a>'

var menuwidth='170px' //default menu width
var menubgcolor='#B3D5EF'  //menu bgcolor
var disappeardelay=250  //menu disappear speed onMouseout (in miliseconds)
var hidemenu_onclick="yes" //hide menu when user clicks within menu?

/////No further editting needed

var ie4=document.all
var ns6=document.getElementById&&!document.all

if (ie4||ns6)
document.write('<div id="dropmenudiv" style="z-index:10000;visibility:hidden;width:'+menuwidth+';background-color:'+menubgcolor+'" onMouseover="clearhidemenu()" onMouseout="dynamichide(event)" ></div>')

function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;

while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}


function showhide(obj, e, visible, hidden, menuwidth){
if (ie4||ns6)
dropmenuobj.style.left=dropmenuobj.style.top=-500
if (menuwidth!=""){
dropmenuobj.widthobj=dropmenuobj.style
dropmenuobj.widthobj.width=menuwidth
}
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover")
obj.visibility=visible
else if (e.type=="click")
obj.visibility=hidden
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=0
if (whichedge=="rightedge"){
var windowedge=ie4 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure-obj.offsetWidth
}
else{
var topedge=ie4 && !window.opera? iecompattest().scrollTop : window.pageYOffset
var windowedge=ie4 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure){ //move up?
edgeoffset=dropmenuobj.contentmeasure+obj.offsetHeight
if ((dropmenuobj.y-topedge)<dropmenuobj.contentmeasure) //up no good either?
edgeoffset=dropmenuobj.y+obj.offsetHeight-topedge
}
}
return edgeoffset
}

function populatemenu(what){
if (ie4||ns6)
dropmenuobj.innerHTML=what.join("");
}


function dropdownmenu(obj, e, menucontents, menuwidth){
document.getElementById("link_7").className="topLinkshover";
var obj_id;
if (window.event) event.cancelBubble=true
else if (e.stopPropagation) e.stopPropagation()
clearhidemenu()
dropmenuobj=document.getElementById? document.getElementById("dropmenudiv") : dropmenudiv
populatemenu(menucontents)
obj_id=obj.id;

if (ie4||ns6){
showhide(dropmenuobj.style, e, "visible", "hidden", menuwidth)
	
	dropmenuobj.x=getposOffset(obj, "left")
	dropmenuobj.y=getposOffset(obj, "top")

dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+"px"
dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px"
}

return clickreturnvalue()
}

function clickreturnvalue(){
if (ie4||ns6) return false
else return true
}

function contains_ns6(a, b) {
while (b.parentNode)
if ((b = b.parentNode) == a)
return true;
return false;
}

function dynamichide(e){
if (ie4&&!dropmenuobj.contains(e.toElement))
delayhidemenu()
else if (ns6&&e.currentTarget!= e.relatedTarget&& !contains_ns6(e.currentTarget, e.relatedTarget))
delayhidemenu()
}

function hidemenu(e){
if (uFile=='manage_users'|| uFile=='associate_pubunitsociate_role' || uFile=='associate_supplier' || uFile=='pub_info'|| uFile=='pub_unitlist'|| uFile=='supplier_info'|| uFile=='supplier_list'
	|| uFile=='transport_details'|| uFile=='user_info'|| uFile=='associate_pubunit_edit'|| uFile=='associate_role_edit'|| uFile=='associate_supplier_edit'|| uFile=='pub_info_edit'|| uFile=='pub_unitlist_edit'
	|| uFile=='supplier_info_edit'|| uFile=='supplier_list_edit'|| uFile=='transport_details-edit'|| uFile=='user_info_edit')
{
	document.getElementById("link_7").className="topLinkshover";
}
else
{
	document.getElementById("link_7").className="topLinks";
}
if (typeof dropmenuobj!="undefined"){
if (ie4||ns6)
dropmenuobj.style.visibility="hidden"
}
}

function delayhidemenu(){
if (ie4||ns6)
delayhide=setTimeout("hidemenu()",disappeardelay)
}

function clearhidemenu(){
if (typeof delayhide!="undefined")
clearTimeout(delayhide)
}

if (hidemenu_onclick=="yes")
document.onclick=hidemenu
