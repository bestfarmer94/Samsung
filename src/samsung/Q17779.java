package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17779 {	// 문제에 오류가 있어보인다. 그대로 식을 써봤는데, 5번 선거구가 길어질 경우 범위 설정이 애매하여 선거구가 삐져나온다.
						// 그래서 그냥 직접 새로운 식으로 구현했다.

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
		sum = new int[6];
		
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
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				int start = y-i+x;
				int end = y+i-x;
				
				if(i > x+d1) {
					start = y-d1 - ((y-i+x)-(y-d1));
				}
				if(i > x+d2) {
					end = y+d2 - ((y+i-x)-(y+d2));
				}
				
				if(j >= start && j <= end) {
					area[i][j] = 5;
					sum[5] += map[i][j];
				}
				
				if(i >= 1 && i < x) {
					if(j <= y) {
						area[i][j] = 1;
						sum[1] += map[i][j];
					}else {
						area[i][j] = 2;
						sum[2] += map[i][j];
					}
					
				}else if(i >= x && i <= x+d1+d2) {
					if(i < x+d1 && j < start) {
						area[i][j] = 1;
						sum[1] += map[i][j];
					}
					
					if(i <= x+d2 && j > end) {
						area[i][j] = 2;
						sum[2] += map[i][j];
					}
					
					if(i >= x+d1 && j < start) {
						area[i][j] = 3;
						sum[3] += map[i][j];
					}
					
					if(i > x+d2 && j > end) {
						area[i][j] = 4;
						sum[4] += map[i][j];
					}
				}else {
					if(j < y-d1+d2) {
						area[i][j] = 3;
						sum[3] += map[i][j];
					}else {
						area[i][j] = 4;
						sum[4] += map[i][j];
					}
				}
			}
		}
		
//		for(int i=1; i<n+1; i++) {
//			for(int j=1; j<n+1; j++) {
//				sum[area[i][j]] += map[i][j];
//				if(area[i][j] == 5) {
//					System.out.print("■ ");
//				}else {
//					System.out.print(area[i][j] + " ");
//				}
//			}
//			System.out.println();
//		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for(int i=1; i<6; i++) {
			if(sum[i] < min) {
				min = sum[i];
			}
			
			if(sum[i] > max) {
				max = sum[i];
			}
		}
		
		answer = Math.min(answer, max-min);
	}
}
