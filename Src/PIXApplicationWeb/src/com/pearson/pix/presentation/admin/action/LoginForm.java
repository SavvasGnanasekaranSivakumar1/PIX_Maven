package com.pearson.pix.presentation.admin.action;

import org.apache.struts.validator.ValidatorForm;

import com.pearson.pix.dto.admin.User;
import com.pearson.pix.presentation.base.action.BaseForm;

/**
 * The Login Form class. This clas is used as the form bean for
 * the Login page
 * 
 */
public class LoginForm extends BaseForm 
{
    public static final long serialVersionUID = 001011L;
    
    /**
     * Default Constructor
     *
     */
    public LoginForm(){
        //do nothing
    }
    
    /* the login id entered by the user*/
    public String loginId;
    
    /* the password entered by the user*/
    public String password;
/* stores the User dto object*/
    
    public String ssoid;
    
    public User user;
    
  /**
   * This method gets User object
   * @return user
   */  
    public User getUser() {
		return user;
	}
/**This method sets the User object
 * 
 * @param user
 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
     * This method gets the loginId.
     * @return Returns the loginId.
     */
    public String getLoginId() {
        return this.loginId;
    }

    /**
     * This method sets the loginId.
     * @param loginId The loginId to set.
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * This method sets the password.
     * @return Returns the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * This method sets the password.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
	public String getSsoid() {
		return ssoid;
	}
	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}
	
}