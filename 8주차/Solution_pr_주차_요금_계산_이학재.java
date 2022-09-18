package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 주차_요금_계산 {

	public static void main(String[] args) {
		int[] fees = new int[] {180, 5000, 10, 600};
		String[] records = new String[] 
				{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", 
						"07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN",
						"23:00 5961 OUT"};
		System.out.println(Arrays.toString(solution(fees,records)));
	}

    public static int[] solution(int[] fees, String[] records) {
    	int[] answer;
    	
    	StringTokenizer st;
    	//입차,출차내역 관리 해시맵
    	HashMap<String, String> hm = new HashMap<>();
    	
    	//차량번호 별 누적 주차시간 관리 해시맵
    	HashMap<String, Integer> result_time = new HashMap<>();
    	
    	for(String s : records) {
    		st = new StringTokenizer(s," ");
    		String time = st.nextToken(); 
    		String carnum = st.nextToken();
    		//이미 입차되었을 때
    		if(hm.containsKey(carnum)) {
    			String intime = hm.get(carnum);
    			int hour = Integer.parseInt(time.substring(0,2)) - Integer.parseInt(intime.substring(0, 2));
    			int min = Integer.parseInt(time.substring(3,5)) - Integer.parseInt(intime.substring(3, 5));
    			min += hour * 60;
    			hm.remove(carnum);
    			//이미 들어온 기록이 있을 경우
    			if(result_time.containsKey(carnum)) {
    				result_time.put(carnum, result_time.get(carnum) + min);
    			}else {
    				result_time.put(carnum,  min);
    			}
    			
    		}else { //입차 되었을 때 
    			hm.put(carnum,time);
    		}
    	}
    	for(String key: hm.keySet()) {
 			String intime = hm.get(key);
			int hour = 23 - Integer.parseInt(intime.substring(0, 2));
			int min = 59 - Integer.parseInt(intime.substring(3, 5));
			min += hour * 60 ;
			if(result_time.containsKey(key)) {
				result_time.put(key, result_time.get(key) + min);
			}else {
				result_time.put(key,  min);
			}
    	}
    	answer = new int[result_time.size()];
    	Object[] mapkey = result_time.keySet().toArray();
    	Arrays.sort(mapkey);

    	int idx = 0;
    	for(Object key: mapkey) {
    		int min = result_time.get(key) - fees[0];
			int nowfee = fees[1];// 기본요금
			if(min > 0) { //추가 요금이 존재할 경우
				//Math.ceil을 써도 됨
				if(min%fees[2] != 0) {
					nowfee += ( min / fees[2] + 1) * fees[3];
				}else {
					nowfee += ( min / fees[2] ) * fees[3];
				}
			}
			answer[idx++] = nowfee;

    	}

        return answer;
    }
	
}
