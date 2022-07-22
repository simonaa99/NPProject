package rs.ac.bg.fon.np_project.server.so.rent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGame;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
@ExtendWith(MockitoExtension.class)
class RestoreGameSOTest extends AbstractSOTest {

	@Mock
	RepositoryRent rent = new RepositoryRent();
	
	@Mock
	 RepositoryGame game = new RepositoryGame();
	
	@InjectMocks
	RestoreGameSO restoreGameSO = new RestoreGameSO();
	
	Object param ;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		rent = null;
		game = null;
		param = null;
		restoreGameSO = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfRent() {
		param = new Rent();
		assertTrue(param instanceof Rent, "Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testExecuteOperation() throws Exception {
		param = new Rent();
		rent.restoreGame((Rent)param);
		assertDoesNotThrow(()->{restoreGameSO.precondition(param);});
	}

	@Test
	void testRestoreGameSO() {
		assertNotEquals(null, rent);
		assertNotEquals(null, game);
	}

}
