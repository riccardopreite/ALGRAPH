package Node;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.lang.Object;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.LinkedList;
import static javafx.scene.paint.Color.*;

public class New_Node<T> {

    private String element;
    private Double circleY;
    private Double circleX;
    private Circle circle;
    private LinkedList<Edges> Archi;
    private VBox ElementBox;

    private Boolean connect;

    public New_Node() {
        element = "";
        circle = null;
        circleX = null;
        circleY = null;
        Archi = new LinkedList();
        connect = false;
    }


    public Boolean IsElement(LinkedList<New_Node> list,String Element){
        for (New_Node node:list) {
            if(node.getElement().equals(Element)) return true;

        }
        return false;

    }


    public String getElement() {
        return element;
    }

    public void setElement(String newelement) {
        element = newelement;
    }

    public Double getCircleY() {
        return circleY;
    }

    public void setCircleY(Double circleYnew) {
        circleY = circleYnew;
    }

    public Double getCircleX() {
        return circleX;
    }

    public void setCircleX(Double circleXnew) {
        circleX = circleXnew;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circlenew) {
        circle = circlenew;
    }


    public void set_ElementBox(){
        Label label = new Label();
        VBox ElementBoX = new VBox(1000);
        label.setText(getElement());
        label.setTextFill(YELLOW);
        ElementBoX.setLayoutY(getCircleY());
        ElementBoX.setLayoutX(getCircleX());

        ElementBoX.setMaxWidth(10.0);
        ElementBoX.getChildren().add(label);
        ElementBox = ElementBoX;


    }

    public void move_VBox(Double NewX, Double NewY){
        ElementBox.setLayoutX(NewX);
        ElementBox.setLayoutY(NewY);
    }


    public VBox get_ElementBox(){
        return ElementBox;

    }
    public void setLine(Line line, Integer weight,New_Node A, New_Node B) {
        Integer index = 0;
        Edges newEdge = new Edges(line, weight,A,B);
        for (Object pd : Archi) {
            if (pd != null){
                if (Archi.get(index).getLine() == line) {
                    Archi.get(index).setWeight(weight);
                    return;
                }
        }
            index++;
        }
        //line_list.add(index, line);

        Archi.add(index, newEdge);
    }

    public void Change_Line(Double X, Double Y, Double OldX, Double OldY, New_Node drag) {
        Integer index = 0;
        Edges arco;
        Line line;
        for (Object pd : Archi) {
            line = drag.Get_Line(index);
            if (line != null) {
                if ((OldX.equals(line.getStartX())) && (OldY.equals(line.getStartY()))) {
                    line.setStartX(X);
                    line.setStartY(Y);
                }
                if ((OldX.equals(line.getEndX())) && (OldY.equals(line.getEndY()))) {
                    line.setEndX(X);
                    line.setEndY(Y);
                }

            }
            index++;
        }

    }

    public Line Get_Line(Integer index) {

        if (Archi.get(index) != null) {
            return Archi.get(index).getLine();
        } else return null;

    }

    public Integer Get_Weight(Integer index) {

        if (Archi.get(index) != null) {
            return Archi.get(index).getWeight();
        } else return null;

    }
    public New_Node Get_B(Integer index) {

        if (Archi.get(index) != null) {
            return Archi.get(index).getNodeB();
        } else return null;

    }

    public void Remove_line(New_Node start, New_Node end, BorderPane layout) {
        Integer index = 0, ind = 0;
        Line lineToRemove, lineToRemove2;
        LinkedList<Edges> tmplist = start.getArchi(), tmplist2 = end.getArchi();
        if (start != null && end != null) {

            for (Edges line : tmplist) {
                ind = 0;

                for (Edges line2 : tmplist2) {
                    lineToRemove = start.Get_Line(index);
                    lineToRemove2 = end.Get_Line(ind);

                    if (lineToRemove == lineToRemove2 && lineToRemove != null && lineToRemove2 != null) {

                      //  end.Archi.
                        end.getArchi().remove(line2);
                        start.getArchi().remove(line);
                     //   end.Remove_Element(line2);
                      //  start.Remove_Element(line);
                        layout.getChildren().remove(lineToRemove);
                        return;


                    } else ind++;

                }
                index++;

            }
        }
        return;
    }
    public void remove_element(BorderPane layout) {
        Integer index = 0;
        Line lineToRemove;

        for (Object pd : Archi) {
            if (Archi.get(index) != null) {
                lineToRemove = Archi.get(index).getLine();
                layout.getChildren().remove(lineToRemove);
             //   Archi.remove(index);
            }
            index++;

        }

        layout.getChildren().removeAll(ElementBox,circle);

    }

    public void ChangeCoordinates(Double x, Double y) {
        circleY = y;
        circleX = x;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    public boolean ifLine(New_Node tmp, New_Node tmp2) {
        Integer ind = 0, index = 0;
        for (Object line : tmp.Archi) {
            ind = 0;
            for (Object line2 : tmp2.Archi) {
                if (tmp.Get_Line(index) == tmp2.Get_Line(ind) && tmp.Get_Line(index) != null) {
                    return false;
                }
                ind++;

            }
            index++;
        }
        return true;

    }



    public New_Node returnNode_element(String Element,LinkedList<New_Node> list) {
        for (New_Node node:list) {
            if(node.getElement().equals(Element)){
                return node;
            }

        }
        return null;

    }

    public LinkedList<Edges> getArchi() {
        return Archi;
    }

    public Boolean getConnect() {

        return connect;
    }

    public void setConnect(Boolean connect) {
        this.connect = connect;
    }

    public Edges getEdges(Line line, Integer weight) {
        Integer index = 0;
        for (Object edges : Archi) {
            if (edges != null) {
                if (Get_Line(index) == line && Get_Weight(index) == weight) {
                    return Archi.get(index);
                }
            }
            index++;
        }
        return null;
    }

    public Line FoundLine(New_Node edge1, New_Node edge2) {
        Integer index = 0, ind = 0;
        Line lineToFound, lineToFound2;

        if (edge1 != null && edge1 != null) {

            for (Object line : edge1.Archi) {
                ind = 0;

                for (Object line2 : edge2.Archi) {
                    lineToFound = edge1.Get_Line(index);
                    lineToFound2 = edge2.Get_Line(ind);

                    if (lineToFound == lineToFound2 && lineToFound != null && lineToFound2 != null) {
                        return lineToFound;
                    } else ind++;

                }
                index++;

            }


        }
        return null;
    }

    public String GetAllEdges(New_Node selected,LinkedList<New_Node> list){
        New_Node tmp = list.getFirst();
        Integer index = 0, ind = 0;
        Line lineToFound, lineToFound2;
        boolean found = false;
        String message = "";
        if(!selected.getElement().equals("")) {
          message = "Element: " + selected.getElement() + "\n";
        }
        else{
            message = "Element: NE"  + "\n";

        }

        if(selected != null) {
            for (Object line : selected.Archi) {
                lineToFound = selected.Get_Line(index);
                found = false;
                for (New_Node node:list) {
                    if (selected != node) {
                        ind = 0;
                        for (Object line2 : node.Archi) {

                            lineToFound2 = node.Get_Line(ind);
                            if (lineToFound == lineToFound2 && lineToFound != null && lineToFound2 != null) {
                                node.getCircle().setFill(RED);
                                found = true;
                                if(selected.getElement().equals(null))
                                {
                                    message = message + "NE"+ "<->" + node.getElement() + " W: " + node.Get_Weight(ind) +  "\n";

                                }
                                else if(node.getElement().equals(null)){
                                    message = message + selected.getElement() + "<->" + "NE" + " W: " + node.Get_Weight(ind) +  "\n";

                                }

                               else message = message + selected.getElement() + "<->" + node.getElement() + " W: " + node.Get_Weight(ind) +  "\n";

                                //circle.setFill(RED);
                                break;
                            } else ind++;


                        }



                    }
                    if(found){
                        break;
                    }
                }
                index++;
            }
        }
        return message;
    }



    public String setRandELement(LinkedList<New_Node> list) {

        Integer t;
        Double rand1;
        String ASCII;
        Integer index = 0;
        rand1 = Math.random() * 100 + 0;
        t = rand1.intValue();
        System.out.println("RAND 1: " + t);
        if (t <= 49 && t >= 0) {

            rand1 = Math.random() * 9;
            t = rand1.intValue();

            for (New_Node node : list) {
                if (node.getElement().equals(t.toString())) {

                    rand1 = Math.random() * 9;
                    t = rand1.intValue();

                    index = 0;
                    while (index < list.size()) {
                        if (list.get(index).getElement().equals(t.toString())) {
                            System.out.println(t.toString() + "UGUALE");


                            rand1 = Math.random() * 9;
                            t = rand1.intValue();
                            index = 0;
                        } else index++;
                    }
                    break;
                }

            }
            return t.toString();
        } else if (t <= 100 && t >= 50) {
            ASCII = RandomStringUtils.randomAlphabetic(1);
            for (New_Node node : list) {
                if (node.getElement().equals(ASCII)) {
                    ASCII = RandomStringUtils.randomAlphabetic(1);
                    index = 0;
                    while (index < list.size()) {
                        if (list.get(index).getElement().equals(ASCII)) {
                            System.out.println(ASCII + "UGUALE");


                            ASCII = RandomStringUtils.randomAlphabetic(1);
                            index = 0;
                        } else index++;
                    }
                    break;

                }

            }
            System.out.println("ASCII: " + ASCII);
            return ASCII;


        }
        return ("NE");

    }























    public New_Node FindElement(Double X, Double Y, LinkedList<New_Node> ListNode){
        if(!ListNode.isEmpty()) {
            for (New_Node node: ListNode) {

                if (exist(X, Y, node)) {
                    return node;
                }


            }
        }
        return null;
    }

    public boolean exist(Double X, Double Y, New_Node tmp) {

        if((X <= tmp.getCircleX() + 17 && X >= tmp.getCircleX() - 17) && (Y <= tmp.getCircleY() + 17 && Y >= tmp.getCircleY() - 17)){
            //	System.out.println("Element Found!\n Exit.");
            return true;
        }
        return false;


    }


    public void random_graph(BorderPane layout, Integer index, Double control,LinkedList<New_Node> list, LinkedList<Edges> kruskal,Integer MaxWeight){
        New_Node tmp, tmp2;

        Double probability,weight;
        Integer aux = 0,ind;

        Line line2;
        Edges edge1;

        if(aux != index) {
            for (New_Node node: list) {
                if(aux == index) break;
                else aux++;
            }

        }
        for (New_Node node : list) {
            probability = Math.random() * 99 + 1;
            if (!node.equals(list.get(aux))) {
                if (node.ifLine(node, list.get(aux))){
                    if (probability <= control) {
                        weight =  Math.random() * MaxWeight + 1;

                        //add line
                        line2 = new Line();
                        line2.setEndX(node.getCircleX());
                        line2.setEndY(node.getCircleY());
                        line2.setStartX(list.get(aux).getCircleX());
                        line2.setStartY(list.get(aux).getCircleY());
                        list.get(aux).setLine(line2, weight.intValue(),list.get(aux),node);
                        node.setLine(line2, weight.intValue(),node,list.get(aux));
                        edge1 = node.getEdges(line2,weight.intValue());
                        ind = 0;
                        if(kruskal == null){
                            kruskal = new LinkedList<Edges>();
                        }
                        for (Object edges : kruskal) {
                            ind++;
                        }
                        kruskal.add(ind,edge1);
                        layout.getChildren().removeAll(node.get_ElementBox(),list.get(aux).get_ElementBox());
                        layout.getChildren().addAll(line2,node.get_ElementBox(),list.get(aux).get_ElementBox());

                        System.out.println();
                    }

                }
            }
        }

    }




    public void rePrint_Circle(LinkedList<New_Node> list){
        for (New_Node node : list) {
            node.getCircle().setFill(BLACK);
        }


    }




    public BorderPane clearLayout(BorderPane layout,LinkedList<New_Node> list){

        for (New_Node node : list) {
            node.remove_element(layout);
            node.setElement(null);
            node.setCircleX(null);
            node.setCircleY(null);
            node.setCircle(null);
        }

        return layout;
    }




    public void File_line(New_Node tmp, New_Node tmp2, BorderPane layout, Integer weight,LinkedList<Edges> kruskal){
        Line line;
        Integer ind;
        Edges edge1;
        if (tmp.ifLine(tmp, tmp2)){
            //add line
            line = new Line();
            line.setEndX(tmp.getCircleX());
            line.setEndY(tmp.getCircleY());
            line.setStartX(tmp2.getCircleX());
            line.setStartY(tmp2.getCircleY());
            tmp2.setLine(line,weight,tmp2,tmp);
            tmp.setLine(line,weight,tmp,tmp2);
            edge1 = tmp.getEdges(line,weight);

            ind = 0;
            for (Object edges : kruskal) {
                ind++;
            }
            kruskal.add(ind,edge1);
            layout.getChildren().removeAll(tmp.get_ElementBox(),tmp2.get_ElementBox());
            layout.getChildren().addAll(line,tmp.get_ElementBox(),tmp2.get_ElementBox());

        }
    }

}
