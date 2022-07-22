package rs.ac.bg.fon.np_project.server.so.rent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

@ExtendWith(MockitoExtension.class)
class GetUserRentsSOTest extends AbstractSOTest {

	@Mock
	RepositoryRent rent = new RepositoryRent();
	
	@InjectMocks
	GetUserRentsSO getUserRentsSO = new GetUserRentsSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		getUserRentsSO = null;
		rent = null;
		param = null;
	}
	
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
		Object p = new User();
		assertDoesNotThrow(()->getUserRentsSO.executeOperation(p));
	}

	@Test
	void testGetUserRentsSO() {
		assertNotEquals(null, rent);
	}

}
