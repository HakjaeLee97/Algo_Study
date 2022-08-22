package a0819.camp;

import java.io.*;
import java.util.*;

public class Main_bj_20055_컨베이어벨트_서울_20반_이해은 {

	static int N, K;
	static int[] dura;
	static boolean[] robot;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dura = new int[2 * N];
		robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int n = 0; n < 2 * N; n++) {
			dura[n] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 1;
		
		while(true) {
			rotation();
			moveRobot();
			if(dura[0] > 0) {
				robot[0] = true;
				dura[0]--;
			}
			
			if(!checkDura()) break;
			answer++;
		}
		
		System.out.println(answer);
		br.close();
	}
	
	//컨베이어밸트의 회전
	static void rotation() {
		//내구도의 회전
		int duraTmp = dura[2 * N - 1];
		for(int n = 2 * N - 1; n >= 1; n--) {
			dura[n] = dura[n - 1];
		}
		dura[0] = duraTmp;
		
		//로봇의 회전
		for(int n = N - 1; n >= 1; n--) {
			robot[n] = robot[n - 1];
		}
		robot[0] = false;
		robot[N - 1] = false;
	}
	
	//로봇의 이동
	static void moveRobot() {
		for(int n = N - 1; n >= 1; n--) {
			if(!robot[n] && robot[n-1] && dura[n] > 0) {
				robot[n] = robot[n - 1];
				robot[n - 1] = false;
				dura[n]--;
				if(n == 1) robot[0] = false;
			}
		}
		robot[N - 1] = false;
	}
	
	//내구도가 모두 소모되었다면 false
	//내구도가 아직 남아있다면 true
	static boolean checkDura() {
		int cnt = 0;
		for(int n = 0; n < 2 * N; n++) {
			if(dura[n] == 0) cnt++;
			if(K <= cnt) return false;
		}
		return true;
	}

}
