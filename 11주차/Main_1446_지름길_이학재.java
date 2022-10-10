package b1446;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int best = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		ArrayList<int[]> shortcuts = new ArrayList<>();
		for(int i = 0; i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			if(len < end-start)	shortcuts.add(new int[] {start,end,len});
		}
		dfs(D,0,0,shortcuts);
		System.out.println(best);
		br.close();
	}
	//순열
	public static void dfs(int D,int now, int length,ArrayList<int[]> shortcuts) {
		if(now > D) {
			return;
		}
		else if(now == D) {
			best = Integer.min(best, length);
			return;
		}
		
		for(int i = 0, size = shortcuts.size(); i<size; i++) {
			int[] cur = shortcuts.get(i);
			//지금길을 탈 수 있을 때
			if(now <= cur[0]) {
				dfs(D,cur[1],length + cur[0] - now + cur[2],shortcuts);
			}
		}
		//아무 길도 안 탈 때
		best = Integer.min(best, length + D - now);
		return;
	}
}
