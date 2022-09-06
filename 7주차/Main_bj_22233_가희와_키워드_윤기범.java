import java.util.*;
import java.io.*;

public class Main {
    public static int N, M;
    public static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            map.put(str, 1);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while(st.hasMoreTokens()) {
                String str = st.nextToken();
                if(map.get(str) != null) {
                    map.remove(str);
                }
            }
            System.out.println(map.size());
        }
    }
}
