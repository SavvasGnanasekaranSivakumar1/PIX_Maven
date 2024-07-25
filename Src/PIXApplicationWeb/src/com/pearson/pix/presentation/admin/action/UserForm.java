package com.pearson.pix.presentation.admin.action;

import com.pearson.pix.dto.admin.User;
import com.pearson.pix.presentation.base.action.BaseForm;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class UserForm extends BaseForm
{

    private static final long serialVersionUID = 1L;
    private User user;
    private String startDate;
    private String endDate;
    private Vector userCollection;
    private String supplierId;
    private String publisherId;
    private String radioPublisher;
    private String radioSupplier;
    private String supplierIds;
    private String publisherIds;
    private String radioUser;
    private String activeFlag;
    private String existingPassword;
    private String changePassword;
    private String planningRead;
    private String planningPost;
    private String bookSpecRead;
    private String bookSpecPost;
    private String purchaseOrderRead;
    private String purchaseOrderPost;
    private String orderConfirmRead;
    private String orderConfirmPost;
    private String orderStatusRead;
    private String orderStatusPost;
    private String deliveryMesgRead;
    private String deliveryMesgPost;
    private String goodsReceiptRead;
    private String goodsReceiptPost;
    private String usageRead;
    private String usagePost;
    private String inventoryRead;
    private String inventoryPost;
    private String arpTitleRead;
    private String arpTitlePost;
    private String v42;
    private String dropshipinstructionsRead;
    private String dropshipinstructionsPost;
    private String uploadShipInfoRead;
    private String uploadShipInfoPost;
    

    /**
	 * @return the dropshipinstructionsRead
	 */
	public String getDropshipinstructionsRead() {
		return dropshipinstructionsRead;
	}

	/**
	 * @param dropshipinstructionsRead the dropshipinstructionsRead to set
	 */
	public void setDropshipinstructionsRead(String dropshipinstructionsRead) {
		this.dropshipinstructionsRead = dropshipinstructionsRead;
	}

	/**
	 * @return the dropshipinstructionsPost
	 */
	public String getDropshipinstructionsPost() {
		return dropshipinstructionsPost;
	}

	/**
	 * @param dropshipinstructionsPost the dropshipinstructionsPost to set
	 */
	public void setDropshipinstructionsPost(String dropshipinstructionsPost) {
		this.dropshipinstructionsPost = dropshipinstructionsPost;
	}

	/**
	 * @return the uploadShipInfoRead
	 */
	public String getUploadShipInfoRead() {
		return uploadShipInfoRead;
	}

	/**
	 * @param uploadShipInfoRead the uploadShipInfoRead to set
	 */
	public void setUploadShipInfoRead(String uploadShipInfoRead) {
		this.uploadShipInfoRead = uploadShipInfoRead;
	}

	/**
	 * @return the uploadShipInfoPost
	 */
	public String getUploadShipInfoPost() {
		return uploadShipInfoPost;
	}

	/**
	 * @param uploadShipInfoPost the uploadShipInfoPost to set
	 */
	public void setUploadShipInfoPost(String uploadShipInfoPost) {
		this.uploadShipInfoPost = uploadShipInfoPost;
	}

	public UserForm()
    {
    }

    public final void reset(ActionMapping mapping, HttpServletRequest request)
    {
        if(user == null)
        {
            user = new User();
        }
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Vector getUserCollection()
    {
        return userCollection;
    }

    public void setUserCollection(Vector userCollection)
    {
        this.userCollection = userCollection;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(String supplierId)
    {
        this.supplierId = supplierId;
    }

    public String getPublisherId()
    {
        return publisherId;
    }

    public void setPublisherId(String publisherId)
    {
        this.publisherId = publisherId;
    }

    public String getRadioPublisher()
    {
        return publisherIds;
    }

    public void setRadioPublisher(String publisherIds)
    {
        this.publisherIds = publisherIds;
    }

    public String getRadioSupplier()
    {
        return supplierIds;
    }

    public void setRadioSupplier(String supplierIds)
    {
        this.supplierIds = supplierIds;
    }

    public String getRadioUser()
    {
        return radioUser;
    }

    public void setRadioUser(String radioUser)
    {
        this.radioUser = radioUser;
    }

    public String getPlanningRead()
    {
        return planningRead;
    }

    public void setPlanningRead(String planningRead)
    {
        this.planningRead = planningRead;
    }

    public String getPlanningPost()
    {
        return planningPost;
    }

    public void setExistingPassword(String existingPassword)
    {
        this.existingPassword = existingPassword;
    }

    public String getExistingPassword()
    {
        return existingPassword;
    }

    public void setChangePassword(String changePassword)
    {
        this.changePassword = changePassword;
    }

    public String getChangePassword()
    {
        return changePassword;
    }

    public void setPlanningPost(String planningPost)
    {
        this.planningPost = planningPost;
    }

    public String getBookSpecRead()
    {
        return bookSpecRead;
    }

    public void setBookSpecRead(String bookSpecRead)
    {
        this.bookSpecRead = bookSpecRead;
    }

    public String getBookSpecPost()
    {
        return bookSpecPost;
    }

    public void setBookSpecPost(String bookSpecPost)
    {
        this.bookSpecPost = bookSpecPost;
    }

    public String getPurchaseOrderRead()
    {
        return purchaseOrderRead;
    }

    public void setPurchaseOrderRead(String purchaseOrderRead)
    {
        this.purchaseOrderRead = purchaseOrderRead;
    }

    public String getPurchaseOrderPost()
    {
        return purchaseOrderPost;
    }

    public void setPurchaseOrderPost(String purchaseOrderPost)
    {
        this.purchaseOrderPost = purchaseOrderPost;
    }

    public String getOrderConfirmRead()
    {
        return orderConfirmRead;
    }

    public void setOrderConfirmRead(String orderConfirmRead)
    {
        this.orderConfirmRead = orderConfirmRead;
    }

    public String getOrderConfirmPost()
    {
        return orderConfirmPost;
    }

    public void setOrderConfirmPost(String orderConfirmPost)
    {
        this.orderConfirmPost = orderConfirmPost;
    }

    public String getOrderStatusRead()
    {
        return orderStatusRead;
    }

    public void setOrderStatusRead(String orderStatusRead)
    {
        this.orderStatusRead = orderStatusRead;
    }

    public String getOrderStatusPost()
    {
        return orderStatusPost;
    }

    public void setOrderStatusPost(String orderStatusPost)
    {
        this.orderStatusPost = orderStatusPost;
    }

    public String getDeliveryMesgRead()
    {
        return deliveryMesgRead;
    }

    public void setDeliveryMesgRead(String deliveryMesgRead)
    {
        this.deliveryMesgRead = deliveryMesgRead;
    }

    public String getDeliveryMesgPost()
    {
        return deliveryMesgPost;
    }

    public void setDeliveryMesgPost(String deliveryMesgPost)
    {
        this.deliveryMesgPost = deliveryMesgPost;
    }

    public String getGoodsReceiptRead()
    {
        return goodsReceiptRead;
    }

    public void setGoodsReceiptRead(String goodsReceiptRead)
    {
        this.goodsReceiptRead = goodsReceiptRead;
    }

    public String getGoodsReceiptPost()
    {
        return goodsReceiptPost;
    }

    public void setGoodsReceiptPost(String goodsReceiptPost)
    {
        this.goodsReceiptPost = goodsReceiptPost;
    }

    public String getUsageRead()
    {
        return usageRead;
    }

    public void setUsageRead(String usageRead)
    {
        this.usageRead = usageRead;
    }

    public String getUsagePost()
    {
        return usagePost;
    }

    public void setUsagePost(String usagePost)
    {
        this.usagePost = usagePost;
    }

    public String getInventoryRead()
    {
        return inventoryRead;
    }

    public void setInventoryRead(String inventoryRead)
    {
        this.inventoryRead = inventoryRead;
    }

    public String getInventoryPost()
    {
        return inventoryPost;
    }

    public void setInventoryPost(String inventoryPost)
    {
        this.inventoryPost = inventoryPost;
    }

    public String getActiveFlag()
    {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag)
    {
        this.activeFlag = activeFlag;
    }

	public String getArpTitleRead() {
		return arpTitleRead;
	}

	public void setArpTitleRead(String arpTitleRead) {
		this.arpTitleRead = arpTitleRead;
	}

	public String getArpTitlePost() {
		return arpTitlePost;
	}

	public void setArpTitlePost(String arpTitlePost) {
		this.arpTitlePost = arpTitlePost;
	}

	public String getV42() {
		return v42;
	}

	public void setV42(String v42) {
		this.v42 = v42;
	}
}
