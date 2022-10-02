import java.io.*;
import java.util.*;

public class Main_17615_볼모으기_서울_20반_이해은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[] arr = br.readLine().toCharArray();
		
		int min = Integer.MAX_VALUE;
		
		boolean redFlag = false;
		boolean blueFlag = false;
		
		int redCnt = 0;
		int blueCnt = 0;
		
		for(int i = 0; i < N; i++) {
			if(arr[i] == 'R') {
				redFlag = true;
			}
			if(arr[i] == 'B') {
				blueFlag = true;
			}
			
			if(redFlag) {
				if(arr[i] == 'B') {
					blueCnt++;
				}
			}
			if(blueFlag) {
				if(arr[i] == 'R') {
					redCnt++;
				}
			}
		}
		
		min = blueCnt < redCnt ? blueCnt : redCnt;
		
		redFlag = false;
		blueFlag = false;
		
		redCnt = 0;
		blueCnt = 0;
		
		for(int i = N - 1; i >= 0; i--) {
			if(arr[i] == 'R') {
				redFlag = true;
			}
			if(arr[i] == 'B') {
				blueFlag = true;
			}
			
			if(redFlag) {
				if(arr[i] == 'B') {
					blueCnt++;
				}
			}
			if(blueFlag) {
				if(arr[i] == 'R') {
					redCnt++;
				}
			}
		}
		
		if(min > (blueCnt < redCnt ? blueCnt : redCnt)) min = (blueCnt < redCnt ? blueCnt : redCnt);
		
		System.out.println(min);
	}

}
