import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	public int x;
	public int y;
	public int dist;
	
	public Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Node o) {
		int r = this.dist - o.dist;
		if(r == 0) {
			r = this.x - o.x;
			if(r == 0) {
				r = this.y - o.y;
			}
		}
		return r;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}
	
}
public class Main {
	public static int n;
	public static int[][] graph;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static int sx, sy;
	public static int cnt = 0;
	public static int size = 2;
	public static int sec = 0, time;
	public static ArrayList<Node> eatable = new ArrayList<>();
	public static boolean[][] visited;
	
	public static void bfs(int x, int y) {
		time = 0;
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(x, y, time));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int len = q.size();
			for(int t = 0; t < len; t++) {
				Node node = q.poll();
				x = node.x;
				y = node.y;
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
         					if(nx < 0 || ny < 0 || nx >= n || ny >= n)
						continue;
					if(graph[nx][ny] <= size && visited[nx][ny] == false) {
						q.offer(new Node(nx, ny, time));
						visited[nx][ny] = true;
						if(0 < graph[nx][ny] && graph[nx][ny] < size) {
							eatable.add(new Node(nx, ny, time + 1));
						}
					}
				}
			}
			time++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 9) {
					sx = i;
					sy = j;
					graph[i][j] = 0;
				}
			}
		}
		
		sec = 0;
		while(true) {
			eatable.clear();
			visited = new boolean[n][n];
			bfs(sx, sy);
			if(eatable.size() == 0) {
				break;
			}
			Collections.sort(eatable);
			int ex = eatable.get(0).x;
			int ey = eatable.get(0).y;
			int t = eatable.get(0).dist;
			sec += t;
			graph[ex][ey] = 0;
			sx = ex;
			sy = ey;
			cnt++;
			if(cnt == size) {
				cnt = 0;
				size++;
			}
		}
		System.out.println(sec);
	}
	
}
