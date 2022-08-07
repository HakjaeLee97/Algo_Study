package study;

import java.io.*;
import java.util.*;

public class BOJ_16234_인구이동 {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	//각 국가 방문 여부 체크
	//1부터 차례대로 부여 같은 숫자끼리 같은 연합
	static int[][] visited;
	
	//입력 맵 데이터 정보
	static int[][] map;
	
	//연합 인덱스 별 평균 값 저장을 위한 배열
	static int[] avgArr;
	
	static int n;
	static int l;
	static int r;
	
	//하루 동안 한 연합의 총 인구 수
	static int totalSum;
	//하루 동안 한 연합의 국가의 수
	static int cntCountry;
	//하루 동안 같은 연합 별로 부여되는 인덱스
	static int guildIdx;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for(int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < n; col++) {
				map[row][col] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		int day = 0;
		
		while(true) {
			//연합이 존재하지 않을 경우 break
			if(isAllMovePossible() == false) break;
			
			//날짜 ++
			day++;
			
			//방문 배열 초기화
			visited = new int[n][n];
			//평균 배열 초기화
			avgArr = new int[5000];
			//연합 인덱스 초기화
			guildIdx = 1;
			
			//DFS 로 모든 국가 순회
			for(int row = 0; row < n; row++) {
				for(int col = 0; col < n; col++) {
					//어느 연합에도 속하지 않을 경우 DFS 탐색
					if(visited[row][col] == 0) {
						//하루동안 한 연합에 속하는 국가의 수 초기화
						cntCountry = 1;
						//하루동안 한 연합에 속하는 총 인구의 수 초기화
						totalSum = 0;
						//DFS 탐색
						DFS(row, col);
						//탐색된 하나의 연합의 평균 값을 저장해놓음
						avgArr[guildIdx++] = totalSum / cntCountry;
					}
				}
			}
			
			//해당하는 평균 값을 각 국가에 할당
			for(int row = 0; row < n; row++) {
				for(int col = 0; col < n; col++) {
					if(visited[row][col] != 0) {
						map[row][col] = avgArr[visited[row][col]];
					}
				}
			}
		}
		
		System.out.println(day);
	}
	
	//모든 도시 인구 이동 불가 확인을 위한 함수
	static boolean isAllMovePossible() {
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				for(int d = 0; d < 4; d++) {
					int nr = row + dr[d];
					int nc = col + dc[d];
					
					if(nr >= 0 && nr < n && nc >= 0 && nc < n) {
						int diff = Math.abs(map[nr][nc] - map[row][col]);
						if(diff >= l && diff <= r) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	static void DFS(int row, int col) {
		//한 연합의 총 인구를 계산하기 위해 현 위치 인구를 합산
		totalSum += map[row][col];

		for(int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			
			if(nr >= 0 && nr < n && nc >= 0 && nc < n && visited[nr][nc] == 0) {
				int diff = Math.abs(map[nr][nc] - map[row][col]);
				
				if(diff < l || diff > r) continue;
				
				//연합에 속할 조건이 되는 경우 정보 갱신
				visited[row][col] = guildIdx;
				visited[nr][nc] = guildIdx;
				cntCountry++;
				DFS(nr, nc);
			}
		}
	}

}
