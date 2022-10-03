package b17615;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String balls = br.readLine();
		int len = balls.length();
		//빨간 공 개수
		int rcnt = 0;
		//O(n) 작업
		for(int i = 0; i<len; i++) {
			if(balls.charAt(i) == 'R') {
				rcnt++;
			}
		}
		//최소 이동 회수
		int best = Integer.MAX_VALUE; 
		//파란 공 개수
		int bcnt = len-rcnt; 
		//빨간 공 끼리 모으는 경우 먼저 생각

		//빨간 공을 전부 오른쪽 or 왼쪽으로 보낸다
		int red_right = 0;
		int red_left = 0;
		
		int blue_right = 0;
		int blue_left = 0;
		
		boolean flag_red = false;
		boolean flag_blue = false;
		for(int i = len-1; i>=0; i--) {
			if(balls.charAt(i)=='B') flag_red = true;
			else if(flag_red && balls.charAt(i) == 'R') {
				red_right ++;
			}
			if(balls.charAt(i)=='R') flag_blue = true;
			else if(flag_blue && balls.charAt(i) == 'B') {
				blue_right ++;
			}
		}
		
		flag_red = false;
		flag_blue =false;
		
		for(int i = 0; i<len; i++) {
			if(balls.charAt(i)=='B') flag_red = true;
			else if(flag_red && balls.charAt(i) == 'R') {
				red_left ++;
			}
			
			if(balls.charAt(i)=='R') flag_blue = true;
			else if(flag_blue && balls.charAt(i) == 'B') {
				blue_left ++;
			}
		}
		best = Math.min(Math.min(blue_left, blue_right), Math.min(red_left, red_right));
		System.out.println(best);
		
		
	}

}
