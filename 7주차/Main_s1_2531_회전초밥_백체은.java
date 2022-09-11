package a0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s1_2531_회전초밥 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] plate = new int[N];
		for (int i = 0; i < N; i++) {
			plate[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		int[] dup = new int[d+1];
		dup[c] = 30001;
		int start = 0;
		int cnt = 1;
		for (int i = 0; i < k; i++) {
			if(dup[plate[i]] == 0) {
				cnt++;
			}
			dup[plate[i]]++;
		}
		int max = cnt;
		while(start < N) {
			if(max == k+1) break;
			start++;
			if(--dup[plate[(start-1)]] == 0) {
				cnt--;
			}
			if(++dup[plate[(start+k-1)%N]] == 1) {
				cnt++;
			}
			max = Math.max(max, cnt);
		}
		System.out.println(max);
	}
}
