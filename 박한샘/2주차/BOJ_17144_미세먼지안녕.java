package August_First_Week;


import java.util.*;
import java.io.*;

public class BOJ_17144_미세먼지안녕 {
   //미세먼지 확산(네방향),(칸이 없거나 공기청정기가 있음 확산 안됨
   //확산은 Ar,c / 5 (소수점은 버리니까 정수 계산)
   //r, c에 남은 미세먼지 양은 Ar,c - (Ar,c/5) x 확산된 방향의 개수
   
   //공기청정기 작동(위쪽 공기청정기는 반시계 , 아래는 시계로 순환)
   //미세먼지가 바람의 방향대로 모두 한 칸씩 이동
   //공기청정기로 들어간 미세 먼지는 모두 정화
   //공기 청정기 바람 이동방향 -> 위에껀  0,1 , -1,0 , 0,-1  , 1, 0 
   //                  아래껀  0,1 ,  1,0 , 0,-1  , -1, 0
   
   static int[][]map;
   
   static int[][]move = {{0,1},{1,0},{0,-1},{-1,0}};
   static int R;
   static int C;

   
   
   public static void main(String[] args) throws Exception {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine()," ");
      
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());
      
      
      map = new int[R][C];
      
      for(int i=0;i<R;i++) {
         st = new StringTokenizer(br.readLine()," ");
         for(int j=0;j<C;j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
         
         }
      
      }
      
      
      
      for(int time=0;time<T;time++) {
         
         for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
               if(map[i][j]>0) dust(i,j);
            }
         }
         
         clear();
         
         
         
      }
      
   }
   
   //공기 청정기 바람 이동방향 -> 위에껀  0,1 , -1,0 , 0,-1  , 1, 0 
   //                  아래껀  0,1 ,  1,0 , -1,0  , -1, 0
   
   static void clear() {
      
      //위 공기청정기
      
      for(int i=R/2-1;i>0;i--) {
         map[i-1][0] = map[i][0];
      }
      for(int j=0;j<C;j++) {
         map[0][j+1] = map[0][j];
      }
      for(int i=0;i<R/2-1;i++) {
         map[i][C] = map[i+1][C]; 
      }
      for(int j=C;j>0;j--) {
         map[R/2-1][j] = map[R/2-1][j-1];
      }
      
      
      //아래공기청정기
      
      
      
   }
   
   static void dust(int y,int x) {
      
      int cnt = 0;
      for(int d=0;d<4;d++) {
         int newI = y + move[d][0];
         int newJ = x + move[d][1];
         
         if(newI<0 || newJ <0 || 
            R-1 < newI || C-1 < newJ 
            || map[newI][newJ]==-1) continue;
         
         map[newI][newJ] += map[y][x] / 5;
         
         cnt++;
         
      }
      
      map[y][x] -= map[y][x]/5 * cnt;
      
   }
   
   

}