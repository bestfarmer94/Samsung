package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12100 {

	static int n;
	static int max = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =  new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		StringTokenizer st;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] > max) {
					max = board[i][j];
				}
			}
		}
		
		move(board, 0);
		
		sb.append(max);
		System.out.println(sb);
		br.close();
	}

	static void move(int[][]board, int count) {
		
		System.out.println(count);
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		if(count == 5) {
			return;
		}
		
		int[][] clone = new int[n][n];
		
		for(int k=0; k<4; k++) {
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					clone[i][j] = board[i][j];
				}
			}
			
//temp = new int[n + 1][n + 1];
//for (int i = 1; i <= n; i++) {
//	temp[i] = map[i].clone();
//}
			
			if(k == 0) {	// 위로 보내기
				for(int j=0; j<n; j++) {
					int cnt = 0;
					
					for(int i=1; i<n; i++) {
						if(clone[i][j] != 0) {
							if(clone[cnt][j] == 0) {	// 보낼 자리가 빔. 위로 올리기.
								clone[cnt][j] = clone[i][j];
								clone[i][j] = 0;
							}else {		// 보낼 자리에 뭔가 있음.
								if(clone[cnt][j] == clone[i][j] && clone[i][j] > 0) {	// 합쳐진다.
									max = Math.max(max, clone[i][j] * 2); // max 갱신
									clone[cnt][j] = clone[i][j]*2;	// 이미 합쳐진 수를 구분하기 위해.
									clone[i][j] = 0;
									cnt++;
								}else {	// 다른 수다.
									cnt++;
									if(i != cnt) {
										clone[cnt][j] = clone[i][j];
										clone[i][j] = 0;
									}
								}
							}
						}
					}
				}
				
				move(clone, count+1);
			}
			
			if(k == 1) {	// 아래로 보내기
				for(int j=0; j<n; j++) {
					int cnt = n-1;
					
					for(int i=n-2; i>=0; i--) {
						if(clone[i][j] != 0) {
							if(clone[cnt][j] == 0) {	// 보낼 자리가 빔. 아래로 내리기.
								clone[cnt][j] = clone[i][j];
								clone[i][j] = 0;
							}else {		// 보낼 자리에 뭔가 있음.
								if(clone[cnt][j] == clone[i][j] && clone[i][j] > 0) {	// 합쳐진다.
									max = Math.max(max, clone[i][j] * 2); // max 갱신
									clone[cnt][j] = clone[i][j]*2;	// 이미 합쳐진 수를 구분하기 위해.
									clone[i][j] = 0;
									cnt--;
								}else {	// 다른 수다.
									cnt--;
									if(i != cnt) {
										clone[cnt][j] = clone[i][j];
										clone[i][j] = 0;
									}
								}
							}
						}
					}
				}
				
				move(clone, count+1);
			}
			
			if(k == 2) {	// 왼쪽으로 보냄
				for(int i=0; i<n; i++) {
					int cnt = 0;
					
					for(int j=1; j<n; j++) {
						if(clone[i][j] != 0) {
							if(clone[i][cnt] == 0) {	// 보낼 자리가 빔. 보내기.
								clone[i][cnt] = clone[i][j];
								clone[i][j] = 0;
							}else {		// 보낼 자리에 뭔가 있음.
								if(clone[i][cnt] == clone[i][j] && clone[i][j] > 0) {	// 합쳐진다.
									max = Math.max(max, clone[i][j] * 2); // max 갱신
									clone[i][cnt] = clone[i][j]*2;	// 이미 합쳐진 수를 구분하기 위해.
									clone[i][j] = 0;
									cnt++;
								}else {	// 다른 수다.
									cnt++;
									if(j != cnt) {
										clone[i][cnt] = clone[i][j];
										clone[i][j] = 0;
									}
								}
							}
						}
					}
				}
				
				move(clone, count+1);
			}
			
			if(k == 3) {	// 오른쪽으로 보내기
				for(int i=0; i<n; i++) {
					int cnt = n-1;
					
					for(int j=n-2; j>=0; j--) {
						if(clone[i][j] != 0) {
							if(clone[i][cnt] == 0) {	// 보낼 자리가 빔. 아래로 내리기.
								clone[i][cnt] = clone[i][j];
								clone[i][j] = 0;
							}else {		// 보낼 자리에 뭔가 있음.
								if(clone[i][cnt] == clone[i][j] && clone[i][j] > 0) {	// 합쳐진다.
									max = Math.max(max, clone[i][j] * 2); // max 갱신
									clone[i][cnt] = clone[i][j]*2;	// 이미 합쳐진 수를 구분하기 위해.
									clone[i][j] = 0;
									cnt--;
								}else {	// 다른 수다.
									cnt--;
									if(j != cnt) {
										clone[i][cnt] = clone[i][j];
										clone[i][j] = 0;
									}
								}
							}
						}
					}
				}
				
				move(clone, count+1);
			}
		}
	}
}