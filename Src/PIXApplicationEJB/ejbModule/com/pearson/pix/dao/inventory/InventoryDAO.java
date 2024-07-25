package com.pearson.pix.dao.inventory;
import com.pearson.pix.exception.AppException;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 * @author Sudam.Sahu
 */
public interface InventoryDAO 
{
   
   /**
    * Gets the Inventory Information
    * @param isbn
    * @param rawMaterialNumber
    * @param san
    * @return java.util.Collection
    */
   public Collection getInventory(String isbn,String isbn13,String rawMaterialNumber,String san)throws AppException;
   
   /**
    * Saves the Inventory information into the database
     * @param printNumber,san,createdBy,inventoryCollection,inventoryId,inventoryVersion
     * @return String
    */
   public String insertInventory(Integer printNumber,String san,String createdBy,Collection inventoryCollection,BigDecimal inventoryId,BigDecimal inventoryVersion)throws AppException;
   /**
    * gets the party name from PIX_ADMIN_PARTY table to display in the drop down of inventory search page
     * @param java.util.Collection
    */
   public Collection getPartyName(int userId)throws AppException;
}
