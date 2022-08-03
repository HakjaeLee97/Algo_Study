package s1249;

import java.util.*;
import java.io.*;

public class Solution {
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] d = new int[N][N];
			for(int i =0; i<N; i++) {
				String tmp = br.readLine();
				
				for(int j=0; j<N; j++) {
					map[i][j] = tmp.charAt(j) - '0';
					d[i][j] = Integer.MAX_VALUE;
				}
			} // 입력 값 받기 및 초기화
			
			PriorityQueue<daij> q = new PriorityQueue<>();
			d[0][0] = 0;
			q.add(new daij(0,0,0));
			int[] dx = {0,0,1,-1};
			int[] dy = {1,-1,0,0}; 
			
			outer: while(!q.isEmpty()) {
				daij tmp = q.poll();
				int x = tmp.x, y = tmp.y, w = tmp.w;
				for (int i=0; i<4; i++) {
					int nowx= x+dx[i], nowy = y+dy[i];
					if(nowx<0 || nowy< 0|| nowy>=N || nowx >= N) continue;
					
					if(nowx == N-1 && nowy == N-1) {
						d[nowx][nowy] = d[x][y];
						break outer;
					}
					if(d[nowx][nowy] > d[x][y] + map[nowx][nowy]) {
						d[nowx][nowy] = w+ map[nowx][nowy];
						q.add(new daij(nowx,nowy,d[nowx][nowy]));
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(d[N-1][N-1]).append("\n");
		}
		System.out.println(sb);		
	}

}

class daij implements Comparable<daij>{
	int x,y,w;
	
	public daij(int x, int y, int w) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
	}
	
	@Override
	public int compareTo(daij o) {
		return this.w-o.w;
	}
}


// import heapq


// T= int(input())

// dx,dy = [-1,1,0,0],[0,0,-1,1]

// for test_case in range(1,T+1):
//     N = int(input())
//     road_map = [list(map(int,list(input()))) for _ in range(N)]
//     distance = [[int(10e9)]*N for _ in range(N)]
//     x, y = 0,0
//     heap = [(road_map[x][y], x,y)]
//     distance[x][y] = road_map[x][y]

//     while heap:
//         dist, x, y = heapq.heappop(heap)
//         if distance[x][y] < dist:
//             continue
//         for i in range(4):
//             nx, ny = x +dx[i] , y+ dy[i]
//             if nx < 0 or nx >= N or ny < 0 or ny >= N:
//                 continue
//             cost = dist + road_map[nx][ny]
//             if cost < distance[nx][ny]:
//                 distance[nx][ny] = cost
//                 heapq.heappush(heap,[cost,nx,ny])

//     print("#%d"%test_case,distance[N-1][N-1])
