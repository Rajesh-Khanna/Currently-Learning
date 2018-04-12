import java.applet.*;
import java.awt.*;

public class ball extends Applet implements Runnable{
	static int width;
	int x,y;
	static int height;
	int sx = 1;
	int sy = 1;
	
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}
	public void init() {
		 width = 400;
		 height = 400;
		 x=10;
		 y=10;
		 sx = 1;
		 sy = 1;	
	}
	public void run() {
		while(true) {
			repaint();
			
			try {
				Thread.sleep(10);
			}catch(Exception e) {
				
			}
			if(x+20>=400 || x<=0)
				sx = -sx;
			if(y+20>=400 || y<=0)
				sy= -sy;
			x+=sx*1;
			y+=sy*1;
		}
	}
	public void paint(Graphics g) {
			g.setColor(Color.green);
			g.fillArc(x, y, 20, 20, 0, 360);	
			}
}
