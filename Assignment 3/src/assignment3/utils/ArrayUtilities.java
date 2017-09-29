package assignment3.utils;

public final class ArrayUtilities { //disallow extension
    private ArrayUtilities(){} //disallow instantiation

    //simple(if inefficient) method to check if a given array contains a given value.
    public static boolean containsInt(int value, int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            if(array[i] == value) { return true; }
        }
        return false;
    }
}
