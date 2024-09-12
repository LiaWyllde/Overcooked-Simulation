package controller;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Kitchen extends Thread {
	
	Semaphore semaphore;
	int min, max;
	
	public Kitchen(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	private int thePlate() {
		return (int)threadId(); }
	
	private String plateName() {
		
		int id = thePlate();
		
		if (id % 2 == 0) {
			return "lasagna";
		} else {
			return "onion soup";
		}
	}
	
	private void cookingTime() {
		
		int maxTime = timeToCook();
		int totalTime = 0;		
		float percent = 0;		
		double ciclo = 0.10;
	
		while (totalTime < maxTime && percent < 100 ) {
			
			try {
				sleep(100);
		
				while(totalTime < maxTime*ciclo) {
					totalTime += (maxTime*0.01);
					percent = ((totalTime/(float)maxTime) * 100);
					percent = (int)percent;	}
				
				ciclo = ciclo + 0.10;
				
			} catch (Exception e) { e.printStackTrace(); }			
				System.out.println("Cooking process of the " + plateName() + " in: " + (percent) + "%");	
		}		
	}
	
	private void deliver() {
		
		try {
			sleep(500);
			System.out.println("♡ The " + plateName() + " is served!" + " ♡ ");
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	private int timeToCook() {
		int plate = thePlate();

		if (plate % 2 == 0) {
			min = 600; 
			max = 1200;
			System.out.println("A customer ordered a lasagna!");
		} else { 
			min = 500;
			max = 800;
			System.out.println("A customer ordered a onion soup!");
		}
		
		Random rand = new Random();
		plate = rand.nextInt(min,max);
		return plate;
	}

	@Override
	public void run() {
		cookingTime();
		
		try {
			semaphore.acquire();
			deliver();
		} catch (Exception e) { e.printStackTrace(); }
		finally { semaphore.release(); }
	}	
}
