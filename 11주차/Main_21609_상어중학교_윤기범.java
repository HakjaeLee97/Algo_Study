import java.util.*;
import java.io.*;

public class Main {
	public static int answer;
	public static int N, M;
	public static int[][] graph;
	public static boolean[][] visited;
	public static LinkedList<Block> list = new LinkedList<>();
	// 4방 탐색
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	
	static class Block implements Comparable<Block> {
		int totalCnt;
		int rainbowCnt;
		int row;
		int col;
		
		public Block(int totalCnt, int rainbowCnt, int row, int col) {
			this.totalCnt = totalCnt;
			this.rainbowCnt = rainbowCnt;
			this.row = row;
			this.col = col;
		}
		
		public int compareTo(Block o) {
			if(this.totalCnt == o.totalCnt) {
				if(this.rainbowCnt == o.rainbowCnt) {
					if(this.row == o.row) {
						return o.col - this.col;
					}
					return o.row - this.row; // 내림차순 
				}
				return o.rainbowCnt - this.rainbowCnt; // 내림차순
			}
			return o.totalCnt - this.totalCnt; // 내림차순
		}
	}
	
	public static void removeBlock() {
		int count = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(visited[i][j] == true) {
					count++;
					graph[i][j] = -2;
				}
			}
		}
		
		answer += (int)Math.pow(count, 2);
	}
	
	public static void gravity() {
		for(int j = 0 ; j < N ; j++) {
			for(int i = N-1 ; i >= 0 ; i--) {
				for(int k = i ; k < N-1 ; k ++) {
					if(graph[k][j] == -1) continue;
					if(graph[k][j] != -2 && graph[k+1][j] == -2) {
						int temp = graph[k][j];
						graph[k][j] = -2;
						graph[k+1][j] = temp;
					}
				}
			}
		}
	}
	
	public static int[][] rotate() {
		int[][] temp = new int[N][N];
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				temp[N-j-1][i]=graph[i][j];
			}
		}
		
		return temp;
	}
	
	public static void bfs(int xPos, int yPos, boolean flag) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(xPos, yPos));
		int value = graph[xPos][yPos]; // 현재 좌표를 기준으로 찾기
 		visited[xPos][yPos] = true; // 방문처리
		int totalCnt = 1;
		int rainbowCnt = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) // 범위 체크
					continue;
				if(visited[nx][ny] == false && (graph[nx][ny] == value || graph[nx][ny] == 0)) { // 방문하지 않고 무지개 혹은 일반 블록 중 같은 경우
					if(graph[nx][ny] == 0)
						rainbowCnt++;
					totalCnt++;
					visited[nx][ny] = true;
					q.offer(new Node(nx, ny));
				}
			}
		}
		if(totalCnt >= 2)
			list.add(new Block(totalCnt, rainbowCnt, xPos, yPos));
		if(flag == true)
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(graph[i][j] == 0)
						visited[i][j] = false;
				}
			}
	}
	
	static class Node {
		public int x;
		public int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == false && graph[i][j] > 0)
						bfs(i, j, true);
				}
			}
			
			if(list.isEmpty())
				break;
			
			Collections.sort(list);
			visited = new boolean[N][N];
			bfs(list.get(0).row, list.get(0).col, false);
			removeBlock();
			
			gravity();
			graph = rotate();
			gravity();
			list.clear();
		}
		System.out.println(answer);
	}
}
