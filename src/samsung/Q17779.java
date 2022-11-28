package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17779 {

	static int n;
	static int answer;
	static int[][] map, area;
	static int[] sum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		area = new int[n+1][n+1];
		StringTokenizer st;
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<n+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		sum = new int[5];
		
		for(int x=1; x<=n-2; x++) {
			for(int y=2; y<=n-1; y++) {
				for(int d1=1; y-d1>=1; d1++) {
					for(int d2=1; y+d2<=n; d2++) {
						if(x+d1+d2 <= n) {
							count(x, y, d1, d2);
						}
					}
				}
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}
	
	static void count(int x, int y, int d1, int d2) {
		
		Arrays.fill(sum, 0);
		
		for(int i=1; i<n+1; i++) {
			Arrays.fill(area[i], 0);
		}
		
		for(int i=0; i<=d1; i++) {
			area[x+i][y-i] = 5;
			for(int j=0; j<=d2; j++) {
				area[x+j][y+j] = 5;
				area[x+i+j][y-i+j] = 5;
			}
		}
		
		for(int i=x; i<=x+d1+d2; i++) {
			for(int j=y-d1; j<=y+d2; j++) {
				if(i)
				System.out.print(area[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(area[i][j] == 0) {
					if(i < x+d1 && j <= y) {
						area[i][j] = 1;
					}else if(i <= x+d2 && j > y) {
						area[i][j] = 2;
					}else if(x+d1 <= i && j < y-d1+d2) {
						area[i][j] = 3;
					}else if(x+d2 < i && y-d1+d2 <= j) {
						area[i][j] = 4;
					}else {
						area[i][j] = 5;
					}
				}
			}
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<5; i++) {
			if(sum[i] < min) {
				min = sum[i];
			}
			
			if(sum[i] > max) {
				max = sum[i];
			}
		}
		
		System.out.println(max - min);
		answer = Math.min(answer, max-min);
	}
}
