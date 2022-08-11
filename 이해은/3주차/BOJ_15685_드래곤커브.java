package study;

import java.io.*;
import java.util.*;

public class BOJ_15685_드래곤커브 {
	
	//info
	//우 : 0
	//상 : 1
	//좌 : 2
	//하 : 3
	
	//우->상
	//상->좌
	//좌->하
	//하->우
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[1000][1000];
		
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			List<Integer> list = new ArrayList<>();
			List<Integer> listTemp = new ArrayList<>();
		
			list.add(d);
			
			for(int i = 0; i < g; i++) {
				listTemp = new ArrayList<>();
				for(int j = list.size() - 1; j >= 0; j--) {
					if(list.get(j) == 0) listTemp.add(1);
					else if(list.get(j) == 1) listTemp.add(2);
					else if(list.get(j) == 2) listTemp.add(3);
					else if(list.get(j) == 3) listTemp.add(0);
				}
				list.addAll(list.size(), listTemp);
			}
			
			map[y][x] = true;
			
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i) == 0) map[y][++x] = true;
				else if(list.get(i) == 1) map[--y][x] = true;
				else if(list.get(i) == 2) map[y][--x] = true;
				else if(list.get(i) == 3) map[++y][x] = true;
			}
		}
		
		int answer = 0;
		
		for(int row = 0; row < 101; row++) {
			for(int col = 0; col < 101; col++) {
				if(map[row][col] && map[row + 1][col] && map[row][col + 1] && map[row + 1][col + 1])
					answer++;
			}
		}
		
		System.out.println(answer);
		
	}

}
