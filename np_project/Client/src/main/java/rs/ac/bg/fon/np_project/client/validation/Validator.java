/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.validation;

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
 *
 * @author Simona
 */
public class Validator {

    private final List<String> validationErros;

    private Validator() {
        validationErros = new ArrayList();
    }

    public static Validator startValidation() {
        return new Validator();
    }

    public Validator validateNotNullOrEmpty(String value, String errorMessage) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }
    public Validator validateFormat(String value, String format, String errorMessage) throws ValidationException {
        Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(value);
        boolean b = m.matches();
        if (value == null || !b) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }
    public Validator validateValueIsNumber(String value, String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                new BigDecimal(value);
            } else {
                this.validationErros.add(errorMessage);
            }
        } catch (NumberFormatException nfe) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }
    
    public Validator validateValueIsDate(String value,String pattern , String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.parse(value);
            } else {
                this.validationErros.add(errorMessage);
            }
        } catch (ParseException ex) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }
    
    public Validator validateListIsNotEmpty(List list , String errorMessage) throws ValidationException {
            if (list == null || list.isEmpty()) {
                this.validationErros.add(errorMessage);
            }
        return this;
    }

    public void throwIfInvalide() throws ValidationException {
        if (!validationErros.isEmpty()) {
            throw new ValidationException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
    }

    public Validator validateNumberNotNegative(String value, String errorMessage) throws ValidationException{
          if ((Double.parseDouble(value))<0) {
                this.validationErros.add(errorMessage);
            }
        return this; 
    }

}
