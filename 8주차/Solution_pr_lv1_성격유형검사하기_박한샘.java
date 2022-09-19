package September_third;

import java.util.*;
import java.io.*;
/*
 test_case
 
 survey : "AN", "CF", "MJ", "RT", "NA"
 choice : 5, 3, 2, 7, 5
 result : TCMA
 
  survey : "TR", "RT", "TR"
 choice : 7,1,3
 result : RCJA
 
 * */


public class Solution_pr_lv1_성격유형검사하기_박한샘 {
	public static void main(String[] args) {
		
		Solution_성격유형검사하기 solution = new Solution_성격유형검사하기();
		String []survey = {"AN", "CF", "MJ", "RT", "NA"};
		int [] choices = {5, 3, 2, 7, 5};
		String answer = solution.solution(survey, choices);
		System.out.println(answer);
	}
}

class Solution_성격유형검사하기 {
	
	Map<String, Integer> map = new HashMap<>();
	
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        //RT CF JM AN
        map.put("RT", 0);
        map.put("CF", 0);
        map.put("JM", 0);
        map.put("AN", 0);
        
        
        
        for(int i=0;i<survey.length;i++) {
        	
        	String sur = survey[i];
        	int choice = choices[i];
        	//앞에 나오는 애가 1일때 3점, 2일떄 2점, 3일때 1점.. 4일떄 0점, 5일때 -1점 6일때 -2점 6일때 -3점
        	//예쁜 수식이 없을까?
        	if(map.containsKey(sur)) map.put(sur, map.get(sur)+(4-choice));
        	else {
        		String tmp = ""+sur.charAt(1) + sur.charAt(0);
        		map.put(tmp, map.get(tmp)+(choice-4));
        	}
        	
        }
        
        for(String str : map.keySet()) {
        	if(map.get(str)>=0) {
        		answer += str.charAt(0);
        	}else {
        		answer += str.charAt(1);
        	}
        }
        
        return answer;
    }
}