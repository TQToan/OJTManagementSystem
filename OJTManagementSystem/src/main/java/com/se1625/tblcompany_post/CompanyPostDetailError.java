/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblcompany_post;

import java.io.Serializable;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class CompanyPostDetailError implements Serializable{
    private String quantitytInternsNotEngough;
    private String expirationDateError;

    public CompanyPostDetailError() {
    }

    public CompanyPostDetailError(String quantitytInternsNotEngough, String expirationDateError) {
        this.quantitytInternsNotEngough = quantitytInternsNotEngough;
        this.expirationDateError = expirationDateError;
    }

    /**
     * @return the quantitytInternsNotEngough
     */
    public String getQuantitytInternsNotEngough() {
        return quantitytInternsNotEngough;
    }

    /**
     * @param quantitytInternsNotEngough the quantitytInternsNotEngough to set
     */
    public void setQuantitytInternsNotEngough(String quantitytInternsNotEngough) {
        this.quantitytInternsNotEngough = quantitytInternsNotEngough;
    }

    /**
     * @return the expirationDateError
     */
    public String getExpirationDateError() {
        return expirationDateError;
    }

    /**
     * @param expirationDateError the expirationDateError to set
     */
    public void setExpirationDateError(String expirationDateError) {
        this.expirationDateError = expirationDateError;
    }
    
    
}
