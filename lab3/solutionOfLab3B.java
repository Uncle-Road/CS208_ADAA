import java.io.*;
import java.math.*;
import java.util.*;

public class solutionOfLab3B {
    static int ans;
    static int sumList[] = new int[21];
    static int word[][] = new int[21][21];
    static ArrayList<Integer> arrayList = new ArrayList<>();


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            HashMap<Character,Integer> map = new HashMap();
            putInMap(map);
            int N = in.nextInt();
            while(N-->0){
                String str = in.next();
                char arr[];
                ans =0;
                Arrays.fill(sumList,0);
                for(int i =0;i<21;i++)
                    Arrays.fill(word[i],0);
                arr=str.toCharArray();
                for(int i  =0 ;i<arr.length-1;i++) {
                    if (map.containsKey(arr[i]) && map.containsKey(arr[i + 1])) {
                        int a = map.get(arr[i]).intValue();
                        int b = map.get(arr[i + 1]).intValue();
                        if (a == b) {
                            continue;
                        }
                        word[a][b] += 1;
                        word[b][a] += 1;
                        sumList[a] += 1;
                        sumList[b] += 1;
                    }
                }
                out.println( DFS(0,0));
            }

        }
    }

    static void putInMap(HashMap map){
        map.put('b',0);
        map.put('c',1);
        map.put('d',2);
        map.put('f',3);
        map.put('g',4);
        map.put('h',5);
        map.put('j',6);
        map.put('k',7);
        map.put('l',8);
        map.put('m',9);
        map.put('n',10);
        map.put('p',11);
        map.put('q',12);
        map.put('r',13);
        map.put('s',14);
        map.put('t',15);
        map.put('v',16);
        map.put('w',17);
        map.put('x',18);
        map.put('y',19);
        map.put('z',20);
//        map.put('B',21);
//        map.put('C',22);
//        map.put('D',23);
//        map.put('F',24);
//        map.put('G',25);
//        map.put('H',26);
//        map.put('J',27);
//        map.put('K',28);
//        map.put('L',29);
//        map.put('M',30);
//        map.put('N',31);
//        map.put('P',32);
//        map.put('Q',33);
//        map.put('R',34);
//        map.put('S',35);
//        map.put('T',36);
//        map.put('V',37);
//        map.put('W',38);
//        map.put('X',39);
//        map.put('Y',40);
//        map.put('Z',41);
    }
    public static int DFS(int index,int ans){
        if(index == 20 ){
            int temp = 0;
            for(int i =0;i<arrayList.size();i++){
                temp += word[index][arrayList.get(i)]<<1;
            }
            ans +=Math.max(0,sumList[index]-temp);
            return ans;
        }else if(sumList[index] == 0){
            return DFS(index+1,ans);
        }else {
            int tempAns =  ans + sumList[index];
            for(int i =0;i<arrayList.size();i++){
                tempAns -= word[index][arrayList.get(i)]<<1;
            }
            arrayList.add(index);
            int temp1 = DFS(index+1,tempAns);
            arrayList.remove((Integer) index);
            int temp2 = DFS(index+1,ans);
            return Math.max(temp1,temp2);
        }
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }
    }
}
