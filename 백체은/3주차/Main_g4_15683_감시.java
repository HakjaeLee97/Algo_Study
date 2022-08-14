package a0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CCTV {
	int type;
	int r;
	int c;

	public CCTV(int type, int r, int c) {
		this.type = type;
		this.r = r;
		this.c = c;
	}
}

public class Main_g4_15683_감시 {

	static List<CCTV> cctvs;
	static int[][] map, copyMap;
	static int[] directions; // 방향 순열
	static int[] dr = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dc = { 0, 1, 0, -1 };
	static int NumOfCCTV, N, M;
	static int min = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cctvs = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (1 <= map[r][c] && map[r][c] <= 5) {
					cctvs.add(new CCTV(map[r][c], r, c));
				}
			}
		}
		br.close();
		directions = new int[cctvs.size()];
		dfs(0);
		System.out.print(min);
	}

	static void dfs(int depth) {
		if (depth == cctvs.size()) {
			// 원본 데이터 유지
			copyMap = new int[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					copyMap[r][c] = map[r][c];
				}
			}

			for (int i = 0; i < cctvs.size(); i++) {
				CCTV cctv = cctvs.get(i);
				int d = directions[i];
				observe(cctv, d);
			}

			// 사각지대 개수 구하기
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (copyMap[r][c] == 0) {
						cnt++;
					}
				}
			}
			min = Math.min(min, cnt);
			return;
		} 
			for (int d = 0; d < 4; d++) {
				directions[depth] = d;
				dfs(depth + 1);
			}
		}

	static void observe(CCTV cctv, int d) {
		if (cctv.type == 1) {
			observeDirection(cctv, d);
		} else if (cctv.type == 2) {
			if (d == 0 || d == 2) {
				observeDirection(cctv, 0);
				observeDirection(cctv, 2);
			} else {
				observeDirection(cctv, 1);
				observeDirection(cctv, 3);
			}
		} else if (cctv.type == 3) {
				observeDirection(cctv, d % 4);
				observeDirection(cctv, (d + 1) % 4);
		} else if (cctv.type == 4) {
			if (d == 0) { // 좌상우
				observeDirection(cctv, 0);
				observeDirection(cctv, 1);
				observeDirection(cctv, 3);
			} else if (d == 1) { // 상우하
				observeDirection(cctv, 0);
				observeDirection(cctv, 1);
				observeDirection(cctv, 2);
			} else if (d == 2) { // 우하좌
				observeDirection(cctv, 1);
				observeDirection(cctv, 2);
				observeDirection(cctv, 3);
			} else if (d == 3) { // 하좌상
				observeDirection(cctv, 2);
				observeDirection(cctv, 3);
				observeDirection(cctv, 0);
			}
		} else if (cctv.type == 5) {
			observeDirection(cctv, 0);
			observeDirection(cctv, 1);
			observeDirection(cctv, 2);
			observeDirection(cctv, 3);
		}
	}

	static void observeDirection(CCTV cctv, int d) {
		int nr = cctv.r + dr[d];
		int nc = cctv.c + dc[d];

		while (0 <= nr && nr < N && 0 <= nc && nc < M && copyMap[nr][nc] != 6) {
			copyMap[nr][nc] = -1;
			nr += dr[d];
			nc += dc[d];
		}
	}

}