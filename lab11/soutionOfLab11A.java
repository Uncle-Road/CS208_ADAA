import java.io.*;
import java.util.*;

public class solutionOfLab11A {
    static class node{
        long L;
        long R;
        long N;
        node(){};
        node(long L,long R,long N){
            this.L = L;
            this.R = R;
            this.N = N;
        }
    }
    static ArrayList<node> table = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        long[] list = new long[N];
        long max = 0;
        table.add(new node(1,0,0));
        for(int i =0;i<N;i++){
            long data = in.nextLong();
            list[i] = data;
            max = Math.max(data,max);
        }
        long level = findN(max);
        for(int i =0;i<N;i++){
            long[] ans = countLRN(list[i]);
            System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
        }
    }
    static long[] countLRN(long len){
        if(len == 0){
            return new long[]{0, 0, 0};
        }
        long index  = (long)(Math.log(len)/Math.log(2));
        node a = table.get((int) index);
        long[] LRN1 = new long[3];
        LRN1[0] =a.L;
        LRN1[1] = a.R;
        LRN1[2] = a.N;
        long[] LRN2 = countLRN(len-(long)Math.pow(2,index));
        long[] LRN3 = new long[3];
        LRN3[0] = LRN1[0] + LRN2[2];
        LRN3[1] = LRN1[1] + LRN2[0];
        LRN3[2] = LRN1[2] + LRN2[1];
        return LRN3;
    }
    static long findN(long index){
        long n = 1;
        long len = 2;
        while(len<=index){

            len *=2;
            node a = new node();
            node node1 = table.get((int) (n-1));
            a.L = node1.L + node1.N;
            a.R = node1.R + node1.L;
            a.N = node1.N + node1.R;
            table.add(a);
            n++;
        }
        return  n;
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
