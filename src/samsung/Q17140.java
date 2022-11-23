package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17140 {

	static int r, c, k;
	static int[][] arr;
	static int answer;
	static int count[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[101][101];
		
		for(int i=1; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<4; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		count = new int[101];
		
		R_C(0, arr, 0);
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}
	
	static void R_C(int depth, int[][] A, int cal) {	// x_length, y_length 해서 다시 해보자.
		
		if(A[r][c] == k || depth == 101) {
			answer = depth;
			return;
		}
		
		if(cal == 0) {	// R연산.
			int max_length = 0;
			
			for(int i=1; i<A.length; i++) {
				if(A[i][1] == 0) {
					break;
				}
				
				Arrays.fill(count, 0);
				
				int max = 0;	// 제일 큰 수
				int temp = 0;	// 가장 클 수 있는 count
				String str = "";
				
				for(int j=1; j<A[i].length; j++) {
					if(A[i][j] == 0) {
						temp = j-1;
						break;
					}
					
					count[A[i][j]]++;
					
					if(A[i][j] > max) {
						max = A[i][j];
					}
				}
				
				for(int j=1; j<101; j++) {
					if(j > temp) {
						break;
					}
					
					for(int k=1; k<101; k++) {
						if(k > max) {
							break;
						}
						
						if(count[k] == j) {
							str += k + " ";
							str += j + " ";
						}
					}
				}
				
				StringTokenizer st = new StringTokenizer(str);
				
				int[] part = new int[st.countTokens()+1];
				
				for(int j=1; j<st.countTokens()+1; j++) {
					part[j] = Integer.parseInt(st.nextToken());
				}
				
				A[i] = part;
				max_length = Math.max(max_length, part.length-1);
			}
			
		}else {	// C연산.
			
		}
	}
}
