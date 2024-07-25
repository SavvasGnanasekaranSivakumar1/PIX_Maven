function StdSendByID(evtDef) {
  a = evtDef.reqParam.split(",");
  requestType = a[0];
  if (requestType.toLowerCase().indexOf("querystring") != -1) {
    qs = "";
    addition = "?";
    for (i = 1; i < a.length; i++) {
      nextElement = a[i];
      b = nextElement.split(".");
      id = b[0];
      attr = b[1];
      if (attr == "value") {
        fe = document.getElementById(id);
        feValues = JWPGetElementValue(fe);
        j = 0;
        while (j < feValues.length) {
          qs += addition + id + '=' + escape(feValues[j]);
          addition = "&";
          j++;
        }
      } else { 
        qs += addition + id + "=";
        qs += escape(eval("document.getElementById('" + id + "')." + attr));
      }
      addition = "&";
    }
    ajaxPartsTaglib.ajaxRequestSender(evtDef, null, qs);
  }
  if (requestType.toLowerCase().indexOf("xml") != -1) {
    b = requestType.split(".");
    rootNode = b[1];
    xml = "<" + rootNode + ">";
    for (i = 1; i < a.length; i++) {
      nextElement = a[i];
      b = nextElement.split(".");
      id = b[0];
      attr = b[1];
      if (attr == "value") {
        fe = document.getElementById(id);
        feValues = JWPGetElementValue(fe);
        j = 0;
        while (j < feValues.length) {
          xml += "<" + id + ">";
          xml += escape(feValues[j]);
          xml += "</" + id + ">";
          j++;
        }
      } else { 
        xml += "<" + id + ">";
        xml += escape(eval("document.getElementById('" + id + "')." + attr));
        xml += "</" + id + ">";
      }
    }
    xml += "</" + rootNode + ">";
    ajaxPartsTaglib.ajaxRequestSender(evtDef, xml, null);
  }
}
