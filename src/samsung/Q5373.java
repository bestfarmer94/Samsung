package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q5373 {

	// 이 문제는 푸는 데에 많은 시간을 소모할 것으로 보여 보류 했다.
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		String[][][] cube = new String[6][3][3];
		String[][] cube_turn = new String[6][4];
		
		for(int i=0; i<6; i++) {
			String fill = " ";
			switch(i) {
			case 0 : fill = "w"; break;
			case 1 : fill = "y"; break;
			case 2 : fill = "r"; break;
			case 3 : fill = "o"; break;
			case 4 : fill = "g"; break;
			case 5 : fill = "b"; break;
			}
			
			for(int j=0; j<3; j++) {
				Arrays.fill(cube[i][j], fill);
			}
			
			for(int k=0; k<4; k++) {
				
			}
		}
		
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			String[] turn = br.readLine().split(" ");
			
			for(int j=0; j<n; j++) {
				
			}
		}

	}

}
