import java.util.*;
import java.io.*;

class Dragon {
	public int x;
	public int y;
	public int d;
	public int g;
	
	public Dragon(int x, int y, int d, int g) {
		this.x = x;
		this.y = y;
		this.d = d;
		this.g =g;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
}

public class Main {
	public static int N;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int[][] graph = new int[101][101];
	public static boolean[][] check = new boolean[101][101];
	
	public static ArrayList<Integer> moves = new ArrayList<>();
	public static ArrayList<Dragon> ans = new ArrayList<>();
	
	public static void dragonCurve(int x, int y, int d, int g) {
		for(int i = 0; i < g; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int j = 0; j < moves.size(); j++) {
				temp.add(moves.get(j));
			}
			
			for(int j = temp.size()-1; j >= 0; j--) {
				d = temp.get(j);
				d = (d + 1) % 4;
				x = x + dx[d];
				y = y + dy[d];
				moves.add(d);
				ans.add(new Dragon(x, y, d, g));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			ans.add(new Dragon(x, y, d, g));
			x = x + dx[d];
			y = y + dy[d];
			ans.add(new Dragon(x, y, d, g));
			moves.add(d);
			dragonCurve(x, y, d, g);
			moves.clear();
		}
		
//		for(int i = 0; i <ans.size(); i++) {
//			System.out.println(ans.get(i));
//		}
		for(int i = 0; i <ans.size(); i++) {
			if(check[ans.get(i).x][ans.get(i).y]) continue;
				check[ans.get(i).x][ans.get(i).y] = true;
		}
		
		int cnt = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(check[i][j] && check[i][j+1] && check[i+1][j] && check[i+1][j+1])
					cnt++;
			}
		}
		System.out.println(cnt);
	}
}
