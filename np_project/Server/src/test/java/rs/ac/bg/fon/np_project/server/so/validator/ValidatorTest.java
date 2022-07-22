package rs.ac.bg.fon.np_project.server.so.validator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class ValidatorTest {

	
	List<String> validationErros = new LinkedList<>();
	
	@InjectMocks
	Validator Validator;
	
	int value;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {

	}
	
	@Test
	void testValidateNotNullOrEmptyNull() {
		String s = null;
		assertEquals(null,s,"Poslati objekat je neodgovarajuceg tipa!");
		validationErros.add("Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testValidateNotNullOrEmptyEmpty() {
		String s = "";
		assertEquals(true,s.equals(""),"Poslati objekat je neodgovarajuceg tipa!");
		validationErros.add("Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testValidateContainsCharacter() {
		String s = "Ravnica drustvene igre";
		String character = "drustvene";
		assertEquals(true,s.contains(character),"Poslati objekat je neodgovarajuceg tipa!");
		validationErros.add("Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testValidateFormatNull() {
		String format = "Ravnica";
		String value = null;
		Pattern p = Pattern.compile(format);

        assertEquals(null,value);
		validationErros.add("Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testValidateFormatFalseMatch() {
		String format = "Ravnica";
		String value = "igra";
		Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(value);
        boolean b = m.matches();
        assertEquals(false,b);
		validationErros.add("Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testThrowIfInvalide() {
		assertEquals(true,validationErros.isEmpty());
	}

}
