package September_first;

import java.util.*;
import java.io.*;

public class Main {

	static int[] arr;
	static int N;
	static int S;
	static int answer = Integer.MAX_VALUE;
	
	static void input()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());	
		
		
		
	}
	static void calc() {
		
		int L = 0;
		int R = 0;
		int sum = 0;
		
		while(true) {
			
			if(sum>=S) {
				answer = Math.min(answer, R-L);
				sum -= arr[L];
				L++;
			}else {
				if(R==N) break;
				else {
					sum += arr[R];
					R++;
				}
			}
		}
		
		
	}
	
	static void output() {
		System.out.println(answer==Integer.MAX_VALUE ? 0 : answer);
	}
	
	public static void main(String[] args) throws Exception {
		input();
		calc();
		output();
		
	}
}
