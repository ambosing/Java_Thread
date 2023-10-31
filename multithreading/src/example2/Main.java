package example2;

public class Main {
    public static void main(String[] args) {
        Thread newThread = new NewThread();

        newThread.start();
    }

    // Thread를 상속받아서 한다면 현재 스레드를 this로 명칭할 수 있다.
    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + this.getName());
        }
    }
}
