package b2531;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int answer=0, result = 0;
		int[] belt = new int[N];
		int[] visit = new int[d+1];
		for(int i = 0; i<N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i =0 ; i<k;i++) {
			if(visit[belt[i]] == 0) {
				result+= 1;
			}
			visit[belt[i]] += 1;
		}
		if(visit[c] == 0) answer = result + 1;
		else answer = result;
		
		
		for(int i = 0; i<N; i++) {
			int start = i;
			int end = (start + k) % N;
			visit[belt[start]] -= 1;
			if(visit[belt[start]] == 0) {
				result -= 1;
			}
			if(visit[belt[end]] == 0) {
				result += 1;
			}
			visit[belt[end]] += 1;
			
			if(visit[c] == 0) {
				answer = Math.max(answer, result + 1);
			}else {
				answer = Math.max(answer, result);
			}
		}
		System.out.println(answer);
		br.close();
	}

}
