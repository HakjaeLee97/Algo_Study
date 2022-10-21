package a10월4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s1_20922_겹치는건싫어 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		int[] cnt = new int[100_001];
		int left = 0;
		int right = 0;
		int max = 0;
		int len = 0;
		at: while (right < N) {

			cnt[arr[right]]++;
			len++;
			while (cnt[arr[right]] > K) {
				cnt[arr[left]]--;
				left++;
				len--;
				if (left == right) {
					cnt = new int[100_001];
					len = 0;
					continue at;
				}
			}
			max = Math.max(max, len);
			right++;
		}
		System.out.println(max);
	}
}
