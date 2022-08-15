package August_second;

import java.io.*;
import java.util.*;
//1번 단방향 4
//2번 양방향 2
//3번 직각 4
//4번 세방향 4
//5번 사방향 1
//combination 문제 같음 각각 number의 콤비네이션을 해줘야함! (1은 4개)->이걸 어떻게 표현할지가 관건일듯?

public class bj_15683_감시_Gold_4 {
	
	static int N;
	static int M;
	static int [][]map;
	static BufferedReader br;
	static StringTokenizer st;
	//경우의 수마다 다른 배열
	static int[][][] cctv = {{{}}, {{0}, {1}, {2}, {3}}, {{0, 2}, {1, 3}},
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
			{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
			{{0, 1, 2, 3}}};
	static int[][]mov = {{-1,0},{0,1},{1,0},{0,-1}};
	//콤비네이션을 위한 배열 선언
	static int[][]arr;
	static List<int[]> cctvList = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	
	static int check(int[][]map) {
	
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void combi(int depth,int[][]map) {
		
		if(depth==cctvList.size()) {
			answer = Math.min(answer, check(map));
			return;
		}
		
		
		int type = cctvList.get(depth)[2]; //cctv가 뭔타입인지 알아야 할거 아녀?
		int y = cctvList.get(depth)[0];
		int x = cctvList.get(depth)[1];
		
		//맵 카피
		for(int i=0;i<cctv[type].length;i++) {
			int[][]tmp = new int[N][M];
			for(int k=0;k<N;k++) {
				tmp[k] = map[k].clone();
			}
			
			for(int j=0;j<cctv[type][i].length;j++) {
				int d = cctv[type][i][j]; //방향(삼방향이나 사방향은 인덱스가 세개나 됨..
				
				int ni = y + mov[d][0];
				int nj = x + mov[d][1]; //방향전환!
				
				while(true) {
														//벽인경우
					if(ni<0||nj<0||N-1<ni||M-1<nj || map[ni][nj]==6) break;
					
					tmp[ni][nj] = -1; //갱신
					ni += mov[d][0];
					nj += mov[d][1];
				
				}
			
			
			}
			
			combi(depth+1,tmp);
		}
		
	}
	
	static void input() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine()," ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(!(map[i][j]==0 || map[i][j]==6)) {
					cctvList.add(new int[] {i,j,map[i][j]});
					}
				}
			}
				
		}
	
	
	
	public static void main(String[] args) throws Exception {
	
		
		input(); //인풋 메소드 따로
		
		combi(0,map);
		
		System.out.println(answer);
		
	
	}
}
