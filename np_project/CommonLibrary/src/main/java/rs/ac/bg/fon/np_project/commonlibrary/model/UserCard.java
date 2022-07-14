/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Simona
 */
public class UserCard extends AbstractDO implements Serializable{
    
    private Long id;
    private String CardNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
       return CardNumber; 
    }

    @Override
    public String getAttributeList() {
        return "brojClanskeKarte, datumIzdavanja, datumIsteka";
    }

    @Override
    public String getClassName() {
        return "clanskaKarta";
    }

    @Override
    public String getAttributeValues() {
        return "'"+CardNumber+"', '"+Date.valueOf(issueDate)+"', '"+Date.valueOf(expiryDate)+"'";
    }

    @Override
    public String getQueryCondition() {
        return "brojClanskeKarte= '"+CardNumber+"'";
    }
    
}
