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
import rs.ac.bg.fon.np_project.server.broker.DatabaseBroker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
@ExtendWith(MockitoExtension.class)
class GetWorkersSOTest extends AbstractSOTest {

	@Mock
	RepositoryWorker worker = new RepositoryWorker();

	
	@InjectMocks
	GetWorkersSO getWorkersSO = new GetWorkersSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		worker = null;
		getWorkersSO = null;
		param = null;
	}

	@Test
	void testExecuteOperation() {
		Worker param = new Worker();
		assertDoesNotThrow(()->getWorkersSO.executeOperation(param));
	}

	@Test
	void testGetWorkersSO() {
		assertNotEquals(null, worker);
	}

}
