package com.pearson.pix.dao.base;

import com.pearson.pix.dao.mismatchreport.MismatchReportDAO;
import com.pearson.pix.dao.mismatchreport.MismatchReportDAOImpl;
import com.pearson.pix.dao.orderstatus.OrderStatusDAO;
import com.pearson.pix.dao.planning.PlanningDAO;
import com.pearson.pix.dao.arptitlesetup.PotentialARPDAO;
import com.pearson.pix.dao.arptitlesetup.PotentialARPDAOImpl;
import com.pearson.pix.dao.purchaseorder.PurchaseOrderDAO;
import com.pearson.pix.dao.releasetopix.ReleaseToPIXDAO;
import com.pearson.pix.dao.releasetopix.ReleaseToPIXDAOImpl;
import com.pearson.pix.dao.reports.ReportDAO;
import com.pearson.pix.dao.admin.AdminDAO;
import com.pearson.pix.dao.bookspecification.BookSpecDAO;
import com.pearson.pix.dao.deliverymessage.DeliveryMessageDAO;
import com.pearson.pix.dao.fileuploading.FileUploadingDAO;
import com.pearson.pix.dao.fileuploading.FileUploadingDAOImpl;
import com.pearson.pix.dao.goodsreceipt.GoodsReceiptDAO;
import com.pearson.pix.dao.usage.UsageDAO;
import com.pearson.pix.dao.inventory.InventoryDAO;

import com.pearson.pix.dao.orderstatus.OrderStatusDAOImpl;
import com.pearson.pix.dao.planning.PlanningDAOImpl;
import com.pearson.pix.dao.purchaseorder.PurchaseOrderDAOImpl;
import com.pearson.pix.dao.reports.ReportDAOImpl;
import com.pearson.pix.dao.admin.AdminDAOImpl;
import com.pearson.pix.dao.bookspecification.BookSpecDAOImpl;
import com.pearson.pix.dao.costaccounting.CostAccountingDAO;
import com.pearson.pix.dao.costaccounting.CostAccountingDAOImpl;
import com.pearson.pix.dao.deliverymessage.DeliveryMessageDAOImpl;
import com.pearson.pix.dao.dropship.DropShipDAOImpl;
import com.pearson.pix.dao.goodsreceipt.GoodsReceiptDAOImpl;
import com.pearson.pix.dao.home.HomePageDAO;
import com.pearson.pix.dao.home.HomePageDAOImpl;
import com.pearson.pix.dao.usage.UsageDAOImpl;
import com.pearson.pix.dao.inventory.InventoryDAOImpl;

/**
 * Factory class containing the methods for returning the DAOs for different 
 * modules
 */
public class DAOFactory 
{
   
   /**
    * Private Constructor made as no instance should be created explicitly outside 
    * the class
    */
   private DAOFactory() 
   {
    
   }
   
   /**
    * Returns the PlanningDAO for DB access
    * 
    * @return com.pearson.pix.dao.planning.PlanningDAO
    */
   public PlanningDAO getPlanningDAO() 
   {
    return new PlanningDAOImpl();
   }
   
   /**
    * Returns the PlanningDAO for DB access
    * 
    * @return com.pearson.pix.dao.planning.PlanningDAO
    */
   public PotentialARPDAO getPotentialARPDAO() 
   {
    return new PotentialARPDAOImpl();
   }
   
   /**
    * Returns the AdminDAO for DB access
    * @return com.pearson.pix.dao.planning.PlanningDAO
    */
   public AdminDAO getAdminDAO() 
   {
    return new AdminDAOImpl();
   }
   
   /**
    * Returns the PurchaseOrderDAO for DB access
    * 
    * @return com.pearson.pix.dao.purchaseorder.PurchaseOrderDAO
    */
   public PurchaseOrderDAO getPurchaseOrderDAO() 
   {
    return new PurchaseOrderDAOImpl();
   }
   
   /**
    * Returns the OrderStatusDAO for DB access
    * 
    * @return com.pearson.pix.dao.orderstatus.OrderStatusDAO
    */
   public OrderStatusDAO getOrderStatusDAO() 
   {
    return new OrderStatusDAOImpl();
   }
   
   /**
    * Returns the DeliveryMessageDAO for DB access
    * 
    * @return com.pearson.pix.dao.deliverymessage.DeliveryMessageDAO
    */
   public DeliveryMessageDAO getDeliveryMessageDAO() 
   {
    return new DeliveryMessageDAOImpl();
   }
   
   /**
    * Returns the new object of DAOFactory (Factory Design Pattern)
    * 
    * @return com.pearson.pix.dao.base.DAOFactory
    */
   public static DAOFactory newInstance() 
   {
    return new DAOFactory();
   }
   
   /**
    * Returns the BookSpecDAO for DB access
    * 
    * @return com.pearson.pix.dao.bookspecification.BookSpecDAO
    */
   public BookSpecDAO getBookSpecDAO() 
   {
    return new BookSpecDAOImpl();
   }
   
   /**
    * Returns the GoodsReceiptDAO for DB access
    * 
    * @return com.pearson.pix.dao.goodsreceipt.GoodsReceiptDAO
    */
   public GoodsReceiptDAO getGoodsReceiptDAO() 
   {
    return new GoodsReceiptDAOImpl();
   }
   
   /**
    * Returns the UsageDAO for DB access
    * @return com.pearson.pix.dao.usage.UsageDAO
    */
   public UsageDAO getUsageDAO() 
   {
    return new UsageDAOImpl();
   }
   
   /**
    * Returns the InventoryDAO for DB access
    * @return com.pearson.pix.dao.inventory.InventoryDAO
    */
   public InventoryDAO getInventoryDAO() 
   {
    return new InventoryDAOImpl();
   }
   
   /**
    * Returns the ReportDAO for DB access
    * 
    * @return com.pearson.pix.dao.reports.ReportDAO
    */
   public ReportDAO getReportDAO() 
   {
    return new ReportDAOImpl();
   }
   
   /**
    * Returns the HomePageDAO for DB access
    * 
    * @return com.pearson.pix.dao.home.HomepageDAO
    */
   
   public HomePageDAO getHomePageDAO() 
   {
    return new HomePageDAOImpl();
   }
   
   
   /**
    * Returns the FileUploadingDAO for DB access
    * 
    * @return com.pearson.pix.dao.fileuploading.FileUploadingDAO
    */
   
   public FileUploadingDAO getFileUploadingDAO() 
   {
    return new FileUploadingDAOImpl();
   }
   
   /**
    * Returns the CostAccountingDAO for DB access
    * 
    * @return com.pearson.pix.dao.costaccounting.CostAccountingDAO
    */
   
   public CostAccountingDAO getCostAccountingDAO() 
   {
    return new CostAccountingDAOImpl();
   }
   
   /**
    * Returns the MismatchReportDAO for DB access
    * 
    * @return com.pearson.pix.dao.mismatchreport.MismatchReportDAO
    */
   
   public MismatchReportDAO getMismatchReportDAO() 
   {
    return new MismatchReportDAOImpl();
   }
   
   
   public ReleaseToPIXDAO getReleaseToPIXDAO() 
   {
    return new ReleaseToPIXDAOImpl();
   }
   
   public DropShipDAOImpl getDropshipDAO() 
   {
    return new DropShipDAOImpl();
   }
}
