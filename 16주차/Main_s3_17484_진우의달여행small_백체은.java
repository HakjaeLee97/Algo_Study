package a11월2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s3_17484_진우의달여행small {

	static int N, M, min = Integer.MAX_VALUE;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		for (int c = 0; c < M; c++) {
			dfs(2, map[0][c], 1, c);
		}
		System.out.println(min);
	}

	private static void dfs(int dir, int sum, int r, int c) {
		if(r == N) {
			min = Math.min(min, sum);
			return;
		}
		
		for (int d = -1; d <= 1; d++) {
			if(dir != d && c+d >= 0 && c+d < M) {
				dfs(d, sum+map[r][c+d], r+1, c+d);
			}
		}
	}
}
