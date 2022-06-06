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
    // Description: Second test for count number with special characters
    public void testCountAlphabeticWords2() {
        mystring.setString("test#with sp@cial ch@rac^ters");
        assertEquals(7, mystring.countAlphabeticWords());
    }

    @Test
    // Description: Third test for counter number with capitalization
    public void testCountAlphabeticWords3() {
        mystring.setString("I HA Dan INTERVIEW to day");
        assertEquals(6, mystring.countAlphabeticWords());
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
    // Description: Second add number test with no flip and adding to a negative number
    public void testAddNumber2() {
        mystring.setString("-11111");
        assertEquals("-11211", mystring.addNumber(100, false));
    }

    @Test
    // Description: Third add number test with flip with special character
    public void testAddNumber3() {
        mystring.setString("12#45");
        assertEquals("22#55", mystring.addNumber(1, true));
    }

    @Test
    // Description: Fourth add number test with flip with spaced numbers
    public void testAddNumber4() {
        mystring.setString("90, 2, 15");
        assertEquals("17, 10, 59", mystring.addNumber(8, true));
    }

    @Test
    // Description: Fifth add number test with zeros
    public void testAddNumber5() {
        mystring.setString("Zer0 2022, 3000");
        assertEquals("Zer100 2302, 103", mystring.addNumber(100, true));
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: Sixth add number test with words and numbers mixed
    public void testAddNumber6() {
        mystring.setString("Hell0 W0r1d");
        mystring.addNumber(-1, true);
    }

    @Test
    // Description: First convert digits example in the interface documentation
    public void testConvertDigitsToNamesInSubstring1() {
        mystring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mystring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mystring.getString());
    }
    @Test(expected=MyIndexOutOfBoundsException.class)
    // Description: Second convert digits example with full range
    public void testConvertDigitsToNamesInSubstring2() {
        mystring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mystring.convertDigitsToNamesInSubstring(1, 51);

    }
    @Test
    // Description: Third convert digits example testing the first position range
    public void testConvertDigitsToNamesInSubstring3() {
        mystring.setString("0123456789");
        mystring.convertDigitsToNamesInSubstring(1, 1);
        assertEquals("Zero123456789", mystring.getString());
    }

    @Test
    // Description: Fourth convert digits example testing special characters
    public void testConvertDigitsToNamesInSubstring4() {
        mystring.setString("0!23#56*89");
        mystring.convertDigitsToNamesInSubstring(2, 6);
        assertEquals("0!TwoThree#Five6*89", mystring.getString());
    }

    @Test
    // Description: Fifth convert digits example with words
    public void testConvertDigitsToNamesInSubstring5() {
        mystring.setString("hello there 153, your place is here");
        mystring.convertDigitsToNamesInSubstring(2, 14);
        assertEquals("hello there OneFive3, your place is here", mystring.getString());
    }

    @Test
    // Description: Sixth convert digits example with final position range
    public void testConvertDigitsToNamesInSubstring6() {
        mystring.setString("0123456789");
        mystring.convertDigitsToNamesInSubstring(10, 10);
        assertEquals("012345678Nine", mystring.getString());
    }
}
