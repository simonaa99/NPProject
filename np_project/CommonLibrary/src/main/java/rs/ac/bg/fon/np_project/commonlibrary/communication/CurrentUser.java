/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.commonlibrary.communication;

import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;

/**
 *
 * @author Simona
 */
public class CurrentUser {
    
    private static Worker currentUser;

    public static void setCurrentUser(Worker currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static Worker getCurrentUser() {
        return currentUser;
    }
    
}
