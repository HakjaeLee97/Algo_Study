package a11월3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_s3_2607_비슷한단어 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String first = br.readLine();
		int size = first.length();
		int cnt = 0;
		for (int i = 0; i < N-1; i++) {
			char[] now = br.readLine().toCharArray();
			char[] tmp = first.toCharArray();
			int len = now.length;
			for (int j = len-1; j >= 0; j--) {
				for (int k = size-1; k >= 0; k--) {
					if(now[j] == tmp[k]) {
						now[j] = 0;
						tmp[k] = 0;
						break;
					}
				}
			}
			// now에서 0이 아닌 경우가 1개 이하
			// tmp에서 0이 아닌 경우가 1개 이하
			// 두 개를 모두 충족하면 cnt++
			int ncnt = 0;
			int tcnt = 0;
			for (int j = 0; j < len; j++) {
				if(now[j] != 0) {
					ncnt++;
				}
			}
			for (int j = 0; j < size; j++) {
				if(tmp[j] != 0) {
					tcnt++;
				}
			}
			if(ncnt <= 1 && tcnt <= 1) {
				cnt++;
			}
		}
		br.close();
		System.out.println(cnt);
	}
}
