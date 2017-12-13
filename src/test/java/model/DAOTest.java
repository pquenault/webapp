/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class DAOTest {
    
    public DAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of authenticateUser method, of class DAO.
     */
    @Test
    public void testAuthenticateUser() throws Exception {
        System.out.println("authenticateUser");
        String email = "";
        String customerId = "";
        DAO instance = null;
        String expResult = "";
        String result = instance.authenticateUser(email, customerId);
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
        String productId = "";
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
