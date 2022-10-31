package October_fourth;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	private static List<Type> list;

	static class Type{
		String Name;
		int CombatPower;
		
		public Type(String name, int combatPower) {
			super();
			Name = name;
			CombatPower = combatPower;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); 
		
		list = new ArrayList<Type>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			String Name = st.nextToken();
			int CombatPower = Integer.parseInt(st.nextToken());
			list.add(new Type(Name, CombatPower));
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int score = Integer.parseInt(br.readLine());
		
			String str = BinarySearch(score);
			sb.append(str).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static String BinarySearch(int score) {
		int L = 0;
		int R = list.size()-1, mid=0;
		
		while(L<=R) {
			mid = (L+R) / 2;
			if(score > list.get(mid).CombatPower) L = mid+1;
			else R = mid - 1;
		}
		
		return list.get(R+1).Name;
	}

}
