package com.pearson.pix.presentation.reports.action;
import java.util.Collection;
import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.dto.reports.Report;
import com.pearson.pix.presentation.base.action.BaseForm;
public class ReportForm extends BaseForm
{
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Report report;
  private Reference reference;
  private String isbn10;
  private String isbn13;
  private String porderNo;
  private String printNo;
  private String item;
  private String startDate;
  private String endDate;
  private Collection reportCollection;
 
  public final void reset(org.apache.struts.action.ActionMapping mapping,javax.servlet.http.HttpServletRequest request)
	{
	  this.report = new Report();
	  
	  if(this.reference==null)
		 {
			 this.reference=new Reference();
		 }
	}
  
    /**
   * Gets Reference
   * @return Reference
   */
  
  public Reference getReference()
	{
	  return this.reference;
	}
  
  /**
   * Sets Reference
   * @param Reference
   */
	 	  
  public void setReference(Reference reference)
  	{
	  this.reference = reference;
  	}
  
  
  /**
   * Gets Report
   * @return Report
   */
  
  public Report getReport()
	{
	  return this.report;
	}
  
  /**
   * Sets Report
   * @param Report
   */
	 	  
  public void setReport(Report report)
  	{
	  this.report = report;
  	}
  /**
 * Gets isbn10
 * @return String
 */

public String getIsbn10()
	{
	  return this.isbn10;
	}

/**
 * Sets isbn10
 * @param isbn10
 */
	 	  
public void setIsbn10(String isbn10)
	{
	  this.isbn10 = isbn10;
	}

/**
 * Gets isbn13
 * @return String
 */

public String getIsbn13()
	{
	  return this.isbn13;
	}

/**
 * Sets isbn13
 * @param isbn13
 */
	 	  
public void setIsbn13(String isbn13)
	{
	  this.isbn13 = isbn13;
	  
  }
/**
 * Gets porderNo
 * @return String
 */

public String getPorderNo()
	{
	  return this.porderNo;
	}

/**
 * Sets pOderNo
 * @param porderNo
 */
	 	  
public void setPorderNo(String porderNo)
	{
	  this.porderNo = porderNo;
}
/**
 * Gets printNo
 * @return String
 */

public String getPrintNo()
	{
	  return this.printNo;
	}

/**
 * Sets printNo
 * @param printNo
 */
	 	  
public void setPrintNo(String printNo)
	{
	  this.printNo = printNo;

}
public String getstartDate()
{
     return this.startDate;
        
}
public void setstartDate(String startDate)
{
     this.startDate = startDate;
}

public String getendDate()
{
     return this.endDate;
        
}
public void setendDate(String endDate)
{
     this.endDate = endDate;
}

/**
 * Gets reportCollection
 * @return java.util.Collection
 */

public Collection getReportCollection()
	{
	  return reportCollection;
	}

/**
 * Sets reportCollection
 * @param reportCollection
 */

public void setReportCollection(Collection reportCollection)
{
	  this.reportCollection = reportCollection;
}
/**
 * Gets item
 * @return String
 */
public String getItem() {
	return item;
}

/**
 * Sets item
 * @param item
 */

public void setItem(String item) {
	this.item = item;
}

}



