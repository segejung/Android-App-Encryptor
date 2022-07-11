package edu.gatech.seclass;
import org.junit.Test;

public class FlawedClassTestBC1 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestBC1() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod1(1,0); //test specification
        run.flawedMethod1(0,0); //test specification
        run.flawedMethod1(-1,0);
    }

}