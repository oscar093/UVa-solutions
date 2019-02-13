package unidirectional_TSP_116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	int[][] area = null;
	int[][] cost = null;
	int[][] path = null;
	
	public static void main(String[] args) throws IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();
		
		
		while(ir.ready()) {
			String[] areastr = ir.readLine().split(" ");
			main.area = new int[Integer.parseInt(areastr[0])][Integer.parseInt(areastr[1])];	
			int k = 0;
			String[] row = ir.readLine().split(" ");
			for(int i = 0; i < main.area.length; i++) {
				for(int j = 0; j < main.area[i].length; j++) {
					if(k < row.length) {
						main.area[i][j] = Integer.parseInt(row[k]);
						++k;
					}else {
						row = ir.readLine().split(" ");
						k = 0;
						main.area[i][j] = Integer.parseInt(row[k]);
						++k;
					}
				}
			}
//			main.print();
			
			int[][] area2 = new int[main.area[0].length][main.area.length];
			main.cost = new int[main.area[0].length][main.area.length];
			main.path = new int[main.area.length][main.area[0].length];
			for(int i = 0; i < area2.length; i++) {
				for(int j = 0; j < area2[i].length; j++) {
					area2[i][j] = main.area[j][i];
					main.cost[i][j] = Integer.MAX_VALUE;
				}
			}
			main.area = area2;
			
			int res = main.cc(0, 0);
			for(int p = 1; p < main.area[0].length; ++p) {
				int tmp = main.cc(0, p);
				if(res < tmp) {
					res = tmp;
				}
			}
			
			main.printPath();
		}
	}
	
	public int cc(int c, int r) {
		if(cost[c][r] != Integer.MAX_VALUE) {
			return cost[c][r];
		}
		int val = Integer.MAX_VALUE;
		if(c >= area.length -1) {
			val = area[c][r];
			cost[c][r] = area[c][r];
		}else {
			for(int i = -1; i <= 1; i++) {
				int k = r + i;
				if(k > area[c].length - 1) {
					k = 0;
				}
				if(k < 0) {
					k = area[c].length - 1;
				}
				if(c + 1 < area.length) {
					int tmp = area[c][r] + cc(c + 1, k);
					
					if(tmp < val) {
						val = tmp;
					}
					cost[c][r] = val;
				}
			}
		}
		return val;
	}
	
	public void printPath() {
		int res = 0;
		int pos = 0;
		int small = Integer.MAX_VALUE;

		for(int i = 0; i < cost[0].length; ++i) {
			if(cost[0][i] < small) {
				small = cost[0][i];
				pos = i;
			}
		}
		res = small;
		System.out.print(pos +1);
		if(cost.length > 1) {
			System.out.print(" ");
		}
		small = Integer.MAX_VALUE;
		for(int i = 1; i < cost.length; ++i) {
			small = Integer.MAX_VALUE;
			
			int k,l;
			if(pos+1 >= cost[0].length) {
				k = 0;
			}else {
				k = pos+1;
			}
			
			if(pos-1 < 0) {
				l = cost[0].length-1;
			}else {
				l = pos-1;
			}
			
			if(cost[i][pos] < small) {
				small = cost[i][pos];
			}
			
			if (small == cost[i][k]) {
				pos = pos < k ? pos : k;
			}else if (cost[i][k] < small) {
				small = cost[i][k];
				pos = k;
			}
			
			if (small == cost[i][l]) {
				pos = pos < l ? pos : l;
			} else if (cost[i][l] < small) {
				small = cost[i][l];
				pos = l;
			}
			
			System.out.print(pos +1);
			if(i < cost.length -1) {
				System.out.print(" ");
			}
		}
		System.out.println();
		System.out.println(res);
	}
	
	public void printCostData() {
		System.out.println("\nCost\n==============================");
		for(int k = 0; k < cost.length; k++) {
			for(int l = 0; l < cost[k].length; l++) {
				if( cost[k][l] == Integer.MAX_VALUE) {
					System.out.print("--" + " ");
				}else {
					System.out.print( cost[k][l] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("==================================");
	}
	
	public void print() {
		System.out.println(area.length + " " + area[0].length);
		for(int i = 0; i < area.length; ++i) {
			System.out.println();
			for(int j = 0; j < area[i].length; ++j) {
				System.out.print(area[i][j] + " ");
			}
		}
	}
}	
