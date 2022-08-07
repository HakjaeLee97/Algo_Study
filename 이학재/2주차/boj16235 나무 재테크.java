package b16235;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {



	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dx = new int[] {1,-1,0,0,1,1,-1,-1};
		int[] dy = new int[] {0,0,1,-1,1,-1,1,-1};
		
		ArrayList<Integer>[][] trees = new ArrayList[N][N];
		int[][] nutrition = new int[N][N];
		int[][] now_nut = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				now_nut[i][j] = 5;
				nutrition[i][j] = Integer.parseInt(st.nextToken());
				trees[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			int z= Integer.parseInt(st.nextToken());
			trees[x-1][y-1].add(z);
		}

		for(int yr = 0; yr< K; yr++) {
			for(int i = 0; i<N;i++) {
				for(int j = 0; j<N; j++) {
					if(trees[i][j].size() != 0) {
						boolean notdead = true;
						int k = 0;
						while(k < trees[i][j].size()) {
							int age = trees[i][j].get(k);
							if(age <= now_nut[i][j] && notdead) { // 양분을 먹고 자람
								now_nut[i][j] -= age;
								trees[i][j].set(k,age+1);
							} 
							else { //양분을 먹지 못하고 죽음
								notdead = false;
								now_nut[i][j] += age/2;
								trees[i][j].remove(k--); // remove로 하나 줄었기 때문에 인덱스 수정
							}
							k++;
						}
							
						
					}
				}
				
			}
			for(int i = 0; i<N;i++) {
				for(int j = 0; j<N; j++) {
					if(trees[i][j].size() != 0) {
						for(int k = 0; k<trees[i][j].size(); k++) {
							int age = trees[i][j].get(k);
							if(age % 5 == 0) {
								for(int l = 0; l<8; l++) {
									int nx = i + dx[l];
									int ny = j + dy[l];
									if(nx < 0 || nx>= N|| ny<0|| ny>=N) continue;
									trees[nx][ny].add(0,1);
								}
							}
						}
					}
				}
			}
			for(int i = 0; i<N;i++) {
				for(int j = 0; j<N; j++) {
					now_nut[i][j] += nutrition[i][j];
				}
			}
		}
		
		int answer =0;
		for(int i = 0; i<N;i++) {
			for(int j = 0; j<N; j++) {
				int tmp = trees[i][j].size();
				if(tmp != 0) {
					answer += tmp;
				}
			}
		}
		System.out.println(answer);

		
	}

}
