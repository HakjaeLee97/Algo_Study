package a0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g4_1806_부분합 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int sum = 0;
		int end = 0;
		int min = 987654321;
		for (int i = 0; i < N; i++) {
			while(sum < S && end < N) {
				sum += arr[end];
				end++;
			}
			if(sum >= S && min > end - i) {
				min = end - i;
			}
			sum -= arr[i];
		}
		if(min == 987654321) {
			System.out.println(0);
		} else {
			System.out.println(min);
		}
	}
}
