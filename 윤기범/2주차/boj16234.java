import java.util.*;
import java.io.*;
	
class Node {
	private int x;
	private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
public class Main {
	public static int n, l, r;
	public static boolean[][] visited;
	public static int[][] graph;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static LinkedList<Node> temp;
	public static int ans = 0;
	
	public static boolean bfs(int x, int y) {
		temp = new LinkedList<>();
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y));
		visited[x][y] = true;
		temp.add(new Node(x, y));
		
		while(!q.isEmpty()) {
			Node newNode = q.poll();
			x = newNode.getX();
			y = newNode.getY();
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if(visited[nx][ny] == false && (Math.abs(graph[x][y] - graph[nx][ny]) >= l && Math.abs(graph[x][y] - graph[nx][ny]) <= r)) {
					q.offer(new Node(nx, ny));
					visited[nx][ny] = true;
					temp.add(new Node(nx, ny));
				}
			}
		}
		
		int sum = 0;
		int cnt = temp.size();
		if(cnt == 1) {
			return false;
		}
		int val = 0;
		for(int i = 0; i < cnt; i++) {
			int xx = temp.get(i).getX();
			int yy = temp.get(i).getY();
			sum += graph[xx][yy];
		}
		val = sum / cnt;
		for(int i = 0; i < cnt; i++) {
			int xx = temp.get(i).getX();
			int yy = temp.get(i).getY();
			graph[xx][yy] = val;
		}
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		graph = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			visited = new boolean[n][n];
			boolean flag = false;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if (visited[i][j] == false && bfs(i, j) == true) {
						flag = true;
					}
				}
			}
			if(flag == true) 
				ans += 1;
			else
				break;
		}
		
		System.out.println(ans);
	}
}
