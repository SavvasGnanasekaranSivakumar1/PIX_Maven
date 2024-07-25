package com.pearson.pix.presentation.ajax.progressbar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.action.ActionForm;

import com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm;

public class SessionFormTag extends TagSupport{
	
	private ActionForm form;

	
	

	public ActionForm getForm() {
		return form;
	}



	public void setForm(ActionForm form) {
		this.form = form;
	}



	public int doStartTag() throws JspException
	    {
	    	
		   
		    HttpServletRequest request=null;
	        try
	        {   
	        	
	        	DeliveryMessageForm objDeliveryMessageForm=(DeliveryMessageForm)getForm();
	        	System.out.println("objDeliveryMessageForm....."+objDeliveryMessageForm);
	        	if(objDeliveryMessageForm!=null){
	        		request=(HttpServletRequest)pageContext.getRequest();
	        		request.getSession(false).setAttribute("deliveryMessageForm",objDeliveryMessageForm);
	        	}
	        	
	 		   //  pageContext.setAttribute(id,subFname);
	        	
	        }
	        catch (ClassCastException ex)
	        {
	            throw new JspException("Page attribute  is not an instance of SelectTargetItem.", ex);
	        }

	        return SKIP_BODY;
	    }



	

}