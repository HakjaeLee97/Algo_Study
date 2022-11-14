package a11월2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g2_9527_1의개수세기 {

	static long[] bit;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		br.close();
		
		bit = new long[55]; // 10^16의 비트길이 54 
		bit[0] = 1;
		for (int i = 1; i <= 54; i++) {
			bit[i] = bit[i-1]*2 + (1L << i);
		}
		
		System.out.println(getOneCnt(B) - getOneCnt(A-1));
	}

	private static long getOneCnt(long x) {
		long ans = x & 1;
		
		for (int i = 54; i > 0; i--) {
			if((x & (1L << i)) != 0) {
				ans += bit[i-1] + (x - (1L << i) + 1);
				x -= (1L << i);
			}
		}
		return ans;
	}
}
