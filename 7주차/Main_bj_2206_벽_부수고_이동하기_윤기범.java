import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int M;
	public static int[][] graph;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int[][][] visited;

	static class Node {
		int x;
		int y;
		int c;
		
		public Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 0));
		visited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			int c = node.c;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if(graph[nx][ny] == 0 && visited[nx][ny][c] == 0) {
					q.offer(new Node(nx, ny, c));
					visited[nx][ny][c] = visited[x][y][c] + 1;
				}
				else if(graph[nx][ny] == 1 && visited[nx][ny][1] == 0 && c == 0) {
					q.offer(new Node(nx, ny, c + 1));
					visited[nx][ny][c + 1] = visited[x][y][c] + 1;
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new int[N][M][2];
        
        for(int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for(int j = 0; j < M; j++) {
        		graph[i][j] = str.charAt(j) - '0';
        	}
        }
        
        bfs();
        
        if(visited[N-1][M-1][0] != 0 && visited[N-1][M-1][1] == 0)
        	System.out.println(visited[N-1][M-1][0]);
        else if(visited[N-1][M-1][0] == 0 && visited[N-1][M-1][1] != 0)
        	System.out.println(visited[N-1][M-1][1]);
        else {
        	int ans = Math.min(visited[N-1][M-1][0], visited[N-1][M-1][1]);
        	if(ans == 0)
        		System.out.println(-1);
        	else
        		System.out.println(ans);
        }
    }
}
