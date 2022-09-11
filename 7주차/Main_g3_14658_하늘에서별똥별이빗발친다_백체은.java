package a0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_g3_14658_하늘에서별똥별이빗발친다 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<int[]> stars = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			stars.add(new int[] { r, c });
		}
		int cnt = K;
		int min = K;
		for (int[] r : stars) {
			for (int[] c : stars) {
				cnt = K;
				for (int[] s : stars) {
					if(r[0] <= s[0] && s[0] <= r[0]+L && c[1] <= s[1] && s[1] <= c[1]+L) {
						cnt--;
					}
				}
				min = Math.min(min, cnt);
			}
		}
		br.close();
		System.out.println(min);
	}
}
