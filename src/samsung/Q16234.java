package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16234 {

	static int n;
	static int l;
	static int r;
	static int[][] map;
	static int[][] group;
	static int answer;
	static int[] dx;
	static int[] dy;
	static Queue<int[]> q;
	static int[] total;
	static int[] count;
	static boolean confirm;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		group = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				group[i][j] = n*i + j;
			}
		}
		
		answer = 0;
		dx = new int[] {-1, 0, 1, 0};
		dy = new int[] {0, 1, 0, -1};
		q = new LinkedList<int[]>();
		
		move();
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}
	
	static void move() {
		
		confirm = false;
		total = new int[n*n];
		count = new int[n*n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(group[i][j] == n*i + j) {
					bfs(i, j, group[i][j]);
				}
			}
		}
		
		if(confirm) {
			answer++;
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					map[i][j] = total[group[i][j]] / count[group[i][j]];
					group[i][j] = n*i + j;
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			move();
		}
	}

	static void bfs(int x, int y, int number) {
		
		q.add(new int[] {x, y});
		total[number] += map[x][y];
		count[number]++;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if(group[nx][ny] == n*nx + ny && group[nx][ny] != number) {
						int minus = Math.abs(map[now[0]][now[1]] - map[nx][ny]);
						
						if(minus >= l && minus <= r) {
							confirm = true;
							group[nx][ny] = number;
							total[number] += map[nx][ny];
							count[number]++;
							q.add(new int[] {nx, ny});
						}
					}
				}
			}
		}
	}
}
