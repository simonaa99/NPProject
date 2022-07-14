/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.exception;

/**
 *
 * @author Simona
 */
public class NoUserFoundException extends Exception{
    
    public NoUserFoundException(String message) {
        super(message);
    }
    
}
