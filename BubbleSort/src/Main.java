public class Main {
    public static void main(String[]args) {
       int[] unsortedArray = {6, 4, 2, 7, 1, 7};
       int[] sortedArray = bubbleSort(unsortedArray);

       for(int i = 0; i < sortedArray.length; i++){
           System.out.println(sortedArray[i]);
       }
    }


    public static int[] bubbleSort(int[] input) {

        int temp = 0;
        boolean sorted = false;
        boolean changed;
        int k = 0;

        while (!sorted) {
            temp = 0;
            changed = false;
            sorted = false;

            for (int i = 0; i < input.length - k; i++) {
                //try to switch
                if (input[i] < temp) {
                    input[i-1] = input[i];
                    input[i] = temp;
                    changed = true;
                }
                temp = input[i];
            }

            k++;
            if(!changed) { sorted = true ; }
        }
        return input;
    }
}
