import java.util.Arrays;
class Student implements Comparable<Student>{
    private String firstName;
    private String lastName;

    public Student(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String toString(){
        return firstName+" "+lastName;
    }

    public int compareTo(Student s){
        return (firstName+lastName).compareTo(s.firstName+s.lastName);
    }
}

public class srt{
    public static void main(String args[]){
        Student[] aray=new Student[5];
        aray[0]=new Student("Aayush", "Gupta");
        aray[1]=new Student("Ayush", "Bansal");
        aray[2]=new Student("Ayush", "Sharma");
        aray[3]=new Student("Yashsvi", "Dixit");
        aray[4]=new Student("Yashvardhan", "Singh");
        Arrays.sort(aray);
        for(Student stu : aray)
           System.out.println(stu);
    }
}
