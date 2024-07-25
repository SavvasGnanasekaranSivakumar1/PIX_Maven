package com.pearson.pix.dto.admin;


	import java.io.Serializable;
	import java.math.BigDecimal;

	public class UserCountDTO implements Serializable {
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String loginid;
		private String msg;
		private String sysname;
		
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getSysname() {
			return sysname;
		}
		public void setSysname(String sysname) {
			this.sysname = sysname;
		}
		public String getLoginId() {
			return loginid;
		}
		public void setLoginId(String loginid) {
			this.loginid = loginid;
		}
		

	}


