import java.util.*;
import java.io.*;

class Solution {
	ArrayList<ArrayList<Integer>> tree =  new ArrayList<ArrayList<Integer>>();
	public int ans = Integer.MIN_VALUE;
	public boolean[] visited;
	public int[] note;
	
	public void dfs(int node, int numOfLamb, int numOfWolf, boolean[] copyVisited) {
		if(note[node] == 0) {
			numOfLamb += 1;
		} else {
			numOfWolf += 1;
		}
		
		if(numOfLamb <= numOfWolf) {
			return;
		}
		ans = Math.max(ans, numOfLamb);
		
		boolean[] list = copyVisited.clone();
		list[node] = true;
		
		for(int i = 0; i < list.length; i++) {
			if(list[i] == true) {
				
				for(int j = 0; j < tree.get(i).size(); j++) {
					int y = tree.get(i).get(j);
					if(list[y] == false) {
						dfs(y, numOfLamb, numOfWolf, list);
					}
				}
			}
		}
	}
	
    public int solution(int[] info, int[][] edges) {
    	int n = info.length;
    	note = new int[n];
    	for(int i = 0; i < n; i++) {
    		note[i] = info[i];
    	}
    	for(int i = 0; i < n + 1; i++) {
    		tree.add(new ArrayList<>());
    	}
    	
    	for(int i = 0; i < edges.length; i++) {
    		int a = edges[i][0];
    		int b = edges[i][1];
    		tree.get(a).add(b);
    	}
    	visited = new boolean[n];
    	dfs(0, 0, 0, visited);
    	return ans;
    }
}
