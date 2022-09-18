package programmers;

public class K진수에서_소수_개수_구하기 {

	public static void main(String[] args) {
		int n = 211;
		int k = 10;
		System.out.println(solution(n,k));
	}
	
	public static int solution(int n, int k) {

		int answer = 0;
		
		// k진수로 변환
		String kbit = Integer.toString(n,k);
//        String kbit = "";
//        while(n > 0) {
//            kbit = n % k + kbit;
//            n /= k;
//        }

		//n과 k의 조건상 int로 담을 수 없음. string으로 풀기

		//맨 왼쪽부터 0나올떄까지 체크
		
		long tmp =0;
		for(int i = 0, size= kbit.length(); i<= size; i++ ) {
			if( i == size) { //마지막 오른쪽 체크 
				if(tmp != 0 && tmp!= 1&& isprime(tmp)) {
					answer++;
				}
				break;
			}
			char now = kbit.charAt(i);
			if(now == '0') {
				if(tmp != 0 && tmp!= 1&& isprime(tmp)) {
					answer++;
				}
				tmp = 0;
			}else {
				tmp *= 10;
				tmp += kbit.charAt(i)-'0';
			}
		}

		
		return answer;
	}
	public static boolean isprime(long num) {
		for(int i = 2, to = (int)Math.sqrt(num)+1; i<to; i++) {
			if(num%i == 0) {
				return false;
			}
		}
		return true;
	}

}
