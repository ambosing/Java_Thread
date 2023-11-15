import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsDemo {

    private static final int NUMBER_OF_VIRTUAL_THREADS = 20;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> System.out.println("내부 스레드 : " + Thread.currentThread());

        // 기존 스레드 = 플랫폼 스레드
//        Thread platformThead = new Thread(runnable);
//        Thread platformThread = Thread.ofPlatform().unstarted(runnable);
//
//        platformThread.start(); // 내부 스레드 : Thread[#21,Thread-0,5,main]
//        platformThread.join();

        // 가상 스레드
//        Thread virtualThread = Thread.ofVirtual().unstarted(runnable);
//        virtualThread.start();  // 내부 스레드 : VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
//        virtualThread.join();

        // 가상의 스레드 여러개 실행
        List<Thread> virtualThreads = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread virtualThead = Thread.ofVirtual().unstarted(runnable);
            virtualThreads.add(virtualThead);
        }

        for (Thread virtualThread : virtualThreads) {
            virtualThread.start();
//            내부 스레드 : VirtualThread[#22]/runnable@ForkJoinPool-1-worker-2
//            내부 스레드 : VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
        }

        for (Thread virtualThread : virtualThreads) {
            virtualThread.join();
        }

        // 20개로 늘렸을 때 가상 스레드는 각각의 ID를 가지지만
        // 플랫폼 스레드는 5개만 생성하고 연결되는 걸 볼 수 있다.
//        내부 스레드 : VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1
//        내부 스레드 : VirtualThread[#29]/runnable@ForkJoinPool-1-worker-3
//        내부 스레드 : VirtualThread[#23]/runnable@ForkJoinPool-1-worker-3
//        내부 스레드 : VirtualThread[#24]/runnable@ForkJoinPool-1-worker-3
//        내부 스레드 : VirtualThread[#34]/runnable@ForkJoinPool-1-worker-5
//        내부 스레드 : VirtualThread[#26]/runnable@ForkJoinPool-1-worker-3
//        내부 스레드 : VirtualThread[#27]/runnable@ForkJoinPool-1-worker-3
//        내부 스레드 : VirtualThread[#28]/runnable@ForkJoinPool-1-worker-3
//        내부 스레드 : VirtualThread[#22]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#30]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#31]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#32]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#33]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#35]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#36]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#37]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#38]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#39]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#40]/runnable@ForkJoinPool-1-worker-2
//        내부 스레드 : VirtualThread[#25]/runnable@ForkJoinPool-1-worker-4
    }
}