import java.io.*;
import java.util.*;

//시작시간 : 10-09 15:15
//종료시간 : 10-09 17:45
//걸린시간 : 02:30
//분 류    : 구현
//특이사항 :
//	- 무지개 블록은 bfs 종료 후 방문 배열 초기화
//	- 대표자는 행과 열이 작은 것으로 선정
//  - 삭제할 그룹 선정은 대표자의 행과 열이 큰 것으로 선정
public class Main_21609_상어중학교_이해은 {

	static int N, M, answer, score;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] v;
	static int totalBlockCnt, totalRainbowCnt;
	
	public static void main(String[] args) throws Exception {
		//1. 조건에 맞는 그룹제거
		//2. 점수획득
		//3. 중력
		//4. 반시계방향 회전
		//5. 중력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			//대표자
			int saveRow = Integer.MIN_VALUE;
			int saveCol = Integer.MIN_VALUE;
			int MaxBlock = Integer.MIN_VALUE;
			int MaxRainbow = Integer.MIN_VALUE;
			
			boolean firstFlag = false;
			v = new boolean[N][N];
			
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(map[row][col] > 0 && !v[row][col]) {
						//그룹내 멤버 수
						int groupMember = 0;
						//그룹내 무지개 블록 수
						int groupRainbow = 0;
						//대표자
						int pRow = row;
						int pCol = col;
						
						searchBFS(row, col);
						for(int rr = 0; rr < N; rr++) {
							for(int cc = 0; cc < N; cc++) {
								if(map[rr][cc] == 0) v[rr][cc] = false;
							}
						}
						
						groupMember = totalBlockCnt;
						groupRainbow = totalRainbowCnt;
						
						if(firstFlag == false) {
							firstFlag = true;
							MaxBlock = groupMember;
							MaxRainbow = groupRainbow;
							saveRow = pRow;
							saveCol = pCol;
						} else {
							if(MaxBlock < groupMember) {
								MaxBlock = groupMember;
								MaxRainbow = groupRainbow;
								saveRow = pRow;
								saveCol = pCol;
							} else if(MaxBlock == groupMember) {
								if(MaxRainbow < groupRainbow) {
									MaxRainbow = groupRainbow;
									saveRow = pRow;
									saveCol = pCol;
								} else if(MaxRainbow == groupRainbow) {
									if(saveRow < pRow) {
										saveRow = pRow;
										saveCol = pCol;
									} else if(saveRow == pRow) {
										if(saveCol < pCol) saveCol = pCol;
									}
								}
							}
						}
					}
				}
			}
			
			if(MaxBlock <= 1) break;
			
			//대표 그룹 제거
			v = new boolean[N][N];
			score = 0; 
			removeBFS(saveRow, saveCol);
			answer += score;
			
			gravity();
			rotation();
			gravity();
		}
		
		System.out.println(answer);
		
		br.close();
	}
	
	static void removeBFS(int row, int col) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int startBlockColor = map[row][col];
		v[row][col] = true;
		q.offer(new int[] {row, col});
		int blockCnt = 0;
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			row = rc[0];
			col = rc[1];
			map[row][col] = -2;
			blockCnt++;
			
			for(int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc] && 
						(startBlockColor == map[nr][nc] || map[nr][nc] == 0)) {
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
		score = blockCnt * blockCnt;
	}
	
	static void searchBFS(int row, int col) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int startBlockColor = map[row][col];
		v[row][col] = true;
		q.offer(new int[] {row, col});
		int blockCnt = 0;
		int rainbowCnt = 0;
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			row = rc[0];
			col = rc[1];
			blockCnt++; // 블록 개수 증가
			if(map[row][col] == 0) rainbowCnt++; //무지개 블록 카운트
			
			for(int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]
						&& (startBlockColor == map[nr][nc] || map[nr][nc] == 0)) {
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
		totalBlockCnt = blockCnt;
		totalRainbowCnt = rainbowCnt;
	}
	
	//중력 작용
	static void gravity() {
		
		for(int row = N -2; row >= 0; row--) {
			for(int col = 0; col < N; col++) {
				if(map[row][col] >= 0) {
					int k = 0;
					boolean emptyFlg = false;
					while(true) {
						//다음 칸이 범위 밖이거나, 공백이 아니면 가장가까이에 있는 공백에 내용 저장
						k++;
						if(row + k >= N || map[row + k][col] != -2) {
							if(emptyFlg) {
								map[row + k - 1][col] = map[row][col];
								map[row][col] = -2;
							}
							
							break;
						}
						if(map[row + k][col] == -2) {
							emptyFlg = true;
							continue;
						}
					}
				}
			}
		}
	}
	
	static void rotation() {
		int[][] tmp = new int[N][N];
		int rowIdx = 0;
		int colIdx = 0;
		
		for(int col = N - 1; col >= 0; col--) {
			for(int row = 0; row < N; row++) {
				tmp[rowIdx][colIdx++] = map[row][col];
				if(colIdx == N) {
					rowIdx++;
					colIdx = 0;
				}
			}
		}
		map = tmp;
	}
}
