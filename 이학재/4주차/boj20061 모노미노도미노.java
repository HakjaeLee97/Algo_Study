package b20061;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n,t,y,x;
	static int[][] green=new int[6][4];
	static int[][] blue=new int[4][6];
	static int score;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			t=Integer.parseInt(st.nextToken());
			y=Integer.parseInt(st.nextToken());
			x=Integer.parseInt(st.nextToken());
			
			moveGreen(t,y,x);
			moveBlue(t,y,x);
			emptyGreen();
			emptyBlue();
			lightGreen();
			lightBlue();
		}
		
		System.out.println(score);
		int cnt=0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if(blue[i][j]==1)cnt+=1;
			}
		}
		int cnt2=0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j]==1)cnt+=1;
			}
		}
		System.out.println(cnt+cnt2);
		br.close();
	}
	
	
	private static void lightBlue() {
		int cnt=0;
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 4; i++) {
				if(blue[i][j]!=0) {
					cnt+=1;//이 행에 블록이 있음
					break;
				}
			}
		}
		
		//cnt 개수 만큼 밀기
			for (int k = 5; k >=2; k--) {//행
				for (int i = 0; i < 4; i++) {
					blue[i][k]=blue[i][k-cnt];//오른쪽으로 쭉쭉 밀어
				}
			}

		
		for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 4; i++) {
			blue[i][j]=0;
		}
	}
		
	}
	private static void lightGreen() {
		int cnt=0;
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 4; i++) {
				if(green[j][i]!=0) {
					cnt+=1;//이 행에 블록이 있음
					break;
				}
			}
		}
		
		//cnt 개수 만큼 밀기
			for (int k = 5; k >=2; k--) {//열
				for (int i = 0; i < 4; i++) {
					green[k][i]=green[k-cnt][i];//아래쪽으로 쭉쭉 밀어
				}
			}

		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 4; i++) {
				green[j][i]=0;
			}
		}
		
	}
	private static void emptyBlue() {
		//없애고 옮겨
		while(true) {
			boolean flag=true;
			for (int j = 5; j>=2; j--) {
				int cnt=0;
				for (int i = 0; i < 4; i++) {
					if(blue[i][j]==1) {
						cnt+=1;
					}
				}
				if(cnt==4) {
					score+=1;
					flag=false;
					for (int i = 0; i < 4; i++) {
						blue[i][j]=0; //해당 열 모두 비워버림
					}
					for (int k = j-1; k >=0; k--) {//행
						for (int i = 0; i < 4; i++) {
							blue[i][k+1]=blue[i][k];//오른쪽으로 쭉쭉 밀어
						}
					}
					for (int i = 0; i < 4; i++) {
						blue[i][0]=0;
					}
					break;
				}
			}
			
			if(flag)break;
		}
		
	}
	private static void emptyGreen() {
		while(true) {
			boolean flag=true;
			for (int j = 5; j>=2; j--) {
				int cnt=0;
				for (int i = 0; i < 4; i++) {
					if(green[j][i]==1) {
						cnt+=1;
					}
				}
				if(cnt==4) {
					score+=1;
					flag=false;
					for (int i = 0; i < 4; i++) {
						green[j][i]=0; //해당 열 모두 비워버림
					}
					for (int k = j-1; k >=0; k--) {//행
						for (int i = 0; i < 4; i++) {
							green[k+1][i]=green[k][i];//아래쪽으로 쭉쭉 밀어
						}
					}
					for (int i = 0; i < 4; i++) {
						green[0][i]=0;
					}
					
					break;
				}
			}
			
			if(flag)break;
		}
		
	}
	private static void moveBlue(int type,int y,int x) {
		if(type==1) {
			int row=0;
			for (int j = 0; j <=5; j++) {
				if(blue[y][j]==0) {
					row=j;
					continue;
				}else {
					break;
				}
			}
			blue[y][row]=1;

		}else if(type==2) {
			int row=0;
			for (int j = 1; j<=5; j++) {
				if(blue[y][j]==0 && blue[y][j-1]==0) {
					row=j;
					continue;
				}else {
					break;
				}
			}
			blue[y][row]=1;
			blue[y][row-1]=1;

		}else {
			
			int row=0;
			for (int j = 0; j<=5; j++) {
				if(blue[y][j]==0 && blue[y+1][j]==0) {
					row=j;
					continue;
				}else {
					break;
				}
			}
			blue[y][row]=1;
			blue[y+1][row]=1;
			

		}
			
	}
	private static void moveGreen(int type,int y,int x) {
		if(type==1) {
			int col=0;
			for (int i = 0; i <=5; i++) {
				if(green[i][x]==0) {
					col=i;
					continue;
				}else {
					break;
				}
			}
			green[col][x]=1;

		}else if(type==2) {
			int col=0;
			for (int j = 0; j<=5; j++) {
				if(green[j][x]==0 && green[j][x+1]==0) {
					col=j;
					continue;
				}else {
					break;
				}
			}
			green[col][x]=1;
			green[col][x+1]=1;

		}else {
			
			int col=0;
			for (int j = 1; j<=5; j++) {
				if(green[j][x]==0 && green[j-1][x]==0) {
					col=j;
					continue;
				}else {
					break;
				}
			}
			green[col][x]=1;
			green[col-1][x]=1;

		}
		
	}
}