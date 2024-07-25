package com.pearson.pix.presentation.base.common;
/**
 * Action Commands to identify what operation and what other ActionMapping. should happen, used by
 * hidden fields in HTML
 */
public class FrontEndConstants {


	/** 
	 * mapForward used in code to find an ActionForward in the mappings for forwarding to the
         * default JSP or URL.
         */

	public static final String FORWARD_FINISHED = "forwardfinished";

	/**
         * actionCommand used in code to switch the child Action's executeCancelled. routine
         */
	
	public static final String FORWARD_CANCELLED = "forwardCancel";

	/**
         * mapForward used in Action to show the bad error page.
         */
	
	public static final String ERROR="error";
	
	/**
         * mapForward used in code to find an ActionForward in the mappings for just displaying the JSP
         * associated with this action.
         */

	public static final String LIST="list";	

	/**
         * mapForward used in code to find an ActionForward in the mappings for forwarding to a display
         * page.
         */

	public static final String DISPLAY="display";
	
	/**
     * mapForward used in code to find an ActionForward in the mappings for forwarding to a Partial display (Related List)
     * page.
     */

	public static final String RELATEDLIST="relatedList";

	/**
         * mapForward used in code to find an ActionForward in the mappings for carrying out an insert
         * with this action.
         */

	public static final String INSERT="insert";

	/**
         * mapForward used in code to find an ActionForward in the mappings for carrying out an update
         * with this action.
         */

	public static final String UPDATE="update";

	/**
         * mapForward used in code to find an ActionForward in the mappings for requesting a delete
         * from user.
         */

	public static final String DELETE="delete";
	
	/**
     * mapForward used in code to find an ActionForward in the mappings for requesting to export
     * pdf.
     */

    public static final String EXPORTPDF="exportpdf";

    /**
     * mapForward used in code to find an ActionForward in the mappings for requesting to export
     * pdf.
     */

    public static final String EXPIRE="expire";
    
    /**
     * mapForward used in code to find an ActionForward in the mappings for requesting to file
     * upload
     */
    
    public static final String FILEUPLOAD="fileupload";
    
    public static final String POTENTIAL_STATUS_FORWARD="statusMessageForward";
    public static final String POTENTIAL_RESULTPOPUP_FORWARD="forwardToResultPopup";
    
	
}