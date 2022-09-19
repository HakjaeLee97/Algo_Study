package a0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_g5_12919_A와B2 {

	static int ans;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		br.close();

		int n = T.length() - S.length();
		solution(0, n, T, S);
		System.out.print(ans);
	}

	static void solution(int depth, int n, String T, String S) {

		if (depth == n) {
			if (S.equals(T)) {
				ans = 1;
			}
			return;
		}

		StringBuffer sb = new StringBuffer(T);
		String T1 = sb.deleteCharAt(0).reverse().toString();
		sb = new StringBuffer(T);
		String T2 = sb.deleteCharAt(T.length() - 1).toString();
		if (T.charAt(0) == 'B' && T.charAt(T.length() - 1) == 'A') {
			solution(depth + 1, n, T1, S);
			solution(depth + 1, n, T2, S);
		} else if (T.charAt(0) == 'B' && T.charAt(T.length() - 1) != 'A') {
			solution(depth + 1, n, T1, S);
		} else if (T.charAt(0) != 'B' && T.charAt(T.length() - 1) == 'A') {
			solution(depth + 1, n, T2, S);
		}
	}
}
