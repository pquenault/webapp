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
public class ProductTest {
    
    public ProductTest() {
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
     * Test of getProductId method, of class Product.
     */
    @Test
    public void testGetProductId() {
        System.out.println("getProductId");
        Product instance = null;
        int expResult = 0;
        int result = instance.getProductId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getManufacturerId method, of class Product.
     */
    @Test
    public void testGetManufacturerId() {
        System.out.println("getManufacturerId");
        Product instance = null;
        int expResult = 0;
        int result = instance.getManufacturerId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductCodeId method, of class Product.
     */
    @Test
    public void testGetProductCodeId() {
        System.out.println("getProductCodeId");
        Product instance = null;
        String expResult = "";
        String result = instance.getProductCodeId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPurchaseCost method, of class Product.
     */
    @Test
    public void testGetPurchaseCost() {
        System.out.println("getPurchaseCost");
        Product instance = null;
        float expResult = 0.0F;
        float result = instance.getPurchaseCost();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantityOnHand method, of class Product.
     */
    @Test
    public void testGetQuantityOnHand() {
        System.out.println("getQuantityOnHand");
        Product instance = null;
        int expResult = 0;
        int result = instance.getQuantityOnHand();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMarkup method, of class Product.
     */
    @Test
    public void testGetMarkup() {
        System.out.println("getMarkup");
        Product instance = null;
        float expResult = 0.0F;
        float result = instance.getMarkup();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailable method, of class Product.
     */
    @Test
    public void testGetAvailable() {
        System.out.println("getAvailable");
        Product instance = null;
        String expResult = "";
        String result = instance.getAvailable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Product.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Product instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
