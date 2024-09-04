package thread.control;

import static util.ThreadUtils.*;

import util.ThreadUtils;

public class CheckedExceptionMain {
	public static void main(String[] args) throws Exception {
		throw new Exception();
	}

	static class CheckedRunnable implements Runnable {
		@Override
		public void run() {
			sleep(1000);
		}


		// 여기서는 throws가 안됨. 부모가 체크 예외를 던지지 않기 때문에, 부모가 던질 수 있는 체크 예외의 하위 타입만 던질 수 있다.
		// @Override
		// public void run() throws Exception{
		// 	throw new Exception();
		// }
	}
}
