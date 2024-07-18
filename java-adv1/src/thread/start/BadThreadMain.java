package thread.start;

public class BadThreadMain {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " : main() start");

		HelloThread helloThread = new HelloThread();
		System.out.println(Thread.currentThread().getName() + " : start() 호출 전");
		helloThread.run(); // run() 직접 실행 / 이렇게 되면 Main 스레드가 실행함
		System.out.println(Thread.currentThread().getName() + " : start() 호출 후");

		System.out.println(Thread.currentThread().getName() + " : main() end");
	}
}
