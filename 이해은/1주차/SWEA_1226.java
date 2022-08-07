package study;

import java.util.Scanner;

public class SWEA_1226 {
	//미로 데이터
	static int[][] map = new int[16][16];
	//방문 여부 체크
	static int[][] check = new int[16][16];
	
	//상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int answer;
	
	//DFS : 재귀함수
	static void dfs(int r, int c) {
		//방문 여부 갱신
		check[r][c] = 1;
		
		//도착했을 경우 완료
		if(map[r][c] == 3) {
			answer = 1;
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			//범위 내에 있고, 방문한적 없고, 뚫려 있는 경우
			if(nr >= 0 && nr < 16 && nc >= 0 && nc < 16) {
				if(check[nr][nc] == 0 && (map[nr][nc] == 0 || map[nr][nc] == 3))
					dfs(nr, nc);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) {
			int tc = sc.nextInt();
			answer = 0;
			map = new int[16][16];
			check = new int[16][16];
			int start_row = 0;
			int start_col =  0;
			
			//미로 데이터 입력
			for(int row = 0; row < 16; row++) {
				String str = sc.next();
				
				for(int col = 0; col < 16; col++) {
					map[row][col] = str.charAt(col) - '0';
					
					if(map[row][col] == 2) {
						start_row = row;
						start_col = col;
					}
				}
			}
			
			dfs(start_row, start_col);
			
			System.out.println("#" + tc + " " + answer);
		}
	}
}
