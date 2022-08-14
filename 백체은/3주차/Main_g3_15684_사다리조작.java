package a0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g3_15684_사다리조작 {
	
	static int N, M, H;
	static int need = -1;
	static int[][] ladder;
	
	// 사다리 추가 3이하로, 넘거나 불가능하면 -1 출력
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로선 개수
		M = Integer.parseInt(st.nextToken()); // 가로선 개수
		H = Integer.parseInt(st.nextToken()); // 가로선 놓을 수 있는 위치의 개수
		
		ladder = new int[H][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // 현재 있는 가로선 정보
			int r = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken());
			ladder[r - 1][c - 1] = 1; // 우측으로 이동
			ladder[r - 1][c] = -1; // 좌측으로 이동
			
		}
		
		for (int limit = 0; limit <= 3; limit++) {
			dfs(0, limit);
			if(need != -1) break;
		}
		
		br.close();
		System.out.print(need);
	}
	
	static void dfs(int depth, int limit) {
		if(depth == limit) {
			if(result()) {
				need = depth;
			}
			return;
		}
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < N - 1; c++) {
				if(ladder[r][c] == 0 && ladder[r][c + 1] == 0) {
					ladder[r][c] = 1;
					ladder[r][c + 1] = -1;
					dfs(depth + 1, limit);
					ladder[r][c] = 0;
					ladder[r][c + 1] = 0;
				}
			}
		}
	}

	static boolean result() {
		
		for (int i = 0; i < N; i++) {
			int r = 0;
			int c = i;
			
			while(r < H) {
				if(ladder[r][c] == 1) c++; // 오른쪽 이동
				else if(ladder[r][c] == -1) c--; // 왼쪽 이동
				r++;
			}
			if(c != i) {
				return false;
			}
		}
		return true;
	}
}