package Node;

import javafx.scene.shape.Line;

public class Edges{

private Line line;
private Integer weight;
private New_Node A;
private New_Node B;


public Edges(Line line, Integer weight, New_Node A, New_Node B){
  this.line = line;
  this.weight = weight;
  this.A = A;
  this.B = B;

}

public Line getLine(){
  return this.line;
}

  public void setLine(Line line){
     this.line = line;
  }


public Integer getWeight(){
    return this.weight;

  }

  public void setWeight(Integer weight){

     this.weight = weight;

  }

    public New_Node getNodeA() {
        return A;
    }

    public New_Node getNodeB() {
        return B;
    }
}
