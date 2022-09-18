import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
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