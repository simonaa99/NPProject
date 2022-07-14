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
public class Worker extends AbstractDO implements Serializable{
    
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    

    public Worker(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Worker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getAttributeList() {
        return "username, password, ime, prezime";
          }

    @Override
    public String getClassName() {
        return "radnik";
    }

    @Override
    public String getAttributeValues() {
        return "'"+username+"', '"+password+"', '"+firstName+"', '"+lastName+"'";
         }

    @Override
    public String getQueryCondition() {
        return "radnikID="+id;
    }
            
}
