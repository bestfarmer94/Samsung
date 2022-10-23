package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14500 {

	static int n;
	static int m;
	static int[][] map;
	static int max;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		tetro();
		
		sb.append(max);
		System.out.println(sb);
		br.close();
	}

	static void tetro() {
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m-3; j++) {
				int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
				max = Math.max(max, sum);
			}
		}
		
		for(int j=0; j<m; j++) {
			for(int i=0; i<n-3; i++) {
				int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
				max = Math.max(max, sum);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m-2; j++) {
				int sum = map[i][j] + map[i][j+1] + map[i][j+2];
				for(int k=0; k<3; k++) {
					int sum2;
					int sum3;
					
					if(i == 0) {
						sum3 = sum + map[i+1][j+k];
						max = Math.max(max, sum3);
					}else if(i == n-1) {
						sum2 = sum + map[i-1][j+k];
						max = Math.max(max, sum2);
					}else {
						sum2 = sum + map[i-1][j+k];
						sum3 = sum + map[i+1][j+k];
						max = Math.max(max, Math.max(sum2, sum3));
					}
				}
			}
		}
		
		for(int j=0; j<m; j++) {
			for(int i=0; i<n-2; i++) {
				int sum = map[i][j] + map[i+1][j] + map[i+2][j];
				for(int k=0; k<3; k++) {
					int sum2;
					int sum3;
					
					if(j == 0) {
						sum3 = sum + map[i+k][j+1];
						max = Math.max(max, sum3);
					}else if(j == m-1) {
						sum2 = sum + map[i+k][j-1];
						max = Math.max(max, sum2);
					}else {
						sum2 = sum + map[i+k][j-1];
						sum3 = sum + map[i+k][j+1];
						max = Math.max(max, Math.max(sum2, sum3));
					}
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m-1; j++) {
				int sum = map[i][j] + map[i][j+1];
				for(int k=-1; k<2; k++) {
					int sum2;
					int sum3;
					
					if(j+k < 0) {
						continue;
					}
					if(j+k+1 >= m) {
						continue;
					}
					
					if(i == 0) {
						sum3 = sum + map[i+1][j+k] + map[i+1][j+k+1];
						max = Math.max(max, sum3);
					}else if(i == n-1) {
						sum2 = sum + map[i-1][j+k] + map[i-1][j+k+1];
						max = Math.max(max, sum2);
					}else {
						sum2 = sum + map[i-1][j+k] + map[i-1][j+k+1];
						sum3 = sum + map[i+1][j+k] + map[i+1][j+k+1];
						max = Math.max(max, Math.max(sum2, sum3));
					}
				}
			}
		}
		
		for(int j=0; j<m; j++) {
			for(int i=0; i<n-1; i++) {
				int sum = map[i][j] + map[i+1][j];
				for(int k=-1; k<2; k++) {
					int sum2;
					int sum3;
					
					if(i+k < 0) {
						continue;
					}
					if(i+k+1 >= n) {
						continue;
					}
					
					if(j == 0) {
						sum3 = sum + map[i+k][j+1] + map[i+k+1][j+1];
						max = Math.max(max, sum3);
					}else if(j == m-1) {
						sum2 = sum + map[i+k][j-1] + map[i+k+1][j-1];
						max = Math.max(max, sum2);
					}else {
						sum2 = sum + map[i+k][j-1] + map[i+k+1][j-1];
						sum3 = sum + map[i+k][j+1] + map[i+k+1][j+1];
						max = Math.max(max, Math.max(sum2, sum3));
					}
				}
			}
		}
	}
}
