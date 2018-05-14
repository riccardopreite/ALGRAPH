package sample;
import javafx.scene.shape.Circle;



public class Node {

	private double circleX;
	private double circleY;




	public Node(double CircleX,double CircleY){

		this.circleX = CircleX;
		this.circleX = CircleY;


	}

	public double getElementX(){

		return this.circleX;
	}
	public double getElementY(){

		return this.circleY;
	}

	public boolean exist(Circle circle) {

		if((circleX==circle.getCenterX() || circleX+ 20==circle.getCenterX() || circleX- 20==circle.getCenterX()) && (circleY==circle.getCenterY() || circleY+ 20==circle.getCenterY() || circleY- 20==circle.getCenterY())){
			return true;
		}
		else return false;

	}
}
