class Solution {
    public String solution(String[] survey, int[] choices) {
        int[] alpha = new int[26];
        int[] score = {3, 2, 1, 0, 1, 2, 3};
        
        for(int i = 0; i < survey.length; i++) {
            char[] arr = survey[i].toCharArray();
            if (1 <= choices[i] && choices[i] <= 3) { // 비동의
                alpha[arr[0] - 65] += score[choices[i] - 1];
            }
            else if (5 <= choices[i] && choices[i] <= 7) { // 동의
            	alpha[arr[1] -65] += score[choices[i] - 1];
            }
            else { // 모르겠음 경우
                continue;
            }
        }
      
        int R = alpha[17];
        int T = alpha[19];
        int C = alpha[2];
        int F = alpha[5];
        int J = alpha[9];
        int M = alpha[12];
        int A = alpha[0];
        int N = alpha[13];
        
        String answer = "";
        
        if(R > T)
        	answer += 'R';
        else if(R < T)
        	answer += 'T';
        else
        	answer += 'R';
        
        if(C > F)
        	answer += 'C';
        else if(C < F)
        	answer += 'F';
        else
        	answer += 'C';
        
        if(J > M)
        	answer += 'J';
        else if(J < M)
        	answer += 'M';
        else
        	answer += 'J';
        
        if(A > N)
        	answer += 'A';
        else if(A < N)
        	answer += 'N';
        else
        	answer += 'A';
        
        return answer;
    }
}