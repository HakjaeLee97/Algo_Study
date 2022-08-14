package a0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_g3_16236_아기상어 {

	static int[][] map;
	static boolean[][] visited;
	static int N, R, C, t = 0, size = 2, eat = 0, left = 0; // R, C : 상어 좌표, t : 이동 시간, left : 남은 물고기 수
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					R = r;
					C = c;
					map[R][C] = 0;
				} else if (map[r][c] > 0)
					left++;
			}
		}
		
		while (left != 0) { // 남은 물고기가 없을 때까지
			int temp = left;
			bfs(0, 0, 0);
			if (temp == left)
				break; // 탐색 후 남은 물고기 수가 같으면 더 이상 먹을 수 있는 물고기가 없음
		}
		System.out.print(t);
	}
	
	static void bfs(int r, int c, int depth) {
		visited = new boolean[N][N];
		ArrayDeque<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] { R, C, 1 });
		visited[R][C] = true;
		
		// 다음 먹을 물고기 위치와 가는 데 걸린 시간 초기화
		int nR = -1, nC = -1, nDepth = 987654321;

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			r = temp[0];
			c = temp[1];
			depth = temp[2];
			// 다음 먹기로 정해진 물고기까지의 이동거리보다 탐색할 위치가 더 오래 걸리면 탐색 종료
			if (nDepth < depth)
				break;
			// 4방으로 이동
			for (int d = 0; d < 4; d++) {
//				nx = r + dr[i];
//				ny = c + dc[i];
				int nr = r + dr[d];
				int nc = c + dc[d];
				// map 안에 있고, 현재 상어의 크기보다 같거나 작을 때 이동 가능
				if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && map[nr][nc] <= size) {
					// 현재 상어의 크기보다 작으면서 물고기가 있을 때 먹을 수 있음
					if (size > map[nr][nc] && map[nr][nc] > 0) {
						// 다음 먹을 물고기가 정해지지 않았을 때
						if (nR == -1) {
							nR = nr;
							nC = nc;
							nDepth = depth;
						}
						// 다음 먹을 물고기가 정해졌을 때
						else {
							// 다음 먹을 물고기보다 현재 물고기가 더 위쪽에 위치 -> 다음 먹을 물고기 변경
							if (nR > nr) {
								nR = nr;
								nC = nc;
							}
							// 다음 먹을 물고기보다 현재 물고기가 더 왼쪽에 위치 -> 다음 먹을 물고기 변경
							else if (nR == nr) {
								if (nC > nc) {
									nR = nr;
									nC = nc;
								}
							}
						}
					}
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc, depth + 1 });
				}
			}
		}

		if (nR != -1) {
			map[nR][nC] = 0;
			t += nDepth;
			left--;
			R = nR;
			C = nC;
			eat++;
			if (eat == size) {
				eat = 0;
				size++;
			}
		}
	}
}
