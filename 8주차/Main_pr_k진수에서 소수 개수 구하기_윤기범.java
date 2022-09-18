import java.util.*;
import java.io.*;

class Solution {
    public static String ans = "";
    public static ArrayList<String> arr = new ArrayList<>();
    public static int answer;
    
    public void calc(int n, int k) {
        if(n == 0)
            return;
        String num = String.valueOf(n % k);
        n = n / k;
        calc(n, k);
        ans += num;
    }
    
    public boolean isPrime(long num) {
        if(num <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
          // x가 해당 수로 나누어떨어진다면
          if (num % i == 0) {
              return false; // 소수가 아님
          }
        }
        return true;
    }
    
    public int solution(int n, int k) {
        calc(n, k);
        System.out.println(ans);
        StringTokenizer st = new StringTokenizer(ans, "0");
        
        while(st.hasMoreTokens()) {
            arr.add(st.nextToken());
        }
        
        for(int i = 0; i < arr.size(); i++) {
            if(isPrime(Long.parseLong(arr.get(i))))
                answer += 1;
        }
        
        return answer;
    }
}
