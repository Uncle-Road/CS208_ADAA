import java.util.*;
import java.io.*;
import java.math.*;

public class solutionOfLab4A{
    static class node{
        int value;
        int degree =0;
        ArrayList<node> childs = new ArrayList<>();
        node(int value){
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int num = in.nextInt();
        node[] nodes = new node[num];
        for(int i =0;i< num;i++){
            nodes[i] = new node(i+1);
        }

        int relation = in.nextInt();

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        ArrayList<Integer> list = new ArrayList<>();
        //create a graph
        for(int i =0 ;i<relation;i++){
            int dad = in.nextInt()-1;
            int child = in.nextInt()-1;
            nodes[dad].childs.add(nodes[child]);
            nodes[child].degree+=1;
        }
        for(int i =0;i<num ;i++){
            if(nodes[i].degree==0){
                minHeap.add(nodes[i].value);
            }
        }

        while (!minHeap.isEmpty()){
            int a = minHeap.remove().intValue();
            list.add(a);
            for(int i =0;i<nodes[a-1].childs.size();i++){
                nodes[a-1].childs.get(i).degree-=1;
                if(nodes[a-1].childs.get(i).degree==0){
                    minHeap.add(nodes[a-1].childs.get(i).value);
                }
            }

        }
        for(int i =0;i<list.size();i++){
            out.print(list.get(i)+" ");
        }
        out.println();

        out.close();
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
