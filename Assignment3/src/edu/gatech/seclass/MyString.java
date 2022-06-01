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
/**
        char[] string = str.toCharArray();
        boolean inWord = false;
        int words = 0;
        for (char c : string) {
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                if (!inWord) {
                    words++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }
        return words;
**/

    }

    @Override
    public String addNumber(int n, boolean flip) {
        return null;
    }

    @Override
    public void convertDigitsToNamesInSubstring(int firstPosition, int finalPosition) {

    }

}
