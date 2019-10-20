/**
 * @author baihu868
 * INFO202 2019
 * productStorageTest
 */

package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class productStorageTest {
    // DaoInterface dao = new ProductStorage();
     DaoInterface dao = new ProductDatabase(
            "jdbc:h2:mem:tests;INIT=runscript from 'src/main/resources/schema.sql'");   
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;

    public productStorageTest() {
    }

    @Before
    public void setUp() {

        this.prodOne = new Product("1", "name1", "cat1", "desc1", new BigDecimal("11.00"), new BigDecimal("22.00"));
        this.prodTwo = new Product("2", "name2", "cat2", "desc2", new BigDecimal("33.00"), new BigDecimal("44.00"));
        this.prodThree = new Product("3", "name3", "cat3", "desc3", new BigDecimal("55.00"), new BigDecimal("66.00"));
        dao.saveProduct(prodOne);
        dao.saveProduct(prodTwo);

    }

    @After
    public void tearDown() {
        dao.removeProduct(prodOne);
        dao.removeProduct(prodTwo);
        dao.removeProduct(prodThree);

    }

    @Test
    public void testSaveProduct() {
        dao.saveProduct(prodThree);
        assertTrue("Ensure that the produyct was saved",
                dao.returnProducts().contains(prodThree));
    }

    @Test
    public void testReturnProducts() {
        Collection<Product> productList = dao.returnProducts();
        // ensure the result includes the two saved products
        assertTrue("prodOne should exist", productList.contains(prodOne));
        assertTrue("prodTwo should exist", productList.contains(prodTwo));
        // ensure the result ONLY includes the two saved products
        assertEquals("Only 2 products in result", 2, productList.size());
    }
    
    @Test
    public void testReturnCategories() {
        Collection<String> categoryList = dao.returnCategories();
        // ensure the result includes the two saved products
        assertTrue("prodOne category should exist", categoryList.contains(prodOne.getCategory()));
        // ensure the result ONLY includes the two saved products
    }
    
    @Test
    public void testReturnSearch() {
        assertEquals("prodOne ID should exist", dao.searchProducts(prodOne.getProductID()), (prodOne));
        assertEquals("prodOne ID should exist", dao.searchProducts("hello"), null);
       
    }
    
    @Test
    public void testReturnProductCategory() {
        assertTrue("prodOne category should exist", dao.returnCategoryProducts(prodOne.getCategory()).contains(prodOne));
        assertFalse("prodOne category should not exist", dao.returnCategoryProducts("hello").contains(prodOne));
    }
    
    @Test
    public void testRemoveProduct() {
        // sanity check to make sure prodOne does exist before we delete it
        assertTrue("Ensure that the product does exist",
                dao.returnProducts().contains(prodOne));
        // delete the product via the DAO
        dao.removeProduct(prodOne);
        // ensure that the product no longer exists
        assertFalse("Ensure that the product does not exist",
                dao.returnProducts().contains(prodOne));
    }
}
