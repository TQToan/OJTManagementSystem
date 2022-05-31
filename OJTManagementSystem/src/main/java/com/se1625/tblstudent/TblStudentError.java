/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblstudent;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblStudentError implements Serializable{
    private String errorAddressLength;
    private String errorPhoneNumberLength;
    private String errorDateInvalid;
    
    public TblStudentError() {
    }

    public TblStudentError(String errorAddressLength, String errorPhoneNumberLength, String errorDateInvalid) {
        this.errorAddressLength = errorAddressLength;
        this.errorPhoneNumberLength = errorPhoneNumberLength;
        this.errorDateInvalid = errorDateInvalid;
    }

    public String getErrorDateInvalid() {
        return errorDateInvalid;
    }

    public void setErrorDateInvalid(String errorDateInvalid) {
        this.errorDateInvalid = errorDateInvalid;
    }

    

    public String getErrorAddressLength() {
        return errorAddressLength;
    }

    public void setErrorAddressLength(String errorAddressLength) {
        this.errorAddressLength = errorAddressLength;
    }

    public String getErrorPhoneNumberLength() {
        return errorPhoneNumberLength;
    }

    public void setErrorPhoneNumberLength(String errorPhoneNumberLength) {
        this.errorPhoneNumberLength = errorPhoneNumberLength;
    }
    
    
}
