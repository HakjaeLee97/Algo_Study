package study;

import java.io.*;
import java.util.*;

public class BOJ_15683_감시 {

	static int N, M;
	static int[][] map;
	static boolean[][] mapTemp;
	static boolean[][] mapTempTemp;
	
	static List<Point> cctv;
	static List<Point> save;
	
	static int resultMin;
	
	static class Point {
		int row;
		int col;
		int cctvNum;
		int dir;
		Point(int row, int col, int cctvNum) {
			this.row = row;
			this.col = col;
			this.cctvNum = cctvNum;
		}
		Point(int row, int col, int cctvNum, int dir) {
			this.row = row;
			this.col = col;
			this.cctvNum = cctvNum;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		mapTemp = new boolean[N][M];
		cctv = new ArrayList<>();
		save = new ArrayList<>();
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] >= 1 && map[row][col] <= 5) cctv.add(new Point(row, col, map[row][col]));
			}
		}
		
		resultMin = Integer.MAX_VALUE;
		
		DFS(0);
		
		System.out.println(resultMin);
		br.close();
	}
	
	static int search() {
		int cnt = 0;
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(mapTemp[row][col] == false && map[row][col] != 6) cnt++;
			}
		}
		return cnt;
	}
	
	static void DFS(int depth) {
		if(depth == cctv.size()) {
			mapTemp = new boolean[N][M];
			for(int i = 0; i < save.size(); i++) {
				cctv(save.get(i).row, save.get(i).col, save.get(i).cctvNum, save.get(i).dir);
			}
			int sagak = search();
			if(resultMin > sagak) resultMin = sagak;
			return;
		}
		
		mapTempTemp = new boolean[N][M];
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				mapTempTemp[n][m] = mapTemp[n][m];
			}
		}
		
		if(cctv.get(depth).cctvNum == 1 || cctv.get(depth).cctvNum == 3 || cctv.get(depth).cctvNum == 4) {
			for(int i = 0; i < 4; i++) {
				save.add(new Point(cctv.get(depth).row, cctv.get(depth).col, cctv.get(depth).cctvNum, i));
				DFS(depth + 1);
				save.remove(save.size() - 1);
				mapTemp = mapTempTemp;
			}
		} else if(cctv.get(depth).cctvNum == 2) {
			for(int i = 0; i < 2; i++) {
				save.add(new Point(cctv.get(depth).row, cctv.get(depth).col, cctv.get(depth).cctvNum, i));
				DFS(depth + 1);
				save.remove(save.size() - 1);
				mapTemp = mapTempTemp;
			}
		} else {
			save.add(new Point(cctv.get(depth).row, cctv.get(depth).col, cctv.get(depth).cctvNum, 0));
			DFS(depth + 1);
			save.remove(save.size() - 1);
			mapTemp = mapTempTemp;
		}
	}
	
	static void cctv(int row, int col, int cctvNum, int pattern) {
		int selectPattern = 0;
		if(cctvNum == 1) {
			if(pattern == 0) 	  selectPattern = 0x0001;
			else if(pattern == 1) selectPattern = 0x0010;
			else if(pattern == 2) selectPattern = 0x0100;
			else if(pattern == 3) selectPattern = 0x1000;
		} else if(cctvNum == 2) {
			if(pattern == 0) 	  selectPattern = 0x0101;
			else if(pattern == 1) selectPattern = 0x1010;
		} else if(cctvNum == 3) {
			if(pattern == 0) 	  selectPattern = 0x0011;
			else if(pattern == 1) selectPattern = 0x0110;
			else if(pattern == 2) selectPattern = 0x1100;
			else if(pattern == 3) selectPattern = 0x1001;
		} else if(cctvNum == 4) {
			if(pattern == 0) 	  selectPattern = 0x1011;
			else if(pattern == 1) selectPattern = 0x0111;
			else if(pattern == 2) selectPattern = 0x1110;
			else if(pattern == 3) selectPattern = 0x1101;
		} else if(cctvNum == 5) {
								  selectPattern = 0x1111;
		}
		
		if((selectPattern & 0x0001) != 0) {
			for(int k = 0; k <= 7; k++) {
				int nr = row - k;
				int nc = col;
				
				if(nr >= 0 && nr < N && map[nr][nc] != 6) mapTemp[nr][nc] = true;
				else break;
			}
		}
		
		if((selectPattern & 0x0010) != 0) {
			for(int k = 0; k <= 7; k++) {
				int nr = row;
				int nc = col + k;
				
				if(nc >= 0 && nc < M && map[nr][nc] != 6) mapTemp[nr][nc] = true;
				else break;
			}
		}
		
		if((selectPattern & 0x0100) != 0) {
			for(int k = 0; k <= 7; k++) {
				int nr = row + k;
				int nc = col;
				
				if(nr >= 0 && nr < N && map[nr][nc] != 6) mapTemp[nr][nc] = true;
				else break;
			}
		}
		
		if((selectPattern & 0x1000) != 0) {
			for(int k = 0; k <= 7; k++) {
				int nr = row;
				int nc = col - k;
				
				if(nc >= 0 && nc < M && map[nr][nc] != 6) mapTemp[nr][nc] = true;
				else break;
			}
		}
	}
}
