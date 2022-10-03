package b20437;

import java.util.*;
import java.io.*;

public class Main {
    static List<Integer>[] list = new ArrayList[26];
    static int K;
    static int short_ans;
    static int long_ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 26; i++){
            list[i] = new ArrayList<>();
        }
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++){
            for (int i = 0; i < 26; i++){
                list[i].clear();
            }
            String W = br.readLine();

            for (int i = 0; i < W.length(); i++){
                int alphabet = W.charAt(i) - 'a';
                list[alphabet].add(i);
            }

            K = Integer.parseInt(br.readLine());

            boolean flag = true;
            short_ans = Integer.MAX_VALUE;
            long_ans = Integer.MIN_VALUE;

            for (int i = 0; i < 26; i++){
                if (list[i].size() >= K){
                    flag = false;
                    sol(i);
                }
            }
            if (flag) {
                System.out.println(-1);
                continue;
            }
            else {
                System.out.printf("%d %d\n", short_ans, long_ans);
            }
        }
    }
    public static void sol(int index){
        List<Integer> arr = list[index];
        int start = 0;
        int end = K - 1;
        while(end < arr.size()){
            short_ans = Math.min(short_ans,arr.get(end) - arr.get(start) + 1);
            long_ans = Math.max(long_ans,arr.get(end) - arr.get(start) + 1);
            start++;
            end++;
        }
    }
}
