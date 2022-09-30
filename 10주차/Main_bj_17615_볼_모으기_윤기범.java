import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static String ball;
	public static int rightBEach = 0;
	public static int rightREach = 0;
	public static int leftBEach = 0;
	public static int leftREach = 0;
	
	public static void solveRight() {
		String temp = ball + "!";
		char[] first = temp.toCharArray();
		int tempNum = 1;
		
		for(int i = 0; i < first.length - 1; i++) {
			if(first[i] == 'B') {
				if(first[i + 1] != '!' && first[i] != first[i + 1]) { // 파란색 덩어리
					rightBEach += tempNum;
					tempNum = 1;
				}
				else if (first[i] == first[i + 1]) {
					tempNum += 1;
				}
			}
			else {
				if(first[i + 1] != '!' && first[i] != first[i + 1]) { // 파란색 덩어리
					rightREach += tempNum;
					tempNum = 1;
				}
				else if (first[i] == first[i + 1]) {
					tempNum += 1;
				}
			}
		}
	}
	
	public static void solveLeft() {
		String temp = "!" + ball;
		char[] first = temp.toCharArray();
		int tempNum = 1;
		
		for(int i = first.length - 1; i >= 1; i--) {
			if(first[i] == 'B') {
				if(first[i - 1] != '!' && first[i] != first[i - 1]) { // 파란색 덩어리
					leftBEach += tempNum;
					tempNum = 1;
				}
				else if (first[i] == first[i - 1]) {
					tempNum += 1;
				}
			}
			else {
				if(first[i - 1] != '!' && first[i] != first[i - 1]) { // 파란색 덩어리
					leftREach += tempNum;
					tempNum = 1;
				}
				else if (first[i] == first[i - 1]) {
					tempNum += 1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		N = Integer.parseInt(br.readLine());
		ball = br.readLine();
		
		solveRight();
		solveLeft();
		
		//System.out.println("rightBEach : " + rightBEach + " rightREach : " + rightREach);
		//System.out.println("leftBEach : " + leftBEach + " leftREach : " + leftREach);
		
		int arr[] = {rightBEach, rightREach, leftBEach, leftREach};
		Arrays.sort(arr);
		System.out.println(arr[0]);
	}
}
