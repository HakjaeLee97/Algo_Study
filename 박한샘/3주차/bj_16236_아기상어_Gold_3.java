package August_second;

import java.util.*;
import java.io.*;
// 물고기 M마리 아기 상어 1마리
// 아기상어2 상하좌우로 인접한 한 칸씩 이동
// 자기보다 큰 물고기가 있는 칸은 못지나감
// 작은 물고기만 먹을 수 있다. (크기가 같은 물고기는 못 먹지만, 지나가는거 ㅆㄱㄴ)
// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
// 이동할지 경정하는 방법
// 1. 먹을 물고기가 없으면 엄마 상어에게 도움 요청
// 2. 먹을 수 있는 물고기가 1마리면 그 물고기 ㄱㄱ
// 3. 먹을 수 있는 물고기가 1마리 이상이면 가장 가까운 물고기 ㄱㄱ
// 4. 같은 거리면, 가장 위, 위도  많으면 가장 왼쪽(-1,0) -> (0,-1)
// 구하는 값 : 엄마 상어에게 도움 요청 전까지 물고기를 잡아먹을 수 있는지(몇 초 동안)

public class bj_16236_아기상어_Gold_3 {
	
	static int[][] dij = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[]pos = new int[2];
	static int[][]map;
	static int[][]dist;
	static int size = 2;
	static int eat = 0;
	static int minX;
	static int minY;
	static int minDist;
	static int N;
	static int time=0;
	
	static void bfs() {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {pos[0],pos[1]});
		
		while(!q.isEmpty()) {
			
			int []c = q.poll();
			int i = c[0];
			int j = c[1];
			
			if(map[i][j]!=0 && map[i][j] < size) {
				
				if(minDist>dist[i][j]) {
					minDist = dist[i][j];
					minX = i;
					minY = j;
					
				}else if(minDist==dist[i][j]) {
					if(minX>i) {
						minDist = dist[i][j];
						minX = i;
						minY = j;
					}else if(minX==i) {
						if(minY>j) {
							minDist = dist[i][j];
							minX = i;
							minY = j;
						}
					}
				}
				
			}
			
			for(int d=0;d<4;d++) {
				
				int ni = i + dij[d][0];
				int nj = j + dij[d][1];
				
				
				if(ni<0||nj<0||N-1<ni||N-1<nj) continue;
				
				if(dist[ni][nj]==0 && map[ni][nj]<=size) {
					dist[ni][nj] = dist[i][j] + 1;
					q.offer(new int[] {ni,nj});
				}
				
				
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					pos[0] = i;
					pos[1] = j;
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			
			dist = new int[N][N];
			minX = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			minDist = Integer.MAX_VALUE;
			
			bfs();
			
			if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
				eat++;
				map[minX][minY] = 0;
				pos[0] = minX;
				pos[1] = minY;
				time += dist[minX][minY];
				
				if(eat==size) {
					size++;
					eat = 0;
				}
			}else break;
			
			
		}
		
		System.out.println(time);
		
	}
	
}