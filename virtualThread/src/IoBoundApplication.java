import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IoBoundApplication {
    private static final int NUMBER_OF_TASKS = 10_000;
    public static void main(String[] args) {
        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);
        long start = System.currentTimeMillis();
        performTasks();
        System.out.printf("작업 수행 완료 하는데 %dms가 걸렸습니다.\n", System.currentTimeMillis() - start);
    }

    private static void performTasks() {
        try {
            // 가상 스레드 사용
            // 결과를 보면 코어수와 같은 10개의 플랫폼 스레드만 만들어짐
            ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();


            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(() -> blockingIoOperation());
            }
        } finally {

        }
    }

    // 긴 blocking IO라고 가정
    private static void blockingIoOperation() {
        System.out.println("Executing a blocking task from thread: " + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}