package how_do_you_add_10943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	int MOD = 1000000;
	int[][] memo = new int[200][200];

	public static void main(String[] args) throws IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();

		while (ir.ready()) {
			String[] strinput = ir.readLine().split(" ");
			if (strinput[0].equals("0") && strinput[1].equals("0")) {
				break;
			}
			System.out.println(main.calc(Integer.parseInt(strinput[0]), Integer.parseInt(strinput[1])));
		}

	}

	public int calc(int n, int k) {
		int res = 0;
		if (n == 0 || k == 1) {
			res =  1;
		}else if (memo[n][k] > 0) {
			res = memo[n][k];
		}else {
			for (int i = 0; i <= n; ++i) {
				res = (res + calc(n - i, k - 1))%MOD;
			}
		}
		memo[n][k] = res;
		return res;
	}
}