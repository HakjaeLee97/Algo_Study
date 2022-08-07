package August_First_Week;

import java.util.*;
import java.io.*;

public class no_14891 {
	
	static int[][]wheel = new int[4][8];
	static int[] isTurn;
	
	public static void main(String[] args) throws Exception {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	
		
		for(int i=0;i<4;i++) {
				String str = br.readLine();
			for(int j=0;j<8;j++) {
				wheel[i][j] = str.charAt(j) -'0';
			}
		}
	

		
		int T = Integer.parseInt(br.readLine());
	
		for(int i=0;i<T;i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int loc = Integer.parseInt(st.nextToken()) - 1;
			int turn = Integer.parseInt(st.nextToken());
			
			isTurn = new int[4];
			
			check(loc, turn);
			
			rotate(isTurn);
			
		}
		
		int answer = 0;
		
		for(int i=0;i<4;i++) {
		
			if(wheel[i][0]==1) {
				if(i==0) answer += 1;
				else answer += Math.pow(2, i);
			}
		}
		
		System.out.println(answer);
		

	}
	static void rotate(int[] isTurn) {
		
		for(int i=0;i<4;i++) {
			//수행을 해줘야 될때만 돌려야 되기 때문에 조건문(1은 시계 -1은 반시계)
			if(isTurn[i] != 0) {
				int [] temp = new int[8];
				
				int idx;
				
				for(int j=0;j<8;j++) {
					
					idx = j + isTurn[i]; //1이거나 -1(돌려준다)
					
				if(idx == -1) {
					idx = 7;
					
				}else if (idx == 8) {
					idx = 0;
				}
				//돌아간 인덱스에 원래 값이 었던 wheel[i][j]를 대입
				temp[idx] = wheel[i][j];
				}
				//wheel을 다시 대입
				wheel[i] = temp;
			}
		}
		
	}
	
	static void check(int loc,int turn) {
		
		//재귀로 들어온 애 돌려주기(-1 or 1)
		isTurn[loc] = turn;
		
		int prev = loc - 1;
		int next = loc + 1;
		
		//1번 바퀴일 경우 왼쪽이 없으니까 , 그리고 아직 안돌았다면,
		if(prev >= 0 && isTurn[prev]==0) {
			//왼쪽
			if(wheel[prev][2] != wheel[loc][6]) {
				//왼쪽 애들 재귀 (반대방향)
				check(prev, turn * -1);
			}
		}
		
		//마지막 바퀴일 경우 오른쪽이 없으니까
		if(next <= 3 && isTurn[next]==0) {
			//오른쪽
			if(wheel[next][6] != wheel[loc][2]) {
				//오른쪽 애들 재귀 (반대방향)
				check(next, turn * -1);
			}
		}
		
	}
	
	

}
