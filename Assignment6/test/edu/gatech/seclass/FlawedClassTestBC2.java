package edu.gatech.seclass;
import org.junit.Test;
import java.lang.ArithmeticException;

public class FlawedClassTestBC2 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestBC2() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod2(1,0); //test specification
        run.flawedMethod2(0,0); //test specification
        run.flawedMethod2(-1,0);
    }

}