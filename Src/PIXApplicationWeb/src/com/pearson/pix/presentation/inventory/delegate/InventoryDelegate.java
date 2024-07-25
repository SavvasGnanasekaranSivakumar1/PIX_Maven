package com.pearson.pix.presentation.inventory.delegate;
import com.pearson.pix.business.inventory.interfaces.InventoryLocal;
import com.pearson.pix.business.inventory.interfaces.InventoryLocalHome;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.naming.NamingException;

/**
 * Business Delegate for Inventory. It acts as the Client for EJB invocation.
 * @author Sudam.Sahu
 */
public class InventoryDelegate 
{
   
   /**
    * Constructor for Initializing InventoryDelegate
    */
   public InventoryDelegate() 
   {
    
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * @return com.pearson.pix.business.inventory.interfaces.InventoryLocal
    */
   private InventoryLocal getInventoryLocal() 
   {
	   InventoryLocal objInventoryLocal = null;
		  try 
		  {
			  InventoryLocalHome objInventoryLocalHome = ServiceLocator.getInventoryLocalHome();
			  objInventoryLocal = objInventoryLocalHome.create();
		  } 
		  catch (NamingException ne)
		  {
				ne.printStackTrace();
		  } 
		  catch (CreateException ce)
		  {
				ce.printStackTrace();
		  }
		  return objInventoryLocal;
   }
   
   /**
    * Calls corresponding Business method of Inventory EJB for the purpose of 
    * Inventory Detail
    * @param isbn
    * @param san
    * @param rawMaterialNumber
    * @return java.util.Collection
    */
   public Collection displayInventory(String isbn,String isbn13, String rawMaterialNumber,String san)throws AppException 
   {
	   Collection objCollection = null;
	   objCollection = getInventoryLocal().displayInventory(isbn,isbn13,rawMaterialNumber,san);
	   return objCollection;
   }
   
   /**
    * Calls corresponding Business method of InventoryEJB for the purpose of 
    * saving information of Inventory info.
    * @param printNumber,san,createdBy,inventoryCollection,inventoryId,inventoryVersion
    * @return String
    */
   public String saveInventory(Integer printNumber,String san,String createdBy,Collection inventoryCollection,BigDecimal inventoryId,BigDecimal inventoryVersion)throws AppException 
   {
	getInventoryLocal().saveInventory(printNumber,san,createdBy,inventoryCollection,inventoryId,inventoryVersion);
	return "";
   }
   
   /**
    * Calls corresponding Business method of InventoryEJB for the purpose of 
    * displaying part name in the search page
    * @return java.util.Collection
    */
   public Collection displayPartyName(int userId) throws AppException
   {
	   Collection objCollection = null;
       objCollection = getInventoryLocal().displayPartyName(userId);
       return objCollection; 
   }
}
