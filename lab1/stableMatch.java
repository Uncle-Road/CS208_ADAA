import java.io.*;
import java.math.*;
import java.util.*;

public class stableMatch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        HashMap<String, Integer> name = new HashMap<>();

        String[] man = new String[N];
        String[] woman = new String[N];
        Queue<Integer> singleMan = new LinkedList();
        HashMap<Integer,Integer> singleWoman =new HashMap<>();
        //store the name
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < N; i++) {
                String a = in.next();
                name.put(a, i);
                if (j == 0) {
                    man[i] = a;
                    singleMan.offer(i);
                    singleWoman.put(i,i);
                } else {
                    woman[i] = a;

                }
            }
        }

        //now single man and all people name is ready
        //do the woman reserve list and man's preference list
        int[][] manPreferenceList = new int[N][N];
        int[][] womanREserveList = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int serial = name.get(in.next());
                manPreferenceList[i][j] = serial;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int serial = name.get(in.next());
                womanREserveList[i][serial] = j;
            }
        }

        int[] manMatchCount = new int[N];
        int[] womanMatchState = new int[N];
        int[] manMatchState = new int[N];
        for (int i = 0; i < N; i++) {
            manMatchCount[i] = 0;//store the man match which index woman
//            womanMatchState[i] = 0;//store womanMatchState ,i.e. the match status
//            manMatchState[i] = 0;
        }

        // work done this problem
        while (!singleMan.isEmpty()) {
            int a = singleMan.poll();//the new man
            int count = manMatchCount[a];//which index woman in list
            int b = manPreferenceList[a][count];//the woman
            //judge woman whether match with him
            if(singleWoman.containsKey(b)){
                womanMatchState[b]=a;
                manMatchState[a] =b;
                singleWoman.remove(b);
            }else {
                int c =womanMatchState[b];// the old man
                if(womanREserveList[b][a]<womanREserveList[b][c]){
                    singleMan.offer(c);
                    womanMatchState[b]=a;
                    manMatchState[a] =b;
                }else{
                    singleMan.offer(a);
                }
            }

            manMatchCount[a] +=1;
        }


        for(int i =0 ; i< N ;i++){
            System.out.println(man[i]+" "+woman[manMatchState[i]]);
        }

    }
}
/* test case
3
Xavier Yancey Zeus
Amy Bertha Clare
Amy Bertha Clare
Clare Bertha Amy
Amy Clare Bertha
Zeus Xavier Yancey
Yancey Xavier Zeus
Xavier Zeus Yancey
 */
