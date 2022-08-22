package a0819.camp;

import java.io.*;
import java.util.*;

public class Main_bj_19237_어른상어_서울_20반_이해은 {

	static int N, M, K, time;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	
	static class Shark {
		int num;         //상어 number
		int dir;         //가지고 있는 방향
		int kCnt;        //냄새가 사라지기까지 카운트
		//우선순위
		int[][] priority = new int[5][5];
		
		Shark() {
			num = 0;         //상어 number
			dir = 0;         //가지고 있는 방향
			kCnt = 0; 
		}
		
		Shark(int num, int dir, int kCnt) {
			this.num = num;
			this.dir = dir;
			this.kCnt = kCnt;
		}
	}
	
	static Shark[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		//1, 2, 3, 4 : 위, 아래, 왼쪽, 오른쪽
		//1000초가 넘어가면 -1 출력
		//1번 상어만 격자에 남게 되기까지 걸리는 시간 출력
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Shark[N][N];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				map[row][col] = new Shark();
			}
		}
		
		int[][] inputSharkIdx = new int[N][N];
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < N; col++) {
				inputSharkIdx[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int m = 1; m <= M; m++) {
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(inputSharkIdx[row][col] == m) {
						map[row][col] = new Shark(m, Integer.parseInt(st.nextToken()), K);
					}
				}
			}
		}
		
		for(int m = 1; m <= M; m++) {
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(map[row][col].num == m) {
						for(int i = 1; i <= 4; i++) {
							st = new StringTokenizer(br.readLine(), " ");
							for(int j = 1; j <= 4; j++) {
								map[row][col].priority[i][j] = Integer.parseInt(st.nextToken());
							}
						}
					}
				}
			}
		}
		
		time = 0;
		
		while(true) {
			//상어의 이동
			if(check()) break;

			time++;
			if(time > 1000) {
				time = -1;
				break;
			}
			SharkMove();
		}
		
		System.out.println(time);
		br.close();
	}
	
	//상어의 이동
	static void SharkMove() {
		Shark[][] mapTmp = new Shark[N][N];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				mapTmp[row][col] = new Shark(map[row][col].num, map[row][col].dir, map[row][col].kCnt);
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 5; j++) {
						mapTmp[row][col].priority[i][j] = map[row][col].priority[i][j];
					}
				}
				
				if(mapTmp[row][col].kCnt > 0) {
					mapTmp[row][col].kCnt--;
					if(mapTmp[row][col].kCnt == 0) {
						mapTmp[row][col] = new Shark();
					}
				}
			}
		}
		
		for(int row = 0; row < N; row++) {
			label : for(int col = 0; col < N; col++) {
				if(map[row][col].kCnt == K) { //상어가 존재하는 위치
					//최고 우선 순위 : 비어있는 공간
					List<Integer> zeroKlist = new ArrayList<>();
					for(int d = 1; d <= 4; d++) {
						int nr = row + dr[d];
						int nc = col + dc[d];
						
						if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc].kCnt == 0) {
							zeroKlist.add(d);
						}
					}
					
					//빈 공간이 없을 경우 자신의 냄새가 있던 공간
					if(zeroKlist.isEmpty()) {
						for(int d = 1; d <= 4; d++) {
							int nr = row + dr[d];
							int nc = col + dc[d];
							
							if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc].num == map[row][col].num) {
								zeroKlist.add(d);
							}
						}
					}
					
					for(int d = 1; d <= 4; d++) {
						boolean isEx = false;
						for(int i = 0; i < zeroKlist.size(); i++) {
							if(map[row][col].priority[map[row][col].dir][d] == zeroKlist.get(i)) {
								isEx = true;
								break;
							}
						}
						
						if(!isEx) continue;
						
						int nr = row + dr[map[row][col].priority[map[row][col].dir][d]];
						int nc = col + dc[map[row][col].priority[map[row][col].dir][d]];
						
						if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
							if(mapTmp[nr][nc].kCnt == K) { //배치된 위치에 이미 상어가 있는 경우
								if(mapTmp[nr][nc].num > map[row][col].num) {
									mapTmp[nr][nc] = new Shark(map[row][col].num, map[row][col].priority[map[row][col].dir][d], K);
									for(int i = 0; i < 5; i++) {
										for(int j = 0; j < 5; j++) {
											mapTmp[nr][nc].priority[i][j] = map[row][col].priority[i][j];
										}
									}
								}
								continue label;
							} else { //배치된 위치에 상어가 없는 경우
								mapTmp[nr][nc] = new Shark(map[row][col].num, map[row][col].priority[map[row][col].dir][d], K);
								for(int i = 0; i < 5; i++) {
									for(int j = 0; j < 5; j++) {
										mapTmp[nr][nc].priority[i][j] = map[row][col].priority[i][j];
									}
								}
								continue label;
							}
						}
					}
				}
			}
		}
		
		map = mapTmp;
	}
	
	static boolean check() {
		int cnt = 0;
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				if(map[row][col].kCnt == K) cnt++;
				if(cnt > 1) return false;
			}
		}
		return true;
	}
}
