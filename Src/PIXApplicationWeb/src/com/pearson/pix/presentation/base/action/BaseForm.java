package com.pearson.pix.presentation.base.action;

import org.apache.struts.validator.ValidatorForm;

/**
 * Action validator form class for All screen presentation.
 * 
 */
public class BaseForm extends ValidatorForm {

    /**
     * Default serial Version UID.
     */
    private static final long serialVersionUID = 8714370451127548682L;
    /**
     * dataChanged variable.
     */
    private boolean dataChange;

    /**
     * @return Returns the dataChange.
     */
    public boolean isDataChange() {
        return this.dataChange;
    }

    /**
     * @param dataChange The dataChange to set.
     */
    public void setDataChange(boolean dataChange) {
        this.dataChange = dataChange;
    }
}
