package thread.start;

public class DaemonThreadMain {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + ": main() start");
		DaemonThread daemonThread = new DaemonThread();
		daemonThread.setDaemon(true);
		daemonThread.start();

		System.out.println(Thread.currentThread().getName() + ": main() end");
	}

	static class DaemonThread extends Thread {


		// throws를 사용할 수 없음
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ": run() start");

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}


			System.out.println(Thread.currentThread().getName() + ": run() end");
		}
	}
}
