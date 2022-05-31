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
<<<<<<< HEAD
 * @author ThanhTy
=======
 * @author Thai Quoc Toan <toantqse151272@fpt.edu.vn>
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
 */
public class TblStudentDTO implements Serializable{
    private String studentCode;
    private Date birthDay;
    private String address;
    private boolean gender;
    private String phone;
<<<<<<< HEAD
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
=======
    private int isInterns;
    private int numberOfCredits;
    private String major;
    private TblAccountDTO account;
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    

    public TblStudentDTO() {
    }

<<<<<<< HEAD
=======
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
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public String getStudentCode() {
        return studentCode;
    }

<<<<<<< HEAD
=======
    /**
     * @param studentCode the studentCode to set
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

<<<<<<< HEAD
=======
    /**
     * @return the birthDay
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public Date getBirthDay() {
        return birthDay;
    }

<<<<<<< HEAD
=======
    /**
     * @param birthDay the birthDay to set
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

<<<<<<< HEAD
=======
    /**
     * @return the address
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public String getAddress() {
        return address;
    }

<<<<<<< HEAD
=======
    /**
     * @param address the address to set
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public void setAddress(String address) {
        this.address = address;
    }

<<<<<<< HEAD
=======
    /**
     * @return the gender
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public boolean isGender() {
        return gender;
    }

<<<<<<< HEAD
=======
    /**
     * @param gender the gender to set
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public void setGender(boolean gender) {
        this.gender = gender;
    }

<<<<<<< HEAD
=======
    /**
     * @return the phone
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public String getPhone() {
        return phone;
    }

<<<<<<< HEAD
=======
    /**
     * @param phone the phone to set
     */
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public void setPhone(String phone) {
        this.phone = phone;
    }

<<<<<<< HEAD
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

=======
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
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
    public String getMajor() {
        return major;
    }

<<<<<<< HEAD
    public void setMajor(String major) {
        this.major = major;
    }
=======
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
    
    
>>>>>>> bee8428da5a125bb55daa1e38d89b53ff54a5338
}
