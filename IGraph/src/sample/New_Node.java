package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.LinkedList;

public class New_Node<T> {

    private T element;
    private Double circleY;
    private Double circleX;
    private Circle circle;
    private int cont = 0;
    LinkedList<Line> line_list;
    private New_Node<T> head;
    private New_Node<T> next;

    public New_Node(){
        element = null;
        circle = null;
        circleX = null;
        circleY = null;
        line_list = new LinkedList();
        head = null;
        next = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Double getCircleY() {
        return circleY;
    }

    public New_Node(T element, Double X, Double Y){

        this.element = element;
        this.circleY = Y;
        this.circleX = X;

    }

    public void setCircleY(Double circleY) {
        this.circleY = circleY;
    }

    public Double getCircleX() {
        return circleX;
    }

    public void setCircleX(Double circleX) {
        this.circleX = circleX;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public void setLine(Line line) {
        Integer index = 0;
        for (Object pd : line_list) {
            index++;
        }
        line_list.add(index, line);
    }

    public void Change_Line(Double X,Double Y, Double OldX, Double OldY){
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

    public void Remove_line(New_Node start, New_Node end, BorderPane layout){
        Integer index = 0, ind = 0;
        if(start != null && end != null) {

            for (Object line : start.line_list) {
                ind = 0;
                for (Object line2 : end.line_list) {
                    if (line == line2) {
                        layout.getChildren().remove(line);
                        end.line_list.set(ind, null);
                        start.line_list.set(index, null);
                        return;
                    } else ind++;

                }
                index++;

            }
        }
        return;
    }

    public void remove_element(BorderPane layout){
        Integer index = 0;

        for ( Object pd : line_list) {
            layout.getChildren().remove(pd);
            line_list.set(index, null);
            index++;

        }

        layout.getChildren().remove(circle);

    }


    public New_Node<T> getHead() {
        return head;
    }

    public void setHead(New_Node<T> head) {
        this.head = head;
    }

    public New_Node<T> getNext() {
        return next;
    }

    public void setNext(New_Node<T> next) {
        this.next = next;
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
