package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        /// Cipher Text initially empty
        String cipher = "";
        for (int i = 0; i < inputText.length; i++)
        {
            if (inputText[i] != ' ')
            {
                cipher = cipher + (char) ((((finalvalue * (inputText[i] - 'A')) + finalvalue2) % 36) + 'A');
            } else
            {
                cipher += inputText[i];
            }
        }
        return cipher;
    }

}