package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15685 {

	static int n;
	static boolean[][] map;
	static int[] dx;
	static int[] dy;
	static ArrayList<Integer> dir;
	//
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		dy = new int[] {0, -1, 0, 1};
		dx = new int[] {1, 0, -1, 0};
		
		StringTokenizer st;
		dir = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dir.clear();
			dragon(x, y, d, g);
		}
		
		int count = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
					count++;
				}
			}
		}
		
//		for(int i=0; i<=10; i++) {
//			for(int j=0; j<=10; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		sb.append(count);
		System.out.println(sb);
		br.close();
	}

	static void dragon(int x, int y, int d, int g) {
		
		dir.add(d);
		
		for(int i=1; i<=g; i++) {
			int start = dir.size();
			
			for(int j=start-1; j>=0; j--) {
				dir.add((dir.get(j)+1)%4);
			}
		}
		
		map[y][x] = true;
		int nx = x;
		int ny = y;
		
		for(int i=0; i<dir.size(); i++) {
			nx += dx[dir.get(i)];
			ny += dy[dir.get(i)];
			
			if(nx >= 0 && ny >= 0 && nx <= 100 && ny <= 100) {
				map[ny][nx] = true;
			}
		}
	}
}
