package a10월4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g5_22251_빌런호석 {

	static int N, K, P, X, cnt;
	static int[][] num = { { 1, 1, 1, 1, 1, 1, 0 }, 
							{ 0, 1, 1, 0, 0, 0, 0 }, 
							{ 1, 1, 0, 1, 1, 0, 1 },
							{ 1, 1, 1, 1, 0, 0, 1 }, 
							{ 0, 1, 1, 0, 0, 1, 1 }, 
							{ 1, 0, 1, 1, 0, 1, 1 }, 
							{ 1, 0, 1, 1, 1, 1, 1 },
							{ 1, 1, 1, 0, 0, 0, 0 }, 
							{ 1, 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 0, 1, 1 } };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		br.close();

		int[] real = new int[K];
		for (int i = 0; i < K; i++) {
			real[i] = X / (int) Math.pow(10, K - 1 - i);
			X %= (int) Math.pow(10, K - 1 - i);
		}

		dfs(0, 0, real, 0, 0);
		System.out.println(cnt);
	}

	public static void dfs(int depth, int reverse, int[] real, int zero, int now) {

		if (reverse > P) {
			return;
		}

		if (depth == K) {
			if(reverse > 0 && zero < K && now <= N) {
				cnt++;
			}
			return;
		}

		for (int i = 0; i < 10; i++) {
			int different = 0;
			for (int j = 0; j < 7; j++) {
				if (num[real[depth]][j] != num[i][j]) {
					different++;
				}
			}
			if(i == 0) {
				dfs(depth+1, reverse+different, real, zero+1, now*10 + i);
			} else {
				dfs(depth+1, reverse+different, real, zero, now*10 + i);
			}
		}
	}
}
