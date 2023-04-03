package myPackage;

public class TestShotgunGood {

	private int offset = -1;
	public void method1(){
		int temp[] = {1,2,3,4,5,6,7,8,9,10};
		int sum = 0;
		
		
		for(int i = 1; i<10; i++) {
			int index = i+offset;
			sum += temp[index];
		}
		
	}
}
