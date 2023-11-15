import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IoBoundApplication {
    private static final int NUMBER_OF_TASKS = 10_000;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Press enter to start");

        s.nextLine();

        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);
        long start = System.currentTimeMillis();
        performTasks();
        System.out.printf("작업 수행 완료 하는데 %dms가 걸렸습니다.\n", System.currentTimeMillis() - start);
    }

    private static void performTasks() {
        try {
//            ExecutorService executorService = Executors.newCachedThreadPool(); // 다이나믹 스레드 풀
            // 너무 작업의 수가 많아지면 JVM에 할당된 메모리가 부족해 더 이상 스레드를 만들 수가 없음
            // 그러면 스레드 충돌이 일어나서 에러가 발생하기 때문에 스레드를 안정적으로 사용하는 것이 중요
            ExecutorService executorService = Executors.newFixedThreadPool(1000); // 고정 스레드 풀


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