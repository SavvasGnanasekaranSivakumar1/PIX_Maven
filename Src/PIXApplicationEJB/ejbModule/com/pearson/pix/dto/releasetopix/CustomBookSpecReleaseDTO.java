package com.pearson.pix.dto.releasetopix;

import java.io.Serializable;
import java.util.Vector;

public class CustomBookSpecReleaseDTO implements Serializable {
	
	private Vector pixBookSpecCes;
	private Vector pixBookSpecLineCes;
	private Vector pixBookSpecPressCes;
	private Vector pixBookSpecNonpressCes;
	private Vector pixBookSpecBindingCes;
	private Vector pixBookSpecMiscCes;
	
	
	public Vector getPixBookSpecCes() {
		return pixBookSpecCes;
	}
	public void setPixBookSpecCes(Vector pixBookSpecCes) {
		this.pixBookSpecCes = pixBookSpecCes;
	}
	public Vector getPixBookSpecLineCes() {
		return pixBookSpecLineCes;
	}
	public void setPixBookSpecLineCes(Vector pixBookSpecLineCes) {
		this.pixBookSpecLineCes = pixBookSpecLineCes;
	}
	public Vector getPixBookSpecPressCes() {
		return pixBookSpecPressCes;
	}
	public void setPixBookSpecPressCes(Vector pixBookSpecPressCes) {
		this.pixBookSpecPressCes = pixBookSpecPressCes;
	}
	public Vector getPixBookSpecNonpressCes() {
		return pixBookSpecNonpressCes;
	}
	public void setPixBookSpecNonpressCes(Vector pixBookSpecNonpressCes) {
		this.pixBookSpecNonpressCes = pixBookSpecNonpressCes;
	}
	public Vector getPixBookSpecBindingCes() {
		return pixBookSpecBindingCes;
	}
	public void setPixBookSpecBindingCes(Vector pixBookSpecBindingCes) {
		this.pixBookSpecBindingCes = pixBookSpecBindingCes;
	}
	public Vector getPixBookSpecMiscCes() {
		return pixBookSpecMiscCes;
	}
	public void setPixBookSpecMiscCes(Vector pixBookSpecMiscCes) {
		this.pixBookSpecMiscCes = pixBookSpecMiscCes;
	}

}
