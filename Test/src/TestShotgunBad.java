package myPackage;

public class TestShotgunBad {
	
	public void method1(){
		int temp[] = {1,2,3,4,5,6,7,8,9,10};
		int total = 0;
		
		
		for(int i = 1; i<10; i++) {
			
			total += temp[i+1];
		}
		
		for(int i = 1; i<10; i++) {
			
			total *= temp[i+1];
		}
		
		
	}

}
