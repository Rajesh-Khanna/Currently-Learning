class DynamicArray{
    private int originalArray[];
    private int tempArray[];
    private int size;
    private int range;
    public DynamicArray(){
        this.originalArray=new int[10];
        this.size=10;
        this.range=0;
    }
    public void add(int x){
        if( size<=range){
            tempArray=new int[range];
            for(int i=0;i<range;i++){
               tempArray[i]=originalArray[i];
            }
            originalArray=new int[2*range];
            size=2*range;
            for(int i=0;i<range;i++){
               originalArray[i]=tempArray[i];
            }
        }
        originalArray[range++]=x;
    }
    public void remove(){
        if(range>=1 && 2*(--range)<=size){
            int i;
            tempArray=new int[range];
            for(i=0;i<range;i++){
               tempArray[i]=originalArray[i];
            }
            originalArray=new int[size/2];
            for(i=0;i<range;i++){
               originalArray[i]=tempArray[i];
            }
        }
    }
    public int size(){
        return range;
    }
    public void print(){
        int i;
        for(i=0;i<range;i++)
           System.out.println(originalArray[i]);
    }
}
public class DynamicArrayTest{
    public static void main(String args[]){
        DynamicArray aray=new DynamicArray();
        int i;
        for(i=1;i<=18;i++)
           aray.add(i);
        System.out.println("size of Array " + aray.size());
        aray.print();
        for(i=0;i<6;i++)
           aray.remove();
        aray.add(15);
        System.out.println("size of Array is " + aray.size());
        aray.print();
    }
}