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
public class DiscountCodeTest {
    
    public DiscountCodeTest() {
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
     * Test of getDiscountCodeId method, of class DiscountCode.
     */
    @Test
    public void testGetDiscountCodeId() {
        System.out.println("getDiscountCodeId");
        DiscountCode instance = null;
        String expResult = "";
        String result = instance.getDiscountCodeId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRate method, of class DiscountCode.
     */
    @Test
    public void testGetRate() {
        System.out.println("getRate");
        DiscountCode instance = null;
        float expResult = 0.0F;
        float result = instance.getRate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
