package edu.gatech.seclass;

import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (string == "easterEgg") {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the number of alphabetic words in the current string, where a
     * "alphabetic word" is a contiguous sequence of alphabetic characters [a-zA-Z].
     * <p>
     * If the current string is empty, the method should return 0.
     * <p>
     * Examples:
     * - countAlphabeticWords would return 5 for string "My numbers are 11, 96, and thirteen".
     * - countAlphabeticWords would return 4 for string "i#love 2 pr00gram.".
     *
     * @return Number of strings in the current string
     * @throws NullPointerException If the current string is null
     */
    @Override
    public int countAlphabeticWords() {

        //throw nullpointer
        if (this.str == null) {
            throw new NullPointerException();
        }
        //return 0 if current string is empty
        if (this.str == "") {
            return 0;
        }
        //counts words
        String[] words = str.split("[A-Za-z]+");
        return words.length;

    }

    /**
     * Returns a string equivalent to the original string with n added to every integer number in
     * the string, where an integer number is defined as a contiguous sequence of digits, and signs
     * are ignored (i.e., all integers are considered to be positive).  All leading zeros should be
     * discarded.
     * <p>
     * If 'flip' is true, the order of the digits within every number will be reversed (before adding n).
     * If 'flip' is false, the digits will remain in their original order within the string.
     * <p>
     * Examples:
     * - For n=2 and flip=false, "hello 90, bye 2" would be converted to "hello 92, bye 4".
     * - For n=10 and flip=false, "-12345" would be converted to "-12355".
     * - For n=0 and flip=true, "12345" would be converted to "54321".
     * - For n=8 and flip=true, "hello 90, bye 2" would be converted to "hello 17, bye 10".
     * - For n=100 and flip=true, "hello 2022, bye 2000" would be converted to "hello 2302, bye 102".
     *
     * @param n    amount to add to every number
     * @param flip Boolean that indicates whether digits within a number should be reversed
     * @return String with n added to every number in the string, with the number reversed, if indicated
     * @throws NullPointerException     If the current string is null
     * @throws IllegalArgumentException If n is negative, and the current string is not null
     */
    @Override
    public String addNumber(int n, boolean flip) {

        //throw nullpointer
        if (this.str == null) {
            throw new NullPointerException();
        }
        // Illegal argument
        if (n < 0 && this.str != null) {
            throw new IllegalArgumentException();
        }

        if (flip) {
            StringBuilder sb = new StringBuilder();
            Matcher m = Pattern.compile("\\d+").matcher(str);
            int pos = 0;
            while (m.find()) {
                int start = m.start();
                String reversed = new StringBuilder(m.group()).reverse().toString();
                int number = Integer.parseInt(reversed); //this is to convert string to int
                int newnumber = number + n; //this is to add the int with n
                sb.append(str.substring(pos, start)).append(newnumber); //append with the rest of the string
                pos = m.end();
            }
            if (sb.length() == 0) // If no number was found
                return str;
            else
                return sb.append(str.substring(pos)).toString();
        } else {
            StringBuilder sb = new StringBuilder();
            Matcher m = Pattern.compile("\\d+").matcher(str);
            int pos = 0;
            while (m.find()) {
                int start = m.start();
                String reversed = new StringBuilder(m.group()).toString();
                int number = Integer.parseInt(reversed);
                int newnumber = number + n;
                sb.append(str.substring(pos, start)).append(newnumber);
                pos = m.end();
            }
            if (sb.length() == 0) // If no number was found
                return str;
            else
                return sb.append(str.substring(pos)).toString();
        }
    }

    /**
     * Replace the individual digits in the current string, between firstPosition and finalPosition
     * (included), with the corresponding name (i.e., string representation) of those digits.
     * The first character in the string is considered to be in Position 1.
     * <p>
     * Examples:
     * - String "I'd b3tt3r put s0me d161ts in this 5tr1n6, right?", with firstPosition=17 and finalPosition=23 would be
     * converted to "I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?"
     * - String "abc416d", with firstPosition=2 and finalPosition=7 would be converted to "abcFourOneSixd"
     *
     * @param firstPosition Position of the first character to consider
     * @param finalPosition Position of the last character to consider
     * @throws NullPointerException        If the current string is null
     * @throws IllegalArgumentException    If "firstPosition" < 1 or "firstPosition" > "finalPosition" (and the string
     *                                     is not null)
     * @throws MyIndexOutOfBoundsException If "finalPosition" is out of bounds (i.e., greater than the length of the
     *                                     string), 1 <= "firstPosition" <= "finalPosition", and the string is not null
     */
    @Override
    public void convertDigitsToNamesInSubstring(int firstPosition, int finalPosition) {


        //throw nullpointer
        if (this.str == null) {
            throw new NullPointerException();
        }
        // Illegal argument
        if (firstPosition < 1 || firstPosition > finalPosition && this.str != null) {
            throw new IllegalArgumentException();
        }
        //my index exception
        if (firstPosition < 1 || finalPosition > this.str.length()) {
            throw new MyIndexOutOfBoundsException();
        }
        String shortStr = str.substring(firstPosition - 1, finalPosition);
        //replace digits to words
        shortStr = shortStr.replaceAll("0", "Zero").replaceAll("1", "One").replaceAll("2", "Two").replaceAll("3", "Three").replaceAll("4", "Four").replaceAll("5", "Five").replaceAll("6", "Six").replaceAll("7", "Seven").replaceAll("8", "Eight").replaceAll("9", "Nine");
        //append the string together
        this.str = str.substring(0, firstPosition - 1) + shortStr + str.substring(finalPosition);

    }
}



