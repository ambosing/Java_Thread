package thread.start.test;

import static java.lang.Thread.*;
import static util.MyLogger.*;

public class StartTest4Main {
	public static void main(String[] args) {
		PrintWork runnableA = new PrintWork("A", 1000);
		PrintWork runnableB = new PrintWork("B", 1000);


		Thread threadA = new Thread(runnableA, "Thread-A");
		Thread threadB = new Thread(runnableB, "Thread-B");
		threadA.start();
		threadB.start();

	}

	static class PrintWork implements Runnable {
		private final String content;
		private final int sleepMs;

		PrintWork(String content, int sleepMs) {
			this.content = content;
			this.sleepMs = sleepMs;
		}

		@Override
		public void run() {
			while (true) {
				log(content);
				try {
					sleep(sleepMs);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
