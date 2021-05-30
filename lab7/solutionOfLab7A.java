import java.util.*;
import java.io.*;

public class solutionOfLab7A {
    static class node {
        int start;
        int end;
        int goal;

        node(int start, int end, int goal) {
            this.start = start;
            this.end = end;
            this.goal = goal;
        }
    }

    static class GoalComparator implements Comparator<node> {
        public int compare(node n1, node n2) {
            return n2.goal - n1.goal;
        }
    }

    static ArrayList<node> nodes = new ArrayList<>();
    static node[] timeslot = new node[100000000];

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            int w = in.nextInt();
            nodes.add(new node(s - 1, t - 1, w));
        }
        Collections.sort(nodes, new GoalComparator());
        long ans = 0;
        for (int i = 0; i < N; i++) {
            node a = nodes.get(i);
            if (LinearMatch(a, a.start)) {
                ans += a.goal;
            }
        }
        out.println(ans);
        out.close();
    }

    static boolean LinearMatch(node i, int x) {
        if (x > i.end) return false;
        if (timeslot[x] == null) {
            // x matches ai
            timeslot[x] = i;
            return true;
        }
        node j = timeslot[x];//getTaskOf(x)
        if (i.end > j.end) {
            return LinearMatch(i, x + 1);//
        } else {
            if (LinearMatch(j, x + 1)) {
                //x matches ai
                timeslot[x] = i;
                return true;
            }
        }
        return false;
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
