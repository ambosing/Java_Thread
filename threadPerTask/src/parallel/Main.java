package parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static void processingSequential(List<Integer> test) {
        System.out.println("Main.processingSequential");
        long start = System.currentTimeMillis();
        List<Integer> result =  test.stream()
                .filter(item -> item % 2 == 0)
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    private static void processingParallel(List<Integer> test) {
        System.out.println("Main.processingParallel");
        long start = System.currentTimeMillis();
        List<Integer> result =  test.parallelStream()
                .filter(item -> item % 2 == 0)
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>();
        List<Integer> test2 = new ArrayList<>();

        for (int i = 0; i < 100000000; i++) {
            test.add(i);
            test2.add(i);
        }

        processingSequential(test);
        processingParallel(test2);
    }
}
