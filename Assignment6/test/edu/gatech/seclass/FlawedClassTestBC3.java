package edu.gatech.seclass;
import org.junit.Test;

public class FlawedClassTestBC3 {

    // 100% statement coverage without fault
    @Test
    public void FlawedClassTestBC3() {
        FlawedClass run = new FlawedClass();
        run.flawedMethod3(1,0); //test specification
        run.flawedMethod3(0,0); //test specification
        run.flawedMethod3(-1,0);
    }

}