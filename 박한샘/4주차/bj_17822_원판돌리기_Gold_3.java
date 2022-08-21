package August_third;

import java.util.*;
import java.io.*;

public class bj_17822_원판돌리기_Gold_3 {
	static class MyScanner {
		BufferedReader bf;
		StringTokenizer st;

		MyScanner() {
			bf = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(bf.readLine());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}

			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	static void printMap(int map[][], int N, int M) {
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MyScanner sc = new MyScanner();

		int N = sc.nextInt();
		int M = sc.nextInt();
		int T = sc.nextInt();

		int map[][] = new int[N + 2][M];

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < T; i++) {
			int x = sc.nextInt();
			int d = sc.nextInt();
			int k = sc.nextInt();

			for (int j = x; j <= N; j += x) { 
				if (d == 0) { 
					k %= M;
					int tempa[] = new int[M];
					for (int ii = 0; ii < M; ii++) {
						tempa[ii] = map[j][ii];
					}
					for (int ii = 0; ii < M; ii++) {
						map[j][(ii + k) % M] = tempa[ii];
					}

				} else { 
					k %= M;

					int tempa[] = new int[M];
					for (int ii = 0; ii < M; ii++) {
						tempa[ii] = map[j][ii];
					}
					int temp = map[j][0 + k];
					for (int ii = 0; ii < M; ii++) {
						map[j][ii] = tempa[(ii + k) % M];
					}
					map[j][0] = temp;
				}
			}

			boolean avg = false;
			boolean flag[][] = new boolean[N + 1][M];
			for (int ii = 1; ii <= N; ii++) {
				for (int jj = 0; jj < M; jj++) {

					if (map[ii][jj] != 0 && map[ii + 1][jj] == map[ii][jj]) {
						flag[ii][jj] = true;
						avg = true;
					}
					if (map[ii][jj] != 0 && map[ii - 1][jj] == map[ii][jj]) {
						flag[ii][jj] = true;
						avg = true;
					}
					if (map[ii][jj] != 0 && map[ii][(jj + 1) % M] == map[ii][jj]) {
						flag[ii][jj] = true;
						avg = true;
					}
					if (map[ii][jj] != 0 && map[ii][(jj - 1 + M) % M] == map[ii][jj]) {
						flag[ii][jj] = true;
						avg = true;
					}

				}
			}

			if (!avg) { // 지워야할 수가 없는 경우
				int count = 0;
				int sum = 0;
				for (int iii = 1; iii <= N; iii++) {
					for (int jjj = 0; jjj < M; jjj++) {
						if (map[iii][jjj] > 0) {
							count++;
							sum += map[iii][jjj];
						}
					}
				}
				if (count == 0)
					continue;

				for (int iii = 1; iii <= N; iii++) {
					for (int jjj = 0; jjj < M; jjj++) {
						if (map[iii][jjj] > 0) {
							if (sum == map[iii][jjj] * count) { 
								continue;
							} else if (sum > map[iii][jjj] * count) {
								map[iii][jjj]++;
							} else if (sum < map[iii][jjj] * count) {
								map[iii][jjj]--;
							}
						}
					}
				}
			} 
			else { 
				for (int ii = 1; ii <= N; ii++) {
					for (int jj = 0; jj < M; jj++) {
						if (flag[ii][jj])
							map[ii][jj] = 0;
					}
				}
			}

		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);

	}

}