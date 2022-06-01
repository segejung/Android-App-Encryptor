package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 *
 * This class is provided to interpret your grades via junit tests
 * and as a reminder, should NOT be posted in any public repositories,
 * even after the class has ended.
 *
 */

public class MyStringTest {

    private MyStringInterface mystring;

    @Before
    public void setUp() {
        mystring = new MyString();
    }

    @After
    public void tearDown() {
        mystring = null;
    }

    @Test
    // Description: First count number example in the interface documentation
    public void testCountAlphabeticWords1() {
        mystring.setString("My numbers are 11, 96, and thirteen");
        assertEquals(5, mystring.countAlphabeticWords());
    }

    @Test
    // Description: Second test for count number with numbers and special characters
    public void testCountAlphabeticWords2() {
        mystring.setString("i#love 2 pr00gram.");
        assertEquals(4, mystring.countAlphabeticWords());
    }

    @Test
    // Description: Third test for counter number with special character and capitalization
    public void testCountAlphabeticWords3() {
        mystring.setString("I hadan INTERVIEW to-day !");
        assertEquals(5, mystring.countAlphabeticWords());
    }

    @Test
    // Description: Fourth test for counter number with empty string
    public void testCountAlphabeticWords4() {
        mystring.setString("");
        assertEquals(0, mystring.countAlphabeticWords());
    }

    @Test
    // Description: First add number example in the interface documentation
    public void testAddNumber1() {
        mystring.setString("hello 90, bye 2");
        assertEquals("hello 92, bye 4", mystring.addNumber(2, false));
    }

    @Test
    // Description: <Add test description here>
    public void testAddNumber2() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testAddNumber3() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testAddNumber4() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testAddNumber5() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testAddNumber6() {
        fail("Not yet implemented");
    }

    @Test
    // Description: First convert digits example in the interface documentation
    public void testConvertDigitsToNamesInSubstring1() {
        mystring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mystring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mystring.getString());
    }

    @Test
    // Description: <Add test description here>
    public void testConvertDigitsToNamesInSubstring2() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testConvertDigitsToNamesInSubstring3() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testConvertDigitsToNamesInSubstring4() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testConvertDigitsToNamesInSubstring5() {
        fail("Not yet implemented");
    }

    @Test
    // Description: <Add test description here>
    public void testConvertDigitsToNamesInSubstring6() {
        fail("Not yet implemented");
    }
}
