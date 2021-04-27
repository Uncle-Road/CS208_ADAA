import java.util.Scanner;

public class solutionOfLab8B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        String[] strArr = new String[n];
        int[] intArr = new int[n];
        for (int i = 0; i < n; i++) {
            strArr[i] = in.next();
            intArr[i] = in.nextInt();
        }
        int op1 = Integer.parseInt(Integer.toBinaryString(0));
        int op2 = Integer.parseInt(Integer.toBinaryString(1));

        for (int i = 0; i < n; i++) {
            if (strArr[i].equals("XOR")) {
                op1 = op1 ^ intArr[i];
                op2 = op2 ^ intArr[i];
            } else if (strArr[i].equals("OR")) {
                op1 = op1 | intArr[i];
                op2 = op2 | intArr[i];
            } else {
                op1 = op1 & intArr[i];
                op2 = op2 & intArr[i];
            }
        }

        System.out.println(Math.max(op1, op2));
    }
}
