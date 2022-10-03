package Septempber_fourth;
import java.io.*;
import java.util.*;

public class Main_bj_14719_빗물_박한샘 {

	static int [][]map;
	static int N,M;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int H = 0;
		answer = N*M;
		
		st= new StringTokenizer(br.readLine()," ");
		for(int j=0;j<M;j++) {
			H = Integer.parseInt(st.nextToken());
			for(int i=0;i<H;i++) {
				map[i][j]=1;
				answer--;
			}
		}
		

		
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) {
					if(!calc(i,j)) answer--;
				}
			}
		}
		System.out.println(answer);
		
	}
	static boolean calc(int i,int j) {
		
		//두번 돌리자
		int check = 0;
		for(int d = 0 ; d <=j ; d++) {
			if(map[i][d]==1) {
				check++;
				break;
			}
		}
		for(int d = j ; d < M ; d++) {
			if(map[i][d]==1) {
				check++;
				break;
			}
		}
		
		if(check==2) return true;
		return false;
	}
	
}
