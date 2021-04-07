import java.io.*;
import java.util.*;

public class solutionOfLab5B {
    static class Edge implements Comparable<Edge>{
        long weight;
        int to;
        Edge(long weight,int to){
            this.weight = weight;
            this.to = to;
        }
        
        @Override
        public int compareTo(Edge edge) {
            long a = profit[to];
            long b = profit[edge.to];
            if ( a >= 0 && b < 0) {
                return -1;
            } else if (a < 0 && b >= 0) {
                return 1;
            } else if (a >= 0 && b >= 0) {
                return Long.compare(costs[to], costs[edge.to]);
            } else {
                return -Long.compare(income[to], income[edge.to]);
            }
        }
    }

    static class Graph{
        int node;
        ArrayList<Edge>[] edgelist;
        
        Graph(int node){
            this.node = node;
            edgelist = new ArrayList[node+1];
            for(int i =0;i<= node;i++){
                edgelist[i] = new ArrayList<>();
            }
        }
        
        void addEdge(int from , int to, long weigth){
            edgelist[from].add(new Edge(weigth,to));
        }

    }

    static int N;
    static long ans;
    static long curHp;
    static long[] hp ;
    static long[] costs ;
    static long[] income ;
    static long[] profit ;
    static boolean[] isVisited;
    static Graph map = new Graph(N);

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        initial(N,in);
        clauProfit(1);
        for(int i =0;i<=N;i++){
            Collections.sort(map.edgelist[i]);
        }
        clauTime(1);
        out.println(ans);
        out.close();
    }

    private static void initial(int N,Reader in)throws IOException {
        hp = new long[N + 1];
        costs = new long[N + 1];
        income = new long[N + 1];
        profit = new long[N + 1];
        isVisited = new boolean[N+1];
        map = new Graph(N);

        for(int i =1 ; i<=N;i++){
            hp[i] = in.nextLong();
        }
        for(int i =0;i<N-1;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            long cost = in.nextLong();
            if(u<v){
                map.addEdge(u,v,cost);
            }else {
                map.addEdge(v,u,cost);
            }
        }
        ans = 0;
        curHp = hp[1];
    }

    private static void clauProfit(int from) {
        isVisited[from] = true;
        long now_hp = hp[from];
        for(int i=0;i<map.edgelist[from].size();i++){
            int index= map.edgelist[from].get(i).to;
            if(!isVisited[index]){
                costs[index] = map.edgelist[from].get(i).weight;
                clauProfit(index);
                now_hp = now_hp + profit[index];
            }
        }
        
        income[from] = now_hp - costs[from];
        profit[from] = income[from] - costs[from];
    }

    private static void clauTime(int from) {

        //for every edges
        for(int i=0;i<map.edgelist[from].size();i++){
            long temp1 = costs[map.edgelist[from].get(i).to] - curHp;
            if(temp1 > 0){
                curHp += temp1;
                ans += temp1;
            }
            curHp = curHp - costs[map.edgelist[from].get(i).to];
            curHp += hp[map.edgelist[from].get(i).to];
            clauTime(map.edgelist[from].get(i).to);
        }

        //hp is weakly
        long temp2 = costs[from] - curHp;
        if(temp2>0){
            curHp += temp2;
            ans += temp2;
        }
        curHp = curHp - costs[from];
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
/*
5
4 2 1 5 7
1 2 4
1 3 5
4 2 9
5 2 3

23

7
4 3 1 10 7 4 7
1 2 4
1 3 5
3 7 7
6 3 8
2 3 5
2 4 4

55
 */
