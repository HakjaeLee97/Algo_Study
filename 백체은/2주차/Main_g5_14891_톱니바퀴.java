package a0808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g5_14891_톱니바퀴 {
	static int[][] gear;
	static int[] d;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		gear = new int[4][8];

		for (int i = 0; i < 4; i++) {
			String state = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = state.charAt(j) - '0';
			}
		}

		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int gNum = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			d = new int[4];
			
			check(gNum, dir);
			turn(d);

		}

		br.close();

		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int num = gear[i][0];
			if (num == 1) {
				sum += Math.pow(2, i);
			}
		}

		System.out.print(sum);
	}

	static void check(int gNum, int dir) {
		d[gNum] = dir;

		int left = gNum - 1;
		int right = gNum + 1;

		if (left >= 0 && d[left] == 0) {
			if (gear[left][2] != gear[gNum][6]) {
				check(left, dir * -1);
			}
		}

		if (right <= 3 && d[right] == 0) {
			if (gear[right][6] != gear[gNum][2]) {
				check(right, dir * -1);
			}
		}
	}

	static void turn(int[] d) {
		for (int i = 0; i < 4; i++) {
			if (d[i] != 0) {
				int[] temp = new int[8];

				int idx;
				for (int j = 0; j < 8; j++) {
					idx = j + d[i];

					if (idx == -1) {
						idx = 7;
					} else if (idx == 8) {
						idx = 0;
					}

					temp[idx] = gear[i][j];
				}

				gear[i] = temp;
			}
		}
	}
}
