package September_third;

import java.util.*;

public class Solution_pr_lv2_k진수에서소수구하기_박한샘 {

	
	public static void main(String[] args) {
		
		Solution_k진수에서소수구하기 solution = new Solution_k진수에서소수구하기();
		
		System.out.println(solution.solution(437674, 3));
		
		
	}
	
	
	
}

class Solution_k진수에서소수구하기{
	public int solution(int n,int k) {
		int answer = 0;
		String str = "";
		
		while(n>0) {
			str = (n%k) + str;
			n = n/k;
		}
		
		List<Long> list = new ArrayList<>();
		
		String tmp = "";
		
		for(int i=0;i<str.length();i++) {
			
			if(str.charAt(i)=='0') {
				if(tmp=="") continue;
				list.add(Long.parseLong(tmp));
				tmp="";
			}else tmp += str.charAt(i);	
		}
		
		if(!tmp.isEmpty()) list.add(Long.parseLong(tmp));
		
		for(long i : list) {
			
			if(check(i)) answer++;
		}
		
		return answer;
	}
	
	static boolean check(long num) {
		
		if(num==1) return false;
		
		for(int i=2;i<=Math.sqrt(num);i++) {
			if(num%i==0) return false;
		}
		
		return true;
	}
}
