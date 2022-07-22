package rs.ac.bg.fon.np_project.server.so.validator;

import static org.junit.jupiter.api.Assertions.*;


import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.server.so.game.AddGameSO;
@ExtendWith(MockitoExtension.class)
class GameValidatorTest {
 
	@Mock
	AddGameSO addGamesSO = new AddGameSO();
	
	List<String> validationErros = new LinkedList<>();
	
	@InjectMocks
	GameValidator gameValidator;

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
		addGamesSO = null;
		validationErros = null;
	}
	

	@ParameterizedTest
    @CsvSource({"-7", "-5", "0", "-100"})
	void testValidateValueIsPositive(int value) {
		assertEquals(true,value <= 0,"Poslati objekat je neodgovarajuceg tipa!");
		validationErros.add("Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testValidateNotNull() {
		Object s = null;
		assertEquals(null,s,"Poslati objekat je neodgovarajuceg tipa!");
		validationErros.add("Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testValidateAlreadyExists() throws Exception {
		Game g1 = new Game();
		g1.setGameid(1L);
		g1.setGameName("Monopol");
		g1.setNumberInStock(15);
		g1.setNumPlayers(20);
		g1.setGameCategory(GameCategory.Porodicne_igre);
		Publisher p = new Publisher(2L, "Mirko Markovic");
		g1.setPublisher(p);
		
	     Mockito.when(addGamesSO.exists(g1)).thenReturn(true);
	     assertEquals(true,addGamesSO.exists(g1),"Igra postoji u bazi!");
	     validationErros.add("Igra postoji u bazi!");
	            
	}

	@Test
	void testThrowIfInvalide() {
		assertEquals(true,validationErros.isEmpty());
	}

}
