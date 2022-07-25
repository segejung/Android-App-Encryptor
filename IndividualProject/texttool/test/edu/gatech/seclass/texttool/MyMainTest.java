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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyMainTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();
    private final Charset charset = StandardCharsets.UTF_8;
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;

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

    /*
     *  TEST UTILITIES
     */

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file = createTmpFile();
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        fileWriter.write(input);
        fileWriter.close();
        return file;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = Files.readString(Paths.get(filename), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // test cases
    //1
    // Purpose: input filename is invalid  : texttool
    // Frame #: 1
    @Test
    public void texttoolTest1() throws Exception {
        String input = "alphanumeric123foobar!" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-f", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest2() throws Exception {
        String input = "alphanumeric123foobar123" + System.lineSeparator();

        String expected = "alphanumeric456foobar123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "123", "456", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest3() throws Exception {
        String input = "alphanumeric123FOObar123" + System.lineSeparator();

        String expected = "alphanumeric123candybar123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-i", "-r", "foo", "candy", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest4() throws Exception {
        String input = "alphanumeric123foobar" + System.lineSeparator();

        String expected = "##alphanumeric123foobar" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", "##", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest5() throws Exception {
        String input = "alphanumeric123foobar" + System.lineSeparator();

        String expected = "alphanumeric123foobar" + System.lineSeparator() +
                "alphanumeric123foobar" + System.lineSeparator() +
                "alphanumeric123foobar" + System.lineSeparator() +
                "alphanumeric123foobar" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "3", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest6() throws Exception {
        String input = "foobar0Foobar1" + System.lineSeparator() +
                "foobar2foobar3" + System.lineSeparator() +
                "foobar4Foobar5" + System.lineSeparator() +
                "foobar6foobar7" + System.lineSeparator() +
                "foobar8Foobar9" + System.lineSeparator();

        String expected = "!!!FOObar0Foobar1" + System.lineSeparator() +
                "!!!FOObar0Foobar1" + System.lineSeparator() +
                "!!!FOObar2foobar3" + System.lineSeparator() +
                "!!!FOObar2foobar3" + System.lineSeparator() +
                "!!!FOObar4Foobar5" + System.lineSeparator() +
                "!!!FOObar4Foobar5" + System.lineSeparator() +
                "!!!FOObar6foobar7" + System.lineSeparator() +
                "!!!FOObar6foobar7" + System.lineSeparator() +
                "!!!FOObar8Foobar9" + System.lineSeparator() +
                "!!!FOObar8Foobar9" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "foo", "FOO", "-f", "-p", "!!!", "-d", "1", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", expected, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest7() throws Exception {
        String input = "alphanumeric123foobar" + System.lineSeparator();

        String expected = "##grvngtaskxoi123luuhgx" + System.lineSeparator() +
                "##grvngtaskxoi123luuhgx" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-20", "-d", "5", "-d", "1", "-p", "!!!", "-p", "##", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest8() throws Exception {
        String input = "01234abc" + System.lineSeparator() +
                "56789def" + System.lineSeparator() +
                "01234ABC" + System.lineSeparator() +
                "56789DEF" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest9() throws Exception {
        String input = "alphanumeric123foobar" + System.lineSeparator();

        String expected = "bmqibovnfsjd123gppcbs" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "1", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    //2
    // Purpose: <Throw an error if there is no string after -r>
    // Frame #: 3
    @Test
    public void texttoolTest10() throws Exception {
        String input = "alphanumeric123foobar!" + System.lineSeparator();

        String expected = "abc" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-f", "abc", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest11() throws Exception {
        String input = "alphanumeric123foobar!" + System.lineSeparator();

        String expected = "alphanumeric123foobar!" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-o", "filename", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest12() throws Exception {
        String input = "alphanumeric123foobar!" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-o", "-f", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest13() throws Exception {
        String input = "alphanumeric123foobar!" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-f", "-o", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest14() throws Exception {
        String input = "alphanumeric123foobar!" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-f", "-o", "243", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest15() throws Exception {
        String input = "alphanumeric123foobar!" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-o", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest16() throws Exception {
        String input = "alphanumeric123foobar789" + System.lineSeparator();

        String expected = "alphanumeric789foobar789" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "123", "789", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest17() throws Exception {
        String input = "happy bday Joseph" + System.lineSeparator();

        String expected = "happy world Joseph" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "bday", "world", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }


    @Test
    public void texttoolTest18() throws Exception {
        String input = "happy bday Joseph" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "-c", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest19() throws Exception {
        String input = "happy bday Joseph" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-r", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest20() throws Exception {
        String input = "happy123bday123Joseph123" + System.lineSeparator();

        String expected = "happy321bday123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "123", "321", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest21() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        String expected = "happy123BDAY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "bDaY", "BDAY", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest22() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        String expected = "happy123sege123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-i", "-r", "bday", "sege", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest23() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        String expected = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "bday", "sege", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest24() throws Exception {
        String input = "happy bday Joseph" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-i", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest25() throws Exception {
        String input = "happy bday Joseph" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "-i", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest26() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        String expected = "!!thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", "!!", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest27() throws Exception {
        String input = "thisisgood" + System.lineSeparator() +
                "isgoodthis" + System.lineSeparator();

        String expected = "!!thisisgood" + System.lineSeparator() +
                "!!isgoodthis" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", "!!", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest28() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        String expected = "thisisgood" + System.lineSeparator() +
                "thisisgood" + System.lineSeparator() +
                "thisisgood" + System.lineSeparator() +
                "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "3", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest29() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "11", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest30() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-r", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest31() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "-c", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest32() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "-f", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest33() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "-o", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest34() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "-i", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest35() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", "-f", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest36() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", "-o", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest37() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", "-i", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest38() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-f", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest39() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-o", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest40() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-i", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest41() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "-f", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest42() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "-o", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest43() throws Exception {
        String input = "thisisgood" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "-i", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest44() throws Exception {
        String input = "alphanumeric123foobar" + System.lineSeparator();

        String expected = "!!!grvngtaskxoi123luuhgx" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-20", "-p", "!!!", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest45() throws Exception {
        String input = "alphanumeric123foobar" + System.lineSeparator();

        String expected = "2grvngtaskxoi123luuhgx" + System.lineSeparator() +
                "2grvngtaskxoi123luuhgx";

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-20", "-p", "2", "-d", "1", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest46() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "", "BDAY", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest47() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", "", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest48() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "0", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest49() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "x", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest50() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "!", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest51() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "26", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest52() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "-28", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest53() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "e", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest54() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "$", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest55() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-r", "-28", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest56() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-p", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest57() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest58() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest59() throws Exception {
        String input = "" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-f", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest60() throws Exception {
        String input = "hellobaby" + System.lineSeparator();

        String expected = "67hellobaby" + System.lineSeparator();
        File inputFile = createInputFile(input);
        String[] args = {"-f", "-p", "67", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", expected, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest61() throws Exception {
        String input = "hellobaby" + System.lineSeparator();

        String expected = "hellobaby" + System.lineSeparator() +
                "hellobaby" + System.lineSeparator();
        File inputFile = createInputFile(input);
        String[] args = {"-f", "-d", "1", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", expected, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest62() throws Exception {
        String input = "hellobaby" + System.lineSeparator();

        String expected = "&hellobaby" + System.lineSeparator() +
                "&hellobaby" + System.lineSeparator();
        File inputFile = createInputFile(input);
        String[] args = {"-f", "-d", "1", "-p", "&", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", expected, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest63() throws Exception {
        String input = "foothisisgoodfoo" + System.lineSeparator();

        String expected = "FOOthisisgoodfoo" + System.lineSeparator() +
                "FOOthisisgoodfoo" + System.lineSeparator() +
                "FOOthisisgoodfoo" + System.lineSeparator() +
                "FOOthisisgoodfoo" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "3", "-r", "foo", "FOO", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest64() throws Exception {
        String input = "FOOthisisgoodfoo" + System.lineSeparator();

        String expected = "FOOthisisgoodfoo" + System.lineSeparator() +
                "FOOthisisgoodfoo" + System.lineSeparator() +
                "FOOthisisgoodfoo" + System.lineSeparator() +
                "FOOthisisgoodfoo" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-d", "3", "-i", "-r", "foo", "FOO", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest65() throws Exception {
        String input = "alphanumeric#^foobar" + System.lineSeparator();

        String expected = "bmqibovnfsjd#^gppcbs" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "1", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }

    @Test
    public void texttoolTest66() throws Exception {
        String input = "ALphanumeric#^foobaR" + System.lineSeparator();

        String expected = "BMqibovnfsjd#^gppcbS" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "1", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest67() throws Exception {
        String input = "ALphanumeric#^foobaR" + System.lineSeparator();

        String expected = "BMqibovnfsjd#^gppcbS" + System.lineSeparator() +
                "BMqibovnfsjd#^gppcbS" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "1", "-d", "1", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest68() throws Exception {
        String input = "ALphanumeric#^foobaR" + System.lineSeparator();

        String expected = "0BMqibovnfsjd#^gppcbS" + System.lineSeparator() +
                "0BMqibovnfsjd#^gppcbS" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "1", "-d", "1", "-p", "0", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest69() throws Exception {
        String input = "ALphanumeric#^foobaR" + System.lineSeparator();

        String expected = "0BMqibovnfsjd#^gppcbS" + System.lineSeparator() +
                "0BMqibovnfsjd#^gppcbS" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-c", "4", "-c", "1", "-d", "1", "-p", "0", inputFile.getPath()};
        Main.main(args);

        assertTrue("stderr output should be empty", errStream.toString().isEmpty());
        assertEquals("stdout output does not match", expected, outStream.toString());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest70() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-i", "-c", "2", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest71() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-i", "-d", "1", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest72() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-i", "-p", "34", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
    @Test
    public void texttoolTest73() throws Exception {
        String input = "happy123bDaY123Joseph123" + System.lineSeparator();

        File inputFile = createInputFile(input);
        String[] args = {"-i", "-o", inputFile.getPath()};
        Main.main(args);

        assertEquals("stderr output does not match", "Usage: texttool [ -f | -o output_file_name | -i | -r old new | -p prefix | -c n | -d n ] FILE", errStream.toString().strip());
        assertTrue("stdout output should be empty", outStream.toString().isEmpty());
        assertEquals("input file content not matched", input, getFileContent(inputFile.getPath()));
    }
}