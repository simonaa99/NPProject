package rs.ac.bg.fon.np_project.server.so.worker;

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
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
@ExtendWith(MockitoExtension.class)
class AddWorkerSOTest extends AbstractSOTest {
	@Mock
	RepositoryWorker worker = new RepositoryWorker();
	@Mock
	DatabaseBroker dbBroker = new DatabaseBroker();
	
	@InjectMocks
	AddWorkerSO addWorkerSO = new AddWorkerSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		worker = null;
		dbBroker = null;
		param = null;
	}
	
	@Test
	void testPreconditionNull() throws Exception {
		assertNotEquals(null,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPreconditionNotInstanceOfWorker() {
		Object p = new Worker();
		assertNotEquals(p,param,"Poslati objekat je neodgovarajuceg tipa!");
	}
	
	@Test
	void testPrecondition() {
		param = new Worker();
		assertDoesNotThrow(()-> addWorkerSO.precondition(param));
	}

	@Test
	void testExecuteOperation() throws Exception {
		Worker w = new Worker();
		Mockito.doNothing().when(worker).add(w);
        worker.add(w);
        assertDoesNotThrow(()-> addWorkerSO.executeOperation(w));
	}

	@Test
	void testAddWorkerSO() {
		assertNotEquals(null, worker);
		assertNotEquals(null, dbBroker);
	}

}
