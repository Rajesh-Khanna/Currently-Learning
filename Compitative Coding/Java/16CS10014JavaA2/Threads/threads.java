public class threads extends Thread{
	public void run(){
	Integer i=0;
	synchronized(i){
			for(i=1;i<20;i++)
			{
				System.out.println(i);
				try{
					Thread.sleep(10);
				}catch(Exception e){}
			}
	}
		}
	public static void main(String args[]){
		threads t = new threads();
		threads t2 = new threads();
		Thread thread = new Thread(t);
		Thread thread2 = new Thread(t2);
		thread.start();
		thread2.start();
	}	
}