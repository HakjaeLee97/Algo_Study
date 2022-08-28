import java.io.*;
import java.util.*;

public class Main {
	public static int N, Q, n;
	public static int[][] graph;
	public static int[] L;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int ans = 0;
	public static boolean[][] visited;
	
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void rotate(int x) {
		int[][] tempGraph = new int[n][n];
		
		int b = (int)Math.pow(2, x);
		
		for(int i = 0; i < n; i+=b) {
			for(int j = 0; j < n; j+=b) {
				int cnt2 = 0;
				for(int k = i; k < i + b; k++) {
					int cnt = 0;
					for(int l = j; l < j + b; l++) {
						tempGraph[k][l] = graph[i + b - 1 - cnt][j + cnt2];
						cnt++;
					}
					cnt2++;
				}
			}
		}
		
		graph = tempGraph;	
	}
	
	public static void melt() {
		int[][] tempGraph = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				tempGraph[i][j] = graph[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int cnt = 0;
				if(tempGraph[i][j] > 0) {
					for(int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx < 0 || ny < 0 || nx >= n || ny >= n)
							continue;
						if(tempGraph[nx][ny] > 0)
							cnt++;
					}
					if(cnt < 3)
						graph[i][j] -= 1;
				}
			}
		}
	}
	
	public static void bfs(int x, int y) {
		int cnt = 1;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty() ) {
			Node node = q.poll();
			x = node.x;
			y = node.y;
			
			for(int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if(graph[nx][ny] > 0 && visited[nx][ny] == false) {
					q.offer(new Node(nx, ny));
					visited[nx][ny] = true;
					cnt += 1;
				}
			}
		}
		
		ans = Math.max(ans, cnt);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		n = (int)Math.pow(2, N);
		graph = new int[n][n];
		L = new int[Q];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			st =  new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < Q; i++) {
			rotate(L[i]);
			melt();
		}
		
		int sum = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sum += graph[i][j];
			}
		}
		System.out.println(sum);
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(graph[i][j] != 0 && visited[i][j] == false) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}
}
