public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Code that will run in a new thread
                // java 8부터는 람다사용 가능해서 쉽게 생성 가능
                System.out.println("We are now in thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        // 스레드 이름 지정하기
        thread.setName("New Worker Thread");

        // 스레드 우선순위 Max 10
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        Thread.sleep(10000);
    }
}