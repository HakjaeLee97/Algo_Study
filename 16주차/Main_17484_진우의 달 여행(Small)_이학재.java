package b17484;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int min = Integer.MAX_VALUE;
	static int[] dy = {-1,0,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {		
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			dfs(0,-1,map[0][i],map,i,N,M);
		}
		System.out.println(min);
		br.close();
	}
	public static void dfs(int depth, int dir,int fuel, int[][] map,int y,int N, int M) {
		if(fuel > min) return;
		if(depth == N-1) {
			min = Math.min(min, fuel);
			return;
		}
		for (int i = 0; i < 3; i++) {
			if(dir == i) continue;
			int ny = y + dy[i];
			if(ny< 0 || ny>= M) continue;
			int morefuel = map[depth+1][ny];
			dfs(depth+1,i,fuel+morefuel,map,ny,N,M);
		}
	}
}
