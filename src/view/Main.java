package view;

import java.util.concurrent.Semaphore;
import controller.*;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaphore = new Semaphore(5);
		
		for (int i = 0; i < 5; i++) {
			
			Kitchen kitchenThread = new Kitchen(semaphore);
			kitchenThread.start();
			
		}
		
	}
}
