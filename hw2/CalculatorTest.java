import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Test
    public void setUp() {
    // tester = new StaffCalculator(); // Comment me out to test your Calculator
     tester = new Calculator();   // Un-comment me to test your Calculator
    //  assertEquals(tester.add(0,0),0);
    //  assertEquals(tester.add(10, 20),30);
      assertEquals(tester.add(11,22),33);
      assertEquals(tester.add(123, 234),357);
      assertEquals(tester.add(-2, 3),1);
      assertEquals(tester.add(3,5),8);
      
      assertEquals(tester.multiply(10, 10),100);
    }

    // TASK 1: WRITE JUNIT TESTS
    // YOUR CODE HERE

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}
