package October_fourth;

import java.io.*;
import java.util.*;
public class Main{
	static int T;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for( int t = 0 ; t < T ; t++ ) {
			int N = Integer.parseInt(br.readLine());
			long stocks[] = new long[N];
			st = new StringTokenizer(br.readLine());
			for( int i = 0 ; i < N ; i++ ) {
				stocks[i] = Integer.parseInt(st.nextToken());
			}
			
			
			long answer=0;
			long max = 0;
			
			for(int i=N-1;i>-1;i--) {
				if(max<stocks[i]) max = stocks[i];
				else answer += max-stocks[i];
			}
			
			sb.append(answer + "\n");
		}
		System.out.println(sb.toString());
	}

}