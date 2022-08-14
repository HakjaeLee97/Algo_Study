package study;

import java.io.*;
import java.util.*;

public class BOJ_17143_낚시왕 {

	static int R;	// 행의 수 
	static int C;	// 열의 수 
	static int M;	// 상어의 수
	static Shark[][] map;
	static Shark[][] mapTemp;
	static int curKingPos;
	static class Shark {
		//int row;
		//int col;
		int s = 0;
		int d = 0;
		int z = 0;
		
		Shark() {
			//row = 0;
			//col = 0;
			s = 0;
			d = 0;
			z = 0;
		}
		Shark(int s, int d, int z) {
			//this.row = row;
			//this.col = col;
			this.s = s;
			this.d = d;
			this.z = z;
 		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R + 1][C + 1];
		for(int row = 0; row < R + 1; row++) {
			for(int col = 0; col < C + 1; col++) {
				map[row][col] = new Shark();
			}
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			//
			int r = Integer.parseInt(st.nextToken()); //상어의 위치 row
			int c = Integer.parseInt(st.nextToken()); //상어의 위치 col
			
			int s = Integer.parseInt(st.nextToken()); //속력, 초당 이동 칸
			//이동 방향
			//1: 위 2: 아래 3:오른쪽 4:왼쪽
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken()); //크기
			
			map[r][c] = new Shark(s, d, z);
		}
		
		//낚시 왕의 이동
		int catchTotalSize = 0;
		for(curKingPos = 1; curKingPos <= C; curKingPos++) {
			int catchFishRow = 1;
			int catchFishCol = curKingPos;
			
			//낚시 왕의 포획
			while(true) {
				if(catchFishRow > R) break;
				//한칸씩 내려가면서 상어 탐색 있으면 포획
				if(map[catchFishRow][catchFishCol].z != 0) {
					catchTotalSize += map[catchFishRow][catchFishCol].z;
					map[catchFishRow][catchFishCol] = new Shark();
					break;
				}
				
				catchFishRow++;
			}
			
			mapTemp = new Shark[R + 1][C + 1];
			for(int row = 0; row < R + 1; row++) {
				for(int col = 0; col < C + 1; col++) {
					mapTemp[row][col] = new Shark();
				}
			}
			
			moveShark();
			map = mapTemp;
		}
		
		System.out.println(catchTotalSize);
	}
	
	//1.낚시왕의 이동
	//2.낚시왕의 포획
	//3.상어의 이동
	//3-1. 상어가 해당 방향으로 해당 속력만큼 이동 벽을 만나면 방향 값이 바뀜
	//3-2. 상어의 이동이 끝났을 때 한칸에 두마리면 큰 상어가 작은 상어를 잡아 먹음
	// 이동 목표 좌표를 위한 배열을 별도로 만들고, 이동을 완료했을 때 그 위치에 이미 상어 객체가 있다면,
	// 크기가 큰 객체로 대체한다.
	
	static void moveShark() {
		//상어가 원 위치로 돌아오기까지 필요한 이동 횟수
		int rowCycle = (R - 1) * 2;
		int colCycle = (C - 1) * 2;
		
		for(int row = 1; row <= R; row++) {
			for(int col = 1; col <= C; col++) {
				if(map[row][col].z != 0) {
					//하
					if(row == 4) {
						System.out.print("");
					}
					if(map[row][col].d == 2) {
						int result = map[row][col].s % rowCycle;
						if(result <= R - row) {
							if(mapTemp[row + result][col].z != 0 && mapTemp[row + result][col].z < map[row][col].z) {
								mapTemp[row + result][col] = new Shark(map[row][col].s, 2, map[row][col].z);
							} else if(mapTemp[row + result][col].z == 0){
								mapTemp[row + result][col] = new Shark(map[row][col].s, 2, map[row][col].z);
							}
						} else if(result <= (R - row) + (R - 1)) {
							if(mapTemp[R - (result - (R - row))][col].z != 0 && mapTemp[R - (result - (R - row))][col].z < map[row][col].z) {
								mapTemp[R - (result - (R - row))][col] = new Shark(map[row][col].s, 1, map[row][col].z);
							} else if(mapTemp[R - (result - (R - row))][col].z == 0) {
								mapTemp[R - (result - (R - row))][col] = new Shark(map[row][col].s, 1, map[row][col].z);
							}
						} else {
							if(mapTemp[result - ((R - row) + (R - 1)) + 1][col].z != 0 && mapTemp[result - ((R - row) + (R - 1)) + 1][col].z < map[row][col].z) {
								mapTemp[result - ((R - row) + (R - 1)) + 1][col] = new Shark(map[row][col].s, 2, map[row][col].z);
							} else if (mapTemp[result - ((R - row) + (R - 1)) + 1][col].z == 0){
								mapTemp[result - ((R - row) + (R - 1)) + 1][col] = new Shark(map[row][col].s, 2, map[row][col].z);
							}
						}
					}
					
					//상
					else if(map[row][col].d == 1) {
						int result = map[row][col].s % rowCycle;
						if(result <= row - 1) {
							if(mapTemp[row - result][col].z != 0 && mapTemp[row - result][col].z < map[row][col].z) {
								mapTemp[row - result][col] = new Shark(map[row][col].s, 1, map[row][col].z);
							} else if(mapTemp[row - result][col].z == 0) {
								mapTemp[row - result][col] = new Shark(map[row][col].s, 1, map[row][col].z);
							}
						} else if(result <= (row - 1) + (R - 1)) {
							if(mapTemp[result - row + 2][col].z != 0 && mapTemp[result - row + 2][col].z < map[row][col].z) {
								mapTemp[result - row + 2][col] = new Shark(map[row][col].s, 2, map[row][col].z);
							} else if(mapTemp[result - row + 2][col].z == 0) {
								mapTemp[result - row + 2][col] = new Shark(map[row][col].s, 2, map[row][col].z);
							}
						} else {
							if(mapTemp[R - (result - ((row - 1) + (R - 1)))][col].z != 0 && mapTemp[R - (result - ((row - 1) + (R - 1)))][col].z < map[row][col].z) {
								mapTemp[R - (result - ((row - 1) + (R - 1)))][col] = new Shark(map[row][col].s, 1, map[row][col].z);
							} else if(mapTemp[R - (result - ((row - 1) + (R - 1)))][col].z == 0) {
								mapTemp[R - (result - ((row - 1) + (R - 1)))][col] = new Shark(map[row][col].s, 1, map[row][col].z);
							}
						}
					}
					
					//우
					else if(map[row][col].d == 3) {
						int result = map[row][col].s % colCycle;
						if(result <= C - col) {
							if(mapTemp[row][col + result].z != 0 && mapTemp[row][col + result].z < map[row][col].z) {
								mapTemp[row][col + result] = new Shark(map[row][col].s, 3, map[row][col].z);
							} else if(mapTemp[row][col + result].z == 0){
								mapTemp[row][col + result] = new Shark(map[row][col].s, 3, map[row][col].z);
							}
						} else if(result <= (C - col) + (C - 1)) {
							if(mapTemp[row][C - (result - (C - col))].z != 0 && mapTemp[row][C - (result - (C - col))].z < map[row][col].z) {
								mapTemp[row][C - (result - (C - col))] = new Shark(map[row][col].s, 4, map[row][col].z);
							} else if(mapTemp[row][C - (result - (C - col))].z == 0) {
								mapTemp[row][C - (result - (C - col))] = new Shark(map[row][col].s, 4, map[row][col].z);
							}
						} else {
							if(mapTemp[row][result - ((C - col) + (C - 1)) + 1].z != 0 && mapTemp[row][result - ((C - col) + (C - 1)) + 1].z < map[row][col].z) {
								mapTemp[row][result - ((C - col) + (C - 1)) + 1] = new Shark(map[row][col].s, 3, map[row][col].z);
							} else if (mapTemp[row][result - ((C - col) + (C - 1)) + 1].z == 0){
								mapTemp[row][result - ((C - col) + (C - 1)) + 1] = new Shark(map[row][col].s, 3, map[row][col].z);
							}
						}
					}
					
					//좌
					else if(map[row][col].d == 4) {
						int result = map[row][col].s % colCycle;
						if(result <= col - 1) {
							if(mapTemp[row][col - result].z != 0 && mapTemp[row][col - result].z < map[row][col].z) {
								mapTemp[row][col - result] = new Shark(map[row][col].s, 4, map[row][col].z);
							} else if(mapTemp[row][col - result].z == 0) {
								mapTemp[row][col - result] = new Shark(map[row][col].s, 4, map[row][col].z);
							}
						} else if(result <= (col - 1) + (C - 1)) {
							if(mapTemp[row][result - col + 2].z != 0 && mapTemp[row][result - col + 2].z < map[row][col].z) {
								mapTemp[row][result - col + 2] = new Shark(map[row][col].s, 3, map[row][col].z);
							} else if(mapTemp[row][result - col + 2].z == 0) {
								mapTemp[row][result - col + 2] = new Shark(map[row][col].s, 3, map[row][col].z);
							}
						} else {
							if(mapTemp[row][C - (result - ((col - 1) + (C - 1)))].z != 0 && mapTemp[row][C - (result - ((col - 1) + (C - 1)))].z < map[row][col].z) {
								mapTemp[row][C - (result - ((col - 1) + (C - 1)))] = new Shark(map[row][col].s, 4, map[row][col].z);
							} else if(mapTemp[row][C - (result - ((col - 1) + (C - 1)))].z == 0) {
								mapTemp[row][C - (result - ((col - 1) + (C - 1)))] = new Shark(map[row][col].s, 4, map[row][col].z);
							}
						}
					}
				}
			}
		}
	}
}
