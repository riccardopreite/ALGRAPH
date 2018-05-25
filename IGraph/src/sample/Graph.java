package sample;

import javafx.scene.shape.Circle;

import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Map.Entry;

public class Graph<T extends Comparable<T>> implements IGraph<T> {


	HashMap<New_Node<T>, HashSet<New_Node<T>>> vertici;

	public Graph() {
		this.vertici = new HashMap<New_Node<T>, HashSet<New_Node<T>>>();
	}

	@Override
	public void insertNode(New_Node<T> u) {
		if (!this.vertici.containsKey(u))
			this.vertici.put(u, new HashSet<New_Node<T>>());
	}

	@Override
	public void deleteNode(New_Node<T> u) {

		if (this.vertici.containsKey(u)) {
			for (New_Node<T> v : this.vertici.keySet()) {
				this.deleteEdge(v, u);
			}
			this.vertici.remove(u);
		}
		else  System.out.println("Not found");


	}

	@Override
	public void insertEdge(New_Node<T> u, New_Node<T> v) {
		if (this.vertici.containsKey(u) && this.vertici.containsKey(v))
			System.out.println("U: " + u +" V: " + v);
			this.vertici.get(u).add(v);

	}

	@Override
	public void deleteEdge(New_Node<T> u, New_Node<T> v) {
		if (this.vertici.containsKey(u))
			this.vertici.get(u).remove(v);

	}

	@Override
	public Set<New_Node<T>> adj(New_Node<T> u) {
		// ritorna null se U non esiste
		if (this.vertici.containsKey(u))
			return this.vertici.get(u);
		else
			return null;
	}

	@Override
	public Set<New_Node<T>> V() {
		return this.vertici.keySet();
	}

	@Override
	public void print() {

		for (Entry<New_Node<T>, HashSet<New_Node<T>>> e : this.vertici.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}

	}


	public boolean Find(Double X, Double Y){

		for (Entry<New_Node<T>, HashSet<New_Node<T>>> e : this.vertici.entrySet()){
			if(this.vertici.containsValue(X)){
				System.out.println("found");

				return true;
			}
			else System.out.println("not found");


			/*
			if (this.vertici.containsKey(x)){

				System.out.println("found");
				return true;
			}
			else {

				System.out.println(e.getKey());
				return false;
			}
*/

		}
		return false;
	}

}
