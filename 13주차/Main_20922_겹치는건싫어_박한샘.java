package October_third;

import java.io.*;
import java.util.*;
public class bj_20922_겹치는건싫어_Silver_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int []arr;
    static int []cnt;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        cnt = new int[100001];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int l =0, r= 0;
        int ans = 0 ;
        
        while(l<=r)
        {
          if(r<=n-1 &&cnt[arr[r]] <k)
          {
              cnt[arr[r]]++;
              r++;

          }
          else if(cnt[arr[r]] == k)
          {
              cnt[arr[l]]--;
              l++;
          }

            ans = Math.max(ans, r - l);
            if(r == n)
                break;
        }
        System.out.println(ans);
    }

}
