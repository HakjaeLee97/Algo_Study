package b22251;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//경우의 수
	static int count = 0;
	static int checkP;
	//change[a][b] = a에서 b로 바꿀 때 필요한 반전 횟수
	static int[][] change = {
			{0,4,3,3,4,3,2,3,1,2},
			{4,0,5,3,2,5,6,1,5,4},
			{3,5,0,2,5,4,3,4,2,3},
			{3,3,2,0,3,2,3,2,2,1},
			{4,2,5,3,0,3,4,3,3,2},
			{3,5,4,2,3,0,1,4,2,1},
			{2,6,3,3,4,1,0,5,1,2},
			{3,1,4,2,3,4,5,0,4,3},
			{1,5,2,2,3,2,1,4,0,1},
			{2,4,3,1,2,1,2,3,1,0}
			};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		//1 이상 N 이하가 되도록
		int N =Integer.parseInt(st.nextToken());
		//디스플레이의 자리수
		int K =Integer.parseInt(st.nextToken());
		//반전 시킬 LED 개수
		int P =Integer.parseInt(st.nextToken());
		checkP = P;
		//현재의 층
		int X =Integer.parseInt(st.nextToken());
		
		//접근 : dfs, 각 자리수 별로 반전시켜서 바꿀 숫자들의 경우의 수로 탐색, 숫자의 크기로 백트래킹
		dfs(0,N,K,P,X);
//		count--; //아무런 수도 반전시키지 않는 경우의 수 하나를 제거
		System.out.println(count);
		br.close();
		
	}
	public static void dfs(int depth,int N, int K, int P, int X) {
		//백 트래킹 : 앞의 자리부터 바꿔 나가므로 현재 N을 초과하면 바로 리턴
		//또는 최대 바꿀 표시등 개수를 초과한 경우
		if(P<0 ) {
			return;
		}
		
		//기저 조건
		else if(depth == K) {
			if(P != checkP && X != 0 && X<=N) {
				
				count++;
			}
			return;
		}
		
		else{
			String tmp =  Integer.toString(X);
			if(tmp.length()<K) {
				for (int i = 0, size = K-tmp.length(); i<size; i++) {
					tmp = "0" + tmp;
				}
			}
			int cur = tmp.charAt(depth)-'0';
			String next;
			for (int i = 0; i < 10; i++) {
				next = tmp.substring(0,depth) + Integer.toString(i) + tmp.substring(depth+1,K);
				dfs(depth+1,N,K,P-change[cur][i],Integer.parseInt(next));
				
			}
		}
	}

}
