package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15684_2 {

	static int n;
	static int m;
	static int h;
	static boolean[][] arr;
	static int min;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		arr = new boolean[h+1][n];
		
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
			permutation(i, 0, 0);
		}
		
		if(min == 4) {
			sb.append(-1);
		}else {
			sb.append(min);
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static void permutation(int depth, int count, int start) {
		
		if(min != 4) {
			return;
		}
		
		if(depth == count) {
			
//			System.out.println("depth " + depth);
//			for(int i=1; i<h+1; i++) {
//				for(int j=1; j<n; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			boolean result = move();
			if(result) {
				min = depth;
			}
			
			return;
		}
		
		for(int i=start; i<h*(n-1); i++) {
			int x = i/(n-1) + 1;
			int y = i%(n-1) + 1;
			
			if(!arr[x][y]) {
				if(y+1 != n) {
					if(!arr[x][y-1] && !arr[x][y+1]) {
						arr[x][y] = true;
						permutation(depth, count+1, i+1);
						arr[x][y] = false;
					}
				}else {
					if(!arr[x][y-1]) {
						arr[x][y] = true;
						permutation(depth, count+1, i+1);
						arr[x][y] = false;
					}
				}
			}
		}
	}
	
	// 이 방식이 좀더 좋은 결과가 나올 것 같다. 실제로 나왔다.
	
	static boolean move() {
		
		boolean confirm = true;
		
		for(int i=1; i<n+1; i++) {
			int temp = i;
			
			if(!confirm) {
				break;
			}
			
			for(int j=1; j<h+1; j++) {
				if(temp-1 != 0 && arr[j][temp-1]) {
					temp--;
				}else if(temp != n && arr[j][temp]) {
					temp++;
				}
			}
			
			if(temp != i) {
				confirm = false;
			}
		}
		
		return confirm;
	}
}
