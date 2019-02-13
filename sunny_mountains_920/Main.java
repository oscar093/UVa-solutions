package sunny_mountains_920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	double[][] points;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();
		
		int TestCases = Integer.parseInt(ir.readLine());
		for(int i = 0; i < TestCases; ++i) {
			int nbrOfPoints = Integer.parseInt(ir.readLine());
			main.points = new double[nbrOfPoints][2];
			for(int j = 0; j < nbrOfPoints; ++j) {
				String[] point = ir.readLine().split(" ");
				main.points[j][0] = Integer.parseInt(point[0]);
				main.points[j][1] = Integer.parseInt(point[1]);
			}
			main.sort();
			System.out.println(String.format("%.2f",main.calc()));
		}
	}
	
	public double euclides(double[] point1, double[] point2) {
		return Math.sqrt(((point1[0]-point2[0]) * (point1[0]-point2[0])) + 
				((point1[1]-point2[1]) * (point1[1]-point2[1])));
	}
	
	public double calc() {
		double result = 0;
		double maxY = 0;
		for(int k = 0; k < points.length; ++k) {
			if(points[k][1] > maxY) {
				result += euclides(points[k], points[k-1]) * ((points[k][1] - maxY)/(points[k][1] - points[k-1][1]));
				maxY = points[k][1];
			}
		}
		return result;
	}
	
	public void sort() {
		for(int i = 0; i < points.length; ++i) {
			for(int j = 0; j < points.length; ++j) {
				if(points[i][0] > points[j][0] ) {
					double[] tmp = points[i];
					points[i] = points[j];
					points[j] = tmp;
				}
			}
		}
	}
}
