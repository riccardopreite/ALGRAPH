package sample;

import java.util.Set;

public interface IGraph<T extends Comparable<T>> {

	public void insertNode(New_Node<T> u);

	public void deleteNode(New_Node<T> u);

	public void insertEdge(New_Node<T> u, New_Node<T> v);

	public void deleteEdge(New_Node<T> u, New_Node<T> v);

	public Set<New_Node<T>> adj(New_Node<T> u);

	public Set<New_Node<T>> V();

	// utility: stampa nodi e liste di adiacenza
	public void print();
}
