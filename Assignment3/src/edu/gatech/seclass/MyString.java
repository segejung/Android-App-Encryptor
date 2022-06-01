package edu.gatech.seclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.String;

public class MyString implements MyStringInterface {

    private String str = null;
    /**
     * Returns the current string.
     * If the string has not been initialized with method setString, it should return null.
     *
     * @return Current string
     */
    public String getString() {
        return this.str;
    }

    /**
     * Sets the value of the current string
     *
     * @param string The value to be set
     * @throws IllegalArgumentException If string is equal to the interface variable `easterEgg`
     */
    @Override
    public void setString(String string) {
        this.str = string;
        // Illegal argument
        if(string == "easterEgg") {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the number of alphabetic words in the current string, where a
     * "alphabetic word" is a contiguous sequence of alphabetic characters [a-zA-Z].
     *
     * If the current string is empty, the method should return 0.
     *
     * Examples:
     * - countAlphabeticWords would return 5 for string "My numbers are 11, 96, and thirteen".
     * - countAlphabeticWords would return 4 for string "i#love 2 pr00gram.".
     *
     * @return Number of strings in the current string
     * @throws NullPointerException     If the current string is null
     */
    @Override
    public int countAlphabeticWords() {

        //throw nullpointer
        if(this.str == null) {
            throw new NullPointerException();
        }
        //return 0 if current string is empty
        if(this.str == "") {
            return 0;
        }
        //counts words
        String[] words = str.split("[^A-Za-z]+");
        return words.length;

    }

    /**
     * Returns a string equivalent to the original string with n added to every integer number in
     * the string, where an integer number is defined as a contiguous sequence of digits, and signs
     * are ignored (i.e., all integers are considered to be positive).  All leading zeros should be
     * discarded.
     *
     * If 'flip' is true, the order of the digits within every number will be reversed (before adding n).
     * If 'flip' is false, the digits will remain in their original order within the string.
     *
     * Examples:
     * - For n=2 and flip=false, "hello 90, bye 2" would be converted to "hello 92, bye 4".
     * - For n=10 and flip=false, "-12345" would be converted to "-12355".
     * - For n=0 and flip=true, "12345" would be converted to "54321".
     * - For n=8 and flip=true, "hello 90, bye 2" would be converted to "hello 17, bye 10".
     * - For n=100 and flip=true, "hello 2022, bye 2000" would be converted to "hello 2302, bye 102".
     *
     * @param n amount to add to every number
     * @param flip Boolean that indicates whether digits within a number should be reversed
     * @return String with n added to every number in the string, with the number reversed, if indicated
     * @throws NullPointerException     If the current string is null
     * @throws IllegalArgumentException If n is negative, and the current string is not null
     */
    @Override
    public String addNumber(int n, boolean flip) {

        //throw nullpointer
        if(this.str == null) {
            throw new NullPointerException();
        }
        // Illegal argument
        if(n < 0 && this.str != null) {
            throw new IllegalArgumentException();
        }

        String newString = "";
        //if flip is true
        if (flip) {
            for (int i = 0; i < this.str.length(); i++) {
                char temp = this.str.charAt(i);
                String temp_1="";
                if (Character.isDigit(temp)) {
                    temp_1 = Integer.toString(((Character.getNumericValue(temp)) + n)%10);
                }
                else{
                    temp_1 = Character.toString(temp);
                }
                newString +=  temp_1;
            }
        }else {
            for (int i = 0; i < this.str.length(); i++) {
                char temp = this.str.charAt(i);
                String temp_1="";
                if (Character.isDigit(temp)) {
                    temp_1 = Integer.toString(((Character.getNumericValue(temp)) - n + 10)%10);
                }
                else{
                    temp_1 = Character.toString(temp);
                }
                newString +=  temp_1;
            }
        }
        return newString;
    }



    @Override
    public void convertDigitsToNamesInSubstring(int firstPosition, int finalPosition) {

    }

}
