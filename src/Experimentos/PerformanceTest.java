package Experimentos;

import PatriciaTrie.PatriciaTrie;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTest {
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void main(String[] args) {
        // I assume you will know how to create a object Person yourself...
        PatriciaTrie ptTest = new PatriciaTrie();



        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
    }
}