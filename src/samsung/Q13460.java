package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q13460 {

	static int result;
	static int[] dx;
	static int[] dy;
	static PriorityQueue<RBO> pq;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		String[][] map = new String[n+1][m+1];
		int x = 0;
		int y = 0;
		int x_b = 0;
		int y_b = 0;
		
		for(int i=1; i<n+1; i++) {
			String[] str = br.readLine().split("");
			
			for(int j=1; j<m+1; j++) {
				map[i][j] = str[j-1];
				if(map[i][j].equals("R")) {
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
		
		pq = new PriorityQueue<RBO>();
		
		bfs(x, y, x_b, y_b, 0, map);
		
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
	
	static void bfs(int x, int y, int x_b, int y_b, int count, String[][] map) {
		
		System.out.println(x + " " + y + " " + x_b + " " + y_b + " " + count);
		for(int i=1; i<map.length; i++) {
			for(int j=1; j<map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
		if(count == 10) {
			return;
		}
		
		move(x, y, x_b, y_b, count+1, map, 0);
		move(x, y, x_b, y_b, count+1, map, 1);
		move(x, y, x_b, y_b, count+1, map, 2);
		move(x, y, x_b, y_b, count+1, map, 3);
		
	}
	
	static void move(int x, int y, int x_b, int y_b, int count, String[][] map, int direction) {
		
		String[][] map_clone = new String[map.length][map[0].length];
		for(int j=1; j<map.length; j++) {
			for(int k=1; k<map.length; k++) {
				map_clone[j][k] = map[j][k];
			}
		}
		
		while(true) {
			
			int nx = x + dx[direction];
			int ny = y + dy[direction];
			
			int nx_b = x_b + dx[direction];
			int ny_b = y_b + dy[direction];
			
			if(map_clone[nx_b][ny_b].equals("O")) {
				map_clone[x_b][y_b] = ".";
				result = -2;
				break;
			}
			
			if(map_clone[nx_b][ny_b].equals(".")) {
				map_clone[nx_b][ny_b] = "B";
				map_clone[x_b][y_b] = ".";
				x_b = nx_b;
				y_b = ny_b;
			}
			
			if(map_clone[nx][ny].equals("O")) {
				map_clone[x][y] = ".";
				result = Math.min(result, count);
			}
			
			if(map_clone[nx][ny].equals(".")) {
				map_clone[nx][ny] = "R";
				map_clone[x][y] = ".";
				x = nx;
				y = ny;
			}
			
			if((x != nx || y != ny) && (x_b != nx_b || y_b != ny_b)) {
				break;
			}
		}
		
		if(result == -2) {
			result = -1;
		}else {
			bfs(x, y, x_b, y_b, count, map_clone);
		}
		
	}

}

class RBO implements Comparable<RBO>{
	
	int x;
	int y;
	int x_b;
	int y_b;
	int count;
	String[][] map;
	
	public RBO(int x, int y, int x_b, int y_b, int count, String[][] map) {
		this.x = x;
		this.y = y;
		this.x_b = x_b;
		this.y_b = y_b;
		this.count = count;
		this.map = new String[map.length][map[0].length];
		
		for(int i=1; i<map.length; i++) {
			for(int j=1; j<map[0].length; j++) {
				this.map[i][j] = map[i][j];
			}
		}
		
		
	}

	@Override
	public int compareTo(RBO o) {
		
		return this.count - o.count ;
	}
}
