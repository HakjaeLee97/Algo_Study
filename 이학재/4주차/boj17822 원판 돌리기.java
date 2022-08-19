package b17822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N,M,T;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean issame;
	static boolean[][] dup;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		dup = new boolean[N][M];
		
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i<T;i++) {
			
			for(int j = 0; j< N; j++) {
				Arrays.fill(dup[j], false);
			}
			
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			spin(arr,x,d,k);
			for(int a = 0;a<N;a++) {
				for (int b= 0; b < M; b++) {
					if(dup[a][b] ) arr[a][b] = -1000;
				}
			}
			
		}
		
		int answer = 0;
		for(int i = 0;i<N;i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] != -1000) answer += arr[i][j] ;
			}
		}
		System.out.println(answer);
		br.close();
	}
	public static void spin(int[][] arr, int x, int d, int k) {
		//회전시키기
		
		for(int i =0; i<N; i++) {
			//한번에 k칸 회전시키면 좋겠는데, 일단 반복문으로 해보기
			if((i+1) % x == 0) { //번호가 x의 배수임
				if(d == 0) { //시계방향 k칸 회전
					for(int s =0; s<k; s++) {
						
						int tmp = arr[i][M-1];
						for (int j = M-1; j > 0; j--) {
							arr[i][j] = arr[i][j-1];
						}
						arr[i][0] = tmp;
					}
				}else { //반시계방향 k칸 회전
					for(int s =0; s<k; s++) {				
						int tmp = arr[i][0];
						for (int j = 0; j < M-1; j++) {
							arr[i][j] = arr[i][j+1];
						}
						arr[i][M-1] = tmp;
					}
				}
			}
		}
		//인접하면서 수가 같은거 찾기
		issame = false;
		for(int i = 0;i<N;i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != - 1000) check(arr,i,j); //원판에서 인접하면서 같은 수 모두 지우기			
			}
			
		}

		if(issame == false) {//원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 뺴고, 작은 수에는 1을 더한다
			double avg = 0;
			int div = N*M;
			for(int i = 0; i<N; i++) {
				for (int j = 0; j < M; j++) {
					if(arr[i][j] != -1000) avg+= arr[i][j];
					else div--;
				}
			}
			avg = (double)avg / (double)div;
			for(int i = 0; i<N; i++) {
				for (int j = 0; j < M; j++) {
					if(arr[i][j] != -1000 && arr[i][j] > avg) arr[i][j]--;
					else if(arr[i][j] != -1000 && arr[i][j] < avg) arr[i][j]++;
				}
			}
		}
	}
	public static void check(int[][] arr, int x, int y) {
		int check = arr[x][y];
		
		for(int i = 0; i<4; i++) {
			int nx = x +dx[i];
			int ny = y +dy[i];
			if(ny <= -1) ny = M-1;
			if(ny >= M) ny = 0;
			if(nx<0 || nx>=N) continue;
			if(arr[nx][ny] == check) {
				dup[nx][ny]= true;
				dup[x][y] = true;				
				issame = true;
			}
		}
		
	}
}
