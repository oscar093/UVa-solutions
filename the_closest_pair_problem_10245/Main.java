package the_closest_pair_problem_10245;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	double[][] points;

	public static void main(String[] args) throws IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();
		int cordinates;
		while (0 != (cordinates = Integer.parseInt(ir.readLine()))) {
			main.points = new double[cordinates][2];
			for (int i = 0; i < cordinates; ++i) {
				String[] point = ir.readLine().split(" ");
				main.points[i][0] = Double.parseDouble(point[0]);
				main.points[i][1] = Double.parseDouble(point[1]);
			}
			main.points = main.mergeSort(main.points, 0, main.points.length);
			double result = main.closestUtil(0, main.points.length, main.points.length);
			if(result < 10000) {
				System.out.println(String.format("%.4f", result));
			}else {
				System.out.println("INFINITY");
			}
		}
	}
	
	public double closestUtil(int i, int j, int n){
		if(n <= 3) {
			return bruteForce(i, j, points);
		}
		
		int mid = j/2;
		double[] midPoint = points[mid];
		double dl = closestUtil(i, mid, mid);
		double dr = closestUtil(mid +1, j, n-mid);
		double d;
		if(dl < dr) {
			d = dl;
		}else {
			d = dr;
		}
		
		ArrayList<double[]> strip = new ArrayList<double[]>();
		
		for(int l = i; l < j; ++l) {
			if(Math.abs(points[l][0] - midPoint[0]) < d) {
				strip.add(points[l]);
			}
		}
		
		double[][] striparr = new double[strip.size()][2];
		strip.toArray(striparr);
		striparr = mergeSort(striparr, 1, striparr.length);
		
		double stripResult = d;
//		
		for(int m = 0; m < striparr.length; ++m) {
			for(int o = m +1; o < striparr.length && Math.abs(striparr[o][1] - striparr[m][1]) < stripResult; ++o) {
				if(euclides(striparr[o], striparr[m]) < stripResult) {
					stripResult = euclides(striparr[o], striparr[m]);
				}
			}
		}
		
		return d < stripResult ? d : stripResult;
	}
	
	
	
	public void sort(double[][] points, int XY) {
		for(int i = 0; i < points.length; ++i) {
			for(int j = 0; j < points.length; ++j) {
				if(points[i][XY] < points[j][XY] ) {
					double[] tmp = points[i];
					points[i] = points[j];
					points[j] = tmp;
				}
			}
		}
	}
	
	public double[][] mergeSort(double[][] points, int XY, int n){
		if(points.length <= 1) {
			return points;
		} 
		
		int mid = n/2;
		
		double[][] res1 = mergeSort(subarray(points, 0, mid, mid), XY, mid);
		double[][] res2 = mergeSort(subarray(points, mid,points.length, points.length - mid), XY, n-mid);
		return merge(res1, res2, XY, mid, n-mid);
	}
	
	public double[][] merge(double[][] p, double[][] q, int XY, int np, int nq){
		int length = np + nq;
		double[][] result = new double[length][2];
		int i = 0, j = 0, k = 0;
		for(;k < length && i < np && j < nq; ++k) {
			
			if(p[i][XY] < q[j][XY]) {
				result[k] = p[i];
				++i;
			}else {
				result[k] = q[j];
				++j;
			}
		}
		
		if(i >= np && j < nq) {
			for(; j < nq; ++j) {
				result[k] = q[j];
				++k;
			}
		}else if(j >= np && i < nq){
			for(; i < np; ++i ) {
				result[k] = p[i];
				++k;
			}
		}
		
		return result;
	}
	
	public double[][] subarray(double[][] points, int i, int j, int n){
		double[][] res = new double[n][2];
		int l = i;
		for(int k = 0; k < n; ++k) {
			res[k] =
					points[l];
			++l;
		}
		return res;
	}
	
	public double euclides(double[] point1, double[] point2) {
		return Math.sqrt(((point1[0]-point2[0]) * (point1[0]-point2[0])) + 
				((point1[1]-point2[1]) * (point1[1]-point2[1])));
	}
	
	public void print(double[][] points) {
		for(double[] p : points) {
			System.out.println(p[0] + " " + p[1]);
		}
		System.out.println();
	}
	
	public double bruteForce(int start, int end, double[][] points) {
		double result = Integer.MAX_VALUE;
		for(int i = start; i < end; ++i) {
			for(int j = start; j < end; ++j) {
				if(i != j) {
					double tmp = euclides(points[i], points[j]);
					if(tmp < result) {
						result = tmp;
					}	
				}
			}
		}
		return result;
	}
}
