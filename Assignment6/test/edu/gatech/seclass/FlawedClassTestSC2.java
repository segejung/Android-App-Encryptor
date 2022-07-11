package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class FlawedClassTestSC2 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestSC2() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod2(1,1); //test specification
        run.flawedMethod2(0,2); //test specification
    }

}