package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button encryptButtonID;
    private EditText entryTextID;
    private EditText argInput1ID;
    private EditText argInput2ID;
    private TextView textEncryptedID;
    private String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encryptButtonID = (Button) findViewById(R.id.encryptButtonID);
        entryTextID = (EditText) findViewById(R.id.entryTextID);
        argInput1ID = (EditText) findViewById(R.id.argInput1ID);
        argInput2ID = (EditText) findViewById(R.id.argInput2ID);
        textEncryptedID = (TextView) findViewById(R.id.textEncryptedID);
    }

    static boolean isPrime(int n) {
        if (n < 36 && n == 1 || n == 5 || n ==7 || n == 11 || n == 13 || n == 17 || n == 19 || n == 23 || n == 25 || n == 29 || n == 31 || n == 35) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isRange(int m) {
        if (m < 1 || m > 36) {
            return false;
        } else {
            return true;
        }
    }

    public void handleClick(View view) {
        //handles error messages
        //String answer = "";
        String value = entryTextID.getText().toString();
        String value2 = argInput1ID.getText().toString();
        String value3 = argInput2ID.getText().toString();
        String regex = "^.*[a-zA-Z0-9]+.*$";

        boolean result = value.matches(regex);


        if (isPrime(Integer.valueOf(value2)) == false) {
            argInput1ID.setError("Invalid Arg Input 1");
        }
        if (isRange(Integer.valueOf(value3)) == false) {
            argInput2ID.setError("Invalid Arg Input 2");
        }

        if (value.length() == 0) {
            entryTextID.setError("Invalid Entry Text");
        }
        if (value2.length() == 0) {
            argInput1ID.setError("Invalid Arg Input 1");
        }
        if (value3.length() == 0) {
            argInput2ID.setError("Invalid Arg Input 2");
        }

        if (result) {
        }
        //handles that there must be at least one letter or number
        else {
            entryTextID.setError("Invalid Entry Text");
        }
        if (result == true && value3.length() != 0 && value2.length() != 0 && value.length() != 0 && isRange(Integer.valueOf(value3)) == true && isPrime(Integer.valueOf(value2)) == true) {

            String answer = entryTextID.getText().toString();
            //removing all the special character in the string
            //answer = answer.replaceAll("[^a-zA-Z0-9]", "%");
            char[] inputText = answer.toCharArray();

            String answer2 = encryptMessage(inputText, argInput1ID.getText().toString(), argInput2ID.getText().toString());

            textEncryptedID.setText(answer2);
        } else {
            textEncryptedID.setText("");
        }

    }
    static String encryptMessage(char[] inputText, String arg1, String arg2)
    {
        int finalvalue = Integer.parseInt(arg1);
        int finalvalue2 = Integer.parseInt(arg2);
        String cipher = "";
        int xmen = 0;

            //if (Character.isUpperCase(ch)) {
            //    inputText[i] = Character.toLowerCase(ch);
            //}
            //else if (Character.isLowerCase(ch)) {
            //    inputText[i] = Character.toUpperCase(ch);
            //}
            /**
             if (inputText[i] != ' ') {
             cipher = cipher + (char) ((((finalvalue * (inputText[i] - 'A')) + finalvalue2) % 36) + 'A');
             } else {
             cipher += inputText[i];
             }
             **/

        String alphanumeric = "abcdefghijklmnopqrstuvwxyz0123456789";
        String alphanumeric2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        char[] upperArray = uppercase.toCharArray();
        char[] lowerArray = lowercase.toCharArray();
        char[] numberArray = number.toCharArray();
        char[] alphaArray = alphanumeric.toCharArray();
        char[] alphaArray2 = alphanumeric2.toCharArray();


        for (int i = 0; i < inputText.length; i++) {
            char ch = inputText[i];
            /**
            if (Character.isUpperCase(ch)) {
                inputText[i] = Character.toLowerCase(ch);
            } else {
                inputText[i] = Character.toLowerCase(ch);
            }
             **/

            if (new String(lowerArray).indexOf(inputText[i]) == -1) {
                int findValue = ((((finalvalue * (findIndex(alphaArray2, inputText[i]))) + finalvalue2) % 36));
                cipher = cipher + (char) Character.toLowerCase(alphanumeric2.charAt(findValue));
            } else if (new String(upperArray).indexOf(inputText[i]) == -1) {
                int findValue = ((((finalvalue * (findIndex(alphaArray, inputText[i]))) + finalvalue2) % 36));
                cipher = cipher + (char) Character.toUpperCase(alphanumeric.charAt(findValue));
            } else if (new String(numberArray).indexOf(inputText[i]) == -1) {
                int findValue = ((((finalvalue * (findIndex(alphaArray, inputText[i]))) + finalvalue2) % 36));
                cipher = cipher + (char) Character.toUpperCase(alphanumeric.charAt(findValue));
            }
            else {
                cipher += inputText[i];
            }





        } return cipher;

    }
    public static int findIndex(char arr[], int t)
    {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == t) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

}