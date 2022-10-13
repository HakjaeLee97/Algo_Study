import java.util.*;
import java.io.*;

public class Main {
	public static int N, X;
	public static int[] arr;
	public static int ans = 0, cnt = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new int[N];
		int sum = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 최초 sum 구하기
		for(int i = 0; i < X; i++) {
			sum += arr[i];
		}
		ans = sum;
		
		if(X == 1) {
			Arrays.sort(arr);
			int num = arr[N-1];
			if(num == 0) {
				System.out.println("SAD");
			}
			else {
				cnt = 0;
				for(int i = 0; i < N; i++) {
					if(arr[i] == num)
						cnt += 1;
				}
				System.out.println(num);
				System.out.println(cnt);
			}
		}
		else {
			for(int i = 1; i <= N - X; i++) {
				sum -= arr[i - 1];
				sum += arr[i + X - 1];
				
				if(sum > ans) {
					ans = sum;
					cnt = 1;
				}
				else if(sum == ans) {
					cnt += 1;
				}
			}
			
			if(sum == 0)
				System.out.println("SAD");
			else {
				System.out.println(ans);
				System.out.println(cnt);
			}
		}
	}
}




