import java.util.*;

public class solutionOfLab3A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T-->0){
            int s = in.nextInt();
            int m = in.nextInt();
            int n = in.nextInt();

            if(s%2==1){
                System.out.println("impossible");
            }else {
                int GCD=gcd(m,n);
                if((s/2)%GCD!=0){
                    System.out.println("impossible");
                }else {
                    s /= GCD;
                    System.out.println(s-1);
                }
            }

        }
    }

    private static int gcd(int a, int b) {
        if(b==0) return a;
        return a % b == 0 ? b : gcd(b, a % b);
    }
}
