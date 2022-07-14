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
 *
 * @author Simona
 */
public class GameValidator {

    private final List<String> validationErros;

    private GameValidator() {
        validationErros = new ArrayList();
    }

    public static GameValidator startValidation() {
        return new GameValidator();
    }

    public GameValidator validateValueIsPositive(int value, String errorMessage) throws ValidationException {
        if (value <= 0) {
            this.validationErros.add(errorMessage);

        
        }
        return this;
    }
    
    public GameValidator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }
    
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
    
    public void throwIfInvalide() throws ValidationException {
        if (!validationErros.isEmpty()) {
            throw new ValidationException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
    }

}
