import java.util.ArrayList;

public class ArrayListDemo{
    public static void main(String args[]){
        ArrayList<Integer> aray=new ArrayList<>();
        int i;
        for(i=1;i<=10;i++){
           aray.add(i*i);
         }
        System.out.println("elements:");
        for(i=0;i<10;i++){
           System.out.println(aray.get(i));
        }
        System.out.println("Size of the list is "+aray.size());
        
        aray.clear();

        if(aray.isEmpty()){
          System.out.println("emptied list ");
        }
        else{
          System.out.println(" not empty");
        }
    }
}