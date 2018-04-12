public class Null{
	public static void main(String args[]){
		String s = null;
		try{
			if(s.equals(""))
				System.out.println(s+"");
		}catch(Exception e){
			System.out.println(e);
		}
	}

}