import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<Long, Long> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        long[] a_arr = new long[N];
        long[] b_arr = new long[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            if (a <= b) {
                a_arr[i] = a;
                b_arr[i] = b;
            } else {
                a_arr[i] = b;
                b_arr[i] = a;
            }
            max = Math.max(max, b_arr[i]);
        }
        long n = findN(max);
        map.put(0L, 0L);
        for (int i = 0; i < N; i++) {
            System.out.println(Num(a_arr[i], b_arr[i]));
        }
    }

    static long Num(long a, long b) {
        long level = 0;
        long len = 1;
        while (b + 1 > len) {
            len *= 2;
            level++;
        }
        long mid = (long) Math.pow(2, level - 1);
        long size = mid * 2;
        //this num in level;
        if (a == 1) {
            if (map.containsKey(b)) {
                return map.get(b);
            } else {
                //divide num(1,11) = to num(1,7) + 1 + num (9,11)
                return Num(1, mid - 1) + 1 + Num(mid + 1, b);
            }
        } else {
            if (a > mid) {
                return (b - a + 1) - (Num(1,size - a) - Num(1, size - b-1));
            } else {
                return Num(1, b) - Num(1, a - 1);
            }
        }
    }

    static long findN(long b) {
        long n = 0;
        long len_1 = 1;
        while (len_1 < b + 1) {
            long num_L = len_1;
            len_1 *= 2;
            if (!map.containsKey(len_1 - 1)) {
                map.put(len_1 - 1, num_L);
                map.put(len_1, num_L + 1);
            }
            n++;
        }
        return n;
    }
}
