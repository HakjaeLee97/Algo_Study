import java.util.*;
import java.io.*;

public class Main {
	public static int n, m;
	public static int[] arr;
	public static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < m; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 1; i < m - 1; i++) {
			int maxL = 0;
			int maxR = 0;
			int criterion = 0;
			
			// 왼쪽으로 큰 값 찾기
			for(int j = 0; j < i; j++) {
				if(arr[j] > maxL)
					maxL = arr[j];
			}
			
			// 오른쪽으로 큰 값 찾기
			for(int j = i + 1; j < m; j++) {
				if(arr[j] > maxR)
					maxR = arr[j];
			}
			
			criterion = Math.min(maxL, maxR);
			if(arr[i] > criterion)
				continue;
			else
				ans += (criterion - arr[i]);
		}
		
		System.out.println(ans);
	}
}
