package b1253;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i =0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int count = 0; //좋은 수의 개수
		for(int i = 0; i<N;i++) {
			int pos1 = 0;
			int pos2 = N-1;
			while(pos1 != pos2) {
				if(i == pos1) {
					pos1++;
					continue;
				}else if(i == pos2) {
					pos2--;
					continue;
				}
				if(arr[i] == arr[pos1] + arr[pos2]) {
					count++;
					break;
				}
				else if(arr[i] > arr[pos1] + arr[pos2]) {
					pos1++;
				}else {
					pos2--;
				}
			}
		}
		System.out.println(count);
		br.close();
		
	}

}
