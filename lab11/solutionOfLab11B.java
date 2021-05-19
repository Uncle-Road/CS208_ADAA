import java.util.*;
import java.io.*;

public class solutionOfLab11B {
    static class complex {
        double r ;
        double i ;

        complex(double r, double i) {
            this.r = r;
            this.i = i;
        }
    }

    static complex add(complex c, complex b) {
        return new complex(c.r + b.r, c.i + b.i);
    }

    static complex sub(complex c, complex b) {
        return new complex(c.r - b.r, c.i - b.i);
    }

    static complex mul(complex c, complex b) {
        return new complex(c.r * b.r - c.i * b.i, c.r * b.i + c.i * b.r);
    }


    static complex[] x1 = new complex[400040];
    static int[] a;
    static long[] num = new long[400040];
    static long[] sum = new long[400040];
    static double PI = Math.PI;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            num[a[i]]++;
        }
        Arrays.sort(a);
        int len1 = a[n - 1] + 1;
        int len = 1;
        while (len < 2 * len1) {
            len <<= 1;
        }
        for (int i = 0; i < len1; i++)
            x1[i] = new complex(num[i], 0);
        for (int i = len1; i < len; i++)
            x1[i] = new complex(0, 0);

        FFT(x1, len, 1);

        for (int i = 0; i < len; i++)
            x1[i] = mul(x1[i] , x1[i]);
        FFT(x1, len, -1);

        for (int i = 0; i < len; i++) {
            num[i] = (long) (x1[i].r + 0.5);
        }
        len = 2 * a[n - 1];
        for (int i = 0; i < n; i++) {
            num[a[i] + a[i]]--;
        }
        for (int i = 1; i <= len; i++) {
            num[i] /= 2;
        }
        sum[0] = 0;

        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + num[i];
        }
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += sum[len] - sum[a[i]];
            cnt -= (long) (n - 1 - i) * i;
            cnt -= (n - 1);
            cnt -= (long) (n - 1 - i) * (n - i - 2) / 2;
        }
        System.out.println(cnt);
    }

    private static void FFT(complex[] y, int len, int on) {
        change(y, len);
        for (int h = 2; h <= len; h <<= 1) {
            complex wn = new complex(Math.cos(-on * 2 * PI / h), Math.sin(-on * 2 * PI / h));
            for (int j = 0; j < len; j += h) {
                complex w = new complex(1, 0);
                for (int k = j; k < j + h / 2; k++) {
                    complex u = y[k];
                    complex t = mul(w, y[k + h / 2]);
                    y[k] = add(u, t);
                    y[k + h / 2] = sub(u, t);
                    w = mul(w, wn);
                }
            }
        }
        if (on == -1) {
            for (int i = 0; i < len; i++) {
                y[i].r /= len;
            }
        }
    }

    private static void change(complex y[], int len) {
        int i, j, k;
        for (i = 1, j = len / 2; i < len - 1; i++) {
            if (i < j) {
                complex temp = y[i];
                y[i] = y[j];
                y[j] = temp;
            }
            k = len / 2;
            while (j >= k) {
                j -= k;
                k /= 2;
            }
            if (j < k) j += k;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}
