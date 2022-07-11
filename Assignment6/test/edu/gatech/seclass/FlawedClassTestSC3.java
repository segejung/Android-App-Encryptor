package edu.gatech.seclass;
import org.junit.Test;

public class FlawedClassTestSC3 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestSC3() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod3(1,1); //test specification
        run.flawedMethod3(0,2); //test specification
    }

}