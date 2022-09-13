
import java.util.*;
import java.io.*;


public class Main {
	
	static int N,d,k,c; //가짓수, 초밥개수, 연소 접시수, 쿠폰 번호
	static int []arr;
	static int answer = 0;
	
	static void input() throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
	}
	static void output() {
		System.out.println(answer);
	}
	static void calc() {
		
		
		
		
	}
	public static void main(String[] args) throws Exception {
		input();
		calc();
		output();
	}
}
