package com.pearson.pix.business.inventory.interfaces;
import com.pearson.pix.exception.AppException;
import javax.ejb.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Local interface for Inventory
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface InventoryLocal extends EJBLocalObject
{
    
    /**
     * Business method for displaying the Inventory Detail
     * @param isbn
     * @param san
     * @param rawMaterialNumber
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayInventory
     */
    public Collection displayInventory (String isbn,String isbn13,String rawMaterialNumber,String san)throws AppException ;
    
    /**
     * Business method for saving the information for Inventory.
     * @param String
     * @J2EE_METHOD  --  saveInventory
     */
    public String saveInventory(Integer printNumber,String san,String createdBy,Collection inventoryCollection,BigDecimal inventoryId,BigDecimal inventoryVersion)throws AppException ;
    
    /**Business method to display party name in the drop down of inventory search page
     * @param java.util.Collection
     * @J2EE_METHOD  --  displayPartyName
     */
    public Collection displayPartyName(int userId)throws AppException;
}