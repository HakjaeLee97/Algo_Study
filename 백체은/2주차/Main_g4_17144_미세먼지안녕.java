package a0808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g4_17144_미세먼지안녕 {

	static int R, C, T;
	static int[][] room, tRoom;
	static int airCleaner;
	static int dust;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		room = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				
				if(room[r][c] == -1) {
					airCleaner = r;
				}
			}
		}
		br.close();

		for (int t = 0; t < T; t++) {
			spread();
			clean();
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(room[r][c] != -1)
					dust += room[r][c];
			}
		}
		
		System.out.print(dust);
	}

	static void spread() {
		tRoom = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tRoom[r][c] += room[r][c];
				
				if (room[r][c] < 5) {
					continue;
				}
				
				int spreadDust = room[r][c] / 5;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr >= 0 && nr < R && nc >= 0 && nc < C && room[nr][nc] != -1) {
						tRoom[nr][nc] += spreadDust;
						tRoom[r][c] -= spreadDust;
					}
				}
			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				room[r][c] = tRoom[r][c];
			}
		}
	}
	
	static void clean() {
		tRoom = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tRoom[r][c] = room[r][c];
			}
		}
		
		for (int i = 0; i < 2; i++) {
			int cr = airCleaner - i;
			int cc = 0;
			
			while(cc + 1 < C) {
				if (room[cr][cc] != -1) {
					tRoom[cr][cc + 1] = room[cr][cc];
				} else {
					tRoom[cr][cc + 1] = 0;
				}
				cc++;
			}
			
			if(i == 0) {
				while(cr + 1 < R) {
					tRoom[cr + 1][cc] = room[cr][cc];
					cr++;
				}
			} else if (i == 1) {
				while(cr > 0) {
					tRoom[cr - 1][cc] = room[cr][cc];
					cr--;
				}
			}
			
			while(cc > 0) {
				tRoom[cr][cc - 1] = room[cr][cc];
				cc--;
			}
			
			if(i == 0) {
				while(cr > airCleaner) {
					tRoom[cr - 1][cc] = room[cr][cc];
					cr--;
				}
			} else if(i == 1) {
				while(cr + 1 < airCleaner) {
					tRoom[cr + 1][cc] = room[cr][cc];
					cr++;
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(room[r][c] == -1) continue;
				room[r][c] = tRoom[r][c];
			}
		}
		
	}
}
