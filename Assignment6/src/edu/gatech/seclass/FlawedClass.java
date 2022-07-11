package edu.gatech.seclass;

/**
 * This is a Georgia Tech provided code example for use in assigned
 * private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it
 * available on publicly viewable websites including repositories such
 * as GitHub and GitLab.  Such sharing may be investigated as a GT
 * honor code violation. Created for CS6300 Summer 2022.
 *
 * Template provided for the White-Box Testing Assignment. Follow the
 * assignment directions to either implement or provide comments for
 * the appropriate methods.
 */

public class FlawedClass {

    //Task1
    public void flawedMethod1 (int x, int y) {

        int e = x;
        int f = y;
        int result;
        if (e > 0) {
            result = e / f;
        } else if (e == 0) {
            result = e / (f);
        } else
            result = e / f;
    }

    //Task2
    public void flawedMethod2 (int x, int y) {
        int a = x;
        int b = y;
        int result;
        if (a > 0){
            result = a/b;
        }
        else if (a == 0){
            result = a/(b);
        }
        else
            result = a/b;
    }

    //Task3
    public void flawedMethod3 (int x, int y) {
        int c = x;
        int d = y;
        int result;
        if (c > 0){
            result = c+d;
        }
        else if (c == 0){
            result = c+d;
        }
        else
            result = c/d;
    }

    //Task4
    public void flawedMethod4 (int x, int y) {
        int g = x;
        int h = y;
        int result;
        if (g > 0){
            result = g/h;
        }
        else if (g == 0){
            result = g/h;
        }
        else
            result = g/h;
    }

    //Task5
    public boolean flawedMethod5 (boolean a, boolean b) {
        int x = 3;
        int y = 1;
        if(a)
            x += y;
        else
            y = y*x;
        if(b)
            y -= x;
        else
            y -= 1;
        return ((x/y)>= 0);
    }
}

