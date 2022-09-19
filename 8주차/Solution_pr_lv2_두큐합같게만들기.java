package September_third;

import java.util.*;
import java.io.*;

/*
 
 3, 2, 7, 2
 4, 6, 5, 1
 2
 
 1, 2, 1, 2
 1, 10 1, 2
 7
 
 1,1 
 1,5
 
 -1
 
 * */

class Solution_두큐합같게만들기 {
	
	Queue<Integer> q1 = new ArrayDeque<>();
	Queue<Integer> q2 = new ArrayDeque<>();
	
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;

        long sum =0;
        long s1=0;
        long s2=0;
        //넣어줍시다 queue에 하나씩
        for(int i=0;i<queue1.length;i++) {
        	
        	q1.offer(queue1[i]);
        	q2.offer(queue2[i]);
        	sum += queue1[i];
        	sum += queue2[i];
        	s1 += queue1[i];
        	s2 += queue2[i];
        }
        
        if(sum==-1) {
        	answer = -1;
        	return answer;
        }
        int p1=0, p2=0, limit=queue1.length*2;
        
        while(p1<=limit && p2<=limit) {

        	if(s1==s2) {
        		return p1+p2;
        		
        	}else if(s1<s2) {
        		s1 += q2.peek();
        		s2 -= q2.peek();
        		p1++;
        		q1.offer(q2.poll());
        	}else {
        		s2 += q1.peek();
        		s1 -= q1.peek();
        		p2++;
        		q2.offer(q1.poll());
        	}
       
        }
        
       return -1;
    }
}

public class Solution_pr_lv2_두큐합같게만들기 {

	public static void main(String[] args) {
		
	
		Solution_두큐합같게만들기 sol = new Solution_두큐합같게만들기();
		int [] queue1 = { 3, 2, 7, 2};
		int [] queue2 = {4,6,5,1};
		
		System.out.println(sol.solution(queue1, queue2));
	
	}

}
