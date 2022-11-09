package November_first;

import java.io.*;
import java.util.*;

public class Main_2668_숫자고르기_Gold_5 {
	
	
	static int N, num, arr[];
	
	static List<Integer> list = new ArrayList<>();
	
	static boolean[] visited;
	
	static void dfs(int i) {
		
		if(arr[i]==num) list.add(num);
		
		if(!visited[arr[i]]) {
			visited[arr[i]] = true;
			dfs(arr[i]);
			visited[arr[i]] = false;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		visited = new boolean[N];
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(br.readLine())-1;
		
		for(int i=0;i<N;i++) {
			visited[i] = true;
			num = i;
			dfs(i);
			visited[i] =  false;
		}
		Collections.sort(list);
		System.out.println(list.size());
		for(int i : list) System.out.println(i+1);
	}
	
	

}
