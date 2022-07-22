package rs.ac.bg.fon.np_project.server.so.publisher;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryRent;
import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;
import rs.ac.bg.fon.np_project.server.so.game.DeleteGameSO;

@ExtendWith(MockitoExtension.class)
class GetAllPublishersSOTest extends AbstractSOTest {

	@Mock
	RepositoryPublisher publisher = new RepositoryPublisher();
	
	@InjectMocks
	GetAllPublishersSO getAllPublishersSO = new GetAllPublishersSO();
	
	Object param = new Object();
	
	
	@Test
	void testExecuteOperation() throws Exception {
		Publisher p1 = new Publisher(2L, "Mirko Markovic");
		Publisher p2 = new Publisher(5L, "Kolina Inc");
		
		Mockito.when(publisher.getAll()).thenReturn(List.of(p1,p2));
		
		assertEquals(p1,publisher.getAll().get(0));
		assertEquals(p2, publisher.getAll().get(1));
		
		assertDoesNotThrow(()->getAllPublishersSO.executeOperation(param));
		
	}

	@Test
	void testGetAllPublishersSO() {
		assertNotEquals(null, publisher);
	}

}
