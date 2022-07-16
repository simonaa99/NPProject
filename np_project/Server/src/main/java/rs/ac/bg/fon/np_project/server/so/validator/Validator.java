/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.so.validator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Predstavlja klasu koja vrsi validaciju. Sadrzi atribut tipa List<String>
 * koja predstavlja listu svih gresaka koje su se javile pri proveri. Sastoji se od konstruktora, metode
 * startValidation, validateNotNullOrEmpty, validateContainsCharacter, validateFormat i throwIfInvalide.
 *
 * @author Simona
 * @version 1.0.0
 */
public class Validator {

	/**
	 * Atribut koji je tipa List<String> i u sebi sadrzi sve greske koje su nastale prilikom
	 * provere igre.
	 */
    private final List<String> validationErros;

    /**
     * Konstruktor koji inicijalizuje listu validationErrors
     */
    private Validator() {
        validationErros = new ArrayList();
    }

    /**
     * Metoda koja pokrece novu validaciju
     * @return novu validaciju 
     */
    public static Validator startValidation() {
        return new Validator();
    }

    /**
     * Metoda koja proverava da li je uneti string null ili prazan.
     * Ako jeste ubacuje poruku o gresci u listu validationErrors.
     * 
     * @param value tipa String koji se proverava
     * @param errorMessage tipa String poruka o gresci
     * @return validaciju 
     * @throws ValidationExceptionrs.ac.bg.fon.np_project.server.so.validator.ValidationException ako dodje do greske 
     * prilikom validacije
     */
    public Validator validateNotNullOrEmpty(String value, String errorMessage) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    /**
     * Metoda proverava da li se odredjeni String nalazi u Stringu value.
     * Ako se nalazi ubacuje poruku o gresci u listu validationErrors.
     * 
     * @param value tipa String koji se proverava
     * @param character tipa String da li se nalazi u stringu value
     * @param errorMessage tipa String poruka o gresci
     * @return validaciju 
     * @throws ValidationExceptionrs.ac.bg.fon.np_project.server.so.validator.ValidationException ako dodje do greske 
     * prilikom validacije
     */
     public Validator validateContainsCharacter(String value, String character, String errorMessage) throws ValidationException {
        if (value.contains(character)) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

     /**
      * Metoda koja proverava da li je string value dobrog formata. Ako nije
      * dodaje poruku o gresci u validationErrors listu.
      * 
      * @param value tipa String koji se proverava
      * @param format tipa String kog formata treba da bude String value
      * @param errorMessage tipa String poruka o gresci
      * @return validaciju 
      * @throws ValidationExceptionrs.ac.bg.fon.np_project.server.so.validator.ValidationException ako dodje do greske 
      * prilikom validacije
      */
    public Validator validateFormat(String value, String format, String errorMessage) throws ValidationException {
        Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(value);
        boolean b = m.matches();
        if (value == null || !b) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    /**
     * Metoda koja baca ValidationException ako lista validationErrors nije prazna i 
     * prikazuje sve poruke koje su se nalazile u listi validationErrors.
     * 
     * @throws rs.ac.bg.fon.np_project.server.so.validator.ValidationException ako dodje do greske 
     * prilikom validacije
     */
    public void throwIfInvalide() throws ValidationException {
        if (!validationErros.isEmpty()) {
            throw new ValidationException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
    }

}
