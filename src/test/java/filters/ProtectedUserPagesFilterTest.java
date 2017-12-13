/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
public class ProtectedUserPagesFilterTest {
    
    public ProtectedUserPagesFilterTest() {
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
     * Test of doFilter method, of class ProtectedUserPagesFilter.
     */
    @Test
    public void testDoFilter() throws Exception {
        System.out.println("doFilter");
        ServletRequest request = null;
        ServletResponse response = null;
        FilterChain chain = null;
        ProtectedUserPagesFilter instance = new ProtectedUserPagesFilter();
        instance.doFilter(request, response, chain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class ProtectedUserPagesFilter.
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        FilterConfig filterConfig = null;
        ProtectedUserPagesFilter instance = new ProtectedUserPagesFilter();
        instance.init(filterConfig);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class ProtectedUserPagesFilter.
     */
    @Test
    public void testDestroy() {
        System.out.println("destroy");
        ProtectedUserPagesFilter instance = new ProtectedUserPagesFilter();
        instance.destroy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
