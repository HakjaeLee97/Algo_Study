package a0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g4_1253_좋다 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		int cnt = 0;
		i: for (int i = 0; i < N; i++) {
			a: for (int a = 0; a < N; a++) {
				int b = a + 1;
				while (b < N) {
					if (i == a) {
						continue a;
					} else if (i == b || a == b) {
						b++;
						continue;
					} else if (i < N && a < N && b < N && num[i] == num[a] + num[b]) {
						cnt++;
						continue i;
					} else {
						b++;
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
