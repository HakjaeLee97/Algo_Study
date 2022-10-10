package October_first;

import java.util.*;
import java.io.*;

public class Main {
	
	static int[] parents;
	static int N,M;
	static String answer = "YES";
	
	static void make() {
		for(int i=1;i<N+1;i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		
		if(parents[a]==a) return a;
		
		return parents[a] = find(parents[a]);
		
	}
	
	static void union(int a,int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot!=bRoot) {
			if(aRoot < bRoot) {
				parents[bRoot] = aRoot;
			}else {
				parents[aRoot] = bRoot;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		
	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	
	N = Integer.parseInt(br.readLine());	
	M = Integer.parseInt(br.readLine());
	StringTokenizer st;
	parents = new int[N+1];
	make();
	for(int i=1;i<N+1;i++) {
		st = new StringTokenizer(br.readLine()," ");
			for(int j=1;j<N+1;j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				if(tmp==1) {
					union(i,j);
				}
			}
		}
	
	st = new StringTokenizer(br.readLine()," ");

	int start = find(Integer.parseInt(st.nextToken()));
	
	for(int i=1;i<M;i++) {
		
		int now = Integer.parseInt(st.nextToken());
		
		
		if(start != find(now)) {
			answer = "NO";
			break;
		}
	}
	
	System.out.println(answer);
	
	}
	
	
	
}
