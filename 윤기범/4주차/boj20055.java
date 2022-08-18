import java.io.*;
import java.util.*;

public class Main {
	public static int N, K;
	public static Info[] belt;
	public static int num = 1;
	
	static class Info {
		int remain;
		boolean isRobot;
		
		public Info(int remain, boolean isRobot) {
			this.remain = remain;
			this.isRobot = isRobot;
		}

		@Override
		public String toString() {
			return "[remain=" + remain + ", isRobot=" + isRobot + "] ";
		}

	}
	
	public static void move() {
		//System.out.println(Arrays.toString(belt));
		Info temp = belt[2*N-1]; // 마지막 정보 백업
		for(int i = 2*N-1; i > 0; i--) { // 벨트 돌리기
			belt[i] = belt[i-1];
		}
		belt[0] = temp;
		//System.out.println(Arrays.toString(belt));
		if(belt[N-1].isRobot) { //마지막에 로봇 올라가 있으면 
			belt[N-1].isRobot = false; // 로봇 내리기 
		}
		
		for(int i = N-1; i > 0; i--) {
			if(belt[i-1].isRobot) { // 로봇 존재하면
				if(belt[i].isRobot == false && belt[i].remain >= 1) { // 다음 벨트에 로봇 없고 내구도가 1 이상인 경우
					belt[i-1].isRobot = false; // 현재 위치 로봇 내리고
					belt[i].isRobot = true; // 다음 위치 로봇 올리고
					belt[i].remain -= 1; // 다음 위치 내구도 1 감소
				}
			}
		}
		if(belt[N-1].isRobot) //마지막에 로봇 올라가 있으면
			belt[N-1].isRobot = false; // 로봇 내리기
		
		if(belt[0].remain >= 1 && belt[0].isRobot == false) { // 첫 칸의 내구도가 0이 아니고 로봇이 없는 경우
			belt[0].isRobot = true; // 로봇 올림
			belt[0].remain -= 1;
			num++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new Info[2*N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 2 * N; i++) {
			belt[i] = new Info(Integer.parseInt(st.nextToken()), false);
		}
		
		int cnt = 0;
		boolean flag = false;
		while(true) {
			cnt++;
			move();
			int zeroCnt = 0;
			for(int i = 0; i < 2 * N; i++) {
				if(belt[i].remain == 0)
					zeroCnt++;
				if(zeroCnt >= K) {
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
		System.out.println(cnt);
	}
}
