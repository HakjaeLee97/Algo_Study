package b15989;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[][] dp;
		for(int i = 0; i<T; i++) {
			int n = Integer.parseInt(br.readLine());
			dp = new int[n+1][4];
			if(n == 0) sb.append("0");
			else if(n == 1) sb.append("1");
			else if(n == 2) sb.append("2");
			else if(n == 3) sb.append("3");
			else {
				dp[1][1] = 1;
				dp[2][1] = 1;
				dp[2][2] = 1;
				dp[3][1] = 1;
				dp[3][2] = 1;
				dp[3][3] = 1;
				for(int j = 4; j<=n; j++) {
					dp[j][1] = 1;
					dp[j][2] = dp[j-2][1] + dp[j-2][2];
					dp[j][3] = dp[j-3][1] + dp[j-3][2] + dp[j-3][3];
				}
				sb.append(dp[n][3]+dp[n][2]+dp[n][1]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
}

