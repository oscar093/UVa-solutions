package box_of_bricks_591;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));

		boolean run = true;
		double staplesCnt;
		StringBuilder output = new StringBuilder();
		int sumOfWall = 0;
		String[] staples;
		double wallHeight;
		int bricksToBeMoved = 0;
		int set = 1;

		while (run) {
			staplesCnt = Integer.parseInt(ir.readLine());
			if (staplesCnt > 0) {
				staples = ir.readLine().split(" ");
				for (int i = 0; i < staplesCnt; i++) {
					sumOfWall += Integer.parseInt(staples[i]);
				}
				wallHeight = sumOfWall / staplesCnt;
				for (int i = 0; i < staplesCnt; i++) {		
					if (Integer.parseInt(staples[i]) > wallHeight) {
						bricksToBeMoved += Integer.parseInt(staples[i]) - wallHeight;
					}
				}
				output.append("Set #" + set++ + "\nThe minimum number of moves is " + bricksToBeMoved + ".\n\n");
			} else {
				run = false;
				System.out.print(output.toString());
				return;
			}
			bricksToBeMoved = 0;
			sumOfWall = 0;
		}
	}
}