package October_third;

import java.util.*;
import java.io.*;

public class bh_20006_랭킹전대기열_Silver_2 {
	
	static class Player implements Comparable<Player>{

		int level;
		String name;

		public Player(int level, String name) {
			super();
			this.level = level;
			this.name = name;
		}


		@Override
		public int compareTo(Player o) {
			return name.compareTo(o.name);
		}
			
		
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int p = Integer.parseInt(st.nextToken()); //수
		int m = Integer.parseInt(st.nextToken()); //정원
		
		List<List<Player>> list = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		
		boolean flag;
		
		int idx = 0;
		
		for(int c=0;c<p;c++) {
			
			flag = false;
			
			
			st = new StringTokenizer(br.readLine()," ");
			
			int level = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			for(int i=0;i<list.size();i++) {
				
				int limitL = list.get(i).get(0).level;
				
				if(Math.abs(limitL-level)<=10 && list.get(i).size()<m) {
					list.get(i).add(new Player(level,name));
					flag = true;
					break;
				}	
			}
			if(!flag) {
				list.add(new ArrayList<Player>());
				list.get(idx++).add(new Player(level,name));
			}

		}
		
		for(int i=0;i<list.size();i++) {
			
			Collections.sort(list.get(i));
			
			if(list.get(i).size()==m) sb.append("Started!");
			else sb.append("Waiting!");
			
			sb.append("\n");
			
			for(int j=0;j<list.get(i).size();j++) sb.append(list.get(i).get(j).level)
													.append(" ")
													.append(list.get(i).get(j).name)
													.append("\n");
			
		}
		
		System.out.println(sb.toString());
		
	}

}
