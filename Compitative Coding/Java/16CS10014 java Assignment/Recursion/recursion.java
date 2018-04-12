public class recursion{
	public static long factorial(int x){
		if(x==1)
			return 1;
		return (x*factorial(x-1));
	}
	public static void main(String[] args){
		System.out.println("factoria of "+args[0]+"is : "+factorial(Integer.parseInt(args[0])));
	}
}