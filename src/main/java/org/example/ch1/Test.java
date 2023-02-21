package org.example.ch1;

public class Test {

    public static void main(String[] args) {
        int n = 1000_000;
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = i;
        }
        long time0 = System.currentTimeMillis();

        f1(list, 5);
        long simpleSearchTime = System.currentTimeMillis() - time0;

        time0 = System.currentTimeMillis();
        f2(list, 5);
        long binarySearchTime = System.currentTimeMillis() - time0;

        System.out.println("simpleSearchTime: " + simpleSearchTime);
        System.out.println("binarySearchTime: " + binarySearchTime);
    }
    private static int f1(int[] list, int element) {
        // The number of executions of each line is written at the end of that line
        // these costs are calculated in worst case
        // c_x stands for the cost of execution of that line for one time
        int n = list.length; // c1 executed 1 time
        // (int i = 0) -->> c2 -->> executed 1 time (1 time in the beginning of loop)
        // (i < n)     -->> c3 -->> executed n+1 times (last time loop will not be executed but condition will be checked)
        // (i++)       -->> c4 -->> executed n times (after each execution of loop)
        for (int i = 0; i < n; i++) {
            if (list[i] == element) { // c5 -->> executed n times
                return i; // c6 -->> executed 0 time (because we are calculating worst case)
            }
        }
        return -1; // c7 -->> executed 1 time
        // worst case execution time:
        // 1*c1 + 1*c2 + (n+1)*c3, (n)*c4 + n*c5 + 0*c6 + 1*c7
        // = c1 + c2 + m*(c3 + c4 + c5) + c3 + c7
    }
    private static int f2(int[] list, int element) { // i+i+1
        int start = 0;
        int end = list.length-1;
        int mid;
        //
        while (start <= end) { // in the line below, if (start:i , end:i+1) -->> mid:i
                               // so when (start==end), we need to execute 'loop'.
            mid = (start+end)/2; //
            if (list[mid] == element) {
                return mid;
            } else if (element < list[mid]) {
                end = mid - 1;
            } else { // element > list[mid]
                start = mid + 1; // i+1
            }
        }
        return -1;
    }

    private static int f3(int[] list, int element) {
        // The number of executions of each line is written at the end of that line
        // these costs are calculated in worst case
        // c_x stands for the cost of execution of that line for one time
        // m is the number of times that loop will be executed
        int start = 0;// c_1 -->> executed 1 time
        int end = list.length-1;// c_2 -->> executed 1 time
        int mid;// c_3  -->> executed 1 time
        while (start <= end) { // c_4 -->> executed m+1 times
            mid = (start+end)/2;// c_5 -->> executed m times
            if (list[mid] == element) {// c_6 -->> executed m times
                return mid;// c_7 -->>  executed 0 time
            } else if (element < list[mid]) {// c_8 -->> executed m times
                end = mid - 1;// c_9
            } else { //              -->> these two lines executed m times in sum
                start = mid + 1;// c_9
            }
        }
        return -1;//c10 executed 1 time
        // worst case execution time::
        // 1*c1 + 1*c2 + 1*c3, (m+1)*c4 + m*c5 + m*c6 + 0*c7 + m*c8 + m*c9
        // = c1 + c2 + c3 + c4 + m*(c4 + c5 + c6 + c8 + c9)
        // m is the number of times that loop will be executed
        // Each time the loop is executed,
        // the distance between start and end is halved
        // so m is the number of times that
        // the (end - start) should be divided
        // until it reaches to '0'
        // that is equals with:  1 +
        // the number of times that '1' multiplied in 2
        // until it reaches to (end - start)
        // thi is actually the concept of logarithm
        // you can search and read about it
    }
}
