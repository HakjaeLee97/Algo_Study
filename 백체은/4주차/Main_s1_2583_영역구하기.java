package a0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_s1_2583_영역구하기 {

	static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static int M, N, K;
	static List<Integer> area;
	static int sum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[M][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());

			for (int r = r1; r < r2; r++) {
				for (int c = c1; c < c2; c++) {
					if (!visited[r][c])
						visited[r][c] = true;
				}
			}
		}
		br.close();

		area = new ArrayList<Integer>();
		int cnt = 0;
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					cnt++;
					sum = 0;
					dfs(r, c);
					area.add(sum);
				}
			}
		}

		System.out.println(cnt);
		area.sort(null);
		for (Integer i : area) {
			System.out.print(i + " ");
		}
	}

	private static void dfs(int r, int c) {

		visited[r][c] = true;
		sum++;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (0 <= nr && nr < M && 0 <= nc && nc < N && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
}
