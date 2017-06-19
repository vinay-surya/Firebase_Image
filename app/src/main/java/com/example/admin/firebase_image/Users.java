package com.example.admin.firebase_image;

public class Users {


    private String dob;
    private String height;
    private String country;
    private String education;
    private String work;
    private String income;
    private String marital;
    private String mother;
    private String religion;
    private String name;
    private String email;
    private String password;
    private String confirmpassword;
    private String mobile;
    private String message;


    public String url;

    public Users(){

    }

    public Users(String dob, String height, String country, String education, String work, String income, String marital, String mother, String religion, String name, String email, String password, String confirmpassword, String mobile, String message, String url) {

        this.dob = dob;
        this.height = height;
        this.country = country;
        this.education = education;
        this.work = work;
        this.income = income;
        this.marital = marital;
        this.mother = mother;
        this.religion = religion;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.mobile = mobile;
        this.message = message;
        this.url = url;


    }



    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
