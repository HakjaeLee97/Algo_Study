package a0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_s2_4963_섬의개수 {

	static int w, h, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) {
				break;
			}
			map = new int[h][w];
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = 0;
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					if(!visited[r][c] && map[r][c] == 1) {
						bfs(r, c);
						ans++;
					}
				}
			}
			sb.append(ans).append("\n");
		}
		br.close();
		System.out.print(sb);
	}
	
	static void bfs(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.offer(new int[] {r, c});
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			r = rc[0];
			c = rc[1];
			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(0 <= nr && nr < h && 0 <= nc && nc < w && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}

}
