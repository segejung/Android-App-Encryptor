package edu.gatech.seclass;
import org.junit.Test;

public class FlawedClassTestPC1 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestPC1() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod1(1,1); //test specification
        run.flawedMethod1(0,1); //test specification
        run.flawedMethod1(-1,1);
    }

}