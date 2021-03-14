import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int caseTimes = in.nextInt();
        while (caseTimes-- > 0) {
            int len = in.nextInt();
            int[] arr = new int[len];
            int count = 0;
            Set<Integer> sites = new HashSet<>();

            for(int i =0; i < len ;i++){
                int data = in.nextInt();
                if(sites.add(data)){
                    arr[count] = data;
                    count++;
                }
            }
//            out.println("count: "+count);
            int[] list = new int[count];
            for(int i = 0;i<count;i++){
                list[i] =arr[i];
            }
            Arrays.sort(list);
//            for(int i =0; i<count;i++) {
//                out.print(list[i]+" ");
//            }
//            out.println();
            int ans = judge(list,count);
            out.println(ans);
        }
        out.close();
    }

    private static int judge(int[] list, int count) {
        if(count==1){
            return list[0];
        }else if(count==2){
            return list[1]%list[0]==0?list[1]:list[0]+list[1];
        }else{
            int firstBig = list[count-1];
            int secondBig = list[count-2];
            int thirdBig = list[count-3];
            if(firstBig%secondBig!=0&&secondBig%thirdBig!=0&&firstBig%thirdBig!=0) return firstBig+secondBig+thirdBig;
            if(firstBig%secondBig!=0 && firstBig <= thirdBig *2){
                int ans = firstBig+secondBig;
                for(int i = count-1;i>=0;i--){
                    if(firstBig%list[i]!=0&&secondBig%list[i]!=0){
                        ans += list[i];
                        return ans;
                    }
                }
                return ans;
            }
            if(firstBig%secondBig==0){
                if(firstBig/secondBig==2){
                    int ans1 = firstBig; int ans2 = secondBig;int count1=0;int count2 =0;
                    for(int i =count-3;i>=0;i--){
                        if(count1!=2&&firstBig%list[i]!=0){
                            ans1+=list[i];
                            count1++;
                        }
                        if(count2!=2&&secondBig%list[i]!=0){
                            ans2+=list[i];
                            count2++;
                        }
                        if(count1==2 && count2==2){
                            return Math.max(ans1,ans2);
                        }
                    }
                    return Math.max(ans1,ans2);
                }
                if(firstBig/secondBig==3){
                    int ans = firstBig;int count1=0;
                    for(int i = count-1;i>=0;i--){
                        if(count1!=2&&firstBig%list[i]==0){
                            ans+=list[i];
                            count1++;
                        }
                        if (count1==2){
                            return ans;
                        }
                    }
                    return ans;
                }

            }
        }
        return 0;
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
