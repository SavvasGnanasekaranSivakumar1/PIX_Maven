function StdSimpleXML(evtDef) {
  xml = "";
  nvp = evtDef.reqParam.split(",");
  root = nvp[0];
  xml += "<" + root + ">";
  i = 1;
  while (i < nvp.length) {
    nav = nvp[i].split("=");
    fe = eval("document." + evtDef.theForm.name + "['" + nav[1] + "']");
    feValues = JWPGetElementValue(fe);
    j = 0;
    while (j < feValues.length) {
      xml += "<" + nav[0] + ">" + escape(feValues[j]) + "</" + nav[0] + ">";
      j++;
    }
    i++;
  }
  xml += "</" + root + ">";
  ajaxPartsTaglib.ajaxRequestSender(evtDef, xml, null);
}
