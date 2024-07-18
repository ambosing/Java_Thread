package thread.start.test;

import static util.MyLogger.*;

public class StartTest3Main {
	public static void main(String[] args) {
		Runnable runnable = () -> {
			for (int i = 1; i < 6; i++) {
				log("value : " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
