/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

/**
 * *
 * The User should have these attributes: ID , TELEPHONE, EMAIL , SURNAME ,
 * LASTNAME , USERNAME , PASSWORD , DATE OF BIRTH, HOME ADDRESS { NUM,CITY,TK, }
 * Administrator's special argument IBAN num
 */

import java.util.Date;

/**
 *
 * @author antig
 */
public class administrator {

    private String username;

    private String password;

    private int id_a;

    private String home_address;

    private String firstname;

    private String lastname;

    private Date date_of_birth;

    private String email;

    private int phone_number;

    private int IBAN;

    public reservation reservation;

    public String getUsername() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.username;
    }

    public void setUsername(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.username = value;
    }

    public String getPassword() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.password;
    }

    public void setPassword(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.password = value;
    }

    public void setId_a(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.id_a = value;
    }

    public int getId_a() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.id_a;
    }

    public String getHome_address() {
        // Automatically generated method. Please delete this comment before entering speci\fic code.
        return this.home_address;
    }

    public void setHome_address(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.home_address = value;
    }

    public Date getDate_of_birth() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.date_of_birth;
    }

    public void setDate_of_birth(Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.date_of_birth = value;
    }

    public String getLastname() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.lastname;
    }

    public void setLastname(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.lastname = value;
    }

    public String getFirstname() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.firstname;
    }

    public void setFirstname(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.firstname = value;
    }

    public int getPhone_number() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.phone_number;
    }

    public void setPhone_number(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.phone_number = value;
    }

    public String getEmail() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.email;
    }

    public void setEmail(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.email = value;
    }

    public void setIBAN(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.IBAN = value;
    }

    public int getIBAN() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.IBAN;
    }

}
