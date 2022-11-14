package a11월2주차;

import java.io.*;
import java.util.*;

public class Main_g4_1027_고층건물 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] h = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int ans = 0;
		for (int i = 0; i < N; i++) { // 모든 건물에 대해
			int cnt = 0;
			// 왼쪽 탐색
			double max = Integer.MIN_VALUE;
			for (int j = i-1; j >= 0; j--) {
				if(max < (double)(h[j]-h[i])/(i-j)) {
					max = (double)(h[j]-h[i])/(i-j);
					cnt++;
				}
			}
			// 오른쪽 탐색
			max = Integer.MIN_VALUE;
			for (int j = i+1; j < N; j++) {
				if(max < (double)(h[j]-h[i])/(j-i)) {
					max = (double)(h[j]-h[i])/(j-i);
					cnt++;
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}
}
