import java.io.*;
import java.util.*;

public class solutionOfLab2 {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int caseTimes = in.nextInt();
        while (caseTimes-- > 0) {
            int ans1;int ans2 =0;
            int len = in.nextInt();
            int[] list = new int[len];
            for(int i =0;i<len;i++){
                list[i] = in.nextInt();
            }
            Arrays.sort(list);
            int firstBig = list[len-1];
            ans1 = firstBig;
            
            //force to find the a[0] the biggest two num

            outer:for(int i =len -2;i>=0;i--){
                if(firstBig%list[i]!=0){
                    ans1 += list[i];
                    for(int j = i-1;j>=0;j--){
                        if(firstBig%list[j]!=0&&list[i]%list[j]!=0){
                            ans1+=list[j];
                            break ;
                        }
                    }
                    break;
                }
            }

            //only 1/2 1/3 1/5 can big than 1
            int[] arr =new int[3];
            arr[0] =0;arr[1] =0;arr[2] =0;
            for(int i = len-2;i>=0;i--){
                if(firstBig == 2*list[i]){
                    arr[0] = list[i];

                }
                if(firstBig == 3*list[i]){
                    arr[1] = list[i];

                }
                if(firstBig == 5*list[i]){
                    arr[2] = list[i];

                }
            }
            ans2 = (arr[1]+arr[0]+arr[2]);
            out.println(ans1>ans2?ans1:ans2);
        }

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
