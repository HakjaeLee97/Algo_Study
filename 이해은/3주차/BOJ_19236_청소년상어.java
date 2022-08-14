package study;

import java.io.*;
import java.util.*;

public class BOJ_19236_청소년상어 {

	static Fish[][] map;
	static int maxFishIndexSum;
	static int sum;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static class Fish {
		int index = 0;
		int dir = 0;
		
		Fish(int index, int d){
			this.index = index;
			this.dir = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new Fish[4][4];
		
		for(int row = 0; row < 4; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < 4; col++) {
				map[row][col] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
		}
		
		sum = map[0][0].index;
		//index == 17 은 상어를 의미
		map[0][0].index = 17;
		
		Dfs(0, 0, map[0][0].dir);
		
		System.out.println(maxFishIndexSum);
		
		br.close();
	}
	
	static void Dfs(int row, int col, int dir) {
		fishMove();
		
		//기저 조건
		//상어의 경로 상에 물고기가 없을 경우
		boolean isFish = false;
		for(int k = 1; k < 4; k++) {
			int nr = row + k * dr[dir - 1];
			int nc = col + k * dc[dir - 1];
			
			if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && map[nr][nc].index != 0) {
				isFish = true;
				break;
			}
		}
		
		if(!isFish) {
			if(maxFishIndexSum < sum) maxFishIndexSum = sum;
			return;
		}
		
		int sumTemp = sum;
		//경로 상의 물고기를 선택
		for(int k = 1; k < 4; k++) {
			int nr = row + k * dr[dir - 1];
			int nc = col + k * dc[dir - 1];
			
			if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && map[nr][nc].index != 0) {
				sum += map[nr][nc].index;
				
				Fish[][] fishTemp = new Fish[4][4];
				for(int rowIdx = 0; rowIdx < 4; rowIdx++) {
					for(int colIdx = 0; colIdx < 4; colIdx++) {
						fishTemp[rowIdx][colIdx] = new Fish(map[rowIdx][colIdx].index, map[rowIdx][colIdx].dir);
					}
				}
				
				map[row][col].index = 0;
				map[row][col].dir = 0;
				map[nr][nc].index = 17;
				
				Dfs(nr, nc, map[nr][nc].dir);
				
				map = fishTemp;
				sum = sumTemp;
			}
		}
	}
	
	//물고기의 이동
	static void fishMove() {
		 for(int i = 1; i <= 16; i++) {
			 label :for(int row = 0; row < 4; row++) {
				for(int col = 0; col < 4; col++) {
					if(map[row][col].index == i) {
						int curFishDir = map[row][col].dir;
						boolean flagFirhtNotFind = false;
						
						while(true) {
							if(flagFirhtNotFind && curFishDir == map[row][col].dir) break label;
							
							int nr = row + dr[curFishDir - 1];
							int nc = col + dc[curFishDir - 1];
							
							if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && map[nr][nc].index != 17) {
								int tmpIdx = map[row][col].index;
								map[row][col].index = map[nr][nc].index;
								map[row][col].dir = map[nr][nc].dir;
								map[nr][nc].index = tmpIdx;
								map[nr][nc].dir = curFishDir;
								break label;
							}
							
							curFishDir++;
							if(curFishDir == 9) curFishDir = 1;
							flagFirhtNotFind = true;
						}
					}
				}
			}
		}
	}
}
