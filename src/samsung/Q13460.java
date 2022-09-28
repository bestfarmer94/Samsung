package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13460 {

	static int result;
	static boolean[][] visited;
	static int[] dx;
	static int[] dy;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		String[][] map = new String[n][m];
		visited = new boolean[n][m];
		int x = 0;
		int y = 0;
		int x_b = 0;
		int y_b = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<m; j++) {
				map[i][j] = st.nextToken();
				if(map[i][j].equals("R")) {
					visited[i][j] = true;
					x = i;
					y = j;
				}
				
				if(map[i][j].equals("B")) {
					x_b = i;
					y_b = j;
				}
			}
		}
		
		dx = new int[] {-1, 0, 1, 0};
		dy = new int[] {0, 1, 0, -1};
		result = -1;
		
		bfs(x, y, x_b, y_b, 0);
		System.out.println(sb);
		br.close();
	}
	
	static void bfs(int x, int y, int x_b, int y_b, int count) {
		
		if(result != -1) {
			return;
		}
		
		count++;
		
		for(int i=0; i<4; i++) {
			while(true) {
				
				int nx = x + dx[i];
				int nx_b = x_b + dx[i];
			}
		}
	}

}
