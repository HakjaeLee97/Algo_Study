package August_third;

import java.util.*;
import java.io.*;

public class bj_20055_컨베이어벨트위의로봇_Gold_5 {

	static int N;
	static int K;
	static int[]belt;
	static int[]robot;
	static int time = 1;
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		belt = new int[N*2];
		robot = new int[N];
		for(int i=0;i<N*2;i++) belt[i] = Integer.parseInt(st.nextToken());
		
	}
	
	static void output() {
		System.out.println(time);
	}
	
	static void operation() {
		
		int tmp = belt[N*2-1];
		
		for(int i=N*2-1; i>0; i--) {
			belt[i] = belt[i - 1];
		}
		for(int i=N-1; i>0 ;i--) {
			robot[i] = robot[i - 1];
		}
		
		belt[0] = tmp;
		robot[0] = 0;
		
	
		//로봇 내리기
		robot[N-1] = 0;
		
		//2 로봇 움직이기
		
		for(int i=N-1;i>0;i--) {
			//만약 위치에 로봇이 있고, 다음 위치에 로봇이 없다면
			if(robot[i-1]==1) {
				
				if(robot[i] == 0 && belt[i] > 0) {
			
					robot[i-1] = 0;
					robot[i] = 1;
					belt[i]--;
					
					}
				}
		}
		
		
		//로봇 올리기
		if(belt[0]>0) {
			robot[0] = 1;
			belt[0]--;
		}
		
		
	}

	
	static void calc() {
		
		
		while(true) {
			operation(); //돌리기 + 로봇 움직이기 + 로봇 올리기
		
			
			//내구도 확인
			int check = 0;
			for(int i=0;i<N*2;i++) if(belt[i]==0) check++;
			if(check>=K) break;

			
			time++;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		input();
		
		calc();
		
		output();
		
	}
}
