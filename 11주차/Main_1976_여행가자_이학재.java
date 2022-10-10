package b1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int[] parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			 st = new StringTokenizer(br.readLine()," ");
			 for (int j = 1; j <= N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(cur == 1) {
					union(parent,i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		//여행계획
		boolean result =true;

		int p = find(parent,Integer.parseInt(st.nextToken()));
		for (int i = 1; i < M; i++) {
			int now = Integer.parseInt(st.nextToken());
			if(p != find(parent,now)) {
				result = false;
				break;
			}
		}

		if(result) System.out.println("YES");
		else System.out.println("NO");
	}
	public static void union(int[] parent, int a, int b) {
		int pa = find(parent,a);
		int pb = find(parent,b);
		if(pa < pb) {
			parent[b] = pa;
		}else if(pa>pb) {
			parent[a] = pb;
		}
	}
	public static int find(int[] parent, int a) {
		if(parent[a] == a) {
			return a;
		}else {
			return parent[a] = find(parent,parent[a]);
		}
 		
	}

}
