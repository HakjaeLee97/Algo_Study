package a10월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_s1_17615_볼모으기 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String balls = br.readLine();
		br.close();

		int min = 987654321;
		
		int cnt = 0;
		boolean change = false;
		for (int i = 0; i < N; i++) {
			if(change && balls.charAt(i) == 'R') {
				cnt++;
				continue;
			}
			if(balls.charAt(i) == 'B') {
				change = true;
			}
		}
		min = Math.min(min, cnt);
		
		cnt = 0;
		change = false;
		for (int i = 0; i < N; i++) {
			if(change && balls.charAt(i) == 'B') {
				cnt++;
				continue;
			}
			if(balls.charAt(i) == 'R') {
				change = true;
			}
		}
		min = Math.min(min, cnt);
		
		cnt = 0;
		change = false;
		for (int i = N-1; i >= 0; i--) {
			if(change && balls.charAt(i) == 'R') {
				cnt++;
				continue;
			}
			if(balls.charAt(i) == 'B') {
				change = true;
			}
		}
		min = Math.min(min, cnt);
		
		cnt = 0;
		change = false;
		for (int i = N-1; i >= 0; i--) {
			if(change && balls.charAt(i) == 'B') {
				cnt++;
				continue;
			}
			if(balls.charAt(i) == 'R') {
				change = true;
			}
		}
		min = Math.min(min, cnt);
		
		System.out.println(min);
	}

}
