package edu.gatech.seclass;
import org.junit.Test;

public class FlawedClassTestBC4 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestBC4() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod4(1,1); //test specification
        run.flawedMethod4(0,1); //test specification
        run.flawedMethod4(-1,1);
    }

}