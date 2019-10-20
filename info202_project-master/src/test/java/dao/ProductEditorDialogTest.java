/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import gui.ProductEditor;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductEditorDialogTest {
        private ProductDatabase dao;
	private DialogFixture fixture;
	private Robot robot;

	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		// Slow down the robot a little bit - default is 30 (milliseconds).
		// Do NOT make it less than 10 or you will have thread-race problems.
		robot.settings().delayBetweenEvents(75);

		// add some categories for testing with
		Collection<String> categories = new ArrayList<>();
		categories.add("Meat");
		categories.add("Veges");
                

		// create a mock for the DAO
		dao = mock(ProductDatabase.class);

		// stub the returnCategories method to return the test categories
		when(dao.returnCategories()).thenReturn(categories);
	}
        
        @After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}
        
        @Test
	public void testSave() {
		// create the dialog passing in the mocked DAO
		ProductEditor dialog = new ProductEditor(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();

		// enter some details into the UI components
		fixture.textBox("txtID").enterText("1234");
		fixture.textBox("txtName").enterText("Carrots");
		fixture.comboBox("comboCategory").selectItem("Veges");
                fixture.textBox("txtPrice").enterText("5");
                fixture.textBox("txtQuantityInStock").enterText("200");
                fixture.textBox("txtareaDescription").enterText("Fresh");

		// click the save button
		fixture.button("productSaveButton").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).saveProduct(argument.capture());

		// retrieve the passed student from the captor
		Product savedProduct = argument.getValue();

		// test that the student's details were properly saved
		assertEquals("Ensure the ID was saved", Integer.valueOf(1234), Integer.valueOf(savedProduct.getProductID()));
		assertEquals("Ensure the name was saved", "Carrots", savedProduct.getName());
		assertEquals("Ensure the major was saved", "Veges", savedProduct.getCategory());
	}
    
}
