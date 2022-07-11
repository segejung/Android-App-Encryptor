package edu.gatech.seclass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FlawedClassTestSC1 {

    // Less than 100% statement coverage with fault.
    @Test
    public void FlawedClassTestSC1() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod1(1,0);
    }
}