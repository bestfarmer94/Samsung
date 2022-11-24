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
	static int[][] A_clone;
	
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
		
		R_C(0, arr, 3, 3);
		
		if(answer > 100) {
			answer = -1;
		}
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}
	
	static void R_C(int depth, int[][] A, int x_length, int y_length) {	// x_length, y_length 해서 다시 해보자.
//		System.out.println();
//		System.out.println(depth);
//		for(int i=1; i<x_length+1; i++) {
//			for(int j=1; j<y_length+1; j++) {
//				System.out.print(A[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		if(A[r][c] == k || depth == 101) {
			answer = depth;
			return;
		}
		
		int temp_length = 0;
		boolean turn = false;
		
		if(x_length < y_length) {
			A_clone = new int[101][101];
//			System.out.println("turn");
			for(int i=1; i<x_length+1; i++) {
				for(int j=1; j<A[i].length; j++) {
					A_clone[j][i] = A[i][j];
				}
			}
//			for(int i=1; i<y_length+1; i++) {
//				for(int j=1; j<x_length+1; j++) {
//					System.out.print(A_clone[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			temp_length = x_length;
			x_length = y_length;
			y_length = temp_length;
			turn = true;
		}else {
			A_clone = new int[101][101];
			
			for(int i=1; i<x_length+1; i++) {
				A_clone[i] = A[i].clone();
			}
		}
		
		int max_length = 0;
		
		for(int i=1; i<x_length+1; i++) {
			
			Arrays.fill(count, 0);
			
			int max = 0;	// 제일 큰 수
			int temp = 0;	// 가장 클 수 있는 count
			String str = "";
			
			for(int j=1; j<y_length+1; j++) {
				
				if(A_clone[i][j] != 0) {
					count[A_clone[i][j]]++;
					temp++;
				}
				
				if(A_clone[i][j] > max) {
					max = A_clone[i][j];
				}
			}
			
//			System.out.println(temp);
//			System.out.println(max);
			
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
						temp -= j;
					}
				}
			}
			
			StringTokenizer st = new StringTokenizer(str);
			temp_length = st.countTokens();
			int[] part = new int[101];
			for(int j=1; j<temp_length+1; j++) {
				if(j == 101) {
					break;
				}
				part[j] = Integer.parseInt(st.nextToken());
//				System.out.print(part[j] + " ");
			}
//			System.out.println();
			
			A_clone[i] = part;
			
			max_length = Math.max(max_length, temp_length);
		}
		
		if(turn) {
			A = new int[max_length+1][x_length+1];
			
			for(int i=1; i<x_length+1; i++) {
				for(int j=1; j<max_length+1; j++) {
					A[j][i] = A_clone[i][j];
				}
			}
			
//			System.out.println(max_length);
//			System.out.println(x_length);
			R_C(depth+1, A, max_length, x_length);
			
		}else {
			
//			System.out.println(x_length);
//			System.out.println(max_length);
			R_C(depth+1, A_clone, x_length, max_length);
		}
		
	}
}
