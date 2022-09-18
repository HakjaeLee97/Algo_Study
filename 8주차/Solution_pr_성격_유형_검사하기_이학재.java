package programmers;

public class 성격_유형_검사하기 {

	public static void main(String[] args) {

	}
    public String solution(String[] survey, int[] choices) {
    	String answer = "";
    	int[] result = new int[4];
    	for(int i = 0, size = survey.length; i<size; i++) {
    		switch(survey[i].charAt(0)) {
    		case 'R':
    			result[0] -= choices[i]-4;
    			break;
    		case 'T':
    			result[0] += choices[i]-4;
    			break;
    		case 'C':
    			result[1] -= choices[i]-4;
    			break;
    		case 'F':
    			result[1] += choices[i]-4;
    			break;
    		case 'J':
    			result[2] -= choices[i]-4;
    			break;
    		case 'M':
    			result[2] += choices[i]-4;
    			break;
    		case 'A':
    			result[3] -= choices[i]-4;
    			break;
    		case 'N':
    			result[3] += choices[i]-4;
    			break;
    		}
    	}
    	if(result[0] >= 0) answer+="R";
    	else answer+="T";
    	if(result[1] >= 0) answer+="C";
    	else answer+="F";
    	if(result[2] >= 0) answer+="J";
    	else answer+="M";
    	if(result[3] >= 0) answer+="A";
    	else answer+="N";
    	
    	
        return answer;
    }
}


/*  앞의 지표가 양수, 뒤의 지표가 음수로 처리
1번 지표	라이언형(R), 튜브형(T)
2번 지표	콘형(C), 프로도형(F)
3번 지표	제이지형(J), 무지형(M)
4번 지표	어피치형(A), 네오형(N)
*/