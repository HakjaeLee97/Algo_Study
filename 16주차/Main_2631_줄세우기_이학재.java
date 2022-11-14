package b2631;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N];
        dp[0] = 1;

        int ans = 0;
        for(int i=1;i<N;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(N-ans);
		br.close();
	}
}
