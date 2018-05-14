package sample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.List;

public class Graph implements IGraph {


	HashMap<Node, HashSet<Node>> vertici;

	public Graph() {
		this.vertici = new HashMap<Node , HashSet<Node >>();
	}

	@Override
	public void insertNode(Node  son, Node parent ) {

		/*
		List<Node> son = new Li

		if (!this.vertici.containsKey(x))
			this.vertici.put(x, new HashSet<Node >());
*/
	}

	@Override
	public void deleteNode(Node  u) {

		if (this.vertici.containsKey(u)) {
			for (Node  v : this.vertici.keySet()) {
				this.deleteEdge(v, u);
			}
			this.vertici.remove(u);
		}

	}

	@Override
	public void insertEdge(Node  u, Node  v) {
		if (this.vertici.containsKey(u) && this.vertici.containsKey(v))
			this.vertici.get(u).add(v);

	}

	@Override
	public void deleteEdge(Node u, Node  v) {
		if (this.vertici.containsKey(u))
			this.vertici.get(u).remove(v);

	}

	@Override
	public Set<Node > adj(Node  u) {
		// ritorna null se U non esiste
		if (this.vertici.containsKey(u))
			return this.vertici.get(u);
		else
			return null;
	}

	@Override
	public Set<Node > V() {
		return this.vertici.keySet();
	}

	@Override
	public void print() {

		for (Entry<Node , HashSet<Node >> e : this.vertici.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}

	}

}
