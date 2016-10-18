package Jan.multiThread;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		MyThread t1 = new MyThread(true);
		t1.start();
		
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		
		
		do{
			if(sc.next().equals("q")){
				t1.setRunning(false);
				isRunning=false;
			}
		}while (isRunning);
	}
}
