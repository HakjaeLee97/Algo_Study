import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            char[] arr = br.readLine().toCharArray();
            int k = Integer.parseInt(br.readLine());

            if(k == 1) {
                System.out.println("1 1");
                continue;
            }

            int[] alpha = new int[26];
            for(int j = 0; j < arr.length; j++) {
                alpha[arr[j] - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;
            for(int j = 0; j < arr.length; j++) {
                if(alpha[arr[j] - 'a'] < k) continue;

                int count = 1;
                for(int l = j + 1; l < arr.length; l++) {
                    if(arr[j] == arr[l]) count++;
                    if(count == k) {
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE)
                System.out.println("-1");
            else
                System.out.println(min + " " + max);
        }
    }
}
