import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class solutionOfLab6B  {
    static class node {
        int parent;
        ArrayList<Integer> childs = new ArrayList<>();
        ArrayList<Long> weights = new ArrayList<>();
        int index;
        long time  =Integer.MAX_VALUE;
        node(int index) {
            this.index = index;
        }
    }
    static long reds[];
    static long sum[];
    static PriorityQueue<node> pq;
    static node list[];
    static boolean isVisited[];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();//vector num
        int E = in.nextInt();// edge num
        isVisited = new boolean[V+1];
        reds = new long[V+1];
        sum = new long[V+1];
        list = new node[V+1];

        Comparator<node> c = new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return (int) (o1.time - o2.time);
            }
        };
        pq = new PriorityQueue<node>(c);

        for (int i = 1; i <= V; i++) {
            list[i] = new node(i);
        }
        for (int i = 1; i <= E; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            long weight = in.nextLong();//the edge weight
            addEdge(v1, v2, weight);
        }
        for (int i = 1; i <= V; i++) {
            long red = in.nextLong();
            long green = in.nextLong();
            long time = red + green;
            reds[i] = red;
            sum [i] = time;
        }
        dijkstra(V);
        System.out.println(list[V].time);
    }

   

    static void dijkstra(int V) {
        int index = 1;
        list[1].time = 0;
        while (index < V){
            node curNode = list[index];
            if(isVisited[index]){
                index = pq.poll().index;
                continue;
            }
            isVisited[index] = true;
            ArrayList<Integer> childList = curNode.childs;
            for(int i =0;i< childList.size();i++){
                int next = childList.get(i).intValue();
                if(isVisited[next]){
                    continue;
                }
                long weight = curNode.weights.get(i).longValue();
                long diff = (curNode.time + weight) % sum[next];
                if(diff >= reds[next]){
                    list[next].time = Math.min(curNode.time + weight,list[next].time );
                }else {
                    list[next].time = Math.min(curNode.time + weight + reds[next] -diff, list[next].time);
                }
                pq.add(list[next]);
            }
            index = pq.poll().index;
        }
    }

    static void addEdge(int cur, int child, long weight) {
       list[cur].childs.add(child);
       list[cur].weights.add(weight);
       list[child].parent = cur;
    }
}
