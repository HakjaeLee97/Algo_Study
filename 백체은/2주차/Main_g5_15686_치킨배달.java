package a0808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_g5_15686_치킨배달 {

	static int N, M;
	static int[][] map;
	static boolean[] visit;
	static int result = Integer.MAX_VALUE;
	static ArrayList<Location> house= new ArrayList<>();
	static ArrayList<Location> chicken = new ArrayList<>();
	static ArrayList<Location> chickenList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == 1) {
					house.add(new Location(r, c));
				} else if (map[r][c] == 2) {
					chicken.add(new Location(r, c));
				}
			}
		}
		
		br.close();
		
		visit = new boolean[chicken.size()];
		dfs(0, 0);
		System.out.println(result);
	}
	
	static void dfs(int start, int cnt) {

		if(cnt == M) {
			int sum = 0;
			for (int i = 0; i < house.size(); i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < chickenList.size(); j++) {
					int distance = Math.abs(house.get(i).x - chickenList.get(j).x) + Math.abs(house.get(i).y - chickenList.get(j).y);

					if (min > distance) min = distance;
				}
				sum += min;
			}
			
			if(sum < result) result = sum;
		} else {
			for (int i = start; i < chicken.size(); i++) {
				if(!visit[i]) {
					visit[i] = true;
					chickenList.add(chicken.get(i));
					dfs(i + 1, cnt + 1);
					chickenList.remove(chickenList.size() - 1);
					visit[i] = false;
				}
			}
		}
	}

	static class Location {
		int x, y;
		
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
