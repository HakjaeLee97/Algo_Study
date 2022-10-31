package a10월5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s2_11501_주식 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int maxIdx = N-1;
			int max = arr[maxIdx];
			int idx = N-1;
			long sum = 0;
			while(idx >= 0) {

				if(arr[idx] >= max){
					maxIdx = idx;
					max = arr[idx];
				} else {
					sum += max - arr[idx];
				}
				idx--;
			}
			sb.append(sum).append("\n");
		}
		br.close();
		System.out.print(sb);
	}
}
