package com.pearson.pix.presentation.ajax.progressbar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RandNoGeneretorTag extends TagSupport{
	
	private Integer msgId;
	private Integer lineNo ;
	
	

	public int doStartTag() throws JspException
	    {
	    	
		    String subFname="" ;
		    int subFolderNoInt=0;
	        try
	        {   
	        	
	        	 Integer msgID=getMsgId();
	        	
	        	 if(msgID!=null){
	        		 subFolderNoInt=msgID.intValue();
	        	 }else{
	        	 double randNum=Math.random();
	 		     double subFolderNo=randNum*10000 ;
	 		     subFolderNoInt=(int)subFolderNo ;
	        	 }
	 		     Integer lineNo =getLineNo() ;
	 		     subFname="sub_"+subFolderNoInt+"_"+lineNo ;
	 		    pageContext.setAttribute(id,subFname);
	        	
	        }
	        catch (ClassCastException ex)
	        {
	            throw new JspException("Page attribute  is not an instance of SelectTargetItem.", ex);
	        }

	        return SKIP_BODY;
	    }



	public Integer getLineNo() {
		return lineNo;
	}



	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}



	public Integer getMsgId() {
		return msgId;
	}



	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

}
