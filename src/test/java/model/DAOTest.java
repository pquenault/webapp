/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import static model.DataSourceFactory.getDataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class DAOTest {
    
    private static DataSource myDataSource;
    private static Connection myConnection ;
	
    private DAO myDAO;
    
    public DAOTest() {
    }
    
    @Before
    public void setUp() throws IOException, SqlToolError, SQLException{
        // On crée la connection vers la base de test "in memory"
		myDataSource = getDataSource();
		myConnection = myDataSource.getConnection();
		// On crée le schema de la base de test
		executeSQLScript(myConnection, "export.sql");
            	myDAO = new DAO(myDataSource);
    }
    
    private void executeSQLScript(Connection connexion, String filename)  throws IOException, SqlToolError, SQLException {
		// On initialise la base avec le contenu d'un fichier de test
		String sqlFilePath = DAOTest.class.getResource(filename).getFile();
		SqlFile sqlFile = new SqlFile(new File(sqlFilePath));

		sqlFile.setConnection(connexion);
		sqlFile.execute();
		sqlFile.closeReader();		
	}
    
    @After
    public void tearDown() throws SQLException {
        myConnection.close();
    }

    /**
     * Test of authenticateUser method, of class DAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testAuthenticateUser() throws Exception {
        System.out.println("authenticateUser");
        String email = "jumboeagle@example.com";
        String customerId = "1";
        String expResult = "Jumbo Eagle Corp";
        String result = myDAO.authenticateUser(email, customerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscountCodes method, of class DAO.
     */
    @Test
    public void testGetDiscountCodes() throws Exception {
        System.out.println("getDiscountCodes");
        DAO instance = null;
        List<DiscountCode> expResult = null;
        List<DiscountCode> result = instance.getDiscountCodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomer method, of class DAO.
     */
    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("getCustomer");
        String customerId = "";
        DAO instance = null;
        Customer expResult = null;
        Customer result = instance.getCustomer(customerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerPurchaseOrders method, of class DAO.
     */
    @Test
    public void testGetCustomerPurchaseOrders() throws Exception {
        System.out.println("getCustomerPurchaseOrders");
        String customerId = "";
        DAO instance = null;
        List<PurchaseOrder> expResult = null;
        List<PurchaseOrder> result = instance.getCustomerPurchaseOrders(customerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPurchaseOrder method, of class DAO.
     */
    @Test
    public void testAddPurchaseOrder() throws Exception {
        System.out.println("addPurchaseOrder");
        int orderNum = 0;
        int customerId = 0;
        int productId = 0;
        int quantity = 0;
        float shippingCost = 0.0F;
        String salesDate = "";
        String shippingDate = "";
        String freightCompany = "";
        DAO instance = null;
        int expResult = 0;
        int result = instance.addPurchaseOrder(orderNum, customerId, productId, quantity, shippingCost, salesDate, shippingDate, freightCompany);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePurchaseOrder method, of class DAO.
     */
    @Test
    public void testDeletePurchaseOrder() throws Exception {
        System.out.println("deletePurchaseOrder");
        String orderNum = "";
        String quantity = "";
        String productId = "";
        DAO instance = null;
        int expResult = 0;
        int result = instance.deletePurchaseOrder(orderNum, quantity, productId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextOrderNum method, of class DAO.
     */
    @Test
    public void testNextOrderNum() throws Exception {
        System.out.println("nextOrderNum");
        DAO instance = null;
        int expResult = 0;
        int result = instance.nextOrderNum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductQuantity method, of class DAO.
     */
    @Test
    public void testGetProductQuantity() throws Exception {
        System.out.println("getProductQuantity");
        String productId = "980001";
        DAO instance = null;
        int expResult = 0;
        int result = instance.getProductQuantity(productId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProductQuantity method, of class DAO.
     */
    @Test
    public void testUpdateProductQuantity() throws Exception {
        System.out.println("updateProductQuantity");
        int quantity = 0;
        String productId = "";
        DAO instance = null;
        int expResult = 0;
        int result = instance.updateProductQuantity(quantity, productId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProducts method, of class DAO.
     */
    @Test
    public void testGetProducts() throws Exception {
        System.out.println("getProducts");
        DAO instance = null;
        List<Product> expResult = null;
        List<Product> result = instance.getProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductCodes method, of class DAO.
     */
    @Test
    public void testGetProductCodes() throws Exception {
        System.out.println("getProductCodes");
        DAO instance = null;
        List<ProductCode> expResult = null;
        List<ProductCode> result = instance.getProductCodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salesByCustomer method, of class DAO.
     */
    @Test
    public void testSalesByCustomer() throws Exception {
        System.out.println("salesByCustomer");
        DAO instance = null;
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.salesByCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salesByState method, of class DAO.
     */
    @Test
    public void testSalesByState() throws Exception {
        System.out.println("salesByState");
        DAO instance = null;
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.salesByState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salesByCategory method, of class DAO.
     */
    @Test
    public void testSalesByCategory() throws Exception {
        System.out.println("salesByCategory");
        DAO instance = null;
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.salesByCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
