package August_second;

import java.util.*;
import java.io.*;
/*
 처음 0,0으로 시작한다.
 물고기를 먹으면 물고기 얼굴 방향으로 돌린다
 물고기가 이동한다
 이동할 곳이 없으면(상어가 있거나, 번위이탈) 반시계 45도로 방향을 돌린 후 이동한다
 이동할 곳이 그래도 없으면 이동하지 않는다.
상어가 먹을수 있는 양의 최대값(bfs 해보자)
  
  */

public class bj_19236_청소년상어_Gold_2 {

	
	static int Max = 0;
	static int[][]dij = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static int[]pos = new int[3]; //상어위치, 먹은횟수
	
	
	static void fishMove(List<int[]> fishList) {
		
		boolean[]fishVisited = new boolean[fishList.size()];
	
		for(int i=0;i<fishList.size()-1;i++) {
				if(fishVisited[i]) continue;
			for(int j=i+1;j<fishList.size();j++) {
				
				int d = fishList.get(i)[0];
				
				for(int k=0;k<8;k++) {
					
					d = (d + k) % 8;
					
					if(fishList.get(j)[2]==fishList.get(i)[2]+dij[d][0]) {
						if(fishList.get(j)[3]==fishList.get(i)[3]+dij[d][1]) {
							//방문 처리 후 위치 교환
							fishVisited[i] = true;
							fishVisited[j] = true;
							int[]tmp = fishList.get(i);
							fishList.remove(i);
							fishList.remove(j);
							fishList.add(i,fishList.get(j));
							fishList.add(j,tmp);
						}
					}
					
					
					
				}
				
			}
		}
		
	}
	
	static void dfs(int[][]map,int i,int j,List<int[]> list,int sum) {
		
		//조건
		if(Max<sum) Max = sum;
		//물고기 대이동
		fishMove(list);
		
		
		//잡아 먹고(이게 재귀)
		for(int tc=0;tc<3;tc++) {

			int ni = pos[0] + dij[pos[2]][0] * tc;
			int nj = pos[1] + dij[pos[2]][1] * tc;
			
			if(ni<0||nj<0||3<ni||3<nj) continue;
			if(map[ni][nj]==0) continue;
			
			int[][]copyArr = new int[4][4];
			List<int[]> copyList = list;
			
			for(int y=0;y<4;y++)
				for(int x=0;x<4;x++)
					copyArr[y][x] = map[y][x];
			
			int idx = 0;
			
			int size = copyArr[ni][nj];
			for(int y = 0 ; y < copyList.size();y++) {
				if(copyList.get(y)[0]==size) {
					idx = y;
					break;
				}
			}
			copyArr[ni][nj] = 0;
			
			int newOne = sum + copyList.get(idx)[0];
			pos[0] = copyList.get(idx)[2];
			pos[1] = copyList.get(idx)[3];
			pos[2] = copyList.get(idx)[1];
			copyList.remove(idx);
			
			dfs(copyArr,ni,nj,copyList,newOne);
			
		}
		
		
		
	}
	

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		int[][]map = new int[4][4];
		List<int[]> fishList = new ArrayList<>();
		for(int i=0;i<4;i++) {
			
			st = new StringTokenizer(br.readLine()," ");
			
			for(int j=0;j<4;j++) {                             
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
									//물고기 크기,방향, 좌표
				map[i][j] = num;
				
				fishList.add(new int[] {num,dir,i,j});
			}
		}
		//물고기 정렬(대이동을 위해)
		Collections.sort(fishList, ( o1,  o2 ) -> {
				return o1[0]-o2[0];		
		});
		
		
		map[0][0] = 0;
		
		int sum = fishList.get(0)[0];
		pos[2] = fishList.get(0)[3];
		fishList.remove(0);
		pos[0] = 0;
		pos[1] = 0;
		dfs(map,0,0,fishList,sum);
		
		
		
		System.out.println(Max);
		
	}
}
