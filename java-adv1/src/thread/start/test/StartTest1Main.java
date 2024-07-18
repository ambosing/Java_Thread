package thread.start.test;

public class StartTest1Main {
	public static void main(String[] args) {
		CountThread countThread = new CountThread();
		countThread.start();
	}
}
