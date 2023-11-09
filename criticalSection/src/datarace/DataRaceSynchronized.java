package datarace;

public class DataRaceSynchronized {
    public static void main(String[] args) throws InterruptedException {
        SharedClass sharedClass = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        } );

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRace();
            }
        } );

        thread1.start();
        thread1.join();
        thread2.start();
    }

    public static class SharedClass {
        private int x = 0;
        private int y = 0;

        public synchronized void increment() {
            x++;
            y++;
        }

        public synchronized void checkForDataRace() {
            if (y > x) {
                System.out.println("y > x - 데이터 레이스가 감지됨!!");
            }
        }
    }
}
