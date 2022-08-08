package b15685;

import java.io.*;
import java.util.*;

public class Main {
		static boolean[][] map= new boolean[101][101];
		static int[] dx = {1,0,-1,0};
		static int[] dy = {0,-1,0,1};
		static int answer = 0;
		
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i< N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			curve(x,y,d,g);
			
		}
		
		for(int i = 0; i<100; i++) {
			for(int j = 0; j<100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
	
	public static void curve(int x, int y, int d,int g) {
		ArrayList<Integer> curves = new ArrayList<>();
		curves.add(d);
		
		for(int i = 1; i <=g; i++) {
			for(int j = curves.size() -1; j>= 0; j--) {
				curves.add(( curves.get(j) + 1) % 4);
			}
		}	
		map[y][x] = true;
		for(Integer dir : curves) {
			x += dx[dir];
			y += dy[dir];
			map[y][x] = true;
		}
	}
}
