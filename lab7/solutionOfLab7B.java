import java.util.*;
import java.io.*;

public class solutionOfLab7B {
    static class Egde {
        int u, v, c;

        Egde(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }

    static class compare1 implements Comparator<Egde> {
        public int compare(Egde e1, Egde e2) {
            return e1.c - e2.c;
        }
    }

    static class compare2 implements Comparator<Egde> {
        public int compare(Egde e1, Egde e2) {
            return e2.c - e1.c;
        }
    }

    static int fib[] = new int[44];
    static int f[] = new int[200005];
    static ArrayList<Egde> e;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        fib[1] = 1;
        fib[2] = 2;
        for (int i = 3; i <= 40; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        int t = in.nextInt();
        while (t-- > 0) {
            e = new ArrayList<>();
            e.add(new Egde(0, 0, 0));
            int n = in.nextInt();
            int m = in.nextInt();
            for (int i = 1; i <= n; i++) f[i] = i;
            for (int i = 1; i <= m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int c = in.nextInt();
                e.add(new Egde(u, v, c));
            }
            for (int i = 1; i <= m; i++) {
                merge(e.get(i).u, e.get(i).v);
            }
            if (!check(n)) {
                out.println("No");
            } else {
                Collections.sort(e, new compare1());
                int flag = 0;
                int low = calu(n, m);
                Collections.sort(e, new compare2());
                int up = calu(n, m);
                for (int i = 1; i <= 28; i++) {
                    if (fib[i] >= low && fib[i] <= up) {
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    out.println("Yes");
                } else {
                    out.println("No");
                }
            }

        }
        out.close();
    }

    static int getf(int v) {
        if (f[v] == v) {
            return v;
        } else {
            f[v] = getf(f[v]);
            return f[v];
        }
    }

    static void merge(int u, int v) {
        int t1 = getf(u), t2 = getf(v);
        f[t2] = t1;
    }

    static boolean check(int n) {
        int parents = getf(1);
        for (int i = 1; i <= n; i++) {
            if (getf(i) != parents) {
                return false;
            }
        }
        return true;
    }

    static int calu(int n, int m) {
        int ans = 0;
        for (int i = 1; i <= n; i++) f[i] = i;
        for (int i = 1; i <= m; i++) {
            if (getf(e.get(i).u) != getf(e.get(i).v)) {
                merge(e.get(i).u, e.get(i).v);
                if (e.get(i).c == 1) ans++;
            }
        }
        return ans;
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
