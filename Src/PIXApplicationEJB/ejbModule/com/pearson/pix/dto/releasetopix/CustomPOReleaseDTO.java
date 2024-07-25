package com.pearson.pix.dto.releasetopix;

import java.io.Serializable;
import java.util.Vector;

public class CustomPOReleaseDTO implements Serializable {
	
	
	private Vector poHeaderCes;
	private Vector poHeaderNotesCes;
	private Vector poLineCes;
	private Vector poPriceDetailCes;
	private Vector poSuppCompCes;
	private Vector poHeaderExt;
	
	
	public Vector getPoHeaderCes() {
		return poHeaderCes;
	}
	public void setPoHeaderCes(Vector poHeaderCes) {
		this.poHeaderCes = poHeaderCes;
	}
	public Vector getPoHeaderNotesCes() {
		return poHeaderNotesCes;
	}
	public void setPoHeaderNotesCes(Vector poHeaderNotesCes) {
		this.poHeaderNotesCes = poHeaderNotesCes;
	}
	public Vector getPoLineCes() {
		return poLineCes;
	}
	public void setPoLineCes(Vector poLineCes) {
		this.poLineCes = poLineCes;
	}
	public Vector getPoPriceDetailCes() {
		return poPriceDetailCes;
	}
	public void setPoPriceDetailCes(Vector poPriceDetailCes) {
		this.poPriceDetailCes = poPriceDetailCes;
	}
	public Vector getPoSuppCompCes() {
		return poSuppCompCes;
	}
	public void setPoSuppCompCes(Vector poSuppCompCes) {
		this.poSuppCompCes = poSuppCompCes;
	}
	public Vector getPoHeaderExt() {
		return poHeaderExt;
	}
	public void setPoHeaderExt(Vector poHeaderExt) {
		this.poHeaderExt = poHeaderExt;
	}
	

}
