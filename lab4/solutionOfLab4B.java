import java.io.*;
import java.util.*;

public class solutionOfLab4B {

    static class node {
        int value;
        long weight;
        ArrayList<node> childs = new ArrayList<>();

        node(int value) {
            this.value = value;
        }
    }

    static node[] nodesList;
    static long[] weightList;
    static long[] s;
    static long[] sum;
    static long ans;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int num = in.nextInt();

        nodesList = new node[num ];
        weightList = new long[num ];
        sum = new long[num ];
        s = new long[num ];
        ans = 0;

        for (int i = 0; i < num; i++) {
            weightList[i] = in.nextLong();
        }
        for (int i = 0; i < num; i++) {
            nodesList[i] = new node(i);
            nodesList[i].weight = weightList[i];
        }

        for (int i = 0; i < num-1; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            nodesList[a].childs.add(nodesList[b]);
            nodesList[b].childs.add(nodesList[a]);
        }

        DFS1(nodesList[0], null,0);
//        for (long i : s) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        ans = sum[0];
        DFS2(nodesList[0],null );
//        for (long i : sum) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        System.out.println(ans);

        out.close();
    }
    static void DFS1(node node, node parent , int deep) {
        s[node.value] = node.weight;
        for (node child : node.childs) {
            if (child != parent) {
                DFS1(child, node, deep + 1);
                s[node.value] += s[child.value];
            }
        }
        sum[0] += node.weight * deep;
    }

    static void DFS2(node node, node parents) {
        if (node.value != 0) {
            sum[node.value] = sum[parents.value] + s[0] - s[node.value] * 2;
            ans = Math.max(sum[node.value], ans);
        }
        for (node child : node.childs) {
            if (child != parents)
                DFS2(child, node);
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