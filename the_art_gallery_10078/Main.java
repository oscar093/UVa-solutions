package the_art_gallery_10078;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	int[][] points;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();
		int cordinates;
		while(0 != (cordinates = Integer.parseInt(ir.readLine()))) {
			main.points = new int[cordinates + 2][2];
			for(int i = 0; i < cordinates; ++i) {
				String[] point = ir.readLine().split(" ");
				main.points[i][0] = Integer.parseInt(point[0]);
				main.points[i][1] = Integer.parseInt(point[1]);
			}
			main.points[main.points.length -2] = main.points[0];
			main.points[main.points.length -1] = main.points[1];
			boolean isConvex = main.calc();
			if(isConvex) {
				System.out.println("No");
			}else {
				System.out.println("Yes");
			}
		}
	}
	
	public boolean calc() {
		if(leftTurn(points[0], points[1], points[2])) {
			for(int i = 1; i < points.length -2; ++i) {
				if(!leftTurn(points[i], points[i +1], points[i +2])) {
					return false;
				}
			}
		}else {
			for(int i = 1; i < points.length -2; ++i) {
				if(leftTurn(points[i], points[i +1], points[i +2])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean leftTurn(int[] p0, int[] p1, int[] p2) {
		int x1 = p1[0] - p0[0]; 
		int x2 = p2[0] - p0[0]; 
		int y1 = p1[1] - p0[1]; 
		int y2 = p2[1] - p0[1]; 
		return x1 * y2 - x2 * y1 > 0;
	}
}
