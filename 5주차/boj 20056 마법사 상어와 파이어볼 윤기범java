import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, K;
	public static ArrayList<Info>[][] graph;
	public static ArrayList<Info> arr = new ArrayList<>();
	public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	public static int[] e = {0, 2, 4, 6};
	public static int[] o = {1, 3, 5, 7};
	
	static class Info {
		int r;
		int c;
		int m;
		int s;
		int d;
		
		public Info(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Info [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}
	
	public static void move() {
		int len = arr.size();
		for(int i = len-1; i >= 0; i--) {
			int x = arr.get(i).r;
			int y = arr.get(i).c;
			int m = arr.get(i).m;
			int s = arr.get(i).s;
			int d = arr.get(i).d;
			
			int nx = x + dx[d] * s;
			int ny = y + dy[d] * s;
			
			if (nx >= 0)
				nx %= N;
			if (ny >= 0)
				ny %= N;
			if (nx < 0) {
				nx %= N;
				if(nx != 0)
					nx += N;
			}
			if(ny < 0) {
				ny %= N;
				if(ny != 0)
					ny += N;
			}
			
			graph[x][y].remove(0);
			graph[nx][ny].add(new Info(nx, ny, m, s, d));
		}
	}
	
	public static void check() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int len = graph[i][j].size();
				if(len >= 2) {
					int mass = 0;
					int velocity = 0;
					int numOfEven = 0;
					int numOfOdd = 0;
					for(int k = 0; k < len; k++) {
						mass += graph[i][j].get(k).m;
						velocity += graph[i][j].get(k).s;
						if(graph[i][j].get(k).d % 2 == 0)
							numOfEven += 1;
						else
							numOfOdd += 1;
					}
					graph[i][j].clear();
					mass = mass / 5;
					velocity = velocity / len;
					if(mass != 0) {
						if(numOfEven == 0 || numOfOdd == 0) {
							for(int k = 0; k < 4; k++) {
								graph[i][j].add(new Info(i, j, mass, velocity, e[k]));
							}
						}
						else {
							for(int k = 0; k < 4; k++) {
								graph[i][j].add(new Info(i, j, mass, velocity, o[k]));
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N][N];
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < N; j++) {
				graph[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[r][c].add(new Info(r, c, m, s, d));
			arr.add(new Info(r, c, m, s, d));
		}
		
		for(int t = 0; t < K; t++) {
			move();
			arr.clear(); // 초기화
			check();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 0; k < graph[i][j].size(); k++) {
						arr.add(new Info(i, j, graph[i][j].get(k).m, graph[i][j].get(k).s, graph[i][j].get(k).d));
					}
				}
			}
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < graph[i][j].size(); k++) {
					ans += graph[i][j].get(k).m;
				}
			}
		}
		System.out.println(ans);
	}
}
