package samsung;

import java.io.*;

public class Q14500_2 {
	static int n,m;
	static int[][] map;
	static int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static boolean[][] visited;
	static int max = 0;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for(int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(temp[j]);
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visited[i][j] = true;
				dfs(0,map[i][j],i,j);
				visited[i][j] = false;
			}
		}
		System.out.println(max);
	}
	
	private static void dfs(int level, int cal, int pointX, int pointY) {
		if(level == 0) {	// 일반 dfs로만 진행하면 visited에 의해  뻐큐 모양은 나올 수 없게 된다. 
							// 이 경우가 뻐큐 모양에 해당한다.
			for(int i = 0; i < 4; i++) {
				int cal1 = cal;
				boolean check = true;
				for(int j = 0; j < 4; j++) {
					int x = pointX + move[j][0];
					int y = pointY + move[j][1];
					if(i!=j) {
						if(isIn(x,y))
							cal1+=map[x][y];
						else {
							check = false;
							break;
						}
					}
				}
				if(check)
					max = Math.max(max, cal1);
			}
		}
		if(level == 3) {
			max = Math.max(max, cal);
			return;
		}
		for(int i = 0; i < 4; i++) {
			int x = pointX + move[i][0];
			int y = pointY + move[i][1];
			if(isIn(x,y) && !visited[x][y]) {
				visited[x][y] = true;
				dfs(level+1,cal+map[x][y],x,y);
				visited[x][y] = false;
			}
		}
	}
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<n && y<m;
	}
}
