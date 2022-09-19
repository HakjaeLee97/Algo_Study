package September_first;

import java.util.*;
import java.io.*;

public class Main {
	
	static int subin; //+1 or -1(이건 1초) or (2*subin 이건 0초)
	static int bro;
	static boolean[]visited;
	static int[] answerArr;
	
	static void input() throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		subin = Integer.parseInt(st.nextToken());
		bro = Integer.parseInt(st.nextToken());
		answerArr = new int[100001];
	}
	
	static void calc() {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {subin,1});//수빈이 위치, 시간 초기화
		
		answerArr[subin] = 1;
		
		while(!q.isEmpty()) {
			
			int[]c = q.poll();
			
			int s = c[0];
			int time = c[1];
			
			
			
			if(s*2<=100000) {
				if(answerArr[s*2]==0 || answerArr[s*2]>time) {
					answerArr[s*2] = time;
					q.offer(new int[] {s*2,time});
				}
			}
			
			if(s-1>-1) {
				if(answerArr[s-1]==0 || answerArr[s-1]>time+1) {
					answerArr[s-1] = time + 1;
					q.offer(new int[] {s-1,time+1});
				}
			}

			if(s+1<=100000) {
				if(answerArr[s+1]==0 || answerArr[s+1] > time+1) {
					answerArr[s+1] = time + 1;
					q.offer(new int[] {s+1,time+1});
					
				}
			}
			
			
		}
		
		
	}
	
	static void output() {
		System.out.println(answerArr[bro]-1);
	}
	
	public static void main(String[] args) throws Exception {
		
		input();
		calc();
		output();
		
	}

}
