package b14719;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] input = new int[W];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<W; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] map = new int[H][W];
		for(int i = H-1; i>=0; i--) {
			for(int j= 0; j<W;j++) {
				if(input[j]-- > 0) {
					map[i][j] = 1;
				}
			}
		}
		int answer = 0;
		
		for(int i = H-1; i>=0; i--) {
			boolean left = false;
			int count = 0;
			for(int j = 0; j<W;j++) {
				if(left == false) {
					if(map[i][j] == 1) {
						left = true;
					}
				}else {
					if(map[i][j] == 0) {
						count++;
					}else {
						answer += count;
						count = 0;
					}
				}
			}
		}
		System.out.println(answer);
		br.close();
	}

}
