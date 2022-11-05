package a11월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s3_19941_햄버거분배 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String table = br.readLine();
		boolean[] eaten = new boolean[N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(table.charAt(i) == 'P') {
				for (int j = i-K; j <= i+K; j++) {
					if(j >= 0 && j < N && table.charAt(j) == 'H' && !eaten[j]) {
						eaten[j] = true;
						cnt++;
						break;
					}
				}
			}
		}
		br.close();
		System.out.println(cnt);
	}

}
