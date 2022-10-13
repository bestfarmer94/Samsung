package samsung;

import java.io.*;
import java.util.*;

public class Q16236 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] aquaMan = new int[n][n];
		
		StringTokenizer st;
		
		int x = 0;
		int y = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<n; j++) {
				aquaMan[i][j] = Integer.parseInt(st.nextToken());
				
				if(aquaMan[i][j] == 9) {
					x = i;
					y = j;
					aquaMan[i][j] = 0;
				}
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) {
						return o1[2] - o2[2];
					}
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
		
		pq.add(new int[] {0, x, y});
		
		int baby = 2;
		int stack = 0;
		int result = 0;
		
		int[] now = new int[3];
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		boolean[][] visited = new boolean[n][n];
		visited[x][y] = true;
		
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			if(baby > aquaMan[now[1]][now[2]] && aquaMan[now[1]][now[2]] != 0) {
				result += now[0];
				aquaMan[now[1]][now[2]] = 0;
				stack++;
				
				if(stack == baby) {
					baby++;
					stack = 0;
				}
				
				pq.clear();
				visited = new boolean[n][n];
				pq.add(new int[] {0, now[1], now[2]});
				visited[now[1]][now[2]] = true;
				
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now[1] + dx[i];
				int ny = now[2] + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if(!visited[nx][ny]) {
						if(baby >= aquaMan[nx][ny]) {
							visited[nx][ny] = true;
							pq.add(new int[] {now[0]+1, nx, ny});
						}
					}
				}
			}
		}
		
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
}
