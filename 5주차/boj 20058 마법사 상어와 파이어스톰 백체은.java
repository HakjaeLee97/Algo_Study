package a0829;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_g4_20058_마법사상어와파이어스톰 {

	static int n, max;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		n = (int) Math.pow(2, N);
		map = new int[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int q = 0; q < Q; q++) {
			int L = Integer.parseInt(st.nextToken());

			int a = (int) Math.pow(2, L);
			int[][] temp = new int[n][n];
			for (int r = 0; r < n; r += a) {
				for (int c = 0; c < n; c += a) {
					for (int i = 0; i < a; i++) {
						for (int j = 0; j < a; j++) {
							temp[r + i][c + j] = map[r + a - j - 1][c + i];
						}
					}
				}
			}
			map = temp;

			// 얼음 양 조절
			int[][] copy = new int[n][n];

			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (map[r][c] == 0)
						continue;
					int ice = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (0 <= nr && nr < n && 0 <= nc && nc < n && map[nr][nc] > 0) {
							ice++;
						}
					}
					if (ice < 3) {
						copy[r][c] = map[r][c] - 1;
					} else {
						copy[r][c] = map[r][c];
					}
				}
			}
			map = copy;
		}
		br.close();

		// 남은 얼음 계산, 최대 얼음 덩어리
		int ans = 0;
		max = 0;
		boolean[][] visited = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ans += map[r][c];
				if (!visited[r][c] && map[r][c] != 0) {
					bfs(r, c, visited);
				}
			}
		}
		System.out.println(ans);
		System.out.println(max);
	}

	static void bfs(int r, int c, boolean[][] visited) {
		int cnt = 1;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.offer(new int[] { r, c });
		while (!q.isEmpty()) {
			r = q.peek()[0];
			c = q.poll()[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(0 <= nr && nr < n && 0 <= nc && nc < n && !visited[nr][nc] && map[nr][nc] != 0) {
					cnt++;
					visited[nr][nc] = true;
					q.add(new int[] {nr, nc});
				}
			}
		}
		max = Math.max(max, cnt);
	}
}
