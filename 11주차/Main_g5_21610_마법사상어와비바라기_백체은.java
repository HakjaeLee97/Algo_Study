package a10월2주차;

import java.io.*;
import java.util.*;

public class Main_g5_21610_마법사상어와비바라기 {
	
	public static int N, M;
	public static int[][] map;
	public static boolean[][] visited;
	public static ArrayDeque<Cloud> clouds = new ArrayDeque<>();

	public static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
	
	public static class Cloud {
		int r;
		int c;
		
		public Cloud(int x, int y) {
			this.r = x;
			this.c = y;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 처음 구름 위치
		clouds.offer(new Cloud(N - 1, 0));
		clouds.offer(new Cloud(N - 1, 1));
		clouds.offer(new Cloud(N - 2, 0));
		clouds.offer(new Cloud(N - 2, 1));

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());

			for (Cloud cloud : clouds) {
				// 1 모든 구름이 di 방향으로 si칸 이동한다.
				cloud.r = (N + cloud.r + dr[d] * (s % N)) % N;
				cloud.c = (N + cloud.c + dc[d] * (s % N)) % N;

				// 2 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
				map[cloud.r][cloud.c]++;
			}

			while (!clouds.isEmpty()) {
				
				// 3 구름이 모두 사라진다.
				Cloud cloud = clouds.poll();

				// 구름이 생기는 칸은 3에서 사라진 칸이 아니어야 한다.
				visited[cloud.r][cloud.c] = true;

				// 물복사버그 마법을 시전
				int cnt = 0;

				for (int k = 1; k < 8; k += 2) { // 인접한 대각선 칸 검사
					int nr = cloud.r + dr[k];
					int nc = cloud.c + dc[k];

					if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] > 0) {
						cnt++;
					}
				}

				// 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 바구니의 물이 양이 증가
				map[cloud.r][cloud.c] += cnt;
			}

			// 5 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && map[r][c] >= 2) {
						clouds.offer(new Cloud(r, c));
						map[r][c] -= 2;
					}
				}
			}
			// 구름 사라진 칸 초기화
			visited = new boolean[N][N];
		}

		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ans += map[r][c];
			}
		}
		System.out.println(ans);
	}
}