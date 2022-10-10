package b21609;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Queue<int[]> q = new ArrayDeque<int[]>();
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		while (true) {
			// 크기가 가장 큰 블록 그룹 찾기,못 찾으면 break
			int[] largest = findgroup(map,N);
			if(largest[0] == -1) {
				break;
			} 
//			System.out.println("가장 큰 그룹의 인덱스: " + largest[0] +", " + largest[1]);
			score += delgroup(map,N,largest); // 그 그룹의 블록 제거 후 점수 증가
//			System.out.println("증가한 점수: " + score);
//			System.out.println("그룹 제거후 맵");
//			printmap(map,N);
			gravity(map,N);//중력 작용
//			System.out.println("중력 작용 후 맵");
//			printmap(map,N);
			rotate(map,N); //90도 회전
//			System.out.println("회전 후 맵");
//			printmap(map,N);
			gravity(map,N); // 중력 작용
//			System.out.println("중력 재작용 후 맵");
//			printmap(map,N);
//			System.out.println("=======================");
		}
		System.out.println(score);
		br.close();
	}
	public static int[] findgroup(int[][] map, int N) {
		//그룹을 찾기 위한 외부 방문 배열, 무지개 블록은 visited 처리 안함
		boolean[][] visited_outer = new boolean[N][N];
		
		int max_size = Integer.MIN_VALUE;
		int max_rainbow = Integer.MIN_VALUE;
		int x = -1, y = -1;
		for(int i = 0; i<N;i++) {
			for(int j= 0; j<N;j++) {
				if(map[i][j] > 0 && visited_outer[i][j] == false) {
					int[] size = calcsize(i,j,N, visited_outer, map);
					if(size[0] != 1) {
						//visited_outer[i][j] = true;
						if(max_size <size[0]) {
							max_size = size[0];
							max_rainbow = size[1];
							x = i;
							y = j;
						}else if(max_size == size[0]) {
							//루프를 돌면서 자연스럽게 행,열이 큰 것을 찾게 함
							if(max_rainbow <= size[1] ) {
								max_rainbow = size[1];
								x = i;
								y = j;
							}
						}
					}
				}
			}
		}
		return new int[] {x,y};
	}
	
	public static int[] calcsize(int x, int y, int N, boolean[][] visited_outer, int[][] map) {
		int size = 0;
		int rainbow_size = 0;
		//bfs를 하기 위한 내부 방문 배열, 무지개 블록도 visited 처리
		boolean[][] visited = new boolean[N][N];
		
		int check = map[x][y];
		q.offer(new int[] {x,y});
		visited_outer[x][y] = true;
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			size++;
			if(map[cur[0]][cur[1]] == 0) {
				rainbow_size++;
			}
			for(int d = 0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if(nx <0 || nx>= N || ny < 0 || ny>= N || visited[nx][ny] ||
					(map[nx][ny] != check && map[nx][ny] != 0)	 )continue;
				q.offer(new int[] {nx,ny});
				visited_outer[nx][ny] = true;
				visited[nx][ny] = true;
			}
		}
		return new int[] {size,rainbow_size};
	}
	public static int delgroup(int[][] map, int N, int[] pos) {
		boolean[][] visited = new boolean[N][N];
		int score = 0;
		int x =  pos[0];
		int y = pos[1];
		int check = map[x][y];
		q.offer(new int[] {x,y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			score++;
			//제거된 칸은 -2로 처리
			map[cur[0]][cur[1]] = -2;
			
			for(int d = 0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if(nx <0 || nx>= N || ny < 0 || ny>= N || visited[nx][ny] ||
					(map[nx][ny] != check && map[nx][ny] != 0)	 )continue;
				q.offer(new int[] {nx,ny});
				visited[nx][ny] = true;
			}
		}
		return (int) Math.pow(score, 2);
	}
	public static void gravity(int[][] map, int N) {
		for(int j = 0; j<N;j++) {
			int i = N-1;
			while(i>0) {
				if(map[i][j] == -2) { //빈칸이면 내릴 블럭 찾기
					int ni = i-1;
					while(ni > 0 && map[ni][j] == -2) ni--;
					if( map[ni][j] != -1) {
						map[i][j] = map[ni][j];
						map[ni][j] = -2;
					}else {
						i = ni;
					}
				}
				i--;
			}
		}
	}
	public static void rotate(int[][] map, int N) {
		int[][] copymap = new int[N][N];
		for(int i = 0; i<N;i++) {
			copymap[i] = Arrays.copyOf(map[i], N);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = copymap[j][N-1-i];
			}
		}
		
	}
	
	public static void printmap(int[][] map, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] >= 0) {
					
					System.out.print(" "+map[i][j] +" ");
				}else {
					
					System.out.print(map[i][j] +" ");
				}
			}
			System.out.println();
		}
	}
	
}
