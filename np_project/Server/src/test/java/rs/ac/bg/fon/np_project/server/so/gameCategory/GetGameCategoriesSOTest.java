package rs.ac.bg.fon.np_project.server.so.gameCategory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.np_project.server.so.AbstractSOTest;

class GetGameCategoriesSOTest extends AbstractSOTest {

	GetGameCategoriesSO getGameCategoriesSO = new GetGameCategoriesSO();
	
	Object param = new Object();

	@Test
	void testExecuteOperation() throws Exception {
		assertDoesNotThrow(()->getGameCategoriesSO.executeOperation(param));
	}

}
