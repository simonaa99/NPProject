package rs.ac.bg.fon.np_project.server.so.worker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
@ExtendWith(MockitoExtension.class)
class RemoveWorkerSOTest extends AbstractSOTest {
	@Mock
	RepositoryWorker worker =new RepositoryWorker();
	
	@InjectMocks
	RemoveWorkerSO removeWorkerSO = new RemoveWorkerSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		worker = null;
		removeWorkerSO = null;
		param = null;
	}
	
	@Test
	void testPreconditionNull() {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionInstanceOfWorker() {
		Object p = new Worker();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
	}

	@Test
	void testPrecondition() {
		Worker w1 = new Worker();
		w1.setId(8L);
		w1.setFirstName("Milena");
		w1.setLastName("Bokic");
		w1.setUsername("Mica5");
		w1.setPassword("1234");
		w1.setLoggedIn(true);
		assertDoesNotThrow(()->removeWorkerSO.precondition(w1));
	}

	@Test
	void testExecuteOperation() {
		Worker w1 = new Worker();
		w1.setId(8L);
		w1.setFirstName("Milena");
		w1.setLastName("Bokic");
		w1.setUsername("Mica5");
		w1.setPassword("1234");
		w1.setLoggedIn(true);
		assertDoesNotThrow(()->removeWorkerSO.executeOperation(w1));
	}

	@Test
	void testRemoveWorkerSO() {
		assertNotEquals(null, worker);
	}

}
