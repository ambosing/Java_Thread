package thread.start.test;

import static util.MyLogger.*;

import util.MyLogger;

public class CountThread extends Thread{

	@Override
	public void run() {
		for (int i = 1; i < 6; i++) {
			log("value : " + i);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
