package CodeSmell;

interface A {
  public void animalSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
} 

interface B {
  public void humanSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
}
 interface C {
  public void animalSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
} 

interface D {
  public void humanSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
}

interface E {
  public void animalSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
} 

interface F {
  public void humanSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
}

class Animal implements B, C, D, E, F {

	@Override
	public void animalSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void humanSound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
  // abstract members of A
  // abstract
  
}