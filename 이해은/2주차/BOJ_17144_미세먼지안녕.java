package study;

import java.util.*;
import java.io.*;

public class BOJ_17144_미세먼지안녕 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		//모든 영역에서 확산이 동시에 일어난다.
		//->확산된 먼지가 동일한 확산 단계에서 원본 먼지 양에 영향을 끼치지 않는다.
		//->원본 먼지 정보를 저장할 배열과 확산 정보를 담을 배열이 필요하다.
		int[][] mapData = new int[r + 1][c + 1];
		int[][] temp = new int[r + 1][c + 1];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		//공기청정기 하단의 좌표
		//공기청정기는 항상 col = 1 에 위치한다.
		int cleaner_up_row = 0;
		int cleaner_down_row = 0;
		
		for(int row = 1; row <= r; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 1; col <= c; col++) {
				int input = Integer.parseInt(st.nextToken());
				mapData[row][col] = input;
				if(input == -1) cleaner_down_row = row;
			}
		}
		
		cleaner_up_row = cleaner_down_row - 1;
		
		for(int tIdx = 0; tIdx < t; tIdx++) {
			temp = new int[r + 1][c + 1];
			
			//확산 단계
			for(int row = 1; row <= r; row++) {
				for(int col = 1; col <= c; col++) {
					if(mapData[row][col] == -1 || mapData[row][col] == 0) continue;
					
					//주시하고 있는 위치에서 빠질 먼지의 총량
					int sub = 0;
					//주시하고 있는 위치에서 좌표 하나당 빠질 수 있는 먼지의 양
					int child = mapData[row][col] / 5;
					
					for(int d = 0; d < 4; d++) {
						int nr = row + dr[d];
						int nc = col + dc[d];
						
						if(nr < 1 || nr > r || nc < 1 || nc > c || mapData[nr][nc] == -1) continue;
						
						sub += child;
						temp[nr][nc] += child;
					}
					
					//확산된 이후 먼지 갱신
					mapData[row][col] -= sub;
				}
			}
			
			//확산 정보로 원본 갱신
			for(int row = 1; row <= r; row++) {
				for(int col = 1; col <= c; col++) {
					mapData[row][col] += temp[row][col];
				}
			}

			//이동 단계
			//상단 하 기류
			for(int idx = cleaner_up_row - 2; idx >= 1; idx--) {
				mapData[idx + 1][1] =  mapData[idx][1];
			}
			//상단 좌 기류
			for(int idx = 2; idx <= c; idx++) {
				mapData[1][idx - 1] =  mapData[1][idx];
			}
			//상단 상 기류
			for(int idx = 2; idx <= cleaner_up_row; idx++) {
				mapData[idx - 1][c] =  mapData[idx][c];
			}
			
			//상단 우 기류
			for(int idx = c - 1; idx >= 2; idx--) {
				mapData[cleaner_up_row][idx + 1] =  mapData[cleaner_up_row][idx];
			}
			
			//맑은 공기 나옴
			mapData[cleaner_up_row][2] = 0;
			
			//하단 상 기류
			for(int idx = cleaner_down_row + 2; idx <= r; idx++) {
				mapData[idx - 1][1] =  mapData[idx][1];
			}
			//하단 좌 기류
			for(int idx = 2; idx <= c; idx++) {
				mapData[r][idx - 1] =  mapData[r][idx];
			}
			//하단 하 기류
			for(int idx = r - 1; idx >= cleaner_down_row; idx--) {
				mapData[idx + 1][c] =  mapData[idx][c];
			}
			
			//하단 우 기류
			for(int idx = c - 1; idx >= 2; idx--) {
				mapData[cleaner_down_row][idx + 1] =  mapData[cleaner_down_row][idx];
			}
			
			//맑은 공기 나옴
			mapData[cleaner_down_row][2] = 0;
		}
		
		int answer = 2;
		
		for(int row = 1; row <= r; row++) {
			for(int col = 1; col <= c; col++) {
				answer += mapData[row][col];
			}
		}
		
		System.out.println(answer);
	}

}
