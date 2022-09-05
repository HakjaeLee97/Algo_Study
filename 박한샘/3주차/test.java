import java.util.*;
import java.io.*;
/*
 초록색 6 by 4
 파란색 4 by 6
 
 초록색 한 j가 다 채워지면 그 칸 없애고 +1
 파란색 한 i가 다 채워지면 그 칸 없애고 +1

초록색 0이나 1값에 무슨 값이 들어오면 -> 그만큼 아래 행 제거
파란색 0이나 1값에 무슨 값이 들어오면 -> 그만큼 오른쪽 열 제거
 
t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우
t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우 (ij로 바꾸면, i+1 , j)
t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우 (ij로 바꾸면, i , j+1)
 
  
 */



public class Main {
   
   static int[][]green;
   static int[][]blue;
   static int[][]oper;
   static int t; // 횟수
   static int answer;
   static int sum; //남은 블록
   static int garo = 4;
   static int sero = 6;
   
   static void input() throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      t = Integer.parseInt(br.readLine());
      oper = new int[t][3];
      StringTokenizer st;
         
      for(int i=0;i<t;i++) {
         st = new StringTokenizer(br.readLine()," ");
         oper[i][0] = Integer.parseInt(st.nextToken()); //크기
         oper[i][2] = Integer.parseInt(st.nextToken()); //x y 값을 i j값으로 바꿀거임
         oper[i][1] = Integer.parseInt(st.nextToken());
         
      }
      green = new int[6][4];
      blue = new int[4][6];
   }

   static void cal() {
      
      for(int tc=0;tc<t;tc++) {
         
         int bSize = oper[tc][0];
         int iLoc = oper[tc][2];
         int jLoc = oper[tc][1];

         
         
         //쌓아주기 (어차피 처음에 쌓아주기가 먼저이기 때문에 -값을 줘도 에러 안날듯=====
         
         if(bSize==1) {
            //green
            
            for(int i=0;i<sero;i++) {
               //블록이 있거나 땅에 닿으면,
               if(green[i][jLoc]!=0) {
                  green[i-1][jLoc] = 1;
                  break;
               }else if(i==sero-1) {
                  green[i][jLoc]= 1;
                  break;
               }
            }
            //blue
            for(int j=0;j<sero;j++) {
               if(blue[iLoc][j]!=0) {
                  blue[iLoc][j-1] = 1;
                  break;
               }else if(j==sero-1) {
                  blue[iLoc][j]= 1;
               }
            }
            
         }else if(bSize==2) {//j가 긴거
            
            //green
            for(int i=0;i<sero;i++) {
               //걸치는거
               if(green[i][jLoc]!=0 || green[i][jLoc+1] !=0) {
                  green[i-1][jLoc] = 1;
                  green[i-1][jLoc+1] = 1;
                  break;
               
               }else if(i==sero-1) {
                  green[i][jLoc] = 1;
                  green[i][jLoc+1] = 1;
                  break;
               }
            }
            //blue 
            for(int j=0;j<sero;j++) {
               //걸쳐지지 않음
               if(blue[iLoc][j]!=0) {
                  blue[iLoc][j-1] = 1;
                  blue[iLoc][j-2] = 1;
                  break;
               }else if( j==sero-1) {
               
                  blue[iLoc][j] = 1;
                  blue[iLoc][j-1] = 1;
                  break;
               }
            }
            
            
         }else { //i가 긴거
            
            //green
            for(int i=0;i<sero;i++) {
               //걸쳐지지 않음
               if(green[i][jLoc]!=0) {
                  green[i-1][jLoc] = 1;
                  green[i-2][jLoc] = 1;
                  break;
               }else if(i==sero-1) {
                  green[i-1][jLoc] = 1;
                  green[i][jLoc] = 1;
                  break;
               }
            }
            //blue
            for(int j=0;j<sero;j++) {
               //걸치는거
               if(blue[iLoc][j]!=0 || blue[iLoc+1][j] !=0) {
                  blue[iLoc][j-1] = 1;
                  blue[iLoc+1][j-1] = 1;
                  
                  break;
               }else if(j==sero-1) {
                  blue[iLoc][j] = 1;
                  blue[iLoc+1][j] = 1;
                  break;
               }
            }
            
         }
            

         
         //없애주기===================================
         
         //green
         for(int i=sero-1; i>-1;i--) {
            int sum = 0;
            for(int j=0;j<garo;j++) {
               if(green[i][j]==1) sum++;
            }
            if(sum==4) {
               for(int ii=i;ii>0;ii--) {
                  for(int jj=0;jj<garo;jj++) {
                     green[ii][jj] = green[ii-1][jj];
                  }
               }
               answer++;
            }
         }
         
         //blue
         for(int j=sero-1; j>-1;j--) {
            int sum = 0;
            for(int i=0;i<garo;i++) {
               if(blue[i][j]==1) sum++;
            }
            if(sum==4) {
               for(int jj=j;jj>0;jj--) {
                  for(int ii=0;ii<garo;ii++) {
                     blue[ii][jj] = blue[ii][jj-1];
                  }
               }
               answer++;
            }
         }

         //0,1칸에 뭐 있나 없나 확인하기========================
         
         //green
         int gcheck =0;
         for(int j=0;j<garo;j++) {
            
            if(green[1][j]!=0) {
               gcheck=1;
            }
         }
         //blue
         int bcheck=0;
         for(int i=0;i<garo;i++) {
            
            if(blue[i][1]!=0) {
               bcheck=1;
            }
         }
         
      
         //===============있으면 옮겨주기
         
         //green 
         if(gcheck==1) {
            //큰수부터 해야됨
            for(int i=sero-1;i>0;i--) {
               for(int j=0;j<garo;j++) {
                  green[i][j] = green[i-1][j];
               }
            }
         }
         //blue 
         if(bcheck==1) {
            for(int j=sero-1;j>0;j--) {
               for(int i=0;i<garo;i++) {
                  blue[i][j] = blue[i][j-1];
               }
            }
            
         }
         
      
      }
      //sum 계산하기
      
      sum = 0;
      //초록
      for(int i=0;i<sero;i++) {
         for(int j=0;j<garo;j++) {
            sum += green[i][j]; //초록
            sum += blue[j][i]; //파랑
         }
      }
      
   
   }
   
   public static void main(String[] args) throws Exception{
      
      input();
      cal();

      
      System.out.println(answer);
      System.out.println(sum);
      
   }
}