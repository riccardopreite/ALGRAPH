package sample.Edit;
import javafx.scene.shape.Circle;
import sample.File.ManageFile;
import sample.MouseEvent.Event;
import sample.Node.Edges;
import sample.Node.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;

public class EditNode{
  public Stage InputStage = new Stage();
  public Integer count2 = 0;

  public  Boolean edge = false, removed = false, add = false, delete_edge = false, isnot = true,delete = false;
  public  New_Node tmp2;


  Scene  scene2;
  Stage window;
  BorderPane layout;
  Line line;
  Integer   ind = 0,weight;
  public Boolean clicked, complete;
  public Boolean  Added = false;
  public Line tmpline;
  public ManageFile file;



  Edges edge_1;
  // private Desktop desktop = Desktop.getDesktop();


 public EditNode(){

  }

  public void SetLayout(BorderPane layout,Stage window,Scene scene){
    this.layout = layout;
    this.window = window;
    this.scene2 = scene;
  }

  public void SetManageFile(ManageFile file){
    this.file = file;
  }


  //Add Element

  public void AddElement(New_Node tmp,Boolean adding){
    TextField InputElement = new TextField();
    InputElement.setText("Inserisci l'elemento:");

    Button Choose = new Button("Choose");
    Button Cancel = new Button("Cancel");


    VBox newInput = new VBox(10);

    newInput.setPadding(new Insets(10,10,10,10));
    newInput.getChildren().addAll(InputElement, Choose, Cancel);

    Scene scene3 = new Scene(newInput, 300, 250);
   if( file.InputStage != null){
     file.InputStage.close();
   }
    file.InputStage.setScene(scene3);
    file.InputStage.setOnCloseRequest(e ->{
      if(!adding){
        for (New_Node node : file.ListNode) {
          if(node.equals(tmp)){
            tmp2 = tmp;
            delete = true;
            break;
          }
        }

        if(delete) {
          file.ListNode.remove(tmp);
        }
        file.event.index = file.ListNode.size();
        file.vertex--;
        if(file.vertex<0){
          file.vertex = 0;
        }
        System.out.println(file.vertex);
      }
      Added = false;
    });
    Added = true;
    file.InputStage.show();
    Choose.setOnAction(e -> {

      for (New_Node node : file.ListNode) {


      if (node.getElement().equals(InputElement.getText())) {
        file.event.SetError("Questo elemento esiste già!");
        isnot = false;
        break;
      }
      isnot = true;

    }
    if(isnot && !InputElement.getText().isEmpty()){
        try{
          layout.getChildren().remove(tmp.get_ElementBox());
        }
        catch (Exception t){}
      tmp.setElement(InputElement.getText());
      tmp.set_ElementBox();
      if (!adding) {
        file.event.index++;
        layout.getChildren().add(tmp.getCircle());

      }

      layout.getChildren().add(tmp.get_ElementBox());

      Added = false;
      file.InputStage.close();
    }
    });
    Cancel.setOnAction(e -> {
          if(!adding){
            for (New_Node node : file.ListNode) {
              if(node.equals(tmp)){
                file.ListNode.remove(node);
              }

            }
            file.event.index = file.ListNode.size();
            file.vertex--;
            if(file.vertex<0){
              file.vertex = 0;
            }

          }
      Added = false;
      file.InputStage.close();

    });
    count2 = 1;

  }


  //Add Edge

  public void Add_Edge(New_Node edgeSN, New_Node edgeFN){

    TextField InputWeight = new TextField();
    removed = false;
    add = false;
    delete_edge = false;
    clicked = true;
    complete = true;
    if(file.vertex < 2) {
      file.event.SetError("Ci sono meno di due nodi, non è possibile creare un arco!");
      return;
    }
    if(!file.ListNode.getFirst().ifLine(edgeSN,edgeFN))
    {
      file.event.SetError("Esiste già un arco fra " + edgeSN.getElement() + "<->" +edgeFN.getElement() + ". \nNon è possibile crearne un altro uguale! ");
      return;
    }
    InputWeight.setText("Inserisci il peso:");

    Button Add = new Button("Inserisci");
    Button Cancel = new Button("Annulla");
    VBox newInput = new VBox(10);
    newInput.setPadding(new Insets(10,10,10,10));
    newInput.getChildren().addAll(InputWeight, Add, Cancel);
    Scene scene3 = new Scene(newInput, 300, 250);
    count2 = 1;
    if( file.InputStage != null){
      file.InputStage.close();
    }    file.InputStage.setScene(scene3);
    file.InputStage.show();
    Add.setOnAction(e -> {
      if (isInt(InputWeight, InputWeight.getText(), file.InputStage, false)) {
        weight = Integer.parseInt(InputWeight.getText());
        if (weight > 0) {
          line = new Line();
          line.setStartX(edgeSN.getCircleX());
          line.setStartY(edgeSN.getCircleY());
          line.setEndX(edgeFN.getCircleX());
          line.setEndY(edgeFN.getCircleY());

          //weight chiesto in input, se non inserito non disegna la linea
          edgeSN.setLine(line, weight,edgeSN,edgeFN);
          edgeFN.setLine(line, weight,edgeSN,edgeFN);
          edge_1 = edgeSN.getEdges(line, weight);
          layout.getChildren().removeAll(edgeSN.get_ElementBox(), edgeFN.get_ElementBox());
          layout.getChildren().addAll(line);
          layout.getChildren().addAll(edgeSN.get_ElementBox(), edgeFN.get_ElementBox());
          ind = 0;
          for (Object edges : file.Kruskal_list) {
            ind++;
          }
          file.Kruskal_list.add(ind, edge_1);
          this.edge = false;

          this.edge = false;
          file.InputStage.close();
        }
        else{
          file.event.SetError("Error!\n Non puoi inserire un peso <= 0.");
        }

    }

    });
    Cancel.setOnAction(e -> {
      file.InputStage.close();
    });
  }



  //Delete edges

  public void Delete_Edge(New_Node edgeS, New_Node edgeF){
    if(file.vertex < 2) {
      file.event.SetError("Ci sono meno di due nodi, non è possibile creare un arco!");
      return;
    }
    if(file.ListNode.getFirst().ifLine(edgeS,edgeF))
    {
      file.event.SetError("Non esiste un arco fra " + edgeS.getElement() + "<->" +edgeF.getElement() + ". \nNon è possibile terminare l'eliminazione.");
      return;
    }

    line = file.ListNode.getFirst().FoundLine(edgeS, edgeF);
    ind = 0;
    for (Object edges : file.Kruskal_list) {
      if(line == file.Kruskal_list.get(ind).getLine()){
        break;
      }
      ind++;
    }
    file.Kruskal_list.remove(ind);
    file.ListNode.getFirst().Remove_line(edgeS, edgeF, layout);
    clicked = true;
    complete = true;
  }



  //Change Weight


  public void Change_Weight(New_Node edge1, New_Node edge2){
    if(file.vertex < 2) {
      file.event.SetError("Ci sono meno di due nodi, non è possibile creare un arco!");
      return;
    }
    if(file.ListNode.getFirst().ifLine(edge1,edge2))
    {
      file.event.SetError("Non esiste un arco fra " + edge1.getElement() + "<->" +edge2.getElement() + ". \nNon è possibile modificare il peso.");
      return;
    }
    TextField InputWeight = new TextField();
    InputWeight.setText("Inserisci il peso:");

    Button Add = new Button("Aggiungi");
    Button Cancel = new Button("Annulla");


    VBox newInput = new VBox(10);

    newInput.setPadding(new Insets(10, 10, 10, 10));
    newInput.getChildren().addAll(InputWeight, Add, Cancel);

    Scene scene3 = new Scene(newInput, 300, 250);
    if( file.InputStage != null){
      file.InputStage.close();
    }    file.InputStage.setScene(scene3);
    file.InputStage.show();
    Add.setOnAction(e -> {
      if (isInt(InputWeight, InputWeight.getText(),file.InputStage,false)) {
        weight = Integer.parseInt(InputWeight.getText());
        System.out.println(weight);
        if (weight > 0) {
          ind = 0;
          tmpline = file.ListNode.getFirst().FoundLine(edge1,edge2);
          edge1.setLine(tmpline, weight,edge1,edge2);
          edge2.setLine(tmpline, weight,edge2,edge1);
          edge_1 = edge1.getEdges(line, weight);
          for (Object edges : file.Kruskal_list) {
            if (file.Kruskal_list.get(ind) == edge_1) {
              file.Kruskal_list.get(ind).setWeight(weight);
              break;
            }
            ind++;
          }
        }
        else{
          file.event.SetError("Error!\n Non puoi inserire un peso <= 0.");
        }
      }
      file.InputStage.close();
    });

    Cancel.setOnAction(e -> {
      file.InputStage.close();
    });
  }



  public boolean isInt(TextField input, String message,Stage InputStage, Boolean graph){
    try{
      int InputVertex = Integer.parseInt(input.getText());

      if(graph) {
        if (InputVertex <= 10) {
          return true;
        }
        else return false;
      }
      else{
        return  true;
      }


    }catch(NumberFormatException e){
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error!\n" + message + "non è un numero");
      error.showAndWait();
      return false;
    }
  }







}
