package b16234;

import java.io.*;
import java.util.*;

class Point{
	int row,col;
	public Point(int row, int col) {this.row = row; this.col = col;}
}

public class Main {
	static Queue<Point> queue = new LinkedList<>();
	static ArrayList<Point> Union = new ArrayList<>();
	static boolean[][] visited;
	static int xArr[] = {-1,0,1,0}, yArr[] = {0,1,0,-1};
	static int[][] country;
	static int L, R;
	static int N;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		visited = new boolean[N][N];
		country = new int[N][N];
		for (int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j= 0; j<N; j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean moved = false;
		int moveday = 0;
		do{
			moved =false;
			for(int i =0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if (visited[i][j] == false) {
						if (bfs(new Point(i,j))) moved = true;
					}
						
				}
			}
			if(moved) moveday++;
			for(int i =0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					visited[i][j] = false;
				}
			}
		} while(moved == true);
		System.out.println(moveday);
	}
	
	public static boolean bfs(Point start) {
		int pop = 0; // 연합의 인구
		queue.add(start);
		Union.add(start);
		int row = start.row;
		int col = start.col;
		boolean result = false;
		
		visited[row][col] = true;
		pop += country[row][col];
		
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			row = point.row;
			col = point.col;
			
			for(int i =0; i<4; i++) {//사방탐색
				int nx = row + xArr[i];
				int ny = col + yArr[i];
				if(nx>=0 && nx<N && ny>=0 && ny<N) {
					int tmp = Math.abs( (country[row][col] - country[nx][ny]));
					if( !visited[nx][ny] && tmp >= L && tmp <= R) {
						Union.add(new Point(nx,ny));
						queue.add(new Point(nx,ny));
						pop += country[nx][ny];
						visited[nx][ny]= true;
						result = true;
					}
				}
			}
		}
		pop = pop / Union.size();
		for(int i = 0; i< Union.size(); i++) {
			Point point = Union.get(i);
			row = point.row;
			col = point.col;
			country[row][col] = pop;
		
		}
		Union.clear();
		return result;
		
	}

}
