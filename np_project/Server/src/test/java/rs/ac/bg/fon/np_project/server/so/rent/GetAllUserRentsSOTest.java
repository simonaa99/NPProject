package rs.ac.bg.fon.np_project.server.so.rent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.game.AddGameSO;

@ExtendWith(MockitoExtension.class)
class GetAllUserRentsSOTest extends AbstractSOTest {

	@Mock
	RepositoryRent rent = new RepositoryRent();
	
	@InjectMocks
	GetAllUserRentsSO getAllUserRentsSO = new GetAllUserRentsSO();
	
	Object param = new Object();
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfUser() {
		Object p = new User();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
	}


	@Test
	void testExecuteOperation() {
		Object u = new User();
		assertDoesNotThrow(()->getAllUserRentsSO.executeOperation(u));
	}
	
	
	@Test
	void testGetAllUserRentsSO() {
		assertNotEquals(null, rent);
	}

}
