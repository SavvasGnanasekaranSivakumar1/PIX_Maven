package com.pearson.pix.presentation.bookspecification.action;
import java.util.LinkedHashMap;
import java.util.Vector;
import com.pearson.pix.dto.bookspecification.BookSpec;
import com.pearson.pix.dto.bookspecification.BookSpecParty;
import com.pearson.pix.presentation.base.action.BaseForm;
public class BookSpecForm extends BaseForm
{
  /**
	 * this formbean class is for setting and getting various values of bookspec form
	 * @author Sudam.Sahu
	 */
  private static final long serialVersionUID = 1L;
  private BookSpec bookSpec;
  private LinkedHashMap bookSpecPrev;
  private Vector bookSpecCollection;
  private String startDate;
  private String endDate;
  private BookSpecParty bookSpecParty;
  private String selectedEntry;
  private String module;
  
  public final void reset(org.apache.struts.action.ActionMapping mapping,javax.servlet.http.HttpServletRequest request)
	{
	  if(this.bookSpec==null  )
	  {
		  this.bookSpec = new BookSpec();
		 
	  }
	 
	}
  /**
   * Gets BookSpecPrev
   * @return LinkedHashMap
   */
  public LinkedHashMap getBookSpecPrev()
	{
	  return this.bookSpecPrev;
	}
  /**
   * Sets BookSpecPrev
   * @param bookSpecPrev
   */
  
  public void setBookSpecPrev(LinkedHashMap bookSpecPrev)
	{
	  this.bookSpecPrev = bookSpecPrev;
	}
  
    /**
   * Gets BookSpec
   * @return BookSpec
   */
  
  public BookSpec getBookSpec()
	{
	  return this.bookSpec;
	}
  
  /**
   * Sets BookSpec
   * @param BookSpec
   */
	 	  
  public void setBookSpec(BookSpec bookSpec)
  	{
	  this.bookSpec = bookSpec;
  	}
  /**
   * Gets BookSpecParty
   * @return BookSpecParty
   */
  
 public BookSpecParty getBookSpecParty()
	{
	  return this.bookSpecParty;
	}
  
  /**
   * Sets BookSpecParty
   * @param BookSpecParty
   */
	 	  
  public void setBookSpecParty(BookSpecParty bookSpecParty)
  	{
	  this.bookSpecParty = bookSpecParty;
  	}
  
  /**
   * Gets startDate
   * @return String
   */
  
  public String getStartDate()
  	{
	  return startDate;
  	}
  
  /**
   * Sets startDate
   * @param startDate
   */
  
  public void setStartDate(String startDate)
 	{
	  this.startDate= startDate;
 	}
  
  /**
   * Gets endDate
   * @return String
   */
  
  public String getEndDate()
  	{
		   return endDate;
	}
  
  /**
   * Sets EndDate
   * @param endDate
   */
  
  public void setEndDate(String endDate)
	{
		this.endDate= endDate;
	}
	
  /**
   * Gets BookSpecCollection
   * @return java.util.Vector
   */
  
  public Vector getBookSpecCollection()
  	{
	  return bookSpecCollection;
  	}
  
  /**
   * Sets BookSpecCollection
   * @param bookSpecCollection
   */
  
  public void setBookSpecCollection(Vector bookSpecCollection)
  {
	  this.bookSpecCollection = bookSpecCollection;
  }
  /**
   * Gets SelectedEntry
   * @return String
   */
  
  public String getSelectedEntry()
  	{
		return selectedEntry;
	}
  /**
   * sets SelectedEntry
   * @param selectedEntry
   */
  public void setSelectedEntry(String selectedEntry)
  	{
	   this.selectedEntry = selectedEntry;
	}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
	  
  }


