package com.redhat.btison.dmn.function;

public class MathLibrary {

    public static boolean isDivisibleBy(int n, int i) {
        if (n % i == 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
