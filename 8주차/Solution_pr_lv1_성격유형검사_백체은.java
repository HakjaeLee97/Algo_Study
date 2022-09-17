package a0919;

import java.util.HashMap;

public class Solution_pr_lv1_성격유형검사 {

	public static void main(String[] args) throws Exception {

		System.out.println(solution(new String[] {"AN", "CF", "MJ", "RT", "NA"}, new int[] {5, 3, 2, 7, 5}));
		System.out.println(solution(new String[] {"TR", "RT", "TR"}, new int[] {7, 1, 3}));
	}

	static String solution(String[] survey, int[] choices) {
		
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('R', 0);
		map.put('T', 0);
		map.put('C', 0);
		map.put('F', 0);
		map.put('J', 0);
		map.put('M', 0);
		map.put('A', 0);
		map.put('N', 0);
		
		for (int i = 0; i < survey.length; i++) {
			if(choices[i] == 4) continue;
			
			if(choices[i] == 1) {
				map.replace(survey[i].charAt(0), map.get(survey[i].charAt(0)) + 3);
			} else if (choices[i] == 2) {
				map.replace(survey[i].charAt(0), map.get(survey[i].charAt(0)) + 2);
			} else if (choices[i] == 3) {
				map.replace(survey[i].charAt(0), map.get(survey[i].charAt(0)) + 1);
			} else if (choices[i] == 5) {
				map.replace(survey[i].charAt(1), map.get(survey[i].charAt(1)) + 1);
			} else if (choices[i] == 6) {
				map.replace(survey[i].charAt(1), map.get(survey[i].charAt(1)) + 2);
			} else if (choices[i] == 7) {
				map.replace(survey[i].charAt(1), map.get(survey[i].charAt(1)) + 3);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(map.get('R') >= map.get('T')) {
			sb.append("R");
		} else {
			sb.append("T");
		}
		if(map.get('C') >= map.get('F')) {
			sb.append("C");
		} else {
			sb.append("F");
		}
		if(map.get('J') >= map.get('M')) {
			sb.append("J");
		} else {
			sb.append("M");
		}
		if(map.get('A') >= map.get('N')) {
			sb.append("A");
		} else {
			sb.append("N");
		}
		
		return sb.toString();
	}
}
