package rs.ac.bg.fon.np_project.server.so.rent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

@ExtendWith(MockitoExtension.class)
class GetAllRentsSOTest extends AbstractSOTest {

	@Mock
	RepositoryRent rent = new RepositoryRent();
	
	@InjectMocks
	GetAllRentsSO getAllRentsSO = new GetAllRentsSO();
	
	Object param = new Object();

	@Test
	void testExecuteOperation() throws Exception {
		Rent r1 = new Rent(); 
		Rent r2 = new Rent();
		
		Mockito.when(rent.getAll()).thenReturn(List.of(r1,r2));
		
		assertEquals(r1,rent.getAll().get(0));
		assertEquals(r2, rent.getAll().get(1));
		
		assertDoesNotThrow(()->getAllRentsSO.executeOperation(param));
	}

	@Test
	void testGetAllRentsSO() {
		assertNotEquals(null, rent);
	}

}
