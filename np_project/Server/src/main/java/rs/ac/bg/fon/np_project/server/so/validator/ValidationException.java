/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.validator;

/**
 * Predstavlja klasu koja kada se desi greska prosledjuje odredjenu poruku 
 * klasi koja ju je pozvala.
 *
 * @author Simona
 * @version 1.0.0
 */
public class ValidationException extends Exception {

	/**
	 * Konstruktor koji prosledjuje unetu poruku 
	 * @param message tipa String poruka o gresci
	 */
    public ValidationException(String message) {
        super(message);
    }
    
    
}
