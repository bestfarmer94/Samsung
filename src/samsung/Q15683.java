package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15683 {
	
	static int n;
	static int m;
	static int[][] map;
	static int[][] clone;
	static ArrayList<int[]> camera;
	static int min;
	static int[] dx;
	static int[] dy;
	static int count;
	static int count_clone;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		clone = new int[n][m];
		camera = new ArrayList<int[]>();
		count = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] != 0 && map[i][j] != 6) {
					camera.add(new int[]{map[i][j], i, j});
				}
				
				if(map[i][j] == 0) {
					count++;
				}
			}
		}
		
		int[] dir = new int[camera.size()];
		dx = new int[] {-1, 0, 1, 0};
		dy = new int[] {0, 1, 0, -1};
		min = count;
		
		permutation(0, dir);
		
		sb.append(min);
		System.out.println(sb);
		br.close();
	}
	
	static void permutation(int depth, int[] dir) {
		
		if(depth == camera.size()) {
//			for(int i=0; i<dir.length; i++) {
//				System.out.print(dir[i] + " ");
//			}
//			System.out.println();
			count_clone = count;
			cctv(dir);
			return;
		}
		
		int number = camera.get(depth)[0];
		
		if(number == 2) {
			for(int i=0; i<2; i++) {
				dir[depth] = i;
				permutation(depth+1, dir);
			}
		}else if(number != 5) {
			for(int i=0; i<4; i++) {
				dir[depth] = i;
				permutation(depth+1, dir);
			}
		}else {
			permutation(depth+1, dir);
		}
	}
	
	static void cctv(int[] dir) {
		
		for(int i=0; i<map.length; i++) {
			clone[i] = map[i].clone();
		}
		
		for(int i=0; i<dir.length; i++) {
			int number = camera.get(i)[0];
			
			if(number != 4) {
				cctv_map(camera.get(i)[1], camera.get(i)[2], dir[i]);
			}
			
			if(number != 1 && number != 2) {
				cctv_map(camera.get(i)[1], camera.get(i)[2], (dir[i]+1)%4);
			}
			
			if(number != 1 && number != 3) {
				cctv_map(camera.get(i)[1], camera.get(i)[2], (dir[i]+2)%4);
			}
			
			if(number == 4 || number == 5) {
				cctv_map(camera.get(i)[1], camera.get(i)[2], (dir[i]+3)%4);
			}
		}
		
//		for(int i=0; i<map.length; i++) {
//			for(int j=0; j<map[0].length; j++) {
//				System.out.print(clone[i][j] + " ");
//			}
//			System.out.println();
//		}
		min = Math.min(min, count_clone);
	}
	
	static void cctv_map(int x, int y, int dir) {
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
			if(clone[nx][ny] == 0) {
				clone[nx][ny] = 7;
				count_clone -= 1;
				cctv_map(nx, ny, dir);
			}else if(clone[nx][ny] == 6) {
				return;
			}else {
				cctv_map(nx, ny, dir);
			}
		}
	}
}
