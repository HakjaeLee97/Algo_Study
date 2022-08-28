package a0829;

import java.io.*;
import java.util.*;

public class Main_g3_20057_마법사상어와토네이도 {

	static int N;
	static int[][] map;
	static int[] dir = { 0, 1, 2, 3 }; // 0: 상, 1: 하, 2: 좌, 3: 우
	static int[] nextDir = { 2, 3, 1, 0 }; // 현재 방향에서 다음방향
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int res; // 격자 밖으로 나간 모래 양

	static int dsr[][] = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 },
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } }; // 모래 퍼지는 r방향
	static int dsc[][] = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } }; // 모래 퍼지는 c방향
	static int rate[] = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		int curR = N / 2, curC = N / 2; // 시작점
		int curDir = 2; // 시작 방향 왼쪽
		int nr = 0, nc = 0; // 다음 칸
		int d = 1; // 이동해야 하는 칸 수
		int cnt = 0; // 이동 횟수
		int check = 0; // 이동해야 하는 칸만큼 2번 이동했는지

		while (true) {
			if (curR == 0 && curC == 0) { // (1,1) 도착하면 소멸
				break;
			}
			nr = curR + dr[curDir];
			nc = curC + dc[curDir];
			cnt++;
			map[nr][nc] += map[curR][curC];
			map[curR][curC] = 0; // x 자리는 이동했으므로 비우기
			int sand = map[nr][nc]; // 모래 질량
			int a = sand; // a 칸에 들어갈 질량
			int sr = 0, sc = 0; // 모래 흩날리는 좌표
			for (int i = 0; i < 9; i++) { // 비율이 적혀있는 9칸에 배치
				sr = nr + dsr[curDir][i];
				sc = nc + dsc[curDir][i];
				int amount = (int) (sand * (rate[i] * 0.01));

				if (0 <= sr && sr < N && 0 <= sc && sc < N) // 범위 안 벗어나면 map에 쌓기
					map[sr][sc] += amount;
				else { // 범위 벗어날 경우 res에 더하기
					res += amount;
				}
				a -= amount;
			}
			int ar = nr + dsr[curDir][9]; // a칸에 배치
			int ac = nc + dsc[curDir][9];
			if (0 <= ar && ar < N && 0 <= ac && ac < N) // 범위 안 벗어나면 map에 쌓기
				map[ar][ac] += a;
			else { // 범위 벗어날 경우 res에 더하기
				res += a;
			}
			map[nr][nc] = 0; // y 자리 모래 비우기

			if (cnt == d) {
				cnt = 0;
				curDir = nextDir[curDir];
				check++;
			}
			if (check == 2) {
				check = 0;
				d++; // 이동해야 하는 칸만큼 2번 이동 했으면 이동해야 하는 칸 수 늘리기
			}
			curR = nr;
			curC = nc;
		}
		System.out.print(res);
	}

}