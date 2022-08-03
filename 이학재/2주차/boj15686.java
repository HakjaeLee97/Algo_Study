package b15686;

import java.io.*;
import java.util.*;


class Point{
	int row,col;
	public Point(int row,int col) {this.row = row; this.col = col;}
}

public class Main {
		static int N, M;
		static int[][] map;
		static ArrayList<Point> chic = new ArrayList<>();
		static ArrayList<Point> house = new ArrayList<>();
		static boolean[] open;
		static int minsum;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		minsum = Integer.MAX_VALUE; // 치킨 거리의 최소값
		map = new int[N][N];
		
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chic.add(new Point(i,j));
				}
				else if(map[i][j] == 1){
					house.add(new Point(i,j)); //map을 입력받고 치킨집과 집을 저장
				}
			}
		}
		
		open = new boolean[chic.size()];

			
		DFS(0,0);
		System.out.println(minsum);
			


		br.close();
	}

	
	public static void DFS(int start, int cnt) {
		if (cnt == M) {
			int res = 0;
			
			for(int i =0; i< house.size(); i++) {
				int temp = Integer.MAX_VALUE;
				for(int j= 0; j<chic.size(); j++) {
					if(open[j]) {
						int distance = Math.abs(house.get(i).row - chic.get(j).row) + Math.abs(house.get(i).col - chic.get(j).col);
						temp = Math.min(temp,  distance);
					}
				}
				res += temp;
			}
			minsum = Math.min(minsum, res);
			return;
		}
		
		for( int i = start; i< chic.size(); i++) {
			open[i] = true;
			DFS(i +1, cnt +1);
			open[i] = false;
		}
	}

}
