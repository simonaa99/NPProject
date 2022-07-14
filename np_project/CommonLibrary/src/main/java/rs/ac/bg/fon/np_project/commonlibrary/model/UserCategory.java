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
public class UserCategory implements Serializable{
    
    private Long UserCategoryId;
    private String name;
    private Double membershipFeeDiscount;

    public Long getUserCategoryId() {
        return UserCategoryId;
    }

    public void setUserCategoryId(Long UserCategoryId) {
        this.UserCategoryId = UserCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMembershipFeeDiscount() {
        return membershipFeeDiscount;
    }

    public void setMembershipFeeDiscount(Double membershipFeeDiscount) {
        this.membershipFeeDiscount = membershipFeeDiscount;
    }

    @Override
    public String toString() {
       return name;
    }
    
}
