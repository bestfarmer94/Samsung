package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] a = new int[n];
		
		for(int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		long count = 0;
		
		for(int i=0; i<n; i++) {
			count++;
			if(a[i] - b > 0) {
				if((a[i] - b)%c == 0) {
					count += (a[i]-b)/c;
				}else {
					count += (a[i]-b)/c + 1;
				}
			}
		}
		
		sb.append(count);
		System.out.println(sb);
		br.close();
	}
}
