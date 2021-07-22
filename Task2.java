import java.util.*;

class Task2 {
    public static void main(String args[ ]) {
        Scanner input=new Scanner(System.in);
        int i,a=0,b=1,c=0,n;
        System.out.print("Enter value of n: ");
        n=input.nextInt();

        System.out.print(a+" "+b);
        for(i=0;i<n-2;i++) {
            c=a+b;
            a=b;
            b=c;
            System.out.print(" "+c);
        }

        System.out.println();

       if(c%2 !=0)
        System.out.print("The biggest odd number of the Fibonacci series is: "+c);
       else if(c%2 ==0)
           System.out.print("The biggest even number of the Fibonacci series is: "+c);
    }

}








