package b15684;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[][] lines;
	static int answer= 5; //추가할 가로선의 개수 
	static ArrayList<int[]> list;
	static boolean finish;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][] map = new int[H+1][N+1]; // 인덱스를 1부터 사용
		list = new ArrayList<>();// 가로선을 놓을 수 있는 위치의 배열
		lines = new int[3][2]; // 선택된 가로선을 놓을 위치의 배열
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
		}
		
		for(int i = 1; i<N; i++) {
			for(int j = 1; j <= H; j++) {
				if(map[j][i] == 0) {
					list.add(new int[] {j,i});
				}
			}
		}
		
		if (check(map,N,H)) System.out.println(0);
		
		else {
			for(int r = 1; r<= Math.min(3,list.size()); r++) {
				comb(0,0,r,map,N,H);
				if(finish) break;
			}
	
	
			//모든 빈칸 중 최대 3개까지 가로선을 추가해서 results를 비교한다. 
			if(answer == 5) answer = -1;
			System.out.println(answer);
		}
		br.close();
	}

	public static boolean check(int[][] map, int N, int H) {
	
		for(int i =1; i<= N; i++) { // 각 세로선마다 반복
			int idx = i;
			for(int j = 1; j <= H; j++) { // 아래로 내려오는 반복
				if(map[j][idx] == 1) { // 오른쪽으로 가야한다
					idx++;
				}else if(map[j][idx-1] == 1) { // 왼쪽으로 간다
					idx--;
				}				
			}
			if(idx != i) return false;
		}

		return true;
	}
	
	public static void comb(int depth, int start, int R, int[][] map, int N, int  H) {
		if(finish) return;
		if (depth == R) {
			for(int i = 0; i< R; i++) {
				int x = lines[i][0]; 
				int y = lines[i][1];
				map[x][y] = 1;
			} // 선택된 자리에 선을 그음
			if(check(map,N,H)) {
				answer = Math.min(answer, R);
				finish = true;
			} 
			for(int i = 0; i< R; i++) {
				int x = lines[i][0]; 
				int y = lines[i][1];
				map[x][y] = 0;
			} // 선을 돌려둠
			return;
		}
		for(int i = start; i<list.size(); i++) {
			lines[depth] = list.get(i);
			comb(depth+1, i+1,R,map,N,H);
			if(finish) break;
		}
	}
}
