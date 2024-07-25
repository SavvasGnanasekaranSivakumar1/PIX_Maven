function StdTextboxArea(ajCall, resParam) {
  for (i = 0; i < ajCall.theForm.elements.length; i++) {
    if (ajCall.theForm.elements[i].name == resParam) {
      ajCall.theForm.elements[i].value = ajCall.xhr.responseText;
    }
  }
}
