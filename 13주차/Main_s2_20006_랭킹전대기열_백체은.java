package a10월4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_s2_20006_랭킹전대기열 {
	
	static class Player implements Comparable<Player> {
		int l;
		String n;

		public Player(int l, String n) {
			this.l = l;
			this.n = n;
		}

		@Override
		public int compareTo(Player o) {
			return n.compareTo(o.n);
		}
		
	}
	
	static int p, m;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Player>> room = new ArrayList<>();
		
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			String n = st.nextToken();
			boolean flag = false;
			for (int j = 0; j < room.size(); j++) {
				Player first = room.get(j).get(0);
				if(room.get(j).size() < m && first.l-10 <= l && l <= first.l+10) {
					room.get(j).add(new Player(l, n));
					flag = true;
					break;
				}
			}
			if(!flag) {
				room.add(new ArrayList<Player>());
				room.get(room.size()-1).add(new Player(l, n));
			}
		}
		br.close();
		
		for (int i = 0; i < room.size(); i++) {
			Collections.sort(room.get(i), (o1, o2) -> o1.compareTo(o2));
			ArrayList<Player> now = room.get(i);
			if(now.size() == m) {
				sb.append("Started!").append("\n");
			} else {
				sb.append("Waiting!").append("\n");
			}
			for (int j = 0; j < now.size(); j++) {
				sb.append(now.get(j).l).append(" ").append(now.get(j).n).append("\n");
			}
		}
		
		System.out.print(sb);
	}
}
