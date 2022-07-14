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
public class Publisher extends AbstractDO implements Serializable{
    
    private Long publisherId;
    private String publisherName;

    public Publisher(Long publisherID, String imePrezime) {
        this.publisherId = publisherID;
        this.publisherName = imePrezime;
    }

    public Publisher() {
         }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return publisherName;   }

    @Override
    public String getAttributeList() {
        return "imePrezime";
    }

    @Override
    public String getClassName() {
        return "izdavac";
    }

    @Override
    public String getAttributeValues() {
        return "'"+getPublisherName()+"'";
    }

    @Override
    public String getQueryCondition() {
        return "id= "+getPublisherId();
    }
    
}
