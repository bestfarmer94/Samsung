package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14890 {

	static int n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		boolean[] visited;
		
		for(int i=0; i<n; i++) {
			int confirm = 0;
			visited = new boolean[n];
			
			for(int j=1; j<n; j++) {
				int a = map[i][j] - map[i][j-1];
				confirm = 0;
				
				if(a == 0) {
					continue;
				}else if(Math.abs(a) > 1) {
					confirm++;
					break;
				}else if(a == 1) {
					if(isIn(j-l)) {
						for(int k=j-l; k<j; k++) {
							if(map[i][k] == map[i][j] - 1 && !visited[k]) {
								visited[k] = true;
							}else {
								confirm++;
								break;
							}
						}
						if(confirm == 0) {
							continue;
						}else {
							break;
						}
					}else {
						confirm++;
						break;
					}
				}else if(a == -1) {
					if(isIn(j+l-1)) {
						for(int k=j; k<j+l; k++) {
							if(map[i][k] == map[i][j-1] - 1 && !visited[k]) {
								visited[k] = true;
							}else {
								confirm++;
								break;
							}
						}
						if(confirm == 0) {
							continue;
						}else {
							break;
						}
					}else {
						confirm++;
						break;
					}
				}
			}
			
			if(confirm == 0) {
				System.out.println("i " + i);
				answer++;
			}
		}
		
		for(int j=0; j<n; j++) {
			int confirm = 0;
			visited = new boolean[n];
			
			for(int i=1; i<n; i++) {
				int a = map[i][j] - map[i-1][j];
				confirm = 0;
				
				if(a == 0) {
					continue;
				}else if(Math.abs(a) > 1) {
					confirm++;
					break;
				}else if(a == 1) {
					if(isIn(i-l)) {
						for(int k=i-l; k<i; k++) {
							if(map[k][j] == map[i][j] - 1 && !visited[k]) {
								visited[k] = true;
							}else {
								confirm++;
								break;
							}
						}
						if(confirm == 0) {
							continue;
						}else {
							break;
						}
					}else {
						confirm++;
						break;
					}
				}else if(a == -1) {
					if(isIn(i+l-1)) {
						for(int k=i; k<i+l; k++) {
							if(map[k][j] == map[i-1][j] - 1 && !visited[k]) {
								visited[k] = true;
							}else {
								confirm++;
								break;
							}
						}
						if(confirm == 0) {
							continue;
						}else {
							break;
						}
					}else {
						confirm++;
						break;
					}
				}
			}
			
			if(confirm == 0) {
				System.out.println("j " + j);
				answer++;
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}
	
	static boolean isIn(int x) {
		return x >= 0 && x < n;
	}

}
