package com.redhat.btison.dmn.function;

public class MathLibrary {

    public static boolean isDivisibleBy(Integer n, Integer i) {
        if (n % i == 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
