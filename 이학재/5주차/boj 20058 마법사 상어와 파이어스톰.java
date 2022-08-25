package b20058;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] v ;
	static int size;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int L = (int)Math.pow(2, N);
		int[][] arr = new int[L][L];
		for(int i = 0; i<L; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < L; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());		
			}
		}
		st = new StringTokenizer(br.readLine()," ");
		for(int i =0; i<Q; i++) {
			int l = (int)Math.pow(2,Integer.parseInt(st.nextToken()));
			spin(arr,l,0,0,L);
			delice(arr,L);
		}
		int ans = 0;
		v = new boolean[L][L];
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				ans += arr[i][j];
				if(!v[i][j] && arr[i][j] != 0) {
					bfs(arr,i,j,L);
				}
				
			}
		}
		System.out.println(ans);
		System.out.println(size);
	}
	public static void spin(int[][] arr, int l,int x,int y, int length) {
		if(length == l) { //격자가 나눠짐
			//90도 회전
			int[][] tmp = new int[l][l];
			for(int i = x ; i< x+l; i++) {
				for(int j = y; j<y+l;j++) {
					tmp[i-x][j-y] = arr[l-1-j+y+x][i+y-x];
				}
			}
			for(int i = 0; i<l; i++) {
				for(int j =0; j<l;j++) {
					arr[i+x][j+y] = tmp[i][j];
				}
			}
		}
		else {
			spin(arr,l,x,y,length/2);
			spin(arr,l,x,y+length/2,length/2);
			spin(arr,l,x+length/2,y,length/2);
			spin(arr,l,x+length/2,y+length/2,length/2);
		}
	}
	public static void delice(int[][] arr, int L) {
		boolean[][] reduce = new boolean[L][L];
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				if(arr[i][j] == 0) continue;
				int cnt =0;
				for(int d = 0; d< 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx<0 || nx>= L || ny <0 || ny>= L || arr[nx][ny] ==0 )continue;
					cnt++;
				}
				if (cnt<3) reduce[i][j] = true;
			}
		}
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				if(reduce[i][j]) arr[i][j]--;
			}
		}
	}
	public static void bfs(int[][] arr,int x, int y, int L) {
		v[x][y] = true;
		int s = 0;
		Queue<int[]> q= new LinkedList<int[]>();
		q.offer(new int[] {x,y});
		
		while(!q.isEmpty()) {
			s++;
			int[] cur= q.poll();
			for(int i =0; i<4;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if(nx<0 || nx>= L || ny < 0 || ny >= L  || arr[nx][ny] == 0|| v[nx][ny]) continue;
				v[nx][ny] = true;
				q.offer(new int[] {nx,ny});
			}
		}
		size = Math.max(size, s);
	}
}	
