package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15684 {

	static int n;
	static int m;
	static int h;
	static boolean[][] arr;
	static int min;
	static int[] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		arr = new boolean[h+1][n];
		map = new int[n+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = true;
		}
		
		min = 4;
		
		for(int i=0; i<4; i++) {
			if(min != 4) {
				break;
			}
			permutation(i, 0, 0, new boolean[h+1][n]);
		}
		
		if(min == 4) {
			sb.append(-1);
		}else {
			sb.append(min);
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static void permutation(int depth, int count, int start, boolean[][] clone) {
		
		if(min != 4) {
			return;
		}
	
		if(start == 0) {
			for(int i=1; i<h+1; i++) {
				clone[i] = arr[i].clone();
			}
		}
		
		if(depth == count) {
			
//			System.out.println("depth " + depth);
//			for(int i=1; i<h+1; i++) {
//				for(int j=1; j<n; j++) {
//					System.out.print(clone[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			int result = move(clone);
			if(result == 0) {
				min = depth;
			}
			
			return;
		}
		
		for(int i=start; i<h*(n-1); i++) {
			int x = i/(n-1) + 1;
			int y = i%(n-1) + 1;
			
			if(!clone[x][y]) {
				if(y+1 != n) {
					if(!clone[x][y-1] && !clone[x][y+1]) {
						clone[x][y] = true;
						permutation(depth, count+1, i+1, clone);
						clone[x][y] = false;
					}
				}else {
					if(!clone[x][y-1]) {
						clone[x][y] = true;
						permutation(depth, count+1, i+1, clone);
						clone[x][y] = false;
					}
				}
			}
		}
	}
	
	
	static int move(boolean[][] clone) {
		
		for(int i=1; i<n+1; i++) {
			map[i] = i;
		}
		
		for(int i=1; i<h+1; i++) {
			for(int j=1; j<n; j++) {
				if(clone[i][j]) {
					int temp = map[j];
					map[j] = map[j+1];
					map[j+1] = temp;
				}
			}
		}
		
		int confirm = 0;
		for(int i=1; i<n+1; i++) {
			if(map[i] != i) {
				confirm = 1;
				break;
			}
		}
		
		return confirm;
	}
}
