package b1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		int[] arr = new int[N+1];
		
		for(int i =0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		

		int left = 0;
		int right = 0;
		int sum = 0;
		int answer = Integer.MAX_VALUE;
		while (left <=N && right <=N) {
			if(sum >= S && answer > right-left) {
				sum -= arr[left];
				answer = right-left;
				left++;
		
			}
			if(sum < S){
				sum += arr[right++];
			}
			else {
				sum-= arr[left++];
			}
		}
		if(answer == Integer.MAX_VALUE) System.out.println(0);
		else{
			System.out.println(answer);
		}
	}

}
