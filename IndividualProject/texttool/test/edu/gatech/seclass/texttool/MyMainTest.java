package edu.gatech.seclass.texttool;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;


public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file =  createTmpFile();

        OutputStreamWriter fileWriter =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        fileWriter.write(input);

        fileWriter.close();
        return file;
    }

    //Read File Utility
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /*
     * TEST FILE CONTENT
     */
    private static final String FILE0 = "";
    private static final String FILE1 = "abc tuvw.XYZ";
    private static final String FILE2 = "Howdy Billy, are you going to take cs6300!!!";
    private static final String FILE3 = "abcXYZ123ABCxyz";
    private static final String FILE4 = "abc123ABC#@!?";


    // test cases
    //1
    // Purpose: input filename is invalid  : texttool
    // Frame #: 1
    @Test
    public void texttoolTest1(){
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-f [string]] [-m string] [-f] [-i|-I] [-o] <filename>", errStream.toString().trim());
    }

    //2
    // Purpose: <Throw an error if there is no string after -r>
    // Frame #: 3
    @Test
    public void texttoolTest2() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-r", inputFile.getPath()};
        Main.main(args);

        assertEquals("Usage: texttool [-f [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //3
    // Purpose: <Throw an error if there is no string after -k>
    // Frame #: 4
    @Test
    public void texttoolTest3() throws Exception {
        File inputFile = createInputFile(FILE1);

        String args[] = {"-k", inputFile.getPath()};
        Main.main(args);

        assertEquals("Usage: texttool [-f [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //4
    //Purpose: test invalid opts 1
    //Frame #: 2
    @Test
    public void texttoolTest5() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"    ", "             ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: texttool [-f [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //5
    //Purpose: test invalid opts 2
    //Frame #: 2
    @Test
    public void texttoolTest6() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-f","      ","-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: texttool [-f [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());

    }

    //6
    //Purpose: test invalid opts 3
    //Frame #: 2
    @Test
    public void texttoolTest7() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-f","-c","     ", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: texttool [-f [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //7
    //Purpose: test invalid opts 4
    //Frame #: 2
    @Test
    public void texttoolTest8() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","-f","      ","-r", inputFile.getPath()};
        Main.main(args);

    }

    //8
    //Purpose: test invalid opts 5
    //Frame #: 3
    @Test
    public void texttoolTest9() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-k","-f","-c","     ", inputFile.getPath()};
        assertEquals("Usage: texttool [-f [string]] [-r string | -k string] [-c] <filename>", errStream.toString().trim());
    }

    //9
    // Purpose: test the input file size that is 0
    // Frame #: 5
    @Test
    public void texttoolTest10() throws Exception {
        File inputFile = createInputFile(FILE0);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String expected = "";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //10
    // Purpose: test without OPT (default to -f and use all non-alphabetic characters) : texttool file1.txt
    // Frame #: 46
    @Test
    public void texttoolTest11() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String expected = "ydwoH ylliB, era uoy gniog ot ekat sc6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //11
    // Purpose: test -f without delimiter : texttool -f file1.txt
    // Frame #: 6
    @Test
    public void texttoolTest12() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-f", inputFile.getPath()};
        Main.main(args);
        String expected = "ydwoH ylliB, era uoy gniog ot ekat !!!0036sc";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //12
    // Purpose: test "-f" with a single character delimiter : texttool -f 'o' file1.txt
    // Frame #: 13
    @Test
    public void texttoolTest13() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-f","o", inputFile.getPath()};
        Main.main(args);
        String expected = "Hoydw Billy, are you gogni to take cs6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //13
    // Purpose: test single argument and option with "-f" but many character delimiters : texttool -f 'il' file1.txt
    // Frame #: 13
    @Test
    public void texttoolTest14() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-f","il", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bilyl, are you going to take cs6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //14
    // Purpose: test single argument and option with "-r" with string : texttool -r 'a' file1.txt
    // Frame #: 21
    @Test
    public void texttoolTest15() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-r","a", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Billy, re you going to tke cs6300!!!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //15
    // Purpose: test single argument and option with "-r" with multiple string : texttool -r 'Ve' file1.txt
    // Frame #: 21
    @Test
    public void texttoolTest16() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-r","aY", inputFile.getPath()};
        Main.main(args);
        String expected = "bcXZ123BCxz";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //16
    // Purpose: test single argument and option with "-r" but with string only contains both digits and letters : texttool -m "9XY8" file1.txt
    // Frame #: 21
    @Test
    public void texttoolTest17() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-r","1X2", inputFile.getPath()};
        Main.main(args);
        String expected = "abcYZ123ABCyz";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //17
    // Purpose: test single argument and option with "-r" but with string  contains other characters : texttool -r "$LAN1991" file1.txt
    // Frame #: 21
    @Test
    public void texttoolTest18() throws Exception {
        File inputFile1 = createInputFile(FILE4);
        String args[] = {"-r","@Ab12", inputFile1.getPath()};
        Main.main(args);
        String expected = "c123C#@!?";
        String actual = getFileContent(inputFile1.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //18
    // Purpose: test single argument and option with "-k" with string : texttool -k 'a' file1.txt
    // Frame #: 29
    @Test
    public void texttoolTest19() throws Exception {
        File inputFile = createInputFile(FILE4);
        String args[] = {"-k","a", inputFile.getPath()};
        Main.main(args);
        String expected = "a123#@!?";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //19
    // Purpose: test single argument and option with "-k" with multiple string : texttool -k 'Ve' file1.txt
    // Frame #: 29
    @Test
    public void texttoolTest20() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-k","aX", inputFile.getPath()};
        Main.main(args);
        String expected1 = "aX123Ax";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //20
    // Purpose: test single argument and option with "-k" but with string only contains both digits and letters : texttool -k "9XY8" file1.txt
    // Frame #: 29
    @Test
    public void texttoolTest21() throws Exception {
        File inputFile = createInputFile(FILE3);
        String args[] = {"-k","1XY3", inputFile.getPath()};
        Main.main(args);
        String expected = "XY123xy";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual1);
    }

    //21
    // Purpose: test single argument and option with "-k" but with string  contains other characters : texttool -k "$LAN1991" file1.txt
    // Frame #: 29
    @Test
    public void texttoolTest22() throws Exception {
        File inputFile = createInputFile(FILE4);
        String args[] = {"-k", "?A", inputFile.getPath()};
        Main.main(args);
        String expected = "a123A#@!?";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    //22
    // Purpose: test single argument and option : texttool -c file1.txt
    // Frame #: 39
    @Test
    public void texttoolTest23() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "hOWDY bILLY, ARE YOU GOING TO TAKE CS6300!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    //23
    // Purpose: test two argument and option : texttool -f -c file1.txt
    // Frame #: 44
    @Test
    public void texttoolTest24() throws Exception {
        File inputFile = createInputFile(FILE2);
        String args[] = {"-f","c", inputFile.getPath()};
        Main.main(args);
        String expected1 = "YDWOh YLLIb, ERA UOY GNIOG OT EKAT SC6300!!!";
        String actual1 = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected1, actual1);
    }

    @Test
    public void texttoolTest25() throws Exception {
        //25
        String x = "25";
    }
    @Test
    public void texttoolTest26() throws Exception {
        //26
        String x = "26";
    }
    @Test
    public void texttoolTest27() throws Exception {
        //27
        String x = "27";
    }
    @Test
    public void texttoolTest28() throws Exception {
        String x = "28";
    }
    @Test
    public void texttoolTest29() throws Exception {
        String x = "29";
    }
    @Test
    public void texttoolTest30() throws Exception {
        String x = "30";
    }
    @Test
    public void texttoolTest31() throws Exception {
        String x = "31";
    }
    @Test
    public void texttoolTest32() throws Exception {
        String x = "32";
    }
    @Test
    public void texttoolTest33() throws Exception {
        String x = "33";
    }
    @Test
    public void texttoolTest34() throws Exception {
        String x = "34";
    }
    @Test
    public void texttoolTest35() throws Exception {
        String x = "35";
    }
    @Test
    public void texttoolTest36() throws Exception {
        String x = "36";
    }
    @Test
    public void texttoolTest37() throws Exception {
        String x = "37";
    }
    @Test
    public void texttoolTest38() throws Exception {
        String x = "38";
    }
    @Test
    public void texttoolTest39() throws Exception {
        String x = "39";
    }
    @Test
    public void texttoolTest40() throws Exception {
        String x = "40";
    }
    @Test
    public void texttoolTest41() throws Exception {
        String x = "41";
    }
    @Test
    public void texttoolTest42() throws Exception {
        String x = "42";
    }
    @Test
    public void texttoolTest43() throws Exception {
        String x = "43";
    }
    @Test
    public void texttoolTest44() throws Exception {
        String x = "44";
    }
    @Test
    public void texttoolTest45() throws Exception {
        String x = "45";
    }
    @Test
    public void texttoolTest46() throws Exception {
        String x = "46";
    }
    @Test
    public void texttoolTest47() throws Exception {
        String x = "47";
    }
    @Test
    public void texttoolTest48() throws Exception {
        String x = "48";
    }

    @Test
    public void texttoolTest49() throws Exception {
        String x = "49";
    }
    @Test
    public void texttoolTest50() throws Exception {
        String x = "50";
    }
    @Test
    public void texttoolTest51() throws Exception {
        String x = "51";
    }
    @Test
    public void texttoolTest52() throws Exception {
        String x = "52";
    }
    @Test
    public void texttoolTest53() throws Exception {
        String x = "53";
    }
    @Test
    public void texttoolTest54() throws Exception {
        String x = "54";
    }
    @Test
    public void texttoolTest55() throws Exception {
        String x = "55";
    }
    @Test
    public void texttoolTest56() throws Exception {
        String x = "56";
    }
    @Test
    public void texttoolTest57() throws Exception {
        String x = "57";
    }
    @Test
    public void texttoolTest58() throws Exception {
        String x = "58";
    }
    @Test
    public void texttoolTest59() throws Exception {
        String x = "59";
    }
    @Test
    public void texttoolTest60() throws Exception {
        String x = "60";
    }
    @Test
    public void texttoolTest61() throws Exception {
        String x = "61";
    }

    @Test
    public void texttoolTest62() throws Exception {
        String x = "62";
    }
    @Test
    public void texttoolTest63() throws Exception {
        String x = "63";
    }
    @Test
    public void texttoolTest64() throws Exception {
        String x = "64";
    }
    @Test
    public void texttoolTest65() throws Exception {
        String x = "65";
    }
    @Test
    public void texttoolTest66() throws Exception {
        String x = "66";
    }
    @Test
    public void texttoolTest67() throws Exception {
        String x = "67";
    }

    @Test
    public void texttoolTest68() throws Exception {
        String x = "68";
    }
    @Test
    public void texttoolTest69() throws Exception {
        String x = "69";
    }
    @Test
    public void texttoolTest70() throws Exception {
        String x = "70";
    }
    @Test
    public void texttoolTest71() throws Exception {
        String x = "71";
    }
    @Test
    public void texttoolTest72() throws Exception {
        String x = "72";
    }
    @Test
    public void texttoolTest73() throws Exception {
        String x = "73";
    }

}