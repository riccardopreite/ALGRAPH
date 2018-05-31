package sample;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.sql.Struct;
import java.util.*;

import static javafx.scene.paint.Color.BLACK;


public class Node<T> {

	Integer cont = 0;



	public Node() {


	}


	public void AddNode(New_Node prev, New_Node succ, Integer count, New_Node tmphead){
			New_Node tmp2 = tmphead;



			if(count == 0){
				System.out.println("Element added.");
				tmp2.setHead(prev);
				tmp2.setNext(null);
				cont++;
			}
			else {
				while(tmp2.getNext() != null){
					tmp2 = tmp2.getNext();
				}
				System.out.println("Element added.");

				tmp2.setNext(succ);
				tmp2 = tmp2.getNext();
				tmp2.setNext(null);

			}
		}

	public New_Node FindElement(Double X, Double Y,New_Node tmphead){
		New_Node tmp2 = tmphead;
		if(tmp2 != null) {
			while (tmp2 != null) {
				if (exist(X, Y, tmp2)) {
					return tmp2;
				}
				tmp2 = tmp2.getNext();

			}
		}
		return null;
	}
	public int DeleteElement(New_Node toDelete, BorderPane layout,Integer count,New_Node tmphead){
		New_Node tmp;
		New_Node tmp2;
		tmp = toDelete;
		tmp2 = tmphead;
				if (tmp.equals(tmphead)) {
					if(tmp.getNext() != null){
						tmp.remove_element(layout);
						tmphead = tmp.getNext();
						tmp.setCircle(null);
						tmp.setCircleX(null);
						tmp.setCircleY(null);
						tmp.setElement(null);
						return count--;
						}
					else{
						tmp.remove_element(layout);
						tmp.setCircle(null);
						tmp.setCircleX(null);
						tmp.setCircleY(null);
						tmp.setElement(null);
						tmphead = null;

						return 0;
					}
				} else {
					while (tmp2.getNext() != tmp) {
						tmp2 = tmp2.getNext();
					}
					tmp.remove_element(layout);
					tmp2.setNext(tmp.getNext());
					tmp.setElement(null);
					tmp.setCircleY(null);
					tmp.setCircle(null);
					tmp.setCircleX(null);

					}

		return count--;
	}

	public Circle CircleToDelete(New_Node toDelete){

		return toDelete.getCircle();

	}

	public void print(New_Node tmphead){
		New_Node tmp = tmphead;

		while(tmp!=null){
			System.out.println("X: " + tmp.getCircleX() + " Y: " + tmp.getCircleY() + " element: " + tmp.getElement());
			tmp = tmp.getNext();
		}
	}



public boolean exist(Double X, Double Y, New_Node tmp) {

			if((X <= tmp.getCircleX() + 17 && X >= tmp.getCircleX() - 17) && (Y <= tmp.getCircleY() + 17 && Y >= tmp.getCircleY() - 17)){
				System.out.println("Element Found!\n Exit.");
				return true;
				}
			return false;


}

	public BorderPane clearLayout(BorderPane layout,New_Node tmphead){
		New_Node tmp;
		tmp = tmphead;

		while(tmp != null){
			tmp.remove_element(layout);
			tmp.setElement(null);
			tmp.setCircleX(null);
			tmp.setCircleY(null);
			tmp.setCircle(null);
			tmp = tmp.getNext();

		}
		this.cont = 0;
		return layout;
	}



public void random_graph(BorderPane layout, Integer index, Double control,Graph graph,New_Node tmphead){
		New_Node tmp, tmp2;
		tmp = tmphead;
		tmp2 = tmphead;
		Double probability;
		Integer aux = 0;
		Line line2;

	if(aux != index) {
			while (aux < index) {
				tmp2 = tmp2.getNext();
				aux++;
			}
		}
		while(tmp != null) {
			probability = (Double) Math.random() * 99 + 1;

			if (probability <= control) {
				//add line
				if(!tmp.equals(tmp2)){
					line2 = new Line();
					line2.setEndX(tmp.getCircleX());
					line2.setEndY(tmp.getCircleY());
             		line2.setStartX(tmp2.getCircleX());
					line2.setStartY(tmp2.getCircleY());
					tmp2.setLine(line2);
					tmp.setLine(line2);
				    layout.getChildren().add(line2);
				    System.out.println();
					graph.insertEdge(tmp2, tmp);
				}

			}
			tmp = tmp.getNext();
		}
	}

	public void rePrint_Circle(New_Node tmphead){

		New_Node tmp = tmphead;
		while(tmp != null){

			tmp.getCircle().setFill(BLACK);
			tmp = tmp.getNext();

		}

	}


}
