package a10월5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_s3_2512_예산 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int total = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		Arrays.sort(arr);
		int limit = Integer.parseInt(br.readLine());
		br.close();
		
		int ans = 0;
		if(total <= limit) {
			System.out.println(arr[N-1]);
		} else {
			int start = 0;
			int end = arr[N-1];
			while(start <= end) {
				int mid = (start+end)/2;
				int sum = 0;
				for (int i = 0; i < N; i++) {
					if(arr[i] > mid) {
						sum += mid;
					} else {
						sum += arr[i];
					}
				}
				if(sum > limit) {
					end = mid - 1;
				} else {
					start = mid + 1;
					ans = mid;
				}
			}
			System.out.println(ans);
		}
	}
}
