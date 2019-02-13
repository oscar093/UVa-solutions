package friends_10608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Integer>[] graph = null;

		int t = Integer.parseInt(ir.readLine());
		for (int i = 0; i < t; i++) {
			String[] nm = ir.readLine().split(" ");
			int n = Integer.parseInt(nm[0]);
			int m = Integer.parseInt(nm[1]);

			graph = new LinkedList[n + 1];
			for (int j = 0; j < m; j++) {
				String[] ab = ir.readLine().split(" ");
				if (graph[Integer.parseInt(ab[0])] == null) {
					graph[Integer.parseInt(ab[0])] = new LinkedList<Integer>();
				}
				if (graph[Integer.parseInt(ab[1])] == null) {
					graph[Integer.parseInt(ab[1])] = new LinkedList<Integer>();
				}
				if (graph[Integer.parseInt(ab[0])].indexOf(Integer.parseInt(ab[1])) < 0) {
					graph[Integer.parseInt(ab[0])].add(Integer.parseInt(ab[1]));
				}
				if (graph[Integer.parseInt(ab[1])].indexOf(Integer.parseInt(ab[0])) < 0) {
					graph[Integer.parseInt(ab[1])].add(Integer.parseInt(ab[0]));
				}
			}
			System.out.println(dfs(graph, n));
		}
	}

	public static int dfs(LinkedList<Integer>[] graph, int amount) {
		boolean[] visited = new boolean[amount + 1];
		for (boolean b : visited) {
			b = false;
		}
		
		int roofCount = 1;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < graph.length; i++) {
			int counter = 0;
			if (!visited[i] && graph[i] != null) {
				stack.push(i);
				while (!stack.isEmpty()) {
					int node = stack.pop();
					if (graph[node] != null) {
						for (int j : graph[node]) {
							if (!visited[j]) {
								stack.push(j);
								visited[j] = true;
								counter++;
							}
						}
					}
				}
			}
			if(counter > roofCount) {
				roofCount = counter;
			}
		}
		return roofCount;
	}
}