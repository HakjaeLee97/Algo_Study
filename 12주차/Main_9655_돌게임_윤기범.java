import java.util.*;
import java.io.*;

public class Main {
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if (N % 2 == 0)
			System.out.println("CY");
		else
			System.out.println("SK");
	}
}
