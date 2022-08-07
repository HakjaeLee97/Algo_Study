package study;

import java.io.*;
import java.util.*;

public class BOJ_14891_톱니바퀴 {

static char[][] wheels;
	
	static void change(int index, int dir, int rl) {
		//기저조건
		if(index == 0 || index == 5) return;

		//회전 전에 맞물려 있는지 확인
		boolean bLeft = false;
		boolean bRight = false;
		
		if(wheels[index - 1][2] != wheels[index][6]) bLeft = true;
		if(wheels[index][2] != wheels[index + 1][6]) bRight = true;
		
		//자기자신의 회전
		if(dir == 1) {
			char temp = wheels[index][7];
			for(int i = 6; i >= 0; i--) {
				wheels[index][i + 1] = wheels[index][i];
			}
			wheels[index][0] = temp;
		} else {
			char temp = wheels[index][0];
			for(int i = 0; i < 7; i++) {
				wheels[index][i] = wheels[index][i + 1];
			}
			wheels[index][7] = temp;
		}
		
		//함수 실행 방향 확인
		//회전 톱니 바퀴의 양 옆의 톱니 바퀴가 다시 회전한 톱니바퀴를 회전시키면 안됨.
		//한쪽 방향으로만 재귀호출이 진행되어야하므로 rl 변수를 사용.
		//rl == 0 최초 회전시킨 톱니바퀴가 호출
		//rl == -1 왼쪽 방향으로 재귀 진행
		//rl == 1 오른쪽 방향으로 재귀 진행
		//극이 반대인지 확인
		if(rl == 0) {
			if(bLeft) {
				change(index - 1, -dir, -1);
			}
			if(bRight) {
				change(index + 1, -dir, +1);
			}
		} else if(rl == -1 && bLeft) {
			change(index - 1, -dir, -1);
		} else if(rl == 1 && bRight) {
			change(index + 1, -dir, +1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        wheels = new char[4 + 2][8];
        wheels[1] = br.readLine().toCharArray();
        wheels[2] = br.readLine().toCharArray();
        wheels[3] = br.readLine().toCharArray();
        wheels[4] = br.readLine().toCharArray();
        
        int k = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < k; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	//톱니바퀴의 번호
        	int index = Integer.parseInt(st.nextToken());
        	//방향 : -1 반시계, +1 시계
        	int dir = Integer.parseInt(st.nextToken());
        	change(index, dir, 0);
        }
        
        int score = 0;
        
        if(wheels[1][0] == '1') score += 1;
        if(wheels[2][0] == '1') score += 2;
        if(wheels[3][0] == '1') score += 4;
        if(wheels[4][0] == '1') score += 8;
        
        System.out.print(score);
	}

}