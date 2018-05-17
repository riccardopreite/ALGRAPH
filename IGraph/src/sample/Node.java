package sample;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Node<T> {

	private T element;
	private Double circleY;
	private Double circleX;
	private Circle circle;
	private  int cont = 0;
	private Node<T> head;
	private Node<T> next;



	public Node(Double CircleX, Double CircleY, T element, Circle Circle) {
		this.circleX = CircleX;
		this.circleY = CircleY;
		this.element = element;
		this.circle = Circle;

	}



	public void AddNode(Node prev, Node succ){
		Node tmp = this.head;
		if(cont == 0){
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
		//System.out.println(head);
		while(tmp!=null){
		//	System.out.println("node: " + tmp.circleX + " mouseX: " + X + " node " + tmp.circleY + " mouse y" + Y);
			if((X <= tmp.circleX + 20 && X >= tmp.circleX - 20) && (Y <= tmp.circleY + 20 && Y >= tmp.circleY - 20)){
				System.out.println("Element Found!\n Exit.");
				return tmp;
			}
			tmp = tmp.next;

		}
		System.out.println("Element not Found!");
		return null;
	}
	public int DeleteElement(Node toDelete){
		Node tmp = this.head;
		Node tmp2 = tmp;
		while(tmp!=null) {
			if (tmp.equals(toDelete)) {
				if (tmp.equals(this.head)) {
					if(tmp.next != null){
						this.head = tmp.next;
						break;
					}
					else{
						this.head = null;
						return 0;
					}

				} else {
					while (tmp2.next != tmp) {
						tmp2 = tmp2.next;
					}
					tmp2.next = tmp.next;
					break;
				}
			}
			tmp = tmp.next;
		}
		return 1;
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


	/*
        public Circle getElementY(){

            //return this.circleY;
        }

        public boolean exist(<T> circle) {



    if(circleX==circle){
            //if((circleX==circle.getCenterX() || circleX+ 20==circle.getCenterX() || circleX- 20==circle.getCenterX()) && (circleY==circle.getCenterY() || circleY+ 20==circle.getCenterY() || circleY- 20==circle.getCenterY())){
        System.out.println("true");

        return true;
            }


            else {
        System.out.println("false");
        return false;
    }

        }
    */

	public BorderPane clearLayout(BorderPane layout){
		Node tmp = head;

		while(tmp != null){
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

	public void ChangeCoordinates(Double x,Double y){
		circleY = y;
		circleX = x;
		circle.setCenterX(x);
		circle.setCenterY(y);
	}
	public String toString() {

		return this.circleX.toString();
	}
}
