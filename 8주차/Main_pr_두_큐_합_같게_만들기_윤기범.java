import java.util.*;
import java.io.*;

class Solution {
    Queue<Integer> que1 = new LinkedList<>();
    Queue<Integer> que2 = new LinkedList<>();
    
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int maxLen = queue1.length + queue2.length;
        long q1 = 0, q2 = 0;
        for(int i = 0; i < queue1.length; i++) {
            q1 += queue1[i];
            que1.add(queue1[i]);
        }
        for(int i = 0; i < queue2.length; i++) {
            q2 += queue2[i];
            que2.add(queue2[i]);
        }
        
        while(true) {
            if(answer > 300000) {
                answer = -1;
                break;
            }
            if(q1 == q2)
                break;
            if (q1 > q2) {
                int a = que1.poll();
                que2.offer(a);
                q1 -= a;
                q2 += a;
            }
            else {
            	int a = que2.poll();
            	que1.offer(a);
            	q1 += a;
            	q2 -= a;
            }
            answer += 1;
        }
        return answer;
    }
}
