package a11월2주차;

import java.io.*;
import java.util.StringTokenizer;

public class Main_g5_17485_진우의달여행large {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		int[][][] dp = new int[2][M][3]; // 스위칭하면서 사용할 것
		for (int i = 0; i < M; i++) {
			dp[0][i][0] = map[0][i]; // 왼쪽으로부터
			dp[0][i][1] = map[0][i]; // 중앙으로부터
			dp[0][i][2] = map[0][i]; // 오른쪽으로부터
		}
		
		for (int r = 1; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// 왼쪽에서 = min( 중앙에서+현재값, 오른쪽에서+현재값 )
				if(c-1 >= 0) {
					dp[r%2][c][0] = Math.min(dp[(r-1)%2][c-1][1]+map[r][c], dp[(r-1)%2][c-1][2]+map[r][c]);
				} else {
					dp[r%2][c][0] = 987654321;
				}
				// 중앙에서
				dp[r%2][c][1] = Math.min(dp[(r-1)%2][c][0]+map[r][c], dp[(r-1)%2][c][2]+map[r][c]);
				// 오른쪽에서
				if(c+1 < M) {
					dp[r%2][c][2] = Math.min(dp[(r-1)%2][c+1][0]+map[r][c], dp[(r-1)%2][c+1][1]+map[r][c]);
				} else {
					dp[r%2][c][2] = 987654321;
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 3; j++) {
				ans = Math.min(ans, dp[(N-1)%2][i][j]);
			}
		}
		System.out.println(ans);
	}
}
