package August_third;

import java.io.*;
import java.util.*;

/*
조건
, 그 승객을 태워 이동하면서 소모한 연료 양의 두 배가 충전된다.
 이동하는 도중에 연료가 바닥나면 이동에 실패하고, 그 날의 업무가 끝난다. 
 승객을 목적지로 이동시킨 동시에 연료가 바닥나는 경우는 실패한 것으로 간주하지 않는다.


고객은 출발지에서만 타고  목적지에서만 택시에 내릴 수 있다.
'현재 위치에서' 최단거리가 가장 짧은 승객을 고른다.
같다면 (i)행 - (j)열 순으로 고른다.
즉, 현재 위치가 항상 정해져 있기 때문에, 순열 문제는 아닐듯?
벽이 있기 때문에, 최단 거리를 계산하기 위해 계속 한번씩 가봐야 겠다
가고 나서, 백트레킹으로 최소값을 저장해주는 변수 선언!

할 일 : 최단 거리 계산하고 데려다 주고, 최단 거리 계산하고 데려다주고 이게 다네? */

public class bj_19238_스타트택시_Gold_3 {
	
	static int N, M, K;//맵크기  //승객 //연료량
	static int[][]mov = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[][]map;
	static int ti; //택시 위치
	static int tj; 
	static int cus[][]; //고객 정보
	static int comp; //최단거리 비교
	static int cnt; //최단거리 비교
	static int goal; //승객 출발-도착
	
	static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		ti = Integer.parseInt(st.nextToken())-1;
		tj = Integer.parseInt(st.nextToken())-1;
		
		cus = new int[M][4];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			cus[i][0] = Integer.parseInt(st.nextToken())-1;
			cus[i][1] = Integer.parseInt(st.nextToken())-1;
			cus[i][2] = Integer.parseInt(st.nextToken())-1;
			cus[i][3] = Integer.parseInt(st.nextToken())-1;
			
		}
	}

	static void bfs(int idx) {
		
		cnt = 0; //cnt 초기화
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {cus[idx][0],cus[idx][1],0}); //위치랑 cnt
	
		while(!q.isEmpty()) {
			
			int[]c = q.poll();
			int i = c[0];
			int j = c[1];
			int thisCnt = c[2];
			
			if(comp<cnt) return; //최단거리각 안나오면 여기서 프루닝 해주자
			
			if(i==ti && j==tj) {
				cnt = thisCnt;
				return;
			}
			
			for(int d=0;d<4;d++) {
				int ni = i + mov[d][0];
				int nj = j + mov[d][1];
				if(ni<0||nj<0||N-1<ni||N-1<nj) continue;
				if(map[ni][nj]==0) {
					q.offer(new int[] {ni,nj,thisCnt+1});					
					}
				} 
			}
				
	}
	
	static void bfs2(int y,int x) {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {y,x,0});
		
		while(!q.isEmpty()) {
			
			int[]c = q.poll();
			int i = c[0];
			int j = c[1];
			int cnt = c[2];
			
			if(i==ti &&j==tj) {
				goal = cnt;
				return;
			}
			
			for(int d=0;d<4;d++) {
				
				int ni = i + mov[d][0];
				int nj = j + mov[d][1];
				
				if(ni<0||nj<0||N-1<ni||N-1<nj) continue;
				
				if(map[ni][nj]==0) {
					q.offer(new int[] {ni,nj,cnt+1});
				}
				
			}
			
		}
		
		
	}
	
	static void cal() {
	
		int idx = M;
		
		while(M>0) {
			List<int[]>list = new ArrayList<>();
			comp = Integer.MAX_VALUE; //초기화
			//최단거리계산 bfs
			for(int i=0;i<idx;i++) {
				if(cus[i][0] != -1) {
					bfs(i);
					if(comp>cnt) {
						
						comp = cnt;
						list.clear();
						list.add(new int[] {cus[i][0],cus[i][1],cus[i][2],cus[i][3],comp,i});
					}else if(comp==cnt) {
						list.add(new int[] {cus[i][0],cus[i][1],cus[i][2],cus[i][3],comp,i});
					}
				}
			}
			//어디 갈지 고르기 위한 람다식
			Collections.sort(list,(o1, o2)-> {
					return o1[0] ==o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
				});
			//이동 (택시->승객)
			int [] custom = new int[4];
			custom = list.get(0);
			if(K<custom[4]) {
				K = -1;
				return;
			}
			//연료 뺴주기
			K -= custom[4];
			
			//택시 위치 변경
			ti = custom[0];
			tj = custom[1];
			//이동 (승객->목적지)
			goal = 0;
			bfs2(custom[2],custom[3]);
			ti = custom[2];
			tj = custom[3];
			if(K<goal) {
				K = -1;
				return;
			}
			//연료계산
			K -= goal;
			K += goal*2;
			//-1해주기 (이미 완수했다는걸 저렇게 표현할거임)
			
			cus[custom[5]][0] = -1; 
			//고객 마이너스 해주기
			M--;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		input();
		cal();
		System.out.println(K);
		
	}

}
