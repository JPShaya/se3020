/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmarket;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jean-Paul Jacques <jjac403@cse.unsw.edu.au>
 */
public class DataVerifyTest {

    public DataVerifyTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of csvReader method, of class DataVerify.
     */
    @Test
    public void testCsvReader() throws Exception {
        System.out.println("csvReader");
        File file = null;
        DataVerify instance = new DataVerify();
        instance.csvReader(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkSecurity method, of class DataVerify.
     */
    @Test
    public void testCheckSecurity() throws Exception {
        System.out.println("checkSecurity");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkSecurity(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDate method, of class DataVerify.
     */
    @Test
    public void testCheckDate() throws Exception {
        System.out.println("checkDate");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkDate(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTime method, of class DataVerify.
     */
    @Test
    public void testCheckTime() throws Exception {
        System.out.println("checkTime");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkTime(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkStatus method, of class DataVerify.
     */
    @Test
    public void testCheckStatus() throws Exception {
        System.out.println("checkStatus");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkStatus(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrice method, of class DataVerify.
     */
    @Test
    public void testCheckPrice() throws Exception {
        System.out.println("checkPrice");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkPrice(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkVolume method, of class DataVerify.
     */
    @Test
    public void testCheckVolume() throws Exception {
        System.out.println("checkVolume");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkVolume(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkValue method, of class DataVerify.
     */
    @Test
    public void testCheckValue() throws Exception {
        System.out.println("checkValue");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkValue(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTrans method, of class DataVerify.
     */
    @Test
    public void testCheckTrans() throws Exception {
        System.out.println("checkTrans");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkTrans(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkBid method, of class DataVerify.
     */
    @Test
    public void testCheckBid() throws Exception {
        System.out.println("checkBid");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkBid(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAsk method, of class DataVerify.
     */
    @Test
    public void testCheckAsk() throws Exception {
        System.out.println("checkAsk");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkAsk(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkType method, of class DataVerify.
     */
    @Test
    public void testCheckType() throws Exception {
        System.out.println("checkType");
        String token = "";
        int lineNum = 0;
        DataVerify instance = new DataVerify();
        instance.checkType(token, lineNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}