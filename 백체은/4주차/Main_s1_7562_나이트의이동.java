package a0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_s1_7562_나이트의이동 {

	static int N, curR, curC, desR, desC, ans;
	static boolean[][] visited;
	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			curR = Integer.parseInt(st.nextToken());
			curC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			desR = Integer.parseInt(st.nextToken());
			desC = Integer.parseInt(st.nextToken());
			ans = 0;
			bfs(curR, curC, 0);
			sb.append(ans).append("\n");
		}
		br.close();
		System.out.print(sb);
	}

	static void bfs(int r, int c, int cnt) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.offer(new int[] {r, c});
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] rc = q.poll();
				r = rc[0];
				c = rc[1];
				if(r == desR && c == desC) {
					ans = cnt;
					break;
				}
				for (int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
			cnt++;
		}
	}
}
