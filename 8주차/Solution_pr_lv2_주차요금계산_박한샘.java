package September_third;
import java.util.*;

public class Solution_pr_lv2_주차요금계산_박한샘 {

	public static void main(String[] args) {
		Solution_주차요금계산 solution = new Solution_주차요금계산();
		int[]fees = {180, 5000, 10, 600};
		String[]records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		for(int i: solution.solution(fees, records)) System.out.println(i+", ");;
		
	}
	
}


class Solution_주차요금계산 {
	
	Map<String,Integer> answerMap = new TreeMap<>();
	Map<String,Integer> nowMap = new HashMap<>();
	
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        
        int BT = fees[0]; //기본시간
        int BP = fees[1]; //기본요금
        int ET = fees[2]; //추가시간
        int EP = fees[3]; //시간당 요금
        
        
        for(int i=0;i<records.length;i++) {
        	String[]tmp = records[i].split(" ");
        	int time = calc(tmp[0]);
        	String num = tmp[1];
        	String action = tmp[2];
        	
        	if(action.equals("IN")) nowMap.put(num, time);
        	else {
        		int resTime = time - nowMap.get(num);
        		answerMap.put(num, answerMap.getOrDefault(num, 0) + resTime);
        		nowMap.remove(num);
        	}
        }
        
        if(!nowMap.isEmpty()) {
        	for(String key : nowMap.keySet()) {
        		int resTime = ((23 * 60) + 59) - nowMap.get(key);
        		answerMap.put(key, answerMap.getOrDefault(key, 0)+resTime);
        	}
        }
        nowMap.clear();
        
        answer = new int[answerMap.size()];
        int idx = 0;
        for(String key : answerMap.keySet()) {
        	
        	int time = answerMap.get(key);
        	int money = BP;
        	if(time<=BT) {
        		answer[idx++] = money;
        	}else {
        		//기본 시간 빼주기
        		time -= BT;
        		//올림해주기
        		int extraT = (int)Math.ceil(1.0*time/ET);
        		answer[idx++] = money + (extraT * EP);
        		
        	}
        	
        	
        }
        
      
        
        
        
        return answer;
    }
    static int calc(String input) {
    	
    	String[]tmp = input.split(":");
    	int res = Integer.parseInt(tmp[0]) * 60;
    	res += Integer.parseInt(tmp[1]);
    	return res;
    }
}