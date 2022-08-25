package b21608;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int[] stu;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] pri = new ArrayList[N*N+1]; // 선호도 배열
		for(int i = 0; i<N*N+1; i++) {
			pri[i] = new ArrayList<>();
		}
		int[][] map = new int[N][N]; //학생이 앉은 정보 배열, 0은 아직 비어있음
		
		stu = new int[N*N+1]; //학생이 앉는 순서 저장하는 배열
		
		for(int i = 1, size =N*N ; i <= size; i++) {
			st = new StringTokenizer(br.readLine()," ");
			stu[i] = Integer.parseInt(st.nextToken());
			for(int j = 1; j<5; j++) {
				pri[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		for (int i = 1,size = stu.length; i < size; i++) {
			sit(pri,map,stu[i]); 
		}
		//만족도 조사
		int result = 0;
		for(int i =0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				result += check(pri,map,i,j);
			}
		}
		System.out.println(result);
		br.close();
	}
	public static void sit(ArrayList<Integer>[] pri, int[][] map,int nowstu) {
		int best = -1;
		int spacebest = -1;
		int idx = 0; // pri배열에서 이번 학생의 위치
		for(int i = 1; i<=N*N; i++) {
			if(stu[i] == nowstu)
				idx = i;
		}
				
		int x = 0, y = 0;
		for(int i = 0; i<N;i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) continue;
				int cnt = 0; //주변에 좋아하는 학생 수 체크
				int space = 0; // 주변에 빈 칸 체크
				for(int d = 0; d< 4; d++) {
					int nx = i +dx[d];
					int ny = j +dy[d];
					if(nx<0 || nx >= N || ny< 0 || ny>= N ) continue;
					if(map[nx][ny] == 0) space++;
					if(pri[idx].contains(map[nx][ny])) cnt++;
				}
				if(cnt>best) {
					best = cnt;
					spacebest = space;
					x = i;
					y = j;
				}else if(cnt == best) {
					if(space > spacebest) {
						spacebest = space;
						x = i;
						y = j;
					}
				}
			}
		}
		map[x][y] = nowstu;
	}
	public static int check(ArrayList<Integer>[] pri, int[][] map,int x, int y) {
		int nowstu = map[x][y];
		int idx = 0; // pri배열에서 이번 학생의 위치
		for(int i = 1; i<=N*N; i++) {
			if(stu[i] == nowstu)
				idx = i;
		}
		
		int cnt = 0; //주변에 좋아하는 학생 수 체크
		for(int d = 0; d< 4; d++) {
			int nx = x +dx[d];
			int ny = y +dy[d];
			if(nx<0 || nx >= N || ny< 0 || ny>= N ) continue;
			if(pri[idx].contains(map[nx][ny])) cnt++;
		}
		switch (cnt){
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 10;
		case 3: 
			return 100;
		case 4:
			return 1000;
		}
		return 0;
	}
}
