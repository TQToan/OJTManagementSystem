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
 * @author ThanhTy
 */
public class TblStudentDTO implements Serializable{
    private String studentCode;
    private Date birthDay;
    private String address;
    private boolean gender;
    private String phone;
    private int isIntern;
    private int numberOfCredit;
    private String major;
    private TblAccountDTO account;

    public TblStudentDTO(String studentCode, Date birthDay, String address, boolean gender, String phone, int isIntern, int numberOfCredit, String major, TblAccountDTO account) {
        this.studentCode = studentCode;
        this.birthDay = birthDay;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.isIntern = isIntern;
        this.numberOfCredit = numberOfCredit;
        this.major = major;
        this.account = account;
    }

    public TblStudentDTO(Date birthDay, String major) {
        this.birthDay = birthDay;
        this.major = major;
    }

    public TblAccountDTO getAccount() {
        return account;
    }

    public void setAccount(TblAccountDTO account) {
        this.account = account;
    }
    

    public TblStudentDTO() {
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsIntern() {
        return isIntern;
    }

    public void setIsIntern(int isIntern) {
        this.isIntern = isIntern;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
