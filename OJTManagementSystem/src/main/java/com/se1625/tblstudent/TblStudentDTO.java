/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1625.tblstudent;

import com.se1625.tblaccount.TblAccountDTO;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
 */
public class TblStudentDTO implements Serializable{
    private String studentCode;
    private Date birthDay;
    private String address;
    private boolean gender;
    private String phone;
    private int isInterns;
    private int numberOfCredits;
    private String major;
    private TblAccountDTO account;   


    public TblStudentDTO() {
    }

    public TblStudentDTO(String studentCode, Date birthDay, String address, boolean gender, String phone, int isInterns, int numberOfCredits, String major) {
        this.studentCode = studentCode;
        this.birthDay = birthDay;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.isInterns = isInterns;
        this.numberOfCredits = numberOfCredits;
        this.major = major;
    }
   
        
    /**
     * @return the studentCode
     */
    public String getStudentCode() {
        return studentCode;
    }

    /**
     * @param studentCode the studentCode to set
     */
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    /**
     * @return the birthDay
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the gender
     */
    public boolean isGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the isInterns
     */
    public int getIsInterns() {
        return isInterns;
    }

    /**
     * @param isInterns the isInterns to set
     */
    public void setIsInterns(int isInterns) {
        this.isInterns = isInterns;
    }

    /**
     * @return the numberOfCredits
     */
    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    /**
     * @param numberOfCredits the numberOfCredits to set
     */
    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return the account
     */
    public TblAccountDTO getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(TblAccountDTO account) {
        this.account = account;
    }
    
     
}
