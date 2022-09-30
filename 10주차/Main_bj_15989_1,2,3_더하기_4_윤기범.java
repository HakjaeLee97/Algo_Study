import java.util.*;
import java.io.*;

public class Main {
	public static int T;
	public static int[] arr;
	public static int[] dp = new int[10001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		arr = new int[T];
		
		for(int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 모두 1의 합으로 만들어 질 수 있으므로
		for(int i = 0; i < 10001; i++) {
			dp[i] = 1;
		}
		
		// 2를 포함한 합으로 만들 수 있는 경우
		for(int i = 2; i < 10001; i++) {
			dp[i] += dp[i - 2];
		}
		
		// 3을 포함한 합으로 만들 수 있는 경우
		for(int i = 3; i < 10001; i++) {
			dp[i] += dp[i - 3];
		}
		
		for(int i = 0; i < T; i++) {
			System.out.println(dp[arr[i]]);
		}
	}
}
