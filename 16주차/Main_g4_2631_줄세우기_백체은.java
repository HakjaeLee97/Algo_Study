package a11월2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_g4_2631_줄세우기 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] kid = new int[N];
		for (int i = 0; i < N; i++) {
			kid[i] = Integer.parseInt(br.readLine());
		}
		
		// 가장 큰 증가하는 부분 수열
		int max = 0;
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if(kid[j] < kid[i] && dp[i] < dp[j]+1) {
					dp[i] = dp[j]+1;
				}
			}
			max = Math.max(max, dp[i]);
		}
		br.close();
		System.out.println(N-max);
	}

}
