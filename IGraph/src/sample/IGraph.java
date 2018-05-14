package sample;


import java.util.Set;

public interface IGraph {

	public void insertNode(Node son, Node parent);

	public void deleteNode(Node u);

	public void insertEdge(Node u, Node v);

	public void deleteEdge(Node u, Node v);

	public Set<Node> adj(Node u);

	public Set<Node> V();

	// utility: stampa nodi e liste di adiacenza
	public void print();
}

