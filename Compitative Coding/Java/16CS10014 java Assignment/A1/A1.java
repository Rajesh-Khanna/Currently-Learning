public class A1{
	public long fact(int x){
		if(x == 1)
			return 1;
		return (x*fact(x-1));
	}
	public static void main(String args[]){
		A1 num = new A1();
		System.out.println(num.fact(Integer.parseInt(args[0])));
	}
}