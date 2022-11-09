package November_first;

import java.io.*;
import java.util.*;

public class bj_19941_햄버거분배_Silver_3 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		char []arr = new char[N];
		int answer = 0;
		
		String str = br.readLine();
		boolean[]check = new boolean[N];
		for(int i=0;i<N;i++) arr[i] = str.charAt(i);
			
		for(int i=0;i<N;i++) {
			
			for(int j=i;j<i+K+1;j++) {
				if(i==j) continue;
				if(N-1<j) break;
				if(!check[j]&&!check[i]&&arr[i]!=arr[j]) {
					check[i] = true;
					check[j] = true;
					answer++;
					
					break;
				}
			}
			
		}
		System.out.println(answer);
		
	}
}
