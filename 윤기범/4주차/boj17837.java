import java.io.*;
import java.util.*;

public class Main {
	public static int N, K;
	public static ArrayList<Integer>[][] chips;
	public static Horse[] horse;
	public static int[][] graph;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {1, -1, 0, 0};
	public static boolean flag = false;
	
	static class Horse {
		int x;
		int y;
		int dir;
		
		public Horse(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Horse [x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}
	}
	
	public static void white(int x, int y, int nx, int ny, int k) { // 제일 위가 제일 오른쪽
		ArrayList<Integer> tmp = new ArrayList<>();
		for(int i = 0; i < chips[x][y].size(); i++) {
			if(chips[x][y].get(i) == k) {
				int num = 0;
				for(int j = i; j < chips[x][y].size(); j++) {
					tmp.add(chips[x][y].get(j));
					num++;
				}
				for(int j = num; j > 0; j--) {
					chips[x][y].remove(chips[x][y].size() - 1);
				}
				break;
			}
		}
		for(int i = 0; i < tmp.size(); i++) {
			chips[nx][ny].add(tmp.get(i));
			horse[tmp.get(i)].x = nx;
			horse[tmp.get(i)].y = ny;
		}
		
		if(chips[nx][ny].size() >= 4)
			flag = true;
	}
	
	public static void red(int x, int y, int nx, int ny, int k) {
		ArrayList<Integer> tmp = new ArrayList<>();
		for(int i = 0; i < chips[x][y].size(); i++) {
			if(chips[x][y].get(i) == k) {
				int num = 0;
				for(int j = i; j < chips[x][y].size(); j++) {
					tmp.add(chips[x][y].get(j));
					num++;
				}
				for(int j = num; j > 0; j--) {
					chips[x][y].remove(chips[x][y].size() - 1);
				}
				break;
			}
		}
		ArrayList<Integer> temp = new ArrayList<>(); 
		for(int i = tmp.size()-1; i >= 0; i--) {
			temp.add(tmp.get(i));
			tmp.remove(i);
		}
		
		for(int i = 0; i < temp.size(); i++) {
			chips[nx][ny].add(temp.get(i));
			horse[temp.get(i)].x = nx;
			horse[temp.get(i)].y = ny;
		}
		if(chips[nx][ny].size() >= 4)
			flag = true;
	} 
	
	public static void move() {
		for(int k = 0; k < K; k++) { // k만큼 반복
			// 말 정보 추출
			int x = horse[k].x;
			int y = horse[k].y;
			int dir = horse[k].dir;
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];				
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || graph[nx][ny] == 2) { // 맵 바깥으로 나가거나 파란색인경우
				// 오른쪽 왼쪽 위 아래
				if(dir == 0)
					dir = 1;
				else if(dir == 1)
					dir = 0;
				else if(dir == 2)
					dir = 3;
				else if(dir == 3)
					dir = 2;
				nx = x + dx[dir];
				ny = y + dy[dir];
				horse[k].dir = dir;
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || graph[nx][ny] == 2) { // 한번 더 맵 바깥으로 나가거나 파란색인 경우
					horse[k].dir = dir;
				}
				else { // 그 외의 경우 흰색 또는 빨간색
					if(graph[nx][ny] == 0) { // 흰색인 경우
						white(x, y, nx, ny, k);
					} 
					else if(graph[nx][ny] == 1) { // 빨간색
						red(x, y, nx, ny, k);
					}
				} 
			}
			else if(graph[nx][ny] == 0) { // 흰색
				white(x, y, nx, ny, k);
			}
			else if(graph[nx][ny] == 1) { // 빨간색
				red(x, y, nx, ny, k);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		graph = new int[N][N];
		horse = new Horse[K];
		chips = new ArrayList[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				chips[i][j] = new ArrayList<>(); // 초기화
			}
		}
		
		for(int i = 0; i < N; i++) { // 그래프 입력 받기
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			horse[i] = new Horse(x, y, dir);
			chips[x][y].add(i);
		}
		
		int cnt = 0;
		while(true) {
			cnt++;
			move();
			if(cnt > 1000) {
				cnt = -1;
				break;
			}
			else {
				if(flag) {
					break;
				}
			}
		}
		System.out.println(cnt);	
	}
}
