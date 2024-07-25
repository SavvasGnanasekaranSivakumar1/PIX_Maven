package com.pearson.pix.presentation.inventory.action;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.dto.inventory.Inventory;
import com.pearson.pix.presentation.base.action.BaseForm;
public class InventoryForm extends BaseForm
{
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Inventory inventory;
  private Collection inventoryCollection;
  private Party party;
  private String isbn10;
  private String rawMaterialNo;
  private String printNum;
  private BigDecimal inventoryId;
  private BigDecimal inventoryVersion;
  private BigDecimal lineItemNo;
  public final void reset(org.apache.struts.action.ActionMapping mapping,javax.servlet.http.HttpServletRequest request)
	{
	  if (inventory == null){
	  this.inventory = new Inventory();
	  }
	  if(this.party==null)
	  {
		  this.party=new Party();
	  }
	  
	}
  
  
	/**
	 * Gets inventoryId
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getInventoryId() {
		return inventoryId;
	}
	/**
    * Sets inventoryId
    * @param inventoryId
    */
	public void setInventoryId(BigDecimal inventoryId) {
		this.inventoryId = inventoryId;
	}
	/**
	 * Gets inventoryVersion
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getInventoryVersion() {
		return inventoryVersion;
	}
	/**
    * Sets inventoryVersion
    * @param inventoryVersion
    */
	public void setInventoryVersion(BigDecimal inventoryVersion) {
		this.inventoryVersion = inventoryVersion;
	}
	
	/**
	 * Gets inventoryLineItemNo
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getLineItemNo() {
		return lineItemNo;
	}
	/**
    * Sets inventoryLineItemNo
    * @param lineItemNo
    */
	public void setLineItemNo(BigDecimal lineItemNo) {
		this.lineItemNo = lineItemNo;
	}
	/**
	 * Gets printNo
	 * @return java.math.Integer
	 */
  public String getPrintNum()
  	{
  	  return this.printNum;
  	}

  /**
   * Sets printNo
   * @param printNo
   */
  	 	  
  public void setPrintNum(String printNum)
  	{
  	  this.printNum = printNum;

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
   * Gets rawMaterialNo
   * @return String
   */

  public String getRawMaterialNo()
  	{
  	  return this.rawMaterialNo;
  	}

  /**
   * Sets rawMaterialNo
   * @param rawMaterialNo
   */
  	 	  
  public void setRawMaterialNo(String rawMaterialNo)
  	{
  	  this.rawMaterialNo = rawMaterialNo;

  }
  
    /**
     * Gets Inventory
     * @return Inventory
     */
  
  public Inventory getInventory()
	{
	  return this.inventory;
	}
  
  /**
   * Sets Inventory
   * @param Inventory
   */
	 	  
  public void setInventory(Inventory inventory)
  	{
	  this.inventory = inventory;
  	}
  /**
   * Gets Party
   * @return Party
   */

public Party getParty()
	{
	  return this.party;
	}

/**
 * Sets Party
 * @param Party
 */
	 	  
public void setParty(Party party)
	{
	  this.party = party;
	}
  /**
   * Gets InventoryCollection
   * @return java.util.Collection
   */
  
  public Collection getInventoryCollection()
  	{
	  return inventoryCollection;
  	}
  
  /**
   * Sets InventoryCollection
   * @param inventoryCollection
   */
  
  public void setInventoryCollection(Collection inventoryCollection)
  {
	  this.inventoryCollection = inventoryCollection;
  }
 
	/**
	* Gets InventoryLine for Inventory
	* @return com.pearson.pix.dto.inventory.Inventory
	*/
	public Inventory getInventoryCollectionIdx(int index)
	{
		return (Inventory)((Vector)this.inventoryCollection).get(index);
	}
	
	/**
  * Sets InventoryLine with index
  * @param int
  * @param com.pearson.pix.dto.inventory.InventoryLine
  */
	public void setInventoryCollectionIdx(int index, Inventory inventory)
	{
		((Vector)this.inventoryCollection).set(index, inventory);			
	}
	  
  }


