import java.util.*;

public class StackDemo
{
    public static void main (String args[])
    {
        Stack stack = new Stack();
        stack.push (new Integer(10));
        stack.push ("a");
        System.out.println("The contents of the Stack is" + stack);
        System.out.println("The size of the Stack is" + stack.size());
        System.out.println("The number poped out is" + stack.pop());
        System.out.println("The number poped out is" + stack.pop());
        //System.out.println("The number poped out is" + stack.pop());       
        System.out.println("The contents of the Stack is" + stack);
        System.out.println("The size of the Stack is" + stack.size());
    }
}