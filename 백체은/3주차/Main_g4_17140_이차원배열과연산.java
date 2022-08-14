package a0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g4_17140_이차원배열과연산 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());

		int[][] A = new int[100][100];

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		int R = 3, C = 3;

		while (A[r][c] != k && time <= 100) {
			if (R >= C) { // R연산
				int max = 0;
				for (int i = 0; i < R; i++) {
					int cnt[] = new int[101]; // 배열 A에 들어있는 수는 100보다 작거나 같은 자연수
					for (int j = 0; j < C; j++) {
						cnt[A[i][j]]++; // 개수 세기
					}
					int l = 0;
					for (int j = 1; j <= C && l < 100; j++) { // C : 현재 배열에서 가장 큰 값
						for (int z = 1; z <= 100; z++) {
							if (cnt[z] == j) {
								A[i][l++] = z;
								A[i][l++] = j;
							}
						}
					}
					for (int j = l; j <= C; j++) {
						A[i][j] = 0;
					}
					if (max < l) {
						max = l;
					}
				}
				C = max;

			} else { // C연산
				int max = 0;
				for (int j = 0; j < C; j++) {
					int cnt[] = new int[101];
					for (int i = 0; i < R; i++) {
						cnt[A[i][j]]++;
					}
					int l = 0;
					for (int i = 1; i <= R; i++) {
						for (int z = 1; z <= 100; z++) {
							if (cnt[z] == i) {
								A[l++][j] = z;
								A[l++][j] = i;
							}
						}
					}
					for (int i = l; i <= R; i++) {
						A[i][j] = 0;
					}
					if (max < l) {
						max = l;
					}
				}
				R = max;
			}
			time++;
		}

		System.out.print(time > 100 ? -1 : time);
	}
}