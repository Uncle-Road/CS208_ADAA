import java.io.*;
import java.util.*;

public class solutionOfLab12A {
    static int[] beautys;//v
    static int[] coins;//w

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        int M = in.nextInt();
        beautys = new int[N + 1];
        coins = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            beautys[i] = in.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            coins[i] = in.nextInt();
        }
        int[][] list = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (i == 0 || j == 0) {
                    list[i][j] = 0;
                } else if (coins[i] > j) {
                    list[i][j] = list[i - 1][j];
                } else {
                    list[i][j] = Math.max(list[i - 1][j],beautys[i] + list[i - 1][j - coins[i]]);
                }
            }
        }
        out.println(list[N][M]);


        out.close();
    }

    static int OPT(int i, int w) {
        if (i == 0) {
            return 0;
        } else if (coins[i] > w) {
            return OPT(i - 1, w);
        } else {
            return Math.max(OPT(i - 1, w), beautys[i] + OPT(i - 1, w - coins[i]));
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
