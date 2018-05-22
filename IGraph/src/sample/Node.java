package sample;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.sql.Struct;
import java.util.*;


public class Node<T> {

	private T element;
	private Double circleY;
	private Double circleX;
	private Circle circle;
	private  int cont = 0;
	LinkedList<Line> line_list = new LinkedList<Line>();
	private Node<T> head;
	private Node<T> next;



	public Node(Double CircleX, Double CircleY, T element, Circle Circle) {
		this.circleX = CircleX;
		this.circleY = CircleY;
		this.element = element;
		this.circle = Circle;

	}


	public void setLine(Node tmp, Line line){
		tmp.line_list.add(line);

	}
	public void getLine(Double X,Double Y, Double OldX, Double OldY){
		Integer index = 0;
		Line line;
		for ( Object pd : line_list) {
			line = line_list.get(index);
			if((OldX.equals(line.getStartX())) && (OldY.equals(line.getStartY()))) {
				System.out.println("START");
				line.setStartX(X);
				line.setStartY(Y);
			}
			 if( (OldX.equals(line.getEndX())) && (OldY.equals(line.getEndY()))) {
				System.out.println("END");
				line.setEndX(X);
				line.setEndY(Y);
			}
			index++;
		}


	}
	public void AddNode(Node prev, Node succ, Integer count){
		Node tmp = this.head;
		if(count == 0){
			this.head = prev;
			prev.next = null;
			cont++;
		}
		else {
			while(tmp.next!=null){
				tmp = tmp.next;
			}
			tmp.next = succ;
			succ.next = null;
		}
	}

	public T getElement() {

		return this.element;
	}

	public Node FindElement(Double X, Double Y){
		Node tmp = this.head;
		while(tmp!=null){
			if(exist(X, Y,tmp)){
				return tmp;
			}
			tmp = tmp.next;

		}
		return null;
	}
	public int DeleteElement(Node toDelete, BorderPane layout,Integer count){
		Node tmp = toDelete;
		Node tmp2 = this.head;
		Integer index = 0;
				if (tmp.equals(this.head)) {
					if(head.next != null){
						index = 0;
						for ( Object pd : tmp.line_list) {
							layout.getChildren().remove(pd);
							tmp.line_list.set(index, null);
							index++;

						}
						layout.getChildren().remove(tmp.circle);

						head.element = null;
						head.circleY = null;
						head.circleX = null;
						head.circle = null;
						this.head = head.next;
						return count--;
						}
					else{
						index = 0;
						for ( Object pd : tmp.line_list) {
							layout.getChildren().remove(pd);
							tmp.line_list.set(index, null);
							index++;

						}
						layout.getChildren().remove(tmp.circle);

						head.element = null;
						head.circleY = null;
						head.circleX = null;
						head.circle = null;
						this.head = null;
						return 0;
					}

				} else {
					while (tmp2.next != tmp) {
						tmp2 = tmp2.next;
					}
					index = 0;
					for ( Object pd : tmp.line_list) {
						layout.getChildren().remove(pd);
						tmp.line_list.set(index, null);
						index++;

					}
					layout.getChildren().remove(tmp.circle);

					tmp2.next = tmp.next;
					tmp.element = null;
					tmp.circleY = null;
					tmp.circleX = null;
					tmp.circle = null;
					}

		return count--;
	}

	public Circle CircleToDelete(Node toDelete){

		return toDelete.circle;

	}

	public void print(){
		Node tmp = this.head;

		while(tmp!=null){
			System.out.println("X: " + tmp.circleX + " Y: " + tmp.circleY + " element: " + tmp.element);
			tmp = tmp.next;
		}
	}



public boolean exist(Double X, Double Y, Node tmp) {

			if((X <= tmp.circleX + 20 && X >= tmp.circleX - 20) && (Y <= tmp.circleY + 20 && Y >= tmp.circleY - 20)){
				System.out.println("Element Found!\n Exit.");
				return true;
				}
			System.out.println("Element not Found!");
			return false;


}

public void ChangeCoordinates(Double x,Double y){
	circleY = y;
	circleX = x;
	circle.setCenterX(x);
	circle.setCenterY(y);
}


public Double return_CenterX(Node tmp){
		return tmp.circleX;

}

public Double return_CenterY(Node tmp){

	return tmp.circleY;

	}


	public BorderPane clearLayout(BorderPane layout){
		Node tmp = head;
		Integer index = 0;

		while(tmp != null){
			index = 0;
			for ( Object pd : tmp.line_list) {
				layout.getChildren().remove(pd);
				tmp.line_list.set(index, null);
				index++;

			}
			layout.getChildren().remove(tmp.circle);
			tmp.element = null;
			tmp.circleY = null;
			tmp.circleX = null;
			tmp.circle = null;
			tmp = tmp.next;

		}
		this.cont = 0;
		return layout;
	}


	public String toString() {

	return this.circleX.toString();
}

public Boolean ifLine(){
	if(line_list.getFirst() != null) return true;
	return false;
}
}
