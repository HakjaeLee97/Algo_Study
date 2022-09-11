import java.util.*;
import java.io.*;

public class Main {
    public static int N, d, k, c;
    public static int[] arr;
    public static int[] check;
    public static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int count = 0;

        arr = new int[N + 1];
        check = new int[3001];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 초기 설정
        for(int i = 0; i < k; i++) {
            if(check[arr[i]] == 0) {
                count += 1;
            }
            check[arr[i]] += 1;
        }
        ans = count;

        for(int i = 0; i < N; i++) {
            if(ans <= count) {
                if(check[c] == 0)
                    ans = count + 1;
                else
                    ans = count;
            }

            if(check[arr[i]] == 1)
                count -= 1;
            check[arr[i]] -= 1;

            if(check[arr[(i + k) % N]] == 0)
                count += 1;
            check[arr[(i + k) % N]] += 1;
        }

        System.out.println(ans);
    }
}
