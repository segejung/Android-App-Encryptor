package edu.gatech.seclass;
import org.junit.Test;

public class FlawedClassTestPC2 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestPC2() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod2(1,1); //test specification
        run.flawedMethod2(0,1); //test specification
        run.flawedMethod2(-1,1);
    }

}