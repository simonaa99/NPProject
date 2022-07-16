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
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.server.so.game.AddGameSO;

/**
 * Predstavlja klasu koja vrsi validaciju drustvene igre. Sadrzi atribut tipa List
 * koja predstavlja listu svih gresaka koje su se javile pri proveri. Sastoji se od konstruktora, metoda startValidation,
 * validateValueIsPositive, validateNotNull, validateAlreadyExists i throwIfInvalide.
 *
 * @author Simona
 * @version 1.0.0
 */
public class GameValidator {

	/**
	 * Atribut koji je tipa List i u sebi sadrzi sve greske koje su nastale prilikom
	 * provere igre.
	 */
    private final List<String> validationErros;

    /**
     * Konstruktor koji inicijalizuje listu validationErrors
     */
    private GameValidator() {
        validationErros = new ArrayList();
    }

    /**
     * Metoda koja pokrece novu validaciju
     * @return novu validaciju 
     */
    public static GameValidator startValidation() {
        return new GameValidator();
    }

    /**
     * Metoda koja proverava da li je vrednost value negativna. Ako jeste
     * poruka greske se ubacuje u listu validationErrors. 
     * 
     * @param value tipa int vrednost za koju se proverava da li je negativna
     * @param errorMessage tipa String poruka greske
     * @return validaciju
     * @throws rs.ac.bg.fon.np_project.server.so.validator.ValidationException ako dodje do greske 
     * prilikom validacije
     */
    public GameValidator validateValueIsPositive(int value, String errorMessage) throws ValidationException {
        if (value <= 0) {
            this.validationErros.add(errorMessage);

        
        }
        return this;
    }
    
    /**
     * 
     * Metoda koja proverava da li je objekat null.
     * 
     * @param value tipa Object nad kojim se vrsi validacija i ne sme da bude null
     * @param errorMessage tipa String poruka greske
     * @return validaciju
     * @throws rs.ac.bg.fon.np_project.server.so.validator.ValidationException ako dodje do greske 
     * prilikom validacije
     */
    public GameValidator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }
    
    /**
     * Metoda proverava da li objekat vec postoji u bazi.
     * 
     * @param value tipa Object nad kojim se vrsi validacija
     * @param so tipa AddGameSO koja se vrsi kad se dodaje igra u bazu
     * @param errorMessage tipa String poruka greske
     * @return validaciju
     * @throws rs.ac.bg.fon.np_project.server.so.validator.ValidationException ako dodje do greske 
     * prilikom validacije sa porukom "Igra postoji u bazi!"
     */
    public GameValidator validateAlreadyExists(Object value, AddGameSO so, String errorMessage) throws ValidationException {
        Game game=(Game) value;
        so=(AddGameSO) so;
        try {
            if (so.exists(game)) {
                this.validationErros.add("Igra postoji u bazi!");
            }
        } catch (Exception ex) {
            this.validationErros.add(ex.getMessage());
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
