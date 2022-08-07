package b17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] tmp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] dusts = new int[R][C];
		int sum = 0;
		int purify_row = 0;
		for(int i = 0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<C; j++) {
				dusts[i][j] = Integer.parseInt(st.nextToken());
				if(dusts[i][j] == -1) purify_row = i; // 공청기의 두 행 중 아래 index가 저장됨
			}
		}
		for(int t = 0; t<T; t++) {
			tmp = new int[R][C];
			for(int i = 0; i<R; i++) {
				for(int j = 0; j<C; j++) {
					if(dusts[i][j] >= 1) {
						spread(dusts, i,j,R,C);
					}
				}
			}
			for(int i = 0; i<R; i++) {
				for(int j = 0; j<C; j++) {
					if(tmp[i][j] != 0) {
						dusts[i][j] += tmp[i][j];
					}
				}
			}
			wind_blow(dusts,purify_row,R,C);
		}
		
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(dusts[i][j] >= 1) {
					sum += dusts[i][j];
				}
			}
		}
		System.out.println(sum);
	}
	
	public static void spread(int[][] dusts, int row,int col, int R, int C) {
		int[] dx = new int[] {1,-1,0,0};
		int[] dy = new int[] {0,0,1,-1};
		int spreaded = 0;
		for(int i = 0; i<4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if(nx < 0 || nx >= R || ny < 0 || ny >= C || dusts[nx][ny] ==-1) continue;
			tmp[nx][ny] += dusts[row][col] / 5;
			spreaded += dusts[row][col] / 5;
		}
		tmp[row][col] -= spreaded;
	}
	
	public static void wind_blow(int[][] dusts, int purify_row,int R, int C) {
		//아래 방향 순환
		for(int i = purify_row + 1; i<R-1; i++) {
			dusts[i][0] = dusts[i+1][0];
		}
		for(int i =0; i<C-1; i++) {
			dusts[R-1][i] = dusts[R-1][i+1]; 
		}
		for(int i = R-1; i>purify_row; i--) {
			dusts[i][C-1] = dusts[i-1][C-1];
		}
		for(int i = C-1; i>1; i--) {
			dusts[purify_row][i] = dusts[purify_row][i-1];
		}
		dusts[purify_row][1] = 0; 
		//위 방향 순환
		for(int i = purify_row-2; i>0; i--) {
			dusts[i][0] = dusts[i-1][0];
		}
		for(int i =0; i<C-1; i++) {
			dusts[0][i] = dusts[0][i+1];
		}
		for(int i = 0; i<purify_row; i++) {
			dusts[i][C-1] = dusts[i+1][C-1];
		}
		
		for(int i = C-1; i> 1; i--) {
			dusts[purify_row-1][i] = dusts[purify_row-1][i-1];
		}
		dusts[purify_row-1][1] = 0;
		


	}

}
