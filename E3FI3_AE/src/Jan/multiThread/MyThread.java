package Jan.multiThread;

public class MyThread extends Thread {
	long time;
	boolean isRunning;

	public MyThread(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		time = System.currentTimeMillis();
		while (isRunning) {
			if (System.currentTimeMillis() - time >= 600) {
				System.out.println(".");
				time = System.currentTimeMillis();
			}
		}
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
