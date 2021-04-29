import java.util.*;

public class soultionOfLab9B {
    static class node {
        int u;
        int v;
        int happiness = 0;

        node(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    static class IntComparator implements Comparator<node> {
        public int compare(node n1, node n2) {
            return n1.v - n2.v;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<node> nodeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            nodeList.add(new node(u, v));
        }
        Collections.sort(nodeList, new IntComparator());
//        System.out.println();
//        for(int i =0;i<N;i++){
//            System.out.println(nodeList.get(i).u+" "+nodeList.get(i).v);
//        }
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int count = 0;
            for(int j =i-1;j>=0;j--){
                if(nodeList.get(i).u>=nodeList.get(j).u){
                    count++;
                }
            }
            nodeList.get(i).happiness = count;
            ans[count]++;
        }
//        for (int i = 0; i < N; i++) {
//            System.out.println(nodeList.get(i).u + " " + nodeList.get(i).v + " " + nodeList.get(i).happiness);
//        }

        for(int i =0 ;i<N;i++){
            System.out.println(ans[i]);
        }
    }
}
