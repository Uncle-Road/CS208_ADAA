import java.util.*;

public class solutionOfLab8A {
    static class node {
        char letter;
        int frequency;

        node(char letter, int frequency) {
            this.letter = letter;
            this.frequency = frequency;
        }
    }
    static class IntComparator implements Comparator<node>{
        public int compare(node n1,node n2){
            if(n1.frequency == n2.frequency){
                return 0;
            }else if(n1.frequency > n2.frequency){
                return 1;
            }
            else {
                return -1;
            }
        }
    }
    static class DataComparator implements Comparator<HuffmanNode>{
        public int compare(HuffmanNode x,HuffmanNode y){
            return x.data - y.data;
        }
    }
    static class HuffmanNode{
        int data;
        char c;
        HuffmanNode left = null;
        HuffmanNode right = null;
        HuffmanNode(int data,char c){
            this.data = data;
            this.c = c;
        }

    }
    static int ans =0;

    public static void printCode(HuffmanNode root, String s){
        if(root.left==null&&root.right==null&&Character.isLetter(root.c)){
            ans+=s.length()* root.data;
//            System.out.println(root.c +": "+s);
            return;
        }
        printCode(root.left,s+"0");
        printCode(root.right,s+"1");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for(int i =0;i<N;i++){
            ans= 0;
            String a = in.next();
            char[] arr;
            HashMap<Character,Integer> map = new HashMap<>();
            arr = a.toCharArray();
            for(int j =0;j<arr.length;j++){
                if(map.containsKey(arr[j])){
                    map.compute(arr[j],(key,value)->value+1);
                }else {
                    map.put(arr[j],1);
                }
            }
//            map.forEach((key,value)->{
//                System.out.println(key+" " + value);
//            });
            ArrayList<node> nodeList = new ArrayList<>();
            map.forEach((key,value)->{
                node node1 = new node(key,value);
                nodeList.add(node1);
            });
            Collections.sort(nodeList,new IntComparator());
//            for(node n: nodeList){
//                System.out.println(n.letter + " " + n.frequency);
//            }
            PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(nodeList.size(),new DataComparator());
            for(int j =0;j< nodeList.size();j++){
                HuffmanNode node = new HuffmanNode(nodeList.get(j).frequency,nodeList.get(j).letter);
                queue.add(node);
            }
            HuffmanNode root = null;
            while(queue.size()>1){
                HuffmanNode x = queue.poll();
                HuffmanNode y = queue.poll();
                HuffmanNode z = new HuffmanNode(x.data+y.data,'+');
                z.left = x;
                z.right = y;
                root = z;
                queue.add(z);
            }
            printCode(root,"");
            System.out.println(ans);
        }
    }


}
