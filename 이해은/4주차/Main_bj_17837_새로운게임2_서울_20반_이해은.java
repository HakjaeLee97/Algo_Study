package a0819.camp;

import java.io.*;
import java.util.*;

public class Main_bj_17837_새로운게임2_서울_20반_이해은 {

	//우, 좌, 상, 하
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};
	static int N, K, answer;
	static int[][] colorMap;
	
	static List<Piece>[][] board;
	
	static class Piece {
		int index; // 1 ~ K
		int dir;   // 방향
		
		Piece() {
			index = 0; // 1 ~ K
			dir = 0;   // 방향
		}
		
		Piece(int index, int dir) {
			this.index = index;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 행, 열의 수
		K = Integer.parseInt(st.nextToken()); // 말의 수
		
		board = new List[N + 1][N + 1];
		for(int row = 0; row < N + 1; row++) {
			for(int col = 0; col < N + 1; col++) {
				board[row][col] = new ArrayList<>();
			}
		}
		
		colorMap = new int[N + 1][N + 1];
		for(int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 1; col <= N; col++) {
				colorMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(new Piece(k, Integer.parseInt(st.nextToken())));
		}
		
		answer = 1;
		
		while(true) {
			//종료조건
			if(endCheck()) break;
			if(answer > 1000) {
				answer = -1;
				break;
			}
			
			//순서대로 탐색
			boolean fourFlag = false;
			label : for(int k = 1; k <= K; k++) {
				for(int row = 1; row <= N; row++) {
					for(int col = 1; col <= N; col++) {
						for(int i = 0; i < board[row][col].size(); i++) {
							if(k == board[row][col].get(i).index) {
								List<Piece> tmpList = new ArrayList<>();
								
								for(int j = i; j < board[row][col].size(); j++) {
									tmpList.add(board[row][col].get(j));
								}
								
								int nr = row + dr[board[row][col].get(i).dir];
								int nc = col + dc[board[row][col].get(i).dir];
								
								if((nr < 1 || nr > N || nc < 1 || nc > N || colorMap[nr][nc] == 2)) { //방향이 반대가 되는 경우
									if(board[row][col].get(i).dir == 1) board[row][col].get(i).dir = 2;
									else if(board[row][col].get(i).dir == 2) board[row][col].get(i).dir = 1;
									else if(board[row][col].get(i).dir == 3) board[row][col].get(i).dir = 4;
									else if(board[row][col].get(i).dir == 4) board[row][col].get(i).dir = 3;
									
									nr = row + dr[board[row][col].get(i).dir];
									nc = col + dc[board[row][col].get(i).dir];
									
									if(!((nr < 1 || nr > N || nc < 1 || nc > N) || colorMap[nr][nc] == 2)) {
										if(colorMap[nr][nc] == 1) Collections.reverse(tmpList);
										//말의 이동
										//기존 위치에서 제거
										for(int j = board[row][col].size() - 1; j >= i; j--) {
											board[row][col].remove(j);
										}
										//새로운 위치에 추가
										board[nr][nc].addAll(board[nr][nc].size(), tmpList);
										if(endCheck()) {
											fourFlag = true;
											break label;
										}
									}
									
									continue label;
									
								} else {
									if(colorMap[nr][nc] == 1) Collections.reverse(tmpList);
									//기존 위치에서 제거
									for(int j = board[row][col].size() - 1; j >= i; j--) {
										board[row][col].remove(j);
									}
									//새로운 위치에 추가
									board[nr][nc].addAll(board[nr][nc].size(), tmpList);
									if(endCheck()) {
										fourFlag = true;
										break label;
									}
									continue label;
								}
							}
						}
					}
				}
			}
			
			if(fourFlag) break;
			if(endCheck()) break;
			answer++;
		}
		
		System.out.println(answer);
		br.close();
	}
	
	
	
	//종료 조건 체크, true : 종료
	static boolean endCheck() {
		for(int row = 1; row <= N; row++) {
			for(int col = 1; col <= N; col++) {
				if(board[row][col].size() >= 4) return true;
			}
		}
		return false;
	}

}
