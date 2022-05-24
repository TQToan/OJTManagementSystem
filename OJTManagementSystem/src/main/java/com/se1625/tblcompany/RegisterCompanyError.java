/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblcompany;

import java.io.Serializable;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class RegisterCompanyError implements Serializable{
    private String companyNameLengthError;
    private String companyAddressLengthError;
    private String companyDescriptionLegthError;
    private String companyPhoneLengthError;
    private String companyLogoLenthError;

    public RegisterCompanyError() {
    }

    public RegisterCompanyError(String companyNameLengthError, String companyAddressLengthError, String companyDescriptionLegthError, String companyPhoneLengthError, String companyLogoLenthError) {
        this.companyNameLengthError = companyNameLengthError;
        this.companyAddressLengthError = companyAddressLengthError;
        this.companyDescriptionLegthError = companyDescriptionLegthError;
        this.companyPhoneLengthError = companyPhoneLengthError;
        this.companyLogoLenthError = companyLogoLenthError;
    }

    /**
     * @return the companyNameLengthError
     */
    public String getCompanyNameLengthError() {
        return companyNameLengthError;
    }

    /**
     * @param companyNameLengthError the companyNameLengthError to set
     */
    public void setCompanyNameLengthError(String companyNameLengthError) {
        this.companyNameLengthError = companyNameLengthError;
    }

    /**
     * @return the companyAddressLengthError
     */
    public String getCompanyAddressLengthError() {
        return companyAddressLengthError;
    }

    /**
     * @param companyAddressLengthError the companyAddressLengthError to set
     */
    public void setCompanyAddressLengthError(String companyAddressLengthError) {
        this.companyAddressLengthError = companyAddressLengthError;
    }

    /**
     * @return the companyDescriptionLegthError
     */
    public String getCompanyDescriptionLegthError() {
        return companyDescriptionLegthError;
    }

    /**
     * @param companyDescriptionLegthError the companyDescriptionLegthError to set
     */
    public void setCompanyDescriptionLegthError(String companyDescriptionLegthError) {
        this.companyDescriptionLegthError = companyDescriptionLegthError;
    }

    /**
     * @return the companyPhoneLengthError
     */
    public String getCompanyPhoneLengthError() {
        return companyPhoneLengthError;
    }

    /**
     * @param companyPhoneLengthError the companyPhoneLengthError to set
     */
    public void setCompanyPhoneLengthError(String companyPhoneLengthError) {
        this.companyPhoneLengthError = companyPhoneLengthError;
    }

    /**
     * @return the companyLogoLenthError
     */
    public String getCompanyLogoLenthError() {
        return companyLogoLenthError;
    }

    /**
     * @param companyLogoLenthError the companyLogoLenthError to set
     */
    public void setCompanyLogoLenthError(String companyLogoLenthError) {
        this.companyLogoLenthError = companyLogoLenthError;
    }
    
    
}
