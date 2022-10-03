package a10월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_g5_20437_문자열게임2_제줄 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String w = br.readLine();
			int k = Integer.parseInt(br.readLine());
			
			 if(k == 1) { 
	                sb.append("1 1").append("\n");
	                continue;
	            }
			
			int[] alpha = new int[26];
			for (int i = 0; i < w.length(); i++) {
				alpha[w.charAt(i) - 'a']++;
			}
			
			int min = 987654321;
			int max = -1;
			for (int i = 0; i < w.length(); i++) {
				if (alpha[w.charAt(i) - 'a'] < k)
					continue;
				int cnt = 1;
				for (int j = i+1; j < w.length(); j++) {
					if (w.charAt(j) == w.charAt(i)) {
						cnt++;
					}
					if(cnt == k) {
						min = Math.min(min, j-i+1);
						max = Math.max(max, j-i+1);
//						System.out.println(min);
//						System.out.println(max);
						break;
					}
				}
			}
			if(min == 987654321 || max == -1) {
				sb.append(-1).append("\n");
			} else {
				sb.append(min).append(" ").append(max).append("\n");
			}
		}
		br.close();
		System.out.print(sb);
	}
}
