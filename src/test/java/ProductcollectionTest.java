/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProductDAODatabase;
import dao.DbConnection;
import dao.ProductDAOCollections;
import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import gui.DataEntryDialog;
import java.util.ArrayList;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.junit.Assert.assertEquals;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import dao.ProductDAO;

public class ProductcollectionTest {


/**
 *
 * @author tylan825
 */
    private DialogFixture fixture;
    private Robot robot;
 //   private ProductDAO dao;

  //  private ProductDAO dao = new productDatabase("jdbc:h2:mem:tests;INIT=runscript from 'src/main/resources/schema.sql'");
   private  ProductDAO dao = new ProductDAOCollections();
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;

    @Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		// Slow down the robot a little bit - default is 30 (milliseconds).
		// Do NOT make it less than 10 or you will have thread-race problems.
		robot.settings().delayBetweenEvents(100);

		// add some majors for testing with
		Collection<String> category = new ArrayList<>();
		category.add("Knitting");
		category.add("Ninjitsu");

		// create a mock for the DAO
		dao = mock(ProductDAO.class);

		// stub the getMajors method to return the test majors
		when(dao.getCategories()).thenReturn(category);
	}

	@After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}
    
    
    
 //   @Before
 //   public void setUp() {
 //       this.prodOne = new Product("1", "name1", "cat1", "desc1",
  //              new BigDecimal("11.00"), new BigDecimal("22.00"));
 //       this.prodTwo = new Product("2", "name2", "cat2", "desc2",
//                new BigDecimal("33.00"), new BigDecimal("44.00"));
 //       this.prodThree = new Product("3", "name3", "cat3", "desc3",
//                new BigDecimal("55.00"), new BigDecimal("66.00"));
 //       // save the products
 //       dao.saveProduct(prodOne);
 //       dao.saveProduct(prodTwo);
        // Note: Intentionally not saving prodThree
//    }
    
 //   @After
  //  public void tearDown() {
  //      dao.removeProduct(prodOne);
   //     dao.removeProduct(prodTwo);
  //      dao.removeProduct(prodThree);
  //  }
        
//    @Test
//   public void testCategoryFilter() {
// Collection<Product> products = dao.categoryFilter("cat2");
// 
//       System.out.println(products);
// 
//        // ensure the result includes the two saved products
//        assertFalse("prodOne should exist", products.contains(prodOne));
//        assertTrue("prodTwo should exist", products.contains(prodTwo));
//        // ensure the result ONLY includes the two saved products
//        assertEquals("Only 1 products in result", 1, products.size());
//   }
//    
//    @Test
//    public void testSaveProduct() {
//        // save the product using the DAO
//        dao.saveProduct(prodThree);
//        // ensure that the data store includes the product
//        assertTrue("Ensure that the product was saved",
//                dao.displayProducts().contains(prodThree));
//    }

    @Test
	public void testSave() {
		// create the dialog passing in the mocked DAO
		DataEntryDialog dialog = new DataEntryDialog(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();

		// enter some details into the UI components
		fixture.textBox("txtId").enterText("1234");
		fixture.textBox("txtName").enterText("Jack");
		fixture.comboBox("comboCategory").selectItem("Knitting");
                fixture.textBox("txtPrice").enterText("1212");
                fixture.textBox("txtQuantity").enterText("1212");
                fixture.textBox("paneDescription").enterText("LOL");

		// click the save button
		fixture.button("buttonSave").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).saveProduct(argument.capture());

		// retrieve the passed student from the captor
		Product savedProduct = argument.getValue();

		// test that the student's details were properly saved
		assertEquals("Ensure the ID was saved", "1234", savedProduct.getProductID());
		assertEquals("Ensure the name was saved", "Jack", savedProduct.getName());
		assertEquals("Ensure the major was saved", "Knitting", savedProduct.getCategory());
	}
    
    
    
    
    
//    @Test
//    public void testDisplayProducts() {
//        Collection<Product> products = dao.displayProducts();
//        // ensure the result includes the two saved products
//        assertTrue("prodOne should exist", products.contains(prodOne));
//        assertTrue("prodTwo should exist", products.contains(prodTwo));
//        // ensure the result ONLY includes the two saved products
//        assertEquals("Only 2 products in result", 2, products.size());
//    }
//    @Test
//    public void testRemoveProduct() {
//        // sanity check to make sure prodOne does exist before we delete it
//        assertTrue("Ensure that the product does exist",
//                dao.displayProducts().contains(prodOne));
//        // delete the product via the DAO
//        dao.removeProduct(prodOne);
//        // ensure that the product no longer exists
//        assertFalse("Ensure that the product does not exist",
//                dao.displayProducts().contains(prodOne));
//
//    }
    
    
}
