package October_first;
import java.io.*;
import java.util.*;



public class Main{

	static int N,M;
	static int [][]map;
	static int [][] viva;
	static int move[][] = {{},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};

	static int dae[][] = {{-1,-1},{-1,1},{1,-1},{1,1}};
	static List<int[]>list = new ArrayList<>();
	static int answer; 
	
	static void calc() {
		
		list.add(new int[] {N-1,0});
		list.add(new int[] {N-1,1});
		list.add(new int[] {N-2,0});
		list.add(new int[] {N-2,1});
		
		
		for(int t=0;t<M;t++) {
			
			int d = viva[t][0];
			int s = viva[t][1];
			boolean[][]check = new boolean[N][N];
			
			List<int[]> tmpList = new ArrayList<>();
			//구름 이동
			for(int c=0;c<list.size();c++) {

				int i = list.get(c)[0];
				int j = list.get(c)[1];
		

				i += move[d][0] * s;
				j += move[d][1] * s;
			
				
				inner:
				while(true) {
					
					if(i<0) i += N;
					if(N-1<i) i -= N;
					if(j<0) j += N;
					if(N-1<j) j -= N;
					
					
					if(-1<i && i<N && -1<j && j<N) break inner;
				}
				
				//물의 양 1씩 증가
	
				map[i][j]++;
				check[i][j] = true;
				tmpList.add(new int[] {i,j});
			}
			
	
			
		//경계선 넘으면 x
			//물 증가한 칸에 대각선 방향으로 1인 칸에 물이 있는 바구니 수만큼 물 양 증가
			for(int c=0;c<tmpList.size();c++) {

				int i = tmpList.get(c)[0];
				int j = tmpList.get(c)[1];
				
				int cnt = 0;
				
				for(int g=0;g<4;g++) {
					
					int ni = i+ dae[g][0];
					int nj = j+ dae[g][1];
					
					if(ni<0||nj<0||N-1<ni||N-1<nj) continue;
					
					if(map[ni][nj]!=0) cnt++;
					
					
				}
				
				map[i][j] += cnt;
			}
			
			
	
			//바구니에 저장된 물 양 2이상인 모든 칸에 구름 생김, 물의 양 2 줄어듦
			//**구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다. (loc은 안해주면 될듯)
		
			list = new ArrayList<>();
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(1<map[i][j]) {
						if(!check[i][j]) {
							list.add(new int[] {i,j});
							map[i][j] -= 2;
							
						}
					}
				}
			}
			
		
			
		}
			

		
	}
	
	static void input()throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		viva = new int[M][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			viva[i][0] = Integer.parseInt(st.nextToken());
			viva[i][1] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	static void output() {
		
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				answer += map[i][j];
		
		
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws Exception{
		
		input();
		calc();
		output();
	
	}

}
