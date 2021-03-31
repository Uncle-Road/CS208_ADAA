import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();

        nodesList = new node[num];
        weightList = new long[num];
        sum = new long[num];
        s = new long[num];
        ans = 0;

        for (int i = 0; i < num; i++) {
            weightList[i] = in.nextLong();
        }
        for (int i = 0; i < num; i++) {
            nodesList[i] = new node(i);
            nodesList[i].weight = weightList[i];
        }

        for (int i = 0; i < num - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            nodesList[a].childs.add(nodesList[b]);
            nodesList[b].childs.add(nodesList[a]);
        }

        DFS1(nodesList[0], null, 0);
        ans = sum[0];
        DFS2(nodesList[0], null);
        System.out.println(ans);
    }

    static void DFS1(node node, node parent, int deep) {
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

}

