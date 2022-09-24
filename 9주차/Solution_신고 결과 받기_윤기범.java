import java.util.*;
import java.io.*;

import java.io.*;
import java.util.*;

class Solution {
    HashMap<String, HashSet<String>> reportMap = new HashMap<>();
    HashMap<String, Integer> cnt = new HashMap<>();
    StringTokenizer st;
    
    public int[] solution(String[] id_list, String[] report, int k) {
    	int[] answer = new int[id_list.length];
    	
    	// reportMap 작성
    	for(int i = 0; i < id_list.length; i++) {
    		reportMap.put(id_list[i], new HashSet<>());
    		cnt.put(id_list[i], 0);
    	}
    	
    	// 신고 정보 입력
    	for(int i = 0; i < report.length; i++) {
    		st = new StringTokenizer(report[i], " ");
    		String reporter = st.nextToken();
    		String reported = st.nextToken();
    		reportMap.get(reporter).add(reported);
    	}
 
    	
    	for(String s: reportMap.keySet()) {
    		HashSet<String> check = new HashSet<>();
    		check = reportMap.get(s);
    		for(String a : check) {
    			cnt.put(a, cnt.get(a) + 1);
    		}
    	}
    	
    	for(int i = 0; i < id_list.length; i++) {
    		HashSet<String> check = new HashSet<>();
    		check = reportMap.get(id_list[i]);
    		int num = 0;
    		for(String s: check) {
    			if(cnt.get(s) >= k) {
    				num += 1;
    			}
    		}
    		answer[i] = num;
    	}
    	return answer;
    }
}
