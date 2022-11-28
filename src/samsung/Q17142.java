package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q17142 {

	static int n, m;
	static int[][] map, map_clone;
	static ArrayList<int[]> virus;
	static boolean[] visited;
	static Queue<int[]> q;
	static int count, min;
	static int[] dx, dy;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		map_clone = new int[n][n];
		virus = new ArrayList<int[]>();
		count = n*n;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					virus.add(new int[] {i, j});
					count--;
				}else if(map[i][j] == 1) {
					count--;
				}
			}
		}
		
		visited = new boolean[virus.size()];
		q = new LinkedList<int[]>();
		min = Integer.MAX_VALUE;
		dx = new int[] {-1, 0, 1, 0};
		dy = new int[] {0, 1, 0, -1};
		
		int[] select = new int[m];
		
		dfs(0, select, 0);
		
		if(min > 10000) {
			min = -1;
		}
		
		sb.append(min);
		System.out.println(sb);
		br.close();
	}
	
	static void dfs(int depth, int[] select, int start) {
		
		if(min == 0 || depth == m) {
			spread(select);
			
			return;
		}
		
		for(int i=start; i<virus.size(); i++) {
			if(!visited[i]) {
				select[depth] = i;
				visited[i] = true;
				dfs(depth+1, select, i+1);
				visited[i] = false;
			}
		}
	}

	static void spread(int[] select) {
		
		for(int i=0; i<n; i++) {
			map_clone[i] = map[i].clone();
		}
		
		for(int i=0; i<m; i++) {
			int x = virus.get(select[i])[0];
			int y = virus.get(select[i])[1];
			map_clone[x][y] = -1;
			q.add(new int[] {0, x, y});
		}
		
		int time = -1;
		int space = count;
		int[] now = new int[3];
		
		if(space == 0) {
			time = 0;
		}
		
		while(space != 0 && !q.isEmpty()) {
			now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[1] + dx[i];
				int ny = now[2] + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
					
					if(map_clone[nx][ny] == 0) {
						map_clone[nx][ny] = -1;
						space--;
						
						if(space == 0) {
							time = now[0] + 1;
							break;
						}
						
						q.add(new int[] {now[0] + 1, nx, ny});
						
					}else if(map_clone[nx][ny] == 2) {
						map_clone[nx][ny] = -1;
						q.add(new int[] {now[0] + 1, nx, ny});
					}
				}
			}
		}
		
		q.clear();
		
		if(time != -1 && time < min) {
			min = time;
		}
	}
}
