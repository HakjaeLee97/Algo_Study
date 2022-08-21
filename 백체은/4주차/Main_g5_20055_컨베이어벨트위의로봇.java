package a0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g5_20055_컨베이어벨트위의로봇 {

	static int stage;
	static boolean[] hasRobot;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] belt = new int[2*N+1];
		hasRobot = new boolean[2*N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		while(true) {
			
			stage++;
			// 컨베이어 이동
			belt[0] = belt[2*N];
			hasRobot[0] = hasRobot[2*N];
			for (int i = 2*N; i > 0; i--) {
				belt[i] = belt[i-1];
				hasRobot[i] = hasRobot[i-1];
			}
			// 내릴 위치의 로봇 떠나기
			hasRobot[N] = false;
			// 로봇 이동
			for (int i = N; i > 1; i--) {
				if(!hasRobot[i] && hasRobot[i-1] && belt[i] >= 1) {
					hasRobot[i] = true;
					hasRobot[i-1] = false;
					belt[i]--;
				}
			}
			// 내릴 위치의 로봇 떠나기
			hasRobot[N] = false;
			// 로봇 올리기
			if(!hasRobot[1] && belt[1] >= 1) {
				hasRobot[1] = true;
				belt[1]--;
			}
			int cnt = 0;
			for (int i = 1; i <= 2*N; i++) {
				if(belt[i] == 0) {
					cnt++;
				}
			}
			if(cnt >= K) {
				break;
			}
			
		}
		System.out.print(stage);
	}
}
