package b20056;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static class fireball{
		int m;
		int d;
		int s;
		
		public fireball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dx = {N-1,N-1,0,1,1,1,0,N-1};
		int[] dy = {0,1,1,1,0,N-1,N-1,N-1};
		
		ArrayList<fireball>[][] map = new ArrayList[N][N];
		for(int i =0; i<N; i++) {
			for(int j=0; j<N;j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i =0; i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new fireball(m,s,d));
		}
		
		for(int i = 0; i<K; i++) { //이동명령 횟수만큼 반복
			int[][] check = new int[N][N];
			
			for(int r = 0; r<N;r++) {
				for(int c = 0; c<N;c++) {
					if(map[r][c].size() - check[r][c] > 0) {
						int iter= map[r][c].size() - check[r][c];
						for(int f =0; f<iter; f++) {
							fireball cur= map[r][c].remove(0);
							int nr = (r + dx[cur.d]*cur.s)%N ;
							int nc = (c + dy[cur.d]*cur.s)%N ;
							check[nr][nc]++;
							map[nr][nc].add(cur);
						}
					}
				}
			}
			for(int a = 0; a<N;a++) {
				for(int b=0; b<N;b++) {
					if(map[a][b].size()>=2) { //파이어볼이 2개 이상 있을 경우
						int mass = 0;
						int speed = 0;
						int dircheck = map[a][b].get(0).d%2;
						boolean dirsum = true;
						int size = map[a][b].size();
						for(int f = 0; f<size; f++) {
							fireball cur = map[a][b].remove(0);
							mass += cur.m;
							speed += cur.s;
							if(cur.d % 2 != dircheck) dirsum = false;
						}
						mass /= 5;
						speed /= size;
						
						if(mass != 0) { //질량이 0이 아닐 경우 파이어볼이 나누어짐
							if(dirsum) { //전부 홀수거나 짝수일 때
								map[a][b].add(new fireball(mass,speed,0));
								map[a][b].add(new fireball(mass,speed,2));
								map[a][b].add(new fireball(mass,speed,4));
								map[a][b].add(new fireball(mass,speed,6));
							}else {
								map[a][b].add(new fireball(mass,speed,1));
								map[a][b].add(new fireball(mass,speed,3));
								map[a][b].add(new fireball(mass,speed,5));
								map[a][b].add(new fireball(mass,speed,7));
							}
						}
							
						
					}
				}
			}
		}
		int result = 0;
		for(int i =0; i<N;i++) {
			for(int j=0; j<N; j++) {
				for(int f=0,size=map[i][j].size();f<size;f++) {
					
					fireball cur = map[i][j].get(f);
					result += cur.m;
					
				}
			}
		}
		System.out.println(result);
		br.close();
		
	}

}

