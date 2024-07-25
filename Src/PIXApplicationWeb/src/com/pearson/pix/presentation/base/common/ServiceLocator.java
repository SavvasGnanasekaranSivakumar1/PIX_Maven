package com.pearson.pix.presentation.base.common;

import com.pearson.pix.business.dropship.interfaces.DropShipLocalHome;

/**
 * Utility class for Admin.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class ServiceLocator
{
   /** Cached local home (EJBLocalHome). Uses lazy loading to obtain its value (loaded by getLocalHome() methods). */
   
	private static com.pearson.pix.business.admin.interfaces.AdminLocalHome AdminLocalHome = null;

   private static com.pearson.pix.business.bookspecification.interfaces.BookSpecLocalHome BookSpecLocalHome = null;
   
   private static com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocalHome DeliveryMessageLocalHome = null;
   
   private static com.pearson.pix.business.exporttoexcel.interfaces.ExportToExcelLocalHome ExportToExcelLocalHome = null;
   
   private static com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocalHome GoodsReceiptLocalHome = null;
   
   private static com.pearson.pix.business.home.interfaces.HomePageLocalHome HomePageLocalHome = null;
   
   private static com.pearson.pix.business.inventory.interfaces.InventoryLocalHome InventoryLocalHome = null;
   
   private static com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocalHome OrderStatusLocalHome = null;  
   
   private static com.pearson.pix.business.planning.interfaces.PlanningLocalHome PlanningLocalHome = null;
   
   private static com.pearson.pix.business.arptitlesetup.interfaces.PotentialARPLocalHome PotentialARPLocalHome = null;
   
   private static com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocalHome PurchaseOrderLocalHome = null;
   
   private static com.pearson.pix.business.reports.interfaces.ReportLocalHome ReportLocalHome = null;
   
   private static com.pearson.pix.business.usage.interfaces.UsageLocalHome UsageLocalHome = null;
   
   private static com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocalHome FileUploadingLocalHome = null;
   
   private static com.pearson.pix.business.costaccounting.interfaces.CostAccountingLocalHome CostAccountingLocalHome = null;
   
   private static com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocalHome MismatchReportLocalHome = null;
   
   private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
      // Obtain initial context
      javax.naming.InitialContext initialContext = new javax.naming.InitialContext(environment);
      try {
         Object objRef = initialContext.lookup(jndiName);
         // only narrow if necessary
         if (java.rmi.Remote.class.isAssignableFrom(narrowTo))
            return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
         else
            return objRef;
      } finally {
         initialContext.close();
      }
   }

   // Home interface lookup methods

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Admin. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.admin.interfaces.AdminLocalHome getAdminLocalHome() throws javax.naming.NamingException
   {
	   System.out.println("---#$##################test----##########mgs---"+com.pearson.pix.business.admin.interfaces.AdminLocalHome.JNDI_NAME);
      if (AdminLocalHome == null) {
    	  AdminLocalHome = (com.pearson.pix.business.admin.interfaces.AdminLocalHome) lookupHome(null, com.pearson.pix.business.admin.interfaces.AdminLocalHome.JNDI_NAME, com.pearson.pix.business.admin.interfaces.AdminLocalHome.class);
      }
      return AdminLocalHome;
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Bookspec. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.bookspecification.interfaces.BookSpecLocalHome getBookSpecLocalHome() throws javax.naming.NamingException
   {
      if (BookSpecLocalHome == null) {
    	  BookSpecLocalHome = (com.pearson.pix.business.bookspecification.interfaces.BookSpecLocalHome) lookupHome(null, com.pearson.pix.business.bookspecification.interfaces.BookSpecLocalHome.JNDI_NAME, com.pearson.pix.business.bookspecification.interfaces.BookSpecLocalHome.class);
      }
      return BookSpecLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DeliveryMessage. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocalHome getDeliveryMessageLocalHome() throws javax.naming.NamingException
   {
      if (DeliveryMessageLocalHome == null) {
    	  DeliveryMessageLocalHome = (com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocalHome) lookupHome(null, com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocalHome.JNDI_NAME, com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocalHome.class);
      }
      return DeliveryMessageLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Exporttoexcel. Lookup using COMP_NAME
    */
   
   public static com.pearson.pix.business.exporttoexcel.interfaces.ExportToExcelLocalHome getExportToExcelLocalHome() throws javax.naming.NamingException
   {
      if (ExportToExcelLocalHome == null) {
    	  ExportToExcelLocalHome = (com.pearson.pix.business.exporttoexcel.interfaces.ExportToExcelLocalHome) lookupHome(null, com.pearson.pix.business.exporttoexcel.interfaces.ExportToExcelLocalHome.JNDI_NAME, com.pearson.pix.business.exporttoexcel.interfaces.ExportToExcelLocalHome.class);
      }
      return ExportToExcelLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for GoodsReceipt. Lookup using COMP_NAME
    */

   public static com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocalHome getGoodsReceiptLocalHome() throws javax.naming.NamingException
   {
      if (GoodsReceiptLocalHome == null) {
    	  GoodsReceiptLocalHome = (com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocalHome) lookupHome(null, com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocalHome.JNDI_NAME, com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocalHome.class);
      }
      return GoodsReceiptLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for HomePage. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.home.interfaces.HomePageLocalHome getHomePageLocalHome() throws javax.naming.NamingException
   {
      if (HomePageLocalHome == null) {
    	  HomePageLocalHome = (com.pearson.pix.business.home.interfaces.HomePageLocalHome) lookupHome(null, com.pearson.pix.business.home.interfaces.HomePageLocalHome.JNDI_NAME, com.pearson.pix.business.home.interfaces.HomePageLocalHome.class);
      }
      return HomePageLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Inventory. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.inventory.interfaces.InventoryLocalHome getInventoryLocalHome() throws javax.naming.NamingException
   {
      if (InventoryLocalHome == null) {
    	  InventoryLocalHome = (com.pearson.pix.business.inventory.interfaces.InventoryLocalHome) lookupHome(null, com.pearson.pix.business.inventory.interfaces.InventoryLocalHome.JNDI_NAME, com.pearson.pix.business.inventory.interfaces.InventoryLocalHome.class);
      }
      return InventoryLocalHome;
   }
   
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Orderstatus. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocalHome getOrderStatusLocalHome() throws javax.naming.NamingException
   {
      if (OrderStatusLocalHome == null) {
    	  OrderStatusLocalHome = (com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocalHome) lookupHome(null, com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocalHome.JNDI_NAME, com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocalHome.class);
      }
      return OrderStatusLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Planning. Lookup using COMP_NAME
    */
   
   public static com.pearson.pix.business.planning.interfaces.PlanningLocalHome getPlanningLocalHome() throws javax.naming.NamingException
   {
      if (PlanningLocalHome == null) {
    	  PlanningLocalHome = (com.pearson.pix.business.planning.interfaces.PlanningLocalHome) lookupHome(null, com.pearson.pix.business.planning.interfaces.PlanningLocalHome.JNDI_NAME, com.pearson.pix.business.planning.interfaces.PlanningLocalHome.class);
      }
      return PlanningLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Potential ARP. Lookup using COMP_NAME
    */
   
   public static com.pearson.pix.business.arptitlesetup.interfaces.PotentialARPLocalHome getPotentialARPLocalHome() throws javax.naming.NamingException
   {
      if (PotentialARPLocalHome == null) {
    	  PotentialARPLocalHome = (com.pearson.pix.business.arptitlesetup.interfaces.PotentialARPLocalHome) lookupHome(null, com.pearson.pix.business.arptitlesetup.interfaces.PotentialARPLocalHome.JNDI_NAME, com.pearson.pix.business.arptitlesetup.interfaces.PotentialARPLocalHome.class);
      }
      return PotentialARPLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for PurchaseOrder. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocalHome getPurchaseOrderLocalHome() throws javax.naming.NamingException
   {
      if (PurchaseOrderLocalHome == null) {
    	  PurchaseOrderLocalHome = (com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocalHome) lookupHome(null, com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocalHome.JNDI_NAME, com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocalHome.class);
      }
      return PurchaseOrderLocalHome;
   }
   
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Report. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.reports.interfaces.ReportLocalHome getReportLocalHome() throws javax.naming.NamingException
   {
      if (ReportLocalHome == null) {
    	  ReportLocalHome = (com.pearson.pix.business.reports.interfaces.ReportLocalHome) lookupHome(null, com.pearson.pix.business.reports.interfaces.ReportLocalHome.JNDI_NAME, com.pearson.pix.business.reports.interfaces.ReportLocalHome.class);
      }
      return ReportLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for Usage. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.usage.interfaces.UsageLocalHome getUsageLocalHome() throws javax.naming.NamingException
   {
      if (UsageLocalHome == null) {
    	  UsageLocalHome = (com.pearson.pix.business.usage.interfaces.UsageLocalHome) lookupHome(null, com.pearson.pix.business.usage.interfaces.UsageLocalHome.JNDI_NAME, com.pearson.pix.business.usage.interfaces.UsageLocalHome.class);
      }
      return UsageLocalHome;
   }
   
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for FileUploading. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocalHome getFileUploadingLocalHome() throws javax.naming.NamingException
   {
      if (FileUploadingLocalHome == null) {
    	  FileUploadingLocalHome = (com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocalHome) lookupHome(null, com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocalHome.JNDI_NAME, com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocalHome.class);
      }
      return FileUploadingLocalHome;
   }
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for CostAccounting. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.costaccounting.interfaces.CostAccountingLocalHome getCostAccountingLocalHome() throws javax.naming.NamingException
   {
      if (CostAccountingLocalHome == null) {
    	  CostAccountingLocalHome = (com.pearson.pix.business.costaccounting.interfaces.CostAccountingLocalHome) lookupHome(null, com.pearson.pix.business.costaccounting.interfaces.CostAccountingLocalHome.JNDI_NAME, com.pearson.pix.business.costaccounting.interfaces.CostAccountingLocalHome.class);
      }
      return CostAccountingLocalHome;
   } 
   
   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for MismatchReport. Lookup using COMP_NAME
    */
   public static com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocalHome getMismatchReportLocalHome() throws javax.naming.NamingException
   {
      if(MismatchReportLocalHome == null) {
    	  MismatchReportLocalHome = (com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocalHome) lookupHome(null, com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocalHome.JNDI_NAME, com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocalHome.class);
      }
      return MismatchReportLocalHome;
   } 
   
   
   
   /** Cached per JVM server IP. */
   private static String hexServerIP = null;

   // initialise the secure random instance
   private static final java.security.SecureRandom seeder = new java.security.SecureRandom();

   /**
    * A 32 byte GUID generator (Globally Unique ID). These artificial keys SHOULD <strong>NOT </strong> be seen by the user,
    * not even touched by the DBA but with very rare exceptions, just manipulated by the database and the programs.
    *
    * Usage: Add an id field (type java.lang.String) to your EJB, and add setId(XXXUtil.generateGUID(this)); to the ejbCreate method.
    */
   public static final String generateGUID(Object o) {
       StringBuffer tmpBuffer = new StringBuffer(16);
       if (hexServerIP == null) {
           java.net.InetAddress localInetAddress = null;
           try {
               // get the inet address

               localInetAddress = java.net.InetAddress.getLocalHost();
           }
           catch (java.net.UnknownHostException uhe) {
               System.err.println("Could not get the local IP address using InetAddress.getLocalHost()!");
               // todo: find better way to get around this...
               uhe.printStackTrace();
               return null;
           }
           byte serverIP[] = localInetAddress.getAddress();
           hexServerIP = hexFormat(getInt(serverIP), 8);
       }

       String hashcode = hexFormat(System.identityHashCode(o), 8);
       tmpBuffer.append(hexServerIP);
       tmpBuffer.append(hashcode);

       long timeNow      = System.currentTimeMillis();
       int timeLow       = (int)timeNow & 0xFFFFFFFF;
       int node          = seeder.nextInt();

       StringBuffer guid = new StringBuffer(32);
       guid.append(hexFormat(timeLow, 8));
       guid.append(tmpBuffer.toString());
       guid.append(hexFormat(node, 8));
       return guid.toString();
   }

   private static int getInt(byte bytes[]) {
       int i = 0;
       int j = 24;
       for (int k = 0; j >= 0; k++) {
           int l = bytes[k] & 0xff;
           i += l << j;
           j -= 8;
       }
       return i;
   }

   private static String hexFormat(int i, int j) {
       String s = Integer.toHexString(i);
       return padHex(s, j) + s;
   }

   private static String padHex(String s, int i) {
       StringBuffer tmpBuffer = new StringBuffer();
       if (s.length() < i) {
           for (int j = 0; j < i - s.length(); j++) {
               tmpBuffer.append('0');
           }
       }
       return tmpBuffer.toString();
   }

   private static DropShipLocalHome objdropShipLocalHome = null;
   
   /**
	 * For DropShip 
	 * @return
	 * @throws javax.naming.NamingException
	 * @author: anil satija
	 */
	public static DropShipLocalHome getDropShipLocalHome() throws javax.naming.NamingException
	{
		if (objdropShipLocalHome == null) {
			objdropShipLocalHome = (DropShipLocalHome) lookupHome(null, DropShipLocalHome.JNDI_NAME, DropShipLocalHome.class);
		}
		return objdropShipLocalHome;
   }

   
}

