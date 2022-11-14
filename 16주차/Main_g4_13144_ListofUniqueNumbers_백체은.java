package a11월2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g4_13144_ListofUniqueNumbers {

	// 회전 초밥 문제와 유사
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] cnt = new int[100001];

		int l = 0;
		int r = l;
		cnt[arr[l]] = 1; 
		long sum = 0; // 최악의 경우 (100000*99999)/2
		while(l < N) {
			while(r < N-1 && cnt[arr[r+1]] == 0) {
				r++; cnt[arr[r]]++; // cnt[arr[r++]]++; 의 경우 r++보다 cnt++가 먼저 실행됨 
			}
			sum += r-l+1;
			cnt[arr[l]]--; l++;
		}
		br.close();
		System.out.println(sum);
	}
}
