import java.io.*;
import java.util.*;

public class Main_2467_용액_이해은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int n = 0; n < N; n++) {
			arr[n] = Long.parseLong(st.nextToken());
		}
		
		int left = 0;
		int right = N - 1;
		int answerL = 0;
		int answerR = 0;
		long min = Long.MAX_VALUE;
		
		while(left < right) {
			//long sum = Math.abs(arr[left] + arr[right]);
			long sum = arr[left] + arr[right];
			//if(min > sum)
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				answerL = left;
				answerR = right;
			}
			if(sum >= 0L) right--;
			else left++;
		}
		
		System.out.println(arr[answerL] + " " + arr[answerR]);
		
		br.close();
	}

}
