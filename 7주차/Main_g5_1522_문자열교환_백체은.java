package a0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_g5_1522_문자열교환 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		br.close();
		int cntA = 0;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if(str.charAt(i) == 'a' ) {
				cntA++;
			} 
		}
		
		int cnt = 0;
		int ans = len;
		
		for (int i = 0; i < len; i++) {
			cnt = 0;
			for (int j = i; j < i + cntA; j++) {
				if(str.charAt(j%len) == 'b') {
					cnt++;
				}
			}
			ans = Math.min(ans, cnt);
		}
		System.out.println(ans);
	}
}
