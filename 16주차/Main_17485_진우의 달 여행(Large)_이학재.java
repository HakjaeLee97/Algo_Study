package b17485;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][][] dp = new int[N][M][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {		
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int j = 0; j < M; j++) {
			for (int k = 0; k < 3; k++) {
				dp[0][j][k] = map[0][j];
			}
		}
		
		// 0은 왼아, 1은 아래, 2는 오른쪽 아래 방향
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(j+1 <= M-1) {
					dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2])+ map[i][j]; 
				}
				if(j== M-1) {
					dp[i][j][1] =  dp[i-1][j][2]+ map[i][j];
				}else if(j==0) {
					dp[i][j][1] = dp[i-1][j][0] + map[i][j];
				}else {
					dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2])+ map[i][j];
				}
				if(j-1>=0) {
					dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1])+ map[i][j]; 
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < M; j++) {
			for (int k = 0; k < 3; k++) {
				if(dp[N-1][j][k] != 0) {
					min = Math.min(min, dp[N-1][j][k]);
					
				}
				
			}
		}
		System.out.println(min);
		br.close();

	}
}
