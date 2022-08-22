package a0819.camp;

import java.io.*;
import java.util.*;

public class Main_bj_17825_주사위윷놀이_서울_20반_이해은 {

	static int answer;
	static int[] dice;             // 나오는 주사위 눈의 순서를 저장할 배열
	static int[] piecePosition;    // 말 (0 ~ 3) 의 각각의 위치를 저장하는 배열
	static int[] permSelected;     // perm 함수에서 선택된 말들 선택 순열 
	static int[] score = {0, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0, 13, 16, 19, 25, 30, 35, 22, 24, 28, 27, 26};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		dice = new int[10];
		for(int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		piecePosition = new int[4];
		piecePosition[0] = piecePosition[1] = piecePosition[2] = piecePosition[3] = 1; 
		permSelected = new int[10];
		
		answer = 0;
		
		perm(0);
		
		System.out.println(answer);
		br.close();
	}
	
	//정지했을 때 파란 선을 따라 가는 것 => 윷놀이와 유사
	//도착할 자리에 이미 말이 있는 경우 불가함
	//도착지점을 제외한 나머지 말들을 선택할 수 있음
	static void perm(int depth) {
		if(depth == 10) {
			//기록된 personPattern 배열로 게임 진행
			int sum = 0;
			boolean failFlag = false;
			piecePosition = new int[4];
			piecePosition[0] = piecePosition[1] = piecePosition[2] = piecePosition[3] = 1;
	label : for(int i = 0; i < 10; i++) {
				//이번 턴에 움직일 말
				int curPiece = permSelected[i];
				//움직일 말의 현 위치
				int curPosition = piecePosition[curPiece];
				//움직여야되는 칸의 수 만큼 한칸씩 전진 반복
				for(int j = 0; j < dice[i]; j++) {
					if(j == 0) {
						//최초 시작단계인경우
						if(curPosition == 6) {
							curPosition = 23;
						} else if(curPosition == 11) {
							curPosition = 29;
						} else if(curPosition == 16) {
							curPosition = 31;
						} else if(curPosition == 30) {
							curPosition = 26;
						} else if(curPosition == 33) {
							curPosition = 26;
						} else if(curPosition == 28) {
							curPosition = 21;
						} else if(curPosition != 22) {
							curPosition++;
						}
					} else {
						if(curPosition == 28) {
							curPosition = 21;
						} else if(curPosition == 30) {
							curPosition = 26;
						} else if(curPosition == 33) {
							curPosition = 26;
						} else if(curPosition == 28) {
							curPosition = 21;
						} else if(curPosition != 22) {
							curPosition++;
						}
					}
					
					//도착지점에는 다른 말들이 없어야함
					if(j == dice[i] - 1) {
						for(int k = 0; k < 4; k++) {
							if(curPosition != 22 && curPosition == piecePosition[k]) {
								failFlag = true;
								break label;
							}
						}
					}
				}
				
				piecePosition[curPiece] = curPosition;
				sum += score[curPosition];
			}
			
			if(!failFlag) {
				if(answer < sum) answer = sum;
			}
			return;
		} 
		
		for(int i = 0; i < 4; i++) {
			permSelected[depth] = i;
			perm(depth + 1);
		}
	}
}
