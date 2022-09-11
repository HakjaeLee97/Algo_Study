package a0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_g4_2206_벽부수고이동하기 {

	static int N, M;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map, move;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		br.close();
		bfs();
		int ans = -1;
		if (move[N - 1][M - 1] != 0) {
			ans = move[N - 1][M - 1];
		}
		System.out.println(ans);
	}

	static void bfs() {
		boolean[][][] visited = new boolean[2][N][M]; // 벽 부순 경우와 아닌 경우를 나눠서 방문체크 해줘야 한다.
		visited[0][0][0] = true;
		visited[1][0][0] = true;
		move = new int[N][M];
		move[0][0] = 1;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 0 });
		out: while (!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.peek()[1];
			int w = q.poll()[2];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < M) {
					if (map[nr][nc] == 1) {
						if (w == 0 && !visited[1][nr][nc]) {
							visited[1][nr][nc] = true;
							move[nr][nc] = move[r][c] + 1;
							q.offer(new int[] { nr, nc, 1 });
						}
					} else {
						if(!visited[w][nr][nc]) {
							visited[w][nr][nc] = true;
							move[nr][nc] = move[r][c] + 1;
							q.offer(new int[] { nr, nc, w });
						}
					}
					if(nr == N-1 && nc == M-1) {
						break out;
					}
				}
			}
		}
	}
}
