package a10월2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g5_2467_용액 {

	public static void main(String[] args) throws Exception {
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int min = 2_000_000_001;
		int left = 0, right = N-1;
		int l = 0, r = N-1;
		while(left < right) {
			int sum = arr[left] + arr[right];
			if(Math.abs(sum) < min){
				l = left;
				r = right;
				min = Math.abs(sum);
			}
			if(sum > 0) {
				right--;
			} else if(sum < 0) {
				left++;
			} else {
				break;
			}
		}
		System.out.println(arr[l] + " " + arr[r]);
	}
}
