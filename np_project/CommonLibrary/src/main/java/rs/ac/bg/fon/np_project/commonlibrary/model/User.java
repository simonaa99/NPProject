/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;

/**
 *
 * @author Simona
 */
public class User extends AbstractDO implements Serializable {
    
    private Long userId;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String address;
    private UserCategory userCategory;
    private UserCard usercard;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public UserCard getUsercard() {
        return usercard;
    }

    public void setUsercard(UserCard usercard) {
        this.usercard = usercard;
    }

    @Override
    public String getAttributeList() {
        return "ime, prezime, brojTelefona, adresa, kategorijaId, clanskaKartaId";
    }

    @Override
    public String getClassName() {
        return "clan";
    }

    @Override
    public String getAttributeValues() {
         return "'"+name+"', '"+lastName+"', '"+phoneNumber+"', '"+address+"', "+userCategory.getUserCategoryId()+", "+usercard.getId();
    }

    @Override
    public String getQueryCondition() {
        return "id="+userId;
    }
    
}
