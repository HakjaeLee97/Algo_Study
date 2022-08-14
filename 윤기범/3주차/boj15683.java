import java.util.*;
import java.io.*;

class CCTV {
	public int num;
	public int x;
	public int y;
	
	public CCTV(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}
public class Main {
	public static int N, M;
	public static int[][] graph;
	public static int[][] copyGraph;
	public static int[] numbers;
	public static ArrayList<CCTV> arr = new ArrayList<>();
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int ans = (int)1e9;
	
	public static void perm(int cnt, int r) {
		if(cnt == r) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					copyGraph[i][j] = graph[i][j];
				}
			}
			for(int i = 0; i < arr.size(); i++) {
				direction(arr.get(i), numbers[i]);
			}
			
			check();
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			numbers[cnt] = i;
			perm(cnt+1, r);
		}
	}
	
	public static void direction(CCTV cctv, int d) {
		int num = cctv.num;
		
		if(num == 1) {
			if(d == 0) watch(cctv, 0);
			else if(d == 1) watch(cctv, 1);
			else if(d == 2) watch(cctv, 2);
			else if(d == 3) watch(cctv, 3);
		} else if(num == 2) {
			if(d == 0 || d == 2) {
				watch(cctv, 0);
				watch(cctv, 2);
			} else {
				watch(cctv, 1);
				watch(cctv, 3);
			}
		} else if(num == 3) {
			if(d == 0) {
				watch(cctv, 0);
				watch(cctv, 1);
			} else if(d == 1) {
				watch(cctv, 1);
				watch(cctv, 2);
			} else if(d == 2) {
				watch(cctv, 2);
				watch(cctv, 3);
			} else if(d == 3) {
				watch(cctv, 3);
				watch(cctv, 0);
			}
		} else if(num == 4) {
			if(d == 0) {
				watch(cctv, 0); 
				watch(cctv, 1);
				watch(cctv, 3);
			} else if(d == 1) {
				watch(cctv, 0); 
				watch(cctv, 1);
				watch(cctv, 2);
			} else if(d == 2) {
				watch(cctv, 1); 
				watch(cctv, 2);
				watch(cctv, 3);
			} else if(d == 3) {
				watch(cctv, 0); 
				watch(cctv, 2);
				watch(cctv, 3);
			}
		} else {
			watch(cctv, 0);
			watch(cctv, 1);
			watch(cctv, 2);
			watch(cctv, 3);
		}
	}
	
	public static void watch(CCTV c, int d) {
		Queue<CCTV> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(c);
		visited[c.x][c.y] = true;
		
		while(!q.isEmpty()) {
			CCTV tempC = q.poll();
			int nx = tempC.x + dx[d];
			int ny = tempC.y + dy[d];
			if(nx < 0 || ny < 0 || nx >= N || ny >= M || copyGraph[nx][ny] == 6) {
				break;
			}
			if(copyGraph[nx][ny] == 0) {
				copyGraph[nx][ny] = -1;
				q.add(new CCTV(c.num, nx, ny));
			} else {
				q.add(new CCTV(c.num, nx, ny));
			}
		}
	}
	
	public static void check() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyGraph[i][j] == 0)
					cnt++;
			}
		}
		ans = Math.min(ans, cnt);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		copyGraph = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(1 <= graph[i][j] && graph[i][j] <= 5)
					arr.add(new CCTV(graph[i][j], i, j));
			}
		}
		
		numbers = new int[arr.size()];
		perm(0, arr.size());
		
		System.out.println(ans);
	}
}
