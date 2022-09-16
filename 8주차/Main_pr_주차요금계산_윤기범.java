import java.util.*;
import java.io.*;

class Solution {
	static class Info implements Comparable<Info>{
		String num;
		int info;
		
		public Info(String num, int info) {
			this.num = num;
			this.info = info;
		}
		
		@Override
		public int compareTo(Info o) {
			return Integer.parseInt(this.num) - Integer.parseInt(o.num);
		}

		@Override
		public String toString() {
			return "Info [num=" + num + ", info=" + info + "]";
		}
	}
	
	public int[] solution(int[] fees, String[] records) {
		StringTokenizer st;
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<Info> arr = new ArrayList<>();
		
		for(int i = records.length - 1; i >= 0; i--) {
			st = new StringTokenizer(records[i], " ");
			String tInfo = st.nextToken();
			String number = st.nextToken();
			String info = st.nextToken();
			
			if(!map.containsKey(number)) { // 처음 나온 경우
				int x = i;
				if(info.equals("IN")) { // in인 경우
					st = new StringTokenizer(tInfo, ":");
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int c = (23*60 + 59) - (a*60 + b);
					map.put(number, c);
					x = i -1;
				}
				
				int cnt = 0;
				int t2 = 0;
				
				for(int j = x; j >= 0; j--) {
					st = new StringTokenizer(records[j], " ");
					String tInfo2 = st.nextToken();
					String number2 = st.nextToken();
					String info2 = st.nextToken();
					st = new StringTokenizer(tInfo2, ":");
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					
					if(number.equals(number2)) {
						if(cnt == 0) { // out 인 경우
							cnt += 1;
							t2 = (a*60)+b;
						}
						else if(cnt == 1) { // in인 경우
							cnt = 0;
							int temp = t2 - ((a*60) + b);
							if(!map.containsKey(number)) {
								map.put(number, temp);
							}
							else {									
								map.put(number, map.get(number) + temp);
							}
						}
					}
				}
			} 
		}
		
		
		for ( String key : map.keySet() ) {
			int num = map.get(key) - fees[0];
			int num2 = num % fees[2];
			int num3 = (int)(num / fees[2]);
			int ans = 0;
			
			if(num <= 0) {
				ans = fees[1];
			}
			else {
				if(num2 != 0) {
					ans = fees[1] + ((num3 + 1) * fees[3]);
				}
				else {
					ans = fees[1] + (num3 * fees[3]);
				}
			}
			arr.add(new Info(key, ans));
		}
		
		Collections.sort(arr);
		
		int[] ans = new int[arr.size()];
		for(int i = 0; i < arr.size(); i++) {
			ans[i] = arr.get(i).info;
		}
		return ans;
    }
}
