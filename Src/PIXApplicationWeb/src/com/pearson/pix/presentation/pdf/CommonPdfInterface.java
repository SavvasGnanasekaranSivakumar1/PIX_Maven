package com.pearson.pix.presentation.pdf;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.lowagie.text.Document;
import com.pearson.pix.exception.AppException;

public interface CommonPdfInterface {
	public Document display(Document document,HttpServletRequest req)throws AppException;
}