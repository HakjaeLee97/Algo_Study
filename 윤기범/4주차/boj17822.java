import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, T;
	public static int[][] circle;
	public static Info[] turn;
	public static ArrayList<Node> arr;
	
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Info {
		int x;
		int d;
		int k;
		
		public Info(int x, int d, int k) {
			this.x = x;
			this.d = d;
			this.k = k;
		}
	}
	
	public static void check() {
		arr = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(j == 0) { // 첫 열
					int temp = circle[i][j];
					if((circle[i][M-1] == circle[i][j]) && temp != 0) {
						arr.add(new Node(i, M-1));
						arr.add(new Node(i, j));
					}
					if((temp == circle[i][j+1])&& temp != 0) {
						arr.add(new Node(i, j + 1));
						arr.add(new Node(i, j));
					}
				}
				else if(j == M-1) { // 마지막 열
					int temp = circle[i][M-1];
					if((circle[i][M-2] == circle[i][M-1]) && temp != 0) {
						arr.add(new Node(i, M-2));
						arr.add(new Node(i, M-1));
					}
					if((temp == circle[i][0]) && temp != 0) {
						arr.add(new Node(i, M-1));
						arr.add(new Node(i, j));
					}
				}
				else { // 그 외					
					int temp = circle[i][j];
					if((circle[i][j-1] == circle[i][j]) && temp != 0) {
						arr.add(new Node(i, j-1));
						arr.add(new Node(i, j));
					}
					if((temp == circle[i][j+1]) && temp != 0) {
						arr.add(new Node(i, j));
						arr.add(new Node(i, j+1));
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(i == 0) { // 첫 행
					if((circle[i][j] == circle[i+1][j]) && circle[i][j] != 0) {
						arr.add(new Node(i, j));
						arr.add(new Node(i+1, j));
					}
				}
				else if(i == N ) { // 마지막 행
					if((circle[i][j] == circle[i-1][j]) && circle[i][j] != 0) {
						arr.add(new Node(i, j));
						arr.add(new Node(i-1, j));
					}
				}
				else { // 그 외
					int temp = circle[i][j];
					if((circle[i][j] == circle[i-1][j]) && temp != 0) {
						arr.add(new Node(i, j));
						arr.add(new Node(i-1, j));
					}
					if((temp == circle[i+1][j]) && temp != 0) {
						arr.add(new Node(i, j));
						arr.add(new Node(i+1, j));
 					}
				}
			}
		}
		
		for(int i = 0; i < arr.size(); i++) {
			Node node = arr.get(i);
			int x = node.x;
			int y = node.y;
			if(circle[x][y] != 0) 
				circle[x][y] = 0;
		}
	}
	
	public static void action() {
		for(int t  = 0; t < T; t++) { // 입력받은 회전 배열
			Info info = turn[t];
			int row = info.x; // 원판 번호
			int dir = info.d; // 방향
			int num = info.k; // 횟수
			for(int n = 0; n < num; n++) {				
				for(int i = 1; i <= N; i++) {
					if(i % row == 0) { // 배수 번째 원판이면 
						if(dir == 0) { // 시계방향
							int temp = circle[i][M-1]; // 마지막 숫자
							for(int j = M-2; j >= 0; j--) { // 하나씩 당겨옴
								circle[i][j+1] = circle[i][j];
							}
							circle[i][0] = temp; // 첫번째 정리
						}
						else { // 반시계 방향
							int temp = circle[i][0]; // 첫 번째 숫자
							for(int j = 0; j < M-1; j++) { // 하나씩 당겨옴
								circle[i][j] = circle[i][j+1];
							}
							circle[i][M-1] = temp; // 마지막 정리
						}
					}
				}
			}
			
			check();

			if(arr.size() == 0) { // 인접하면서 같은 수가 없는 경우
				int sum = 0;
				double avg = 0.0;
				int cnt = 0;
				for(int i = 1; i <= N; i++) {
					for(int j = 0; j < M; j++) {
						if(circle[i][j] != 0) {
							sum += circle[i][j];
							cnt += 1;
						}
					}
				}
				if(cnt != 0) { // 0으로 나누는것 방지
					avg = 1.0 * sum / cnt;
					for(int i = 1; i <= N; i++) {
						for(int j = 0; j < M; j++) {
							if(circle[i][j] != 0) {
								if(circle[i][j] < avg)
									circle[i][j] += 1;
								else if(circle[i][j] > avg)
									circle[i][j] -= 1;
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
		T = Integer.parseInt(st.nextToken());
		
		circle = new int[N + 1][M]; // 계산의 편의를 위해 N+1행
		turn = new Info[T];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			turn[i] = new Info(a, b, c);
		}
		
		action();
		
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				sum += circle[i][j];
			}
		}
		System.out.println(sum);
	}
}
