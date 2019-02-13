package killing_aliens_in_borg_maze_10307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main0 {
	String[][] area;
	boolean[][] visited;
	int name = 0;
	ArrayList<Node> visitd = new ArrayList<Node>();
	
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public static void main(String[] args) throws IOException {
		Main0 m = new Main0();
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		int testcases = Integer.parseInt(ir.readLine());
		for(int i = 0; i < testcases; i++) {
			String[] areaDim = ir.readLine().split(" ");
			m.area = new String[Integer.parseInt(areaDim[1])][Integer.parseInt(areaDim[0])];
			m.visited = new boolean[Integer.parseInt(areaDim[1])][Integer.parseInt(areaDim[0])];
			for(int j = 0; j < Integer.parseInt(areaDim[1]); j++) {
				m.area[j] = ir.readLine().split("");
			}
			int result = m.mkGraph();
//			System.out.println("\nResult: " + result + "\n\n");
			m.edges.clear();
		}
	}
	
	public int mkGraph() {
		int i = 0, j = 0;
		for (String[] sv : area) {
			j=0;
			for (String s : sv) {
				if (s.contentEquals("S") || s.contentEquals("A")) {
					bfs(i,j);
//					System.out.println("Ny");
				}
				j++;
			}
			i++;
		}
		System.out.println(prim());
		visitd.clear();
		return 1;
	}
	
	public boolean whitinBound(int x, int y) {
		boolean checked = x < area.length && x > -1 && y < area[x].length && y > - 1 && !visited[x][y] && !area[x][y].contentEquals("#");
		return checked;
	}
	
	public boolean edgeIsInEdges(Edge e) {
		for(Edge edge : edges) {
			if(e.node1.x == edge.node1.x && e.node1.y == edge.node1.y && e.node2.x == edge.node2.x && e.node2.y == edge.node2.y) {
				return true;
			}
			if(e.node2.x == edge.node1.x && e.node2.y == edge.node1.y && e.node1.x == edge.node2.x && e.node1.y == edge.node2.y) {
				return true;
			}
		}
		return false;
	}
	
	public int bfs(int startx, int starty) {
		ArrayList<Node> graph = new ArrayList<Node>();
		Node start = new Node(startx, starty, 0);
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(start);
		visited[start.x][start.y] = true;
		while (!queue.isEmpty()) {
			Node pos = queue.poll();
			int x = pos.x;
			int y = pos.y;
			if (area[pos.x][pos.y].contentEquals("A") || area[pos.x][pos.y].contentEquals("S")) {
				graph.add(pos);
//				System.out.println("INlagd: " + area[x][y]);
			}
			if (whitinBound(x, y + 1)) {
				Node n = new Node(x, y + 1, pos.w + 1);
				queue.add(n);
				visited[x][y + 1] = true;
			}
			if (whitinBound(x + 1, y)) {
				Node n = new Node(x + 1, y, pos.w + 1);
				queue.add(n);
				visited[x + 1][y] = true;
			}
			if (whitinBound(x, y - 1)) {
				Node n = new Node(x, y - 1, pos.w + 1);
				queue.add(n);
				visited[x][y - 1] = true;
			}
			if (whitinBound(x - 1, y)) {
				Node n = new Node(x - 1, y, pos.w + 1);
				queue.add(n);
				visited[x - 1][y] = true;
			}
//			System.out.println();
		}
		for(int i = 0; i < graph.size(); i++) {
//			System.out.println(area[graph.get(i).x][graph.get(i).y] + ": x: " + graph.get(i).x + ", y: " + graph.get(i).y + " w: " + graph.get(i).w);
			if(graph.get(i).w > 0) {
				Edge e = new Edge(start, graph.get(i), graph.get(i).w);
				if(!edgeIsInEdges(e)) {
					edges.add(e);
				}
			}
		}
		clearVisited();
		graph.clear();
		return 0;
	}
	
	private class Edge implements Comparable<Edge>{
		public Edge(Node n1, Node n2, int w) {
			node1 = n1;
			node2 = n2; 
			this.w = w;
		}
		Node node1; 
		Node node2;
		int w;
		@Override
		public int compareTo(Edge arg0) {
			return this.w - arg0.w;
		}
	}
	
	private class Node{
		Node(int x, int y, int w){
			this.x = x;
			this.y = y;
			this.w = w;
		}
		int x;
		int y;
		int w;
		ArrayList<E> neighbours = new ArrayList<E>();
	}
	
	class E{
		E(Node n, int w){
			this.n = n;
			this.w = w;
		}
		Node n;
		int w;
	}
	
	public void clearVisited() {
		for(int i = 0; i < visited.length; i++) {
			for(int j = 0; j < visited[i].length; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	public boolean edgeIsSame(Edge e1, Edge e2) {
		return (e1.node1.x == e2.node1.x && e1.node1.y == e2.node1.y) && (e1.node2.x == e2.node2.x && e1.node2.y == e2.node2.y)
				|| (e1.node1.x == e2.node2.x && e1.node1.y == e2.node2.y) && (e1.node2.x == e2.node1.x && e1.node2.y == e2.node1.y);
	}
	
	public boolean edgeIsVisitd(Node node) {
		boolean n1 = false;
		for(Node n : visitd) {
			if(n.x == node.x && n.y == node.y) {
				return true;
			}
		}
		return n1;
	}
	
	public int prim() {
		int result = 0;
		ArrayList<Edge> MST = new ArrayList<Edge>();
		edges.sort(null);
		for (Edge e : edges) {
			boolean n1 = false;
			boolean n2 = false;
				if (edgeIsVisitd(e.node1)) {
					visitd.add(e.node1);
					n1 = true;
				}
				if (edgeIsVisitd(e.node2)) {
					visitd.add(e.node2);
					n2 = true;
				}
				if(!n1 || !n2) {
					MST.add(e);
					result += e.w;
				}
			System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
					+ ", y: " + e.node2.y + ")" + " w: " + e.w);
		}

		System.out.println("Edges in MST: ");
		for (Edge e : MST) {
			System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
					+ ", y: " + e.node2.y + ")" + " w: " + e.w);
		}
		return result;
	}
}
