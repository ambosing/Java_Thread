package thread.start;

import static util.MyLogger.*;

public class InnerRunnableMainV2 {
	public static void main(String[] args) {
		log("main() start");

		Runnable runnable = () -> log("run() start");
		Thread thread = new Thread(runnable);
		thread.start();

		log("main() end");
	}

}
