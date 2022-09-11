import java.util.*;
import java.io.*;

public class Main {
    public static String word;
    public static char[] arr;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();
        arr = word.toCharArray();
        int len = arr.length;

        // a 개수 파악
        int cnt = 0;
        for(int i = 0; i < len; i++) {
            if(arr[i] == 'a')
                cnt++;
        }

        // 슬라이딩 윈도우
        for(int i = 0; i < len; i++) {
            int count = 0;
            for(int j = i; j < i + cnt; j++) {
                if(arr[j % len] == 'b')
                    count++;
            }
            ans = Math.min(ans, count);
        }
        System.out.println(ans);
    }
}
