package killing_aliens_in_borg_maze_10307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	String[][] area;
	boolean[][] visited;
	int name = 0;
	ArrayList<Node> visitd = new ArrayList<Node>();
	ArrayList<Edge> MST;
	int disjoint = 0;
	
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public static void main(String[] args) throws IOException {
		Main m = new Main();
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
//		System.out.println(prim());
		System.out.println(kruskal());
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
		int E = Integer.MAX_VALUE;
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
	
	public boolean isSameNode(Node n1, Node n2) {
		return (n1.x == n2.x && n1.y == n2.y) || (n1.x == n2.x && n1.y == n2.y);
	}
	
	public void updateEdge(Edge e) {
		for(Edge emst : MST) {
			if(emst.node1.E == e.node1.E || emst.node2.E == e.node1.E  ) {
				emst.node1.E = e.node1.E;
				emst.node2.E = e.node1.E;
			}
			if(emst.node2.E == e.node2.E || emst.node1.E == e.node2.E  ) {
				emst.node1.E = e.node1.E;
				emst.node2.E = e.node1.E;
			}
		}
	}
	
	public boolean addToMST(Edge e) {
		disjoint++;
			if (!edgeIsVisitd(e.node1)) {
				e.node1.E = disjoint;
				visitd.add(e.node1);
			}
			if (!edgeIsVisitd(e.node2)) {
				e.node2.E = disjoint;
				visitd.add(e.node2);
			}
			if (e.node1.E == Integer.MAX_VALUE || e.node2.E == Integer.MAX_VALUE) {
				for(Edge mste : MST) {
					if(isSameNode(e.node1, mste.node1)) {
						e.node1.E = mste.node1.E;
					}
					if(isSameNode(e.node2, mste.node2)) {
						e.node2.E = mste.node2.E;
					}
					if(isSameNode(e.node1, mste.node2)) {
						e.node1.E = mste.node2.E;
					}
					if(isSameNode(e.node2, mste.node1)) {
						e.node2.E = mste.node1.E;
					}
				}
				if(e.node1.E == e.node2.E) {
					System.out.println("CycleNode; ");
				}else {
					updateEdge(e);
					MST.add(e);
					return true;
				}
			}else {
				MST.add(e);
				return true;
			}
			return false;
	}
	
	public int prim() {
		int result = 0;
		MST = new ArrayList<Edge>();
		edges.sort(null);
		int i = 0;
		for (Edge e : edges) {
			if(addToMST(e)) {
				result += e.w;
			}			
			System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
					+ ", y: " + e.node2.y + ")" + " w: " + e.w);
		}

		System.out.println("Edges in MST: ");
		for (Edge e : MST) {
			System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
					+ ", y: " + e.node2.y + ")" + " w: " + e.w + " E1: " + e.node1.E + " E2: " + e.node2.E);
		}
		MST.clear();
		return result;
	}
	
	public int kruskal() {
		int groupCounter = 0;
		ArrayList<Integer> groups = new ArrayList<Integer>();
		ArrayList<Node> visited = new ArrayList<Node>();
		MST = new ArrayList<Edge>();
		edges.sort(null);
		int result = 0;
		for (Edge e : edges) {
			Node n1 = hasNode(visited, e.node1);
			Node n2 = hasNode(visited, e.node2);
			if(n1 == null && n2 == null) {
				++groupCounter;
				e.node1.E = groupCounter;
				e.node2.E = groupCounter;
				visited.add(e.node1);
				visited.add(e.node2);
				result += e.w;
//				System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
//						+ ", y: " + e.node2.y + ")" + " w: " + e.w + " E1: " + e.node1.E + " E2: " + e.node2.E);
			}else if (n1 != null && n2 != null) {
				if(n1.E != n2.E) {
//					System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
//							+ ", y: " + e.node2.y + ")" + " w: " + e.w + " E1: " + e.node1.E + " E2: " + e.node2.E);
					joinGroups(n2.E, n1.E, visited);
					result += e.w;
				}	
			}else if(n1 == null) {
				e.node1.E = n2.E;
				visited.add(e.node1);
				result += e.w;
//				System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
//						+ ", y: " + e.node2.y + ")" + " w: " + e.w + " E1: " + e.node1.E + " E2: " + e.node2.E);
			}else if(n2 == null) {
				e.node2.E = n1.E;
				visited.add(e.node2);
				result += e.w;
//				System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
//						+ ", y: " + e.node2.y + ")" + " w: " + e.w + " E1: " + e.node1.E + " E2: " + e.node2.E);
			}
		}
//		System.out.println("\nAll edges:");
//		for(Edge e : edges) {
////			System.out.println("	Node1(x: " + e.node1.x + ", y: " + e.node1.y + ")" + " Node2(x: " + e.node2.x
////					+ ", y: " + e.node2.y + ")" + " w: " + e.w + " E1: " + e.node1.E + " E2: " + e.node2.E);
//		}
		return result;
	}
	
	public Node hasNode(ArrayList<Node> visited, Node v) {
		for(Node n : visited) {
			if(isSameNode(n, v)) {
				return n;
			}
		}
		return null;
	}
	
	public void joinGroups(int before, int after, ArrayList<Node> visited ) {
		for(Node n : visited) {
			if(n.E == before) {
				n.E = after;
			}
		}
	}
}
