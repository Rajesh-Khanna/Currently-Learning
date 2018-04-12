import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Image{
	public static void main(String args[]){
		int width = 400,height = 400;
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		File f = null;

		for(int j=0;j<height;j++){
			for(int i=0;i<width;i++){
				int g = 255;
				int r = 255;
				int b = 255;
				int a = 255;
				if((i-200)*(i-200)+(j-200)*(j-200) < 200){r = 0;b = 0;}
				int p = (a<<24) | (r<<16) | (g<<8) | b;
				img.setRGB(i,j,p);
			}
		}
		try{
			f = new File("img");
			ImageIO.write(img,"png",f);
		}catch(Exception e){

		}
	}
}