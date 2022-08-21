package b19238;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,fuel,x,y;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		x = Integer.parseInt(st.nextToken())-1;
		y = Integer.parseInt(st.nextToken())-1;

		int[][] client = new int[M][4];
		int clientcnt = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			client[i][0] = x1;
			client[i][1] = y1;
			client[i][2] = x2;
			client[i][3] = y2;
			map[x1][y1] = 2; //출발지는 2로 저장
		}
		
		while(clientcnt<M) {
			if(findcl(map,client)) {; //다음 승객을 찾아 이동
				int dist = 0;
				if(fuel <= 0) { //이동실패
					fuel = -1;
					break;
				} else { //이동성공
					for(int i = 0; i<M; i++) {
						if(x == client[i][0] && y == client[i][1]) {
							dist = godest(map,client,client[i][2],client[i][3]);
							break;
						}
					}
				} 
				if(dist == -1) { //도착지 이동 실패
					fuel = -1;
					break;
				}
				//도착지로 이동 성공했으나 연료부족
				else if(fuel < 0) {
					fuel = -1;
					break;
				} else {//이동성공
					fuel += dist * 2;
					clientcnt+= 1;
				}
				
			} else { //다음 승객을 찾는것에 실패함
				fuel = -1;
				break;
			}
		}
		System.out.println(fuel);
		
	}
	
	public static boolean findcl(int[][] map, int[][] client) {
		if(map[x][y] == 2) { //시작지점에 손님이 있을 경우
			map[x][y] = 0;
			return true;
		}
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {x,y,0,0}); //x,y, 거리를 저장
		
		ArrayList<int[]> cl = new ArrayList<>();
		int dist = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(!cl.isEmpty() && cur[2] > dist || cur[3] == 1) {
				int minx = Integer.MAX_VALUE;
				int miny = Integer.MAX_VALUE;

				for(int i =0 , size = cl.size(); i<size; i++) {
					int[] cur_cl = cl.get(i);
					if(cur_cl[0] < minx && cur_cl[2] <=dist) {
						minx = cur_cl[0];
						miny = cur_cl[1];						
					}else if(cur_cl[0] == minx && cur_cl[2] <=dist) {
						if(cur_cl[1] < miny) {
							minx = cur_cl[0];
							miny = cur_cl[1];
						}
					}
				}
				
				fuel = fuel - dist;
				x = minx;
				y = miny;
				map[x][y] = 0;
				return true;
			}
			
			for(int i =0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if(nx<0 || nx>= N || ny<0 || ny>= N || map[nx][ny] == 1 || visited[nx][ny]) continue;
				if(map[nx][ny] == 2  ) {//승객을 만난 경우
					cl.add(new int[] {nx,ny,cur[2]+1});
					dist = Math.min(cur[2]+1,dist);
					q.add(new int[] {nx,ny,cur[2]+1,1});
				}
				else {
					q.add(new int[] {nx,ny,cur[2]+1,0});
				}
				visited[nx][ny] = true;
				
			}
		}
		return false; //손님을 만나지 못함
	}


public static int godest(int[][] map, int[][] client, int destx, int desty) {
	boolean[][] visited = new boolean[N][N];
	visited[x][y] = true;
	Queue<int[]> q = new LinkedList<int[]>();
	q.add(new int[] {x,y,0}); //x,y, 거리를 저장
	
	int dist = Integer.MAX_VALUE;
	
	while(!q.isEmpty()) {
		int[] cur = q.poll();
		
		for(int i =0; i<4; i++) {
			int nx = cur[0] + dx[i];
			int ny = cur[1] + dy[i];
			if(nx<0 || nx>= N || ny<0 || ny>= N || map[nx][ny] == 1||visited[nx][ny]) continue;
			
			if(nx == destx && ny == desty) {
				fuel -= cur[2]+1;
				x = destx;
				y = desty;
				return cur[2]+1;
			}
			else{
				dist = Math.min(cur[2]+1,dist);
				q.add(new int[] {nx,ny,cur[2]+1});
				visited[nx][ny] = true;
			}
			
			
		}
	}
	return -1;
}

}
