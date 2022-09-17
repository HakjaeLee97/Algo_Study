package a0919;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_pr_lv2_주차요금계산 {

	public static void main(String[] args) {
		
		// [180, 5000, 10, 600]	
		// ["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]	
		// [14600, 34400, 5000]
		
		// [120, 0, 60, 591]	
		// ["16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"]	
		// [0, 591]
		
		// [1, 461, 1, 10]	
		// ["00:00 1234 IN"]	
		// [14841]
		
		for (int i : solution(new int[] {180, 5000, 10, 600}, new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"})) {
			System.out.println(i);
		}
	}

	static int[] solution(int[] fees, String[] records) {
		
		ArrayList<int[]> in = new ArrayList<>();
		ArrayList<int[]> out = new ArrayList<>();
		for (int i = 0; i < records.length; i++) {
			StringTokenizer st = new StringTokenizer(records[i], " ");
			String[] hm = st.nextToken().split(":");
			int h = Integer.parseInt(hm[0])*60;
			int m = Integer.parseInt(hm[1]);
			int time = h+m;
			int carNum = Integer.parseInt(st.nextToken());
			String inOut = st.nextToken();
			if(inOut.equals("IN")) {
				in.add(new int[] {time, carNum});
			} else {
				out.add(new int[] {time, carNum});
			}
		}
		
		Collections.sort(in, (c1, c2) -> c1[1] - c2[1]);
		Collections.sort(out, (c1, c2) -> Integer.compare(c1[1], c2[1]));
//		for (int[] i : in) {
//			System.out.println(i[1] + " " + i[0]);
//		}
//		for (int[] o : out) {
//			System.out.println(o[1] + " " + o[0]);
//		}
		
		int baseTime = fees[0]; 
		int baseFee = fees[1]; 
		int unitTime = fees[2]; 
		int unitFee = fees[3]; 
		
		ArrayList<Integer> feeList = new ArrayList<>();
		while(!in.isEmpty()) {
			int fee = baseFee;
			int num = in.get(0)[1];
			int time = 0;
			while(!in.isEmpty() && num == in.get(0)[1]) {
				if(out.isEmpty() || (!out.isEmpty() && in.get(0)[1] != out.get(0)[1])) {
					time += 1439 - in.remove(0)[0];
				} else if(in.get(0)[1] == out.get(0)[1]) {
					time += out.remove(0)[0] - in.remove(0)[0];
				} 
			}
			if(time - baseTime > 0) {
				fee += ((time - baseTime -1) / unitTime + 1) * unitFee;
			}
			feeList.add(fee);
		}
		
		int[] answer = new int[feeList.size()];
		for (int i = 0; i < feeList.size(); i++) {
			answer[i] = feeList.get(i);
		}
		
        return answer;
    }
}
