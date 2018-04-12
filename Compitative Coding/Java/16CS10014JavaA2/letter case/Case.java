import java.util.Scanner;
public class Case{
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		String res = "";
		for (int i =0;i<str.length();i++){
			if(i%2==0){
				res+=Character.toLowerCase(str.charAt(i));
			}
			else{
				res+=Character.toUpperCase(str.charAt(i));	
			}
		}
		System.out.print(res);
	}
}
