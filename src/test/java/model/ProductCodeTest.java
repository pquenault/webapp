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
public class ProductCodeTest {
    
    public ProductCodeTest() {
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
     * Test of getProductCodeId method, of class ProductCode.
     */
    @Test
    public void testGetProductCodeId() {
        System.out.println("getProductCodeId");
        ProductCode instance = null;
        String expResult = "";
        String result = instance.getProductCodeId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscountCodeId method, of class ProductCode.
     */
    @Test
    public void testGetDiscountCodeId() {
        System.out.println("getDiscountCodeId");
        ProductCode instance = null;
        String expResult = "";
        String result = instance.getDiscountCodeId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class ProductCode.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        ProductCode instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
