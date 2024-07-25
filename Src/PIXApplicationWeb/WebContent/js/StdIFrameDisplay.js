function StdIFrameDisplay(ajCall, resParam) {
  a = resParam.split(",");
  for (i = 0; i < a.length; i++) {
    window.frames[a[i]].document.open();
    window.frames[a[i]].document.write(ajCall.xhr.responseText);
    window.frames[a[i]].document.close();
  }
}
