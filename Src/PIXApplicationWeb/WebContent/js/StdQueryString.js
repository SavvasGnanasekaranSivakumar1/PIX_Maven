function StdQueryString(evtDef) {
  qs = "";
  addition = "?";
  nvp = evtDef.reqParam.split(",");
  i = 0;
  while (i < nvp.length) {
    nav = nvp[i].split("=");
    if (nav[1].charAt(0) == "\'") {
      fe = nav[1].substring(1, nav[1].length - 1);
      qs += addition + nav[0] + '=' + escape(fe);
    } else {
      fe = eval("document." + evtDef.theForm.name + "[\"" + nav[1] + "\"]");
      feValues = JWPGetElementValue(fe);
      j = 0;
      while (j < feValues.length) {
        qs += addition + nav[0] + '=' + escape(feValues[j]);
        addition = "&";
        j++;
      }
    }
    addition = "&";
    i++;
  }
  ajaxPartsTaglib.ajaxRequestSender(evtDef, null, qs);
}
