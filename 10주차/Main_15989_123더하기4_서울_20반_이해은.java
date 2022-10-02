import java.io.*;
import java.util.*;

public class Main_15989_123더하기4_서울_20반_이해은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());
		
		
		// 모든 경우의 수 (4 이상일 때)
		// 해당 숫자에 도달하기 위해 1만 사용할 경우의 수
		// +
		// 해당 숫자에 도달하기 위해 1과 2만을 사용할 경우의 수 (2를 반드시 포함)
		// +
		// 해당 숫자에 도달하기 위해 1, 2, 3을 사용할 경우의 수 (3을 반드시 포함)
		
		//1
		//1
		//dp[1][1] = 1
		
		//2
		//1+1 dp[2][1] = 1
		//2   dp[2][2] = 1
		
		//3
		//1+1+1  dp[3][1] = 1
		//2+1    dp[3][2] = 1
		//3      dp[3][3] = 1
		
		//4
		//1+1+1+1  dp[4][1] = dp[3][1]
		//2+1+1    dp[4][2] = dp[2][2] + dp[2][1]   // 1+1 dp[2][1]
		//2+2                                       // 2   dp[2][2]
		//3+1      dp[1][1] = 1
		
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] dp = new int[10001][4];
			
			dp[1][1] = 1;
			dp[2][1] = 1;
			dp[2][2] = 1;
			dp[3][1] = 1;
			dp[3][2] = 1;
			dp[3][3] = 1;
			
			for(int n = 4; n <= N; n++) {
				dp[n][1] = dp[n - 1][1];
				dp[n][2] = dp[n - 2][2] + dp[n - 2][1];
				dp[n][3] = dp[n - 3][3] + dp[n - 3][2] + dp[n - 3][1];
			}
			
			sb.append(dp[N][1] + dp[N][2] + dp[N][3]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
