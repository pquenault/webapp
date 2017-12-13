/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class PurchaseOrderTest {
    
    public PurchaseOrderTest() {
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
     * Test of getOrderNum method, of class PurchaseOrder.
     */
    @Test
    public void testGetOrderNum() {
        System.out.println("getOrderNum");
        PurchaseOrder instance = null;
        int expResult = 0;
        int result = instance.getOrderNum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerId method, of class PurchaseOrder.
     */
    @Test
    public void testGetCustomerId() {
        System.out.println("getCustomerId");
        PurchaseOrder instance = null;
        int expResult = 0;
        int result = instance.getCustomerId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductId method, of class PurchaseOrder.
     */
    @Test
    public void testGetProductId() {
        System.out.println("getProductId");
        PurchaseOrder instance = null;
        int expResult = 0;
        int result = instance.getProductId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity method, of class PurchaseOrder.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        PurchaseOrder instance = null;
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShippingCost method, of class PurchaseOrder.
     */
    @Test
    public void testGetShippingCost() {
        System.out.println("getShippingCost");
        PurchaseOrder instance = null;
        float expResult = 0.0F;
        float result = instance.getShippingCost();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSalesDate method, of class PurchaseOrder.
     */
    @Test
    public void testGetSalesDate() {
        System.out.println("getSalesDate");
        PurchaseOrder instance = null;
        String expResult = "";
        String result = instance.getSalesDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShippingDate method, of class PurchaseOrder.
     */
    @Test
    public void testGetShippingDate() {
        System.out.println("getShippingDate");
        PurchaseOrder instance = null;
        String expResult = "";
        String result = instance.getShippingDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFreightCompany method, of class PurchaseOrder.
     */
    @Test
    public void testGetFreightCompany() {
        System.out.println("getFreightCompany");
        PurchaseOrder instance = null;
        String expResult = "";
        String result = instance.getFreightCompany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
