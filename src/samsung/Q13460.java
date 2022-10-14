package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q13460 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		String[][] map = new String[n][m];
		
		Queue<Ball> q = new LinkedList<Ball>();
		int x = 0; int y = 0;
		int x2 = 0; int y2 = 0;
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			
			for(int j=0; j<m; j++) {
				map[i][j] = Character.toString(str.charAt(j));
				
				if(map[i][j].equals("R")) {
					x = i;
					y = j;
				}
				
				if(map[i][j].equals("B")) {
					x2 = i;
					y2 = j;
				}
			}
		}
		
		boolean[][][][] visited = new boolean[n][m][n][m];
		q.add(new Ball(0, x, y, x2, y2));
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int result = -1;
		
		while(!q.isEmpty() && result == -1) {
			
			Ball b = q.poll();
//			System.out.println("poll" + b.count);
			if(b.count == 10) {
				break;
			}
			
			for(int i=0; i<4; i++) {
				String in = "";
				
				int rx = b.rx;	// 단순히 초기화
				int ry = b.ry;
				int bx = b.bx;
				int by = b.by;
//				System.out.println("next");
				
				while(true) {
					int nrx = rx + dx[i];
					int nry = ry + dy[i];
					int nbx = bx + dx[i];
					int nby = by + dy[i];
					int move = 0;
					
					if(in.length() == 0) {
						if(map[nrx][nry].equals("O")) {
							in += "R";
							rx = -1;
							ry = -1;
							move++;
						}else {
							if(!map[nrx][nry].equals("#")) {
								if(!(nrx == bx && nry == by)) {
									rx = nrx;
									ry = nry;
									move++;
								}
							}
						}
					}
					
					if(map[nbx][nby].equals("O")) {
						in += "B";
						break;
					}else {
						if(!map[nbx][nby].equals("#")) {
							if(!(nbx == rx && nby == ry)) {
								bx = nbx;
								by = nby;
								move++;
							}
						}
					}
					
					if(move == 0) {
						break;
					}
				}
				
//				System.out.println(in);
				if(in.length() != 0) {
					if(in.equals("R")) {
						result = b.count+1;
						break;
					}else {
						continue;
					}
				}else {
					if(!visited[rx][ry][bx][by]) {
						visited[rx][ry][bx][by]= true;
						q.add(new Ball(b.count + 1, rx, ry, bx, by));
					}
				}
			}
		}
		
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
}

class Ball{
	
	int count;
	int rx;
	int ry;
	int bx;
	int by;
	
	public Ball(int count, int rx, int ry, int bx, int by) {
		this.count = count;
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
	}
}

