interface A{
	void doSomething();
}

interface B extends A{
	void doSomethingMore();
}

public class C implements B {
 public void doSomething(){
  System.out.println("Do Something.");
 }
 public void doSomethingMore(){
  System.out.println("do SomethingMore");
 }
}