import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int[] arr;
	public static int leftIndex, rightIndex;
	public static int sum = Integer.MAX_VALUE;
	public static int ansA = 0, ansB = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		leftIndex = 0;
		rightIndex = N - 1;
		
		// 반복문 조건
		while(leftIndex < rightIndex) {
			int temp = arr[leftIndex] + arr[rightIndex];
			
			if(Math.abs(sum) > Math.abs(temp)) {
				ansA = leftIndex;
				ansB = rightIndex;
				sum = temp;
			}
			
			if(temp >= 0)
				rightIndex -= 1;
			else
				leftIndex += 1;
		}
		
		System.out.println(arr[ansA] + " " + arr[ansB]);
	}
}
