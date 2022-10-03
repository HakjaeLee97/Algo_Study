package a10월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_s1_15989_123더하기4_dp {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[] dp = new int[n+1];
			dp[1] = 1;
			for (int i = 2; i <= n; i++) {
				dp[i] = dp[i-1] + (i/6) + 1;
				if(i%6 == 1) dp[i] -= 1;
			}
			System.out.println(dp[n]);
			// 2*3의 배수마다 등차+1
			// 2*3의 배수+1에서는 -1
			// 이유는 모르겠음
		}
		br.close();
	}
}
