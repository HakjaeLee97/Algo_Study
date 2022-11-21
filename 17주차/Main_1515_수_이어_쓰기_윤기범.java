import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int pt = 0;

        int i = 0;
        loop: while(true) {
        	i += 1;
            String tmp = String.valueOf(i);
            for (int a = 0; a < tmp.length(); a++) {
                if (tmp.charAt(a) == s.charAt(pt))
                    pt++;
                if (pt == s.length()) {
                    System.out.println(i);
                    break loop;
                }
            }
        }
	}
}
