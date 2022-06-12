package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

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
        for (int i = 0; i < inputText.length; i++) {
            char ch = inputText[i];
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
            HashMap<Integer, Character> pairing = new HashMap<>();
            /**
            pairing.put('A',0);
            pairing.put('B',1);
            pairing.put('C',2);
            pairing.put('D',3);
            pairing.put('E',4);
            pairing.put('F',5);
            pairing.put('G',6);
            pairing.put('H',7);
            pairing.put('I',8);
            pairing.put('J',9);
            pairing.put('K',10);
            pairing.put('L',11);
            pairing.put('M',12);
            pairing.put('N',13);
            pairing.put('O',14);
            pairing.put('P',15);
            pairing.put('Q',16);
            pairing.put('R',17);
            pairing.put('S',18);
            pairing.put('T',19);
            pairing.put('U',20);
            pairing.put('V',21);
            pairing.put('W',22);
            pairing.put('X',23);
            pairing.put('Y',24);
            pairing.put('Z',25);
             **/
            pairing.put(0,'a');
            pairing.put(1,'b');
            pairing.put(2,'c');
            pairing.put(3,'d');
            pairing.put(4,'e');
            pairing.put(5,'f');
            pairing.put(6,'g');
            pairing.put(7,'h');
            pairing.put(8,'i');
            pairing.put(9,'j');
            pairing.put(10,'k');
            pairing.put(11,'l');
            pairing.put(12,'m');
            pairing.put(13,'n');
            pairing.put(14,'o');
            pairing.put(15,'p');
            pairing.put(16,'q');
            pairing.put(17,'r');
            pairing.put(18,'s');
            pairing.put(19,'t');
            pairing.put(20,'u');
            pairing.put(21,'v');
            pairing.put(22,'w');
            pairing.put(23,'x');
            pairing.put(24,'y');
            pairing.put(25,'z');
            pairing.put(26,'0');
            pairing.put(27,'1');
            pairing.put(28,'2');
            pairing.put(29,'3');
            pairing.put(30,'4');
            pairing.put(31,'5');
            pairing.put(32,'6');
            pairing.put(33,'7');
            pairing.put(34,'8');
            pairing.put(35,'9');

            if (inputText[i] == 'a' || inputText[i] == 'b' || inputText[i] == 'c' || inputText[i] == 'd' || inputText[i] == 'e' || inputText[i] == 'f' || inputText[i] == 'g' || inputText[i] == 'h' || inputText[i] == 'i' || inputText[i] == 'j' || inputText[i] == 'k' || inputText[i] == 'l' || inputText[i] == 'm' || inputText[i] == 'n' || inputText[i] == 'o' || inputText[i] == 'p' || inputText[i] == 'q' || inputText[i] == 'r' || inputText[i] == 's' || inputText[i] == 't' || inputText[i] == 'u' || inputText[i] == 'v' || inputText[i] == 'w' || inputText[i] == 'x' || inputText[i] == 'y' || inputText[i] == 'z')
            {
                inputText[i] = Character.toUpperCase(ch);
                cipher = cipher + (char) ((((finalvalue * (inputText[i] - 'A')) + finalvalue2) % 36) + 'A');
            }

            else if ((inputText[i] == 'A' || inputText[i] == 'B' || inputText[i] == 'C' || inputText[i] == 'D' || inputText[i] == 'E' || inputText[i] == 'F' || inputText[i] == 'G' || inputText[i] == 'H' || inputText[i] == 'I' || inputText[i] == 'J' || inputText[i] == 'K' || inputText[i] == 'L' || inputText[i] == 'M' || inputText[i] == 'N' || inputText[i] == 'O' || inputText[i] == 'P' || inputText[i] == 'Q' || inputText[i] == 'R' || inputText[i] == 'S' || inputText[i] == 'T' || inputText[i] == 'U' || inputText[i] == 'V' || inputText[i] == 'W' || inputText[i] == 'X' || inputText[i] == 'Y' || inputText[i] == 'Z'))
            {
                inputText[i] = Character.toLowerCase(ch);
                cipher = cipher + (char) ((((finalvalue * (inputText[i] - 'a')) + finalvalue2) % 36) + 'a');
            } else {
                cipher += inputText[i];
            }

        } return cipher;



    }

}