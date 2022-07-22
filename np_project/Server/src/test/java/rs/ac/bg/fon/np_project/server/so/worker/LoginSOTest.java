package rs.ac.bg.fon.np_project.server.so.worker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
@ExtendWith(MockitoExtension.class)
class LoginSOTest extends AbstractSOTest {

	@Mock
	RepositoryWorker worker =new RepositoryWorker();
	
	@InjectMocks
	LoginSO loginSO = new LoginSO();
	
	Object param;
	
	@BeforeEach
	void setUp() throws Exception {
		param = new Object();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		worker = null;
		loginSO = null;
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
	void testPreconditionUsernameIsNull() {
		Worker p = new Worker();
		assertNull(p.getUsername(), "Nisu poslati kredencijali!");
	}
	
	@Test
	void testPreconditionPasswordIsNull() {
		Worker p = new Worker();
		assertNull(p.getPassword(), "Nisu poslati kredencijali!");
	}
	

	@Test
	void testExecuteOperation() throws Exception {
		Worker w1 = new Worker();
		w1.setId(8L);
		w1.setFirstName("Milena");
		w1.setLastName("Bokic");
		w1.setUsername("Mica5");
		w1.setPassword("1234");
		w1.setLoggedIn(true);
		
		Worker w2 = new Worker();
		w2.setId(8L);
		w2.setFirstName("Mita");
		w2.setLastName("Polic");
		w2.setUsername("mita4");
		w2.setPassword("987");
		w2.setLoggedIn(false);
		
		List<Worker> workers = new LinkedList<>();
        Worker currentUser = new Worker();
        Worker user = w2;
        Mockito.when(worker.getAll()).thenReturn(List.of(w1,w2));
        workers = worker.getAll();
            for (Worker r : workers) {
               if (r.getUsername().equals(user.getUsername()) && r.getPassword().equals(user.getPassword())) {
                   currentUser = r;
                   assertNotEquals(true,currentUser.isLoggedIn(),"Korisnik je vec prijavljen.");
                   }
              
       Mockito.doNothing().when(worker).setUserIsLoggedIn(currentUser);     
       worker.setUserIsLoggedIn(currentUser);
            }

        
        assertNotEquals(null,currentUser,"Nepoznat korisnik!");
        assertDoesNotThrow(()->loginSO.executeOperation(w2));
	}

	@Test
	void testLoginSO() {
		assertNotEquals(null, worker);
	}

}
