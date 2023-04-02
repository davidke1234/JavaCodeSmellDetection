package CodeSmell;

interface Animal {
  public void animalSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
} 

interface Human {
  public void humanSound(); // interface method (does not have a body)
  public void run(); // interface method (does not have a body)
}